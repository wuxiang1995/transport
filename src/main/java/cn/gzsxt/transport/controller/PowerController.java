package cn.gzsxt.transport.controller;

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
import cn.gzsxt.transport.service.DictionaryService;
import cn.gzsxt.transport.service.ModularService;
import cn.gzsxt.transport.service.PowerService;
import cn.gzsxt.transport.utils.Global;
import cn.gzsxt.transport.utils.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="/power")
public class PowerController {
	
	private static final Logger logger =LogManager.getLogger(AdminController.class);
	
	@Autowired
	private  PowerService powerService;
	@Autowired
	private ModularService modularService;
	@Autowired
	private DictionaryService dictionaryService;
	
	/**
	 * 跳转到权限类别
	 * path：${pageContext.request.contextPath }/power/toPowerList.do
	 * @return
	 */
	@RequestMapping(value="/toPowerList")
	public String toPowerList(@RequestParam Map<String, Object> condition,Integer index,HttpServletRequest request) {
		logger.debug("-跳转到权限列表-"+condition+"，索引："+index);
		try {
			if (index==null) {
				index=0;
			}
			
			Page page = powerService.findPowerToPage(condition, index, Global.PAGE_SIZE);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/powerList";
	}
	
	/**
	 * 跳转到增加权限页面
	 * path:${pageContext.request.contextPath }/power/toPowerAdd.do
	 * @return
	 */

	@RequestMapping(value="/toPowerAdd")
	@TokenForm(create=true)
	public String toPowerAdd(HttpServletRequest request) {
		logger.debug("跳转到增加权限页面--");
		//1.模块数据
		List<Map<String, Object>> modulars = modularService.findAllModular();
		request.setAttribute("modulars", modulars);
		
		//2.数据字典-权限显示状态数据
		List<Map<String, Object>> isShow = dictionaryService.findDictionaryByTypeCode(1001);
		request.setAttribute("isShow", isShow);
		//3.权限数据
		List<Map<String, Object>> powers = powerService.findAllPower();
		request.setAttribute("powers", powers);
		
		return "manager/powerAdd";
	}
	
	/**
	 * 增加权限
	 * path:${pageContext.request.contextPath }/power/powerAdd.do
	 * @return
	 */
	@RequestMapping(value="/addPower")
	@TokenForm(remove=true)
	public String addPower(@RequestParam Map<String,Object> entity,HttpServletRequest request) {
		logger.debug("增加权限："+entity);

		
		try {
			Map<String, Object> power = powerService.addPower(entity);
			if (power!=null) {
				request.setAttribute("power_add_msg", "增加权限成功！");
				return "forward:/power/toPowerAdd.do";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("power_add_msg", "增加权限失败");
		return "forward:/power/toPowerAdd.do";
	}
	

	/**
	 * 跳转到编辑权限页面
	 * path:${pageContext.request.contextPath }/power/toPowerEdit.do
	 * @return
	 */
	@RequestMapping(value="/toPowerEdit")
	@TokenForm(create=true)
	public String toPowerEdit(Long powerId,HttpServletRequest request ) {
		logger.debug("跳转到编辑权限页面--"+powerId);
		//通过权限编号查询权限的信息
		try {
			request.removeAttribute("power_edit_msg");
			Map<String, Object> power = powerService.findPowerById(powerId);
			request.setAttribute("power", power);
			
			//1.所有模块数据
			List<Map<String, Object>> modulars = modularService.findAllModular();
			request.setAttribute("modulars", modulars);
			
			//2.数据字典-权限显示状态数据
			List<Map<String, Object>> isShow = dictionaryService.findDictionaryByTypeCode(1001);
			request.setAttribute("isShow", isShow);
			//3.所有权限数据
			List<Map<String, Object>> powers = powerService.findAllPower();
			request.setAttribute("powers", powers);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/powerEdit";
	}
	
	/**
	 * 编辑权限
	 * path:${pageContext.request.contextPath }/power/editPower.do
	 * @param entity
	 * @return
	 */
	@TokenForm(remove=true)
	@RequestMapping(value="/editPower")
	public String editPower(@RequestParam Map<String, Object> entity,HttpServletRequest request ) {
		logger.debug("-编辑权限-"+entity);
		try {
			Map<String, Object> power = powerService.editPower(entity);
			if (power!=null) {
				request.setAttribute("power_edit_msg", "更新权限成功");
				//更新成功，重设表单值
				request.setAttribute("power", power);
			}else {
				request.setAttribute("power_edit_msg", "更新权限失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("power_edit_msg", "更新权限失败");
		}
		//将编号类型修改为Long类型
		Long powerId =Long.valueOf((String)entity.get("power_id")) ;
		
		return "forward:/power/toPowerAdd.do?powerId="+powerId;
	}
	/**
	 * 删除权限
	 * path:${pageContext.request.contextPath }/power/deletePower.do?powerId=${power.power_id}
	 * @param powerId
	 * @return
	 */
	@RequestMapping(value="/deletePower")
	public String deletePower(Long powerId,HttpServletRequest request) {
		logger.debug("-删除权限:"+powerId);
		powerService.deletePowerByIds(powerId);
		return this.toPowerList(null, 0, request);
	}
	
	/**
	 * 批量删除权限
	 * path:${pageContext.request.contextPath }/power/deletePowers.do?powerId=1&powerId=2
	 * @param powerId
	 * @return
	 */
	@RequestMapping(value="/deletePowers")
	public String deletePowers(Long[] powerId,HttpServletRequest request) {
		logger.debug("-批量删除权限:"+powerId);
		powerService.deletePowerByIds((Object[])powerId);
		return this.toPowerList(null, 0, request);
	}
	@RequestMapping("/toPowerSearch")
	public String searchPowers(@RequestParam Map<String, Object> entity,Integer index,HttpServletRequest request){
		try {
			if (index==null) {
				index=0;
			}
			Object object = entity.get("search_thing");
			request.setAttribute("search_thing", object);
			Page page = powerService.searchPowerInfo(entity, index, Global.PAGE_SIZE);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/powerSearch";
	}
	@RequestMapping("/searchPower")
	public String searchPower(@RequestParam Map<String, Object> entity,Integer index,HttpServletRequest request){
		try {
			if (index==null) {
				index=0;
			}
			Object thing = entity.get("search_thing");
			request.setAttribute("search_thing", thing);
			Page page = powerService.searchPowerInfo(entity, index, Global.PAGE_SIZE);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/powerSearch";
	}

}

