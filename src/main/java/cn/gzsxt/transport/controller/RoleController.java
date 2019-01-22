package cn.gzsxt.transport.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.gzsxt.transport.annotation.TokenForm;
import cn.gzsxt.transport.service.ModularService;
import cn.gzsxt.transport.service.PowerService;
import cn.gzsxt.transport.service.RoleService;
import cn.gzsxt.transport.utils.Global;
import cn.gzsxt.transport.utils.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="/role")
public class RoleController {
	
	private static final Logger logger =LogManager.getLogger(AdminController.class);
	
	@Autowired
	private  RoleService roleService;
	@Autowired
	private ModularService modularService;
	@Autowired 
	private PowerService powerService;
	
	
	/**
	 * 跳转到角色类别
	 * path：${pageContext.request.contextPath }/role/toRoleList.do
	 * @return
	 */
	@RequestMapping(value="/toRoleList")
	public String toRoleList(@RequestParam Map<String, Object> condition,Integer index,HttpServletRequest request) {
		logger.debug("-跳转到角色列表-"+condition+"，索引："+index);
		try {
			if (index==null) {
				index=0;
			}
			
			Page page = roleService.findRoleToPage(condition, index, Global.PAGE_SIZE);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/roleList";
	}
	
	/**
	 * 跳转到增加角色页面
	 * path:${pageContext.request.contextPath }/role/toRoleAdd.do
	 * @return
	 */
	@RequestMapping(value="/toRoleAdd")
	@TokenForm(create=true)
	public String toRoleAdd(HttpServletRequest request) {
		logger.debug("跳转到增加角色页面--");
		//1.获得所有模块的信息
		List<Map<String, Object>> modulars = modularService.findAllModular();
		request.setAttribute("modulars", modulars);
		//2.获得所有的权限
		List<Map<String, Object>> powers = powerService.findAllPower();
		request.setAttribute("powers", powers);
		
		return "manager/roleAdd";
	}
	
	/**
	 * 增加角色
	 * path:${pageContext.request.contextPath }/role/roleAdd.do
	 * @return
	 */
	@TokenForm(remove=true)
	@RequestMapping(value="/addRole")
	public String addRole(@RequestParam Map<String,Object> entity,String[] rolePower,HttpServletRequest request) {
		logger.debug("增加角色："+entity);
		
		String rolePowerStr = Arrays.toString(rolePower);
		rolePowerStr = rolePowerStr.replaceAll("\\s*", "");
		//.去掉方括号
		StringBuilder builder=new StringBuilder(rolePowerStr);
		builder.deleteCharAt(builder.indexOf("["));
		builder.deleteCharAt(builder.indexOf("]"));
		
		logger.debug("权限字符串："+builder.toString());
		
		entity.put("role_powers", builder.toString());
		try {
			Map<String, Object> role = roleService.addRole(entity);
			if (role!=null) {
				request.setAttribute("role_add_msg", "增加角色成功！");
				return "forward:/role/toRoleAdd.do";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("role_add_msg", "增加角色失败");
		
		return "forward:/role/toRoleAdd.do";
	}
	

	/**
	 * 跳转到编辑角色页面
	 * path:${pageContext.request.contextPath }/role/toRoleEdit.do
	 * @return
	 */
	@RequestMapping(value="/toRoleEdit")
	@TokenForm(create=true)
	public String toRoleEdit(Long roleId,HttpServletRequest request ) {
		logger.debug("跳转到编辑角色页面--"+roleId);
	
		try {
			//通过角色编号查询角色的信息
			Map<String, Object> role = roleService.findRoleById(roleId);
			request.setAttribute("role", role);
			
			//1.获得所有模块的信息
			List<Map<String, Object>> modulars = modularService.findAllModular();
			request.setAttribute("modulars", modulars);
			//2.获得所有的权限
			List<Map<String, Object>> powers = powerService.findAllPower();
			request.setAttribute("powers", powers);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/roleEdit";
	}
	
	/**
	 * 编辑角色
	 * path:${pageContext.request.contextPath }/role/editRole.do
	 * @param entity
	 * @return
	 */
	@TokenForm(remove=true)
	@RequestMapping(value="/editRole")
	public String editRole(@RequestParam Map<String, Object> entity,String[] rolePower,HttpServletRequest request ) {
		logger.debug("-编辑角色-"+entity);
		//获得角色编号
		Long roleId =Long.valueOf((String)entity.get("role_id"));
		try {
			String rolePowerStr = Arrays.toString(rolePower);
			rolePowerStr = rolePowerStr.replaceAll("\\s*", "");
			//.去掉方括号
			StringBuilder builder=new StringBuilder(rolePowerStr);
			builder.deleteCharAt(builder.indexOf("["));
			builder.deleteCharAt(builder.indexOf("]"));
			
			logger.debug("权限字符串："+builder.toString());
			
			entity.put("role_powers", builder.toString());
			

			Map<String, Object> role = roleService.editRole(entity);
			if (role!=null) {
				request.setAttribute("role_edit_msg", "更新角色成功");
				//更新成功，重设表单值
				request.setAttribute("role", role);
			}else {
				request.setAttribute("role_edit_msg", "更新角色失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("role_edit_msg", "更新角色失败");
		}
		
		return "forward:/role/toRoleEdit.do?roleId="+roleId;
	}
	/**
	 * 删除角色
	 * path:${pageContext.request.contextPath }/role/deleteRole.do?roleId=${role.role_id}
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="/deleteRole")
	public String deleteRole(Long roleId,HttpServletRequest request) {
		logger.debug("-删除角色:"+roleId);
		roleService.deleteRoleByIds(roleId);
		return this.toRoleList(null, 0, request);
	}
	
	/**
	 * 批量删除角色
	 * path:${pageContext.request.contextPath }/role/deleteRole.do?roleId=1&roleId=2
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="/deleteRoles")
	public String deleteRoles(Long[] roleId,HttpServletRequest request) {
		logger.debug("-批量删除角色:"+roleId);
		roleService.deleteRoleByIds((Object[])roleId);
		return this.toRoleList(null, 0, request);
	}
	/**
	 * 模糊查询
	 * path:${pageContext.request.contextPath }/role/deleteRole.do?roleId=1&roleId=2
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="/toRoleSearch")
	public String toSearchAdmin(@RequestParam Map<String, Object> entity,HttpServletRequest request,Integer index){
		try {
			if (index==null) {
				index=0;
			}
			Object object = entity.get("search_thing");
			request.setAttribute("search_thing", object);
			Page page = roleService.searchRoleInfo(entity, index, 5);
			request.setAttribute("page", page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "manager/roleSearch";
	}
	@RequestMapping(value="/searchRole")
	public String searchAdmin(@RequestParam Map<String, Object> entity,HttpServletRequest request,Integer index){
		try {
			if (index==null) {
				index=0;
			}
			Object object = entity.get("search_thing");
			request.setAttribute("search_thing", object);
			Page page = roleService.searchRoleInfo(entity, index, 5);
			request.setAttribute("page", page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "manager/roleSearch";
	}

}
