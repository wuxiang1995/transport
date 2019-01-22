package cn.gzsxt.transport.controller;

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
import cn.gzsxt.transport.utils.Global;
import cn.gzsxt.transport.utils.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="/modular")
public class ModularController {
	
	private static final Logger logger =LogManager.getLogger(AdminController.class);
	
	@Autowired
	private  ModularService modularService;
	
	/**
	 * 跳转到模块类别
	 * path：${pageContext.request.contextPath }/modular/toModularList.do
	 * @return
	 */
	@RequestMapping(value="/toModularList")
	public String toModularList(@RequestParam Map<String, Object> condition,Integer index,HttpServletRequest request) {
		logger.debug("-跳转到模块列表-"+condition+"，索引："+index);
		try {
			if (index==null) {
				index=0;
			}
			
			Page page = modularService.findModularToPage(condition, index, Global.PAGE_SIZE);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/modularList";
	}
	
	/**
	 * 跳转到增加模块页面
	 * path:${pageContext.request.contextPath }/modular/toModularAdd.do
	 * @return
	 */
	@TokenForm(create=true)
	@RequestMapping(value="/toModularAdd")
	public String toModularAdd() {
		logger.debug("跳转到增加模块页面--");
		return "manager/modularAdd";
	}
	
	/**
	 * 增加模块
	 * path:${pageContext.request.contextPath }/modular/modularAdd.do
	 * @return
	 */
	@TokenForm(remove=true)
	@RequestMapping(value="/addModular")
	public String addModular(@RequestParam Map<String,Object> entity,HttpServletRequest request) {
		logger.debug("增加模块："+entity);
		try {
			Map<String, Object> modular = modularService.addModular(entity);
			if (modular!=null) {
				request.setAttribute("modular_add_msg", "增加模块成功！");
				return "forward:/modular/toModularAdd.do";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("modular_add_msg", "增加模块失败");
		
		return "forward:/modular/toModularAdd.do";
	}
	

	/**
	 * 跳转到编辑模块页面
	 * path:${pageContext.request.contextPath }/modular/toModularEdit.do
	 * @return
	 */
	@RequestMapping(value="/toModularEdit")
	@TokenForm(create=true)
	public String toModularEdit(Long modularId,HttpServletRequest request ) {
		logger.debug("跳转到更新模块页面--"+modularId);
		//通过模块编号查询模块的信息
		try {
			Map<String, Object> modular = modularService.findModularById(modularId);
			request.setAttribute("modular", modular);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/modularEdit";
	}
	
	/**
	 * 编辑模块
	 * path:${pageContext.request.contextPath }/modular/editModular.do
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/editModular")
	@TokenForm(remove=true)
	public String editModular(@RequestParam Map<String, Object> entity,HttpServletRequest request ) {
		logger.debug("-编辑模块-"+entity);
		try {
			Map<String, Object> modular = modularService.editModular(entity);
			if (modular!=null) {
				request.setAttribute("modular_edit_msg", "更新模块成功");
				//更新成功，重设表单值
				request.setAttribute("modular", modular);
			}else {
				request.setAttribute("modular_edit_msg", "更新模块失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("modular_edit_msg", "更新模块失败");
		}
		Long modularId=Long.valueOf((String)entity.get("modular_id"));
		return "forward:/modular/toModularEdit.do?modularId="+modularId;
	}
	/**
	 * 删除模块
	 * path:${pageContext.request.contextPath }/modular/deleteModular.do?modularId=${modular.modular_id}
	 * @param modularId
	 * @return
	 */
	@RequestMapping(value="/deleteModular")
	public String deleteModular(Long modularId,HttpServletRequest request) {
		logger.debug("-删除模块:"+modularId);
		modularService.deleteModularByIds(modularId);
		return this.toModularList(null, 0, request);
	}
	
	/**
	 * 批量删除模块
	 * path:${pageContext.request.contextPath }/modular/deleteModulars.do?modularId=1&modularId=2
	 * @param modularId
	 * @return
	 */
	@RequestMapping(value="/deleteModulars")
	public String deleteModulars(Long[] modularId,HttpServletRequest request) {
		logger.debug("-批量删除模块:"+modularId);
		modularService.deleteModularByIds((Object[])modularId);
		return this.toModularList(null, 0, request);
	}
	
	

}
