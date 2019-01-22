package cn.gzsxt.transport.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.gzsxt.transport.annotation.TokenForm;
import cn.gzsxt.transport.service.AdminService;
import cn.gzsxt.transport.service.DictionaryService;
import cn.gzsxt.transport.service.RoleService;
import cn.gzsxt.transport.utils.Global;
import cn.gzsxt.transport.utils.Md5Utils;
import cn.gzsxt.transport.utils.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="/admin")
public class AdminController {
	
	private static final Logger logger =LogManager.getLogger(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private RoleService roleservice;
	
	@RequestMapping(value="/toIndex.do")
	public String toIndex() {
		return "manager/index";
	}

	/**
	 * 登录管理员
	 * path:${pageContext.request.contextPath }/admin/loginAdmin.do
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/loginAdmin")
	public String loginAdmin(@RequestParam Map<String,Object> entity,HttpSession session,HttpServletRequest request) {
		logger.debug("管理员登录"+entity);
		try {
			String sysCode = null;
			sysCode = session.getAttribute("rand").toString().toLowerCase();
			logger.debug("验证码："+sysCode);
			if (!sysCode.equals(entity.get("verifycode").toString().toLowerCase())) {
				request.setAttribute("admin_login_msg", "登录失败，验证码错误");
				return "forward:/login.jsp";
			}
			//1.将密码md5加密后在校验
			entity.put("admin_pwd", Md5Utils.md5((String)entity.get("admin_pwd")));
			Map<String, Object> admin = adminService.loginAdmin(entity);
			if (admin!=null) {
			
				session.setAttribute("admin_info", admin);
				return "manager/index";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("admin_login_msg", "登录失败，请确认用户密码是否正确");
		return "forward:/login.jsp";
	
	}
	
	/**
	 * 管理员注销
	 * path:${pageContext.request.contextPath }/admin/undoAdmin.do
	 * @return
	 */
	@RequestMapping(value="/undoAdmin")
	public String undoAdmin(HttpSession session) {
		Object adminInfo = session.getAttribute("admin_info");
		if(adminInfo!=null) {
			session.removeAttribute("admin_info");
		}
		
		
		return "forward:/login.jsp";
	}
	
	/**
	 * 跳转到修改密码页面
	 * path：${pageContext.request.contextPath }/admin/toSettingAdmin.do
	 * @return
	 */
	@RequestMapping(value="/toAdminSetting")
	@TokenForm(create=true)
	public String toAdminSetting() {
		
		return "manager/setting";
	}
	
	/**
	 * 修改当前用户密码
	 * path：${pageContext.request.contextPath }/admin/setAdminPwd.do
	 * @param entity
	 * @param sesssion
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/setAdminPwd")
	@TokenForm(remove=true)
	public String setAdminPwd(@RequestParam Map<String,Object> entity,HttpSession sesssion,HttpServletRequest request) {
		logger.debug("-修改当前登录管理员密码-"+entity);
	
		try {
			Map<String, Object> admin = (Map<String, Object>) sesssion.getAttribute("admin_info");
			//1.获得表单的原密码与当前的登录管理员校验是否正确
			if(Md5Utils.md5((String)entity.get("source_admin_pwd")).equals(admin.get("admin_pwd"))) {
				//2.新的密码与确认密码是否一致
				if(entity.get("new_admin_pwd").equals(entity.get("confirm_admin_pwd"))) {
					Map<String,Object > params=new HashMap<>();
					//根据管理员编号，修改密码
					params.put("admin_id", admin.get("admin_id"));
					params.put("admin_pwd", Md5Utils.md5((String)entity.get("new_admin_pwd")) );
					Map<String, Object> resultAdmin = adminService.editAdminPassword(params);
					sesssion.setAttribute("admin_info", resultAdmin);
					request.setAttribute("admin_edit_password_msg", "修改密码成功");
					return "forward:/admin/toAdminSetting.do";
				}else {
					request.setAttribute("admin_edit_password_msg", "修改密码失败，确认密码不一致");
				}
				
			}else {
				request.setAttribute("admin_edit_password_msg", "修改密码失败，原密码密码不正确");
			}
		} catch (Exception e) {
			request.setAttribute("admin_edit_password_msg", "修改密码失败，出现未知异常，请联系管理员");
			e.printStackTrace();
		}
		
		
		return "forward:/admin/toAdminSetting.do";
	}
	
	
	/**
	 * 跳转到管理员类别
	 * path：${pageContext.request.contextPath }/admin/toAdminList.do
	 * @return
	 */
	@RequestMapping(value="/toAdminList")
	public String toAdminList(@RequestParam Map<String, Object> condition,Integer index,HttpServletRequest request) {
		logger.debug("-跳转到管理员列表-"+condition+"，索引："+index);
		try {
			if (index==null) {
				index=0;
			}
			
			Page page = adminService.findAdminToPage(condition, index, Global.PAGE_SIZE);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/adminList";
	}
	
	/**
	 * 跳转到增加管理员页面
	 * path:${pageContext.request.contextPath }/admin/toAdminAdd.do
	 * @return
	 */
	@RequestMapping(value="/toAdminAdd")
	@TokenForm(create=true)
	public String toAdminAdd(HttpServletRequest request) {
		logger.debug("跳转到增加管理员页面--");
		try {
			//1.在数据字典获得状态数据
			List<Map<String, Object>> status = dictionaryService.findDictionaryByTypeCode(1000);
			request.setAttribute("statuses", status);
			//2.获得角色数据
			List<Map<String, Object>> roles = roleservice.findAllRole();
			request.setAttribute("roles", roles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "manager/adminAdd";
	}
	
	/**
	 * 增加管理员
	 * path:${pageContext.request.contextPath }/admin/adminAdd.do
	 * @return
	 */
	
	@RequestMapping(value="/addAdmin")
	@TokenForm(remove=true)
	public String addAdmin(@RequestParam Map<String,Object> entity,HttpServletRequest request) {
		logger.debug("增加管理员："+entity);
		try {
			//需要将密码使用md5加密
			entity.put("admin_pwd", Md5Utils.md5((String)entity.get("admin_pwd")));
			Map<String, Object> admin = adminService.addAdmin(entity);
			if (admin!=null) {
				request.setAttribute("admin_add_msg", "增加管理员成功！");
				return "forward:/admin/toAdminAdd.do";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("admin_add_msg", "增加管理员失败");
		
		return "forward:/admin/toAdminAdd.do";
	}
	

	/**
	 * 跳转到编辑管理员页面
	 * path:${pageContext.request.contextPath }/admin/toAdminEdit.do
	 * @return
	 */
	@RequestMapping(value="/toAdminEdit")
	@TokenForm(create=true)
	public String toAdminEdit(Long adminId,HttpServletRequest request ) {
		logger.debug("跳转到增加管理员页面--"+adminId);
		
		try {
			//1.通过管理员编号查询管理员的信息
			Map<String, Object> admin = adminService.findAdminById(adminId);
			request.setAttribute("admin", admin);
			
			//2.在数据字典获得状态数据
			List<Map<String, Object>> status = dictionaryService.findDictionaryByTypeCode(1000);
			request.setAttribute("statuses", status);
			//3.获得角色数据
			List<Map<String, Object>> roles = roleservice.findAllRole();
			request.setAttribute("roles", roles);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/adminEdit";
	}
	
	/**
	 * 编辑管理员
	 * path:${pageContext.request.contextPath }/admin/editAdmin.do
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/editAdmin")
	@TokenForm(remove=true)
	public String editAdmin(@RequestParam Map<String, Object> entity,HttpServletRequest request ) {
		logger.debug("-编辑管理员-"+entity);
		try {
			//需要将密码使用md5加密
			entity.put("admin_pwd", Md5Utils.md5((String)entity.get("admin_pwd")));
			Map<String, Object> admin = adminService.editAdmin(entity);
			if (admin!=null) {
				request.setAttribute("admin_edit_msg", "更新管理员成功");
				//更新成功，重设表单值
				request.setAttribute("admin", admin);
				
			}else {
				request.setAttribute("admin_edit_msg", "更新管理员失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("admin_edit_msg", "更新管理员失败");
		}
		
		//需要通过toAdminEdit方法跳转
		//默认Map里面的值的字符串类型的
		Long adminId=Long.valueOf((String)entity.get("admin_id"));
		return "forward:/admin/toAdminEdit.do?adminId="+adminId;
	}
	/**
	 * 删除管理员
	 * path:${pageContext.request.contextPath }/admin/deleteAdmin.do?adminId=${admin.admin_id}
	 * @param adminId
	 * @return
	 */
	@RequestMapping(value="/deleteAdmin")
	public String deleteAdmin(Long adminId,HttpServletRequest request) {
		logger.debug("-删除管理员:"+adminId);
		adminService.deleteAdminByIds(adminId);
		return this.toAdminList(null, 0, request);
	}
	
	/**
	 * 批量删除管理员
	 * path:${pageContext.request.contextPath }/admin/deleteAdmin.do?adminId=1&adminId=2
	 * @param adminId
	 * @return
	 */
	@RequestMapping(value="/deleteAdmins")
	public String deleteAdmins(Long[] adminId,HttpServletRequest request) {
		logger.debug("-批量删除管理员:"+adminId);
		adminService.deleteAdminByIds((Object[])adminId);
		return this.toAdminList(null, 0, request);
	}
	/**
	 * 模糊查询
	 * @return
	 */
	@RequestMapping("/toAdminSearch")
	public String toSearchAdmin(@RequestParam Map<String, Object> condition,HttpServletRequest request,Integer index){
		if (index==null) {
			index=0;
		}
		Object object = condition.get("search_thing");
		request.setAttribute("search_thing", object);
		Page page = adminService.searchAdminInfo(condition, index, Global.PAGE_SIZE);
		request.setAttribute("page", page);
		return "manager/adminSearch";
	}
	@RequestMapping("/searchAdmin")
	public String searchAdmin(@RequestParam Map<String, Object> condition,HttpServletRequest request,Integer index){
		if (index==null) {
			index=0;
		}
		Object object = condition.get("search_thing");
		request.setAttribute("search_thing", object);
		Page page = adminService.searchAdminInfo(condition, index, Global.PAGE_SIZE);
		request.setAttribute("page", page);
		return "manager/adminSearch";
	}
	

}
