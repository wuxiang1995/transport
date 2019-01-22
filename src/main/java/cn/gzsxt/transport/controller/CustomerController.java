package cn.gzsxt.transport.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import cn.gzsxt.transport.service.CustomerService;
import cn.gzsxt.transport.utils.Global;
import cn.gzsxt.transport.utils.Page;

@Controller
@Scope(value = "request")
@RequestMapping(value = "/customer")
public class CustomerController {
	private static final Logger logger = LogManager.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;
	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/toCustomerAdd")
	@TokenForm(create = true)
	public String toAdminAdd(HttpServletRequest request) {
		logger.debug("跳转到增加客户页面--");
		List<Map<String, Object>> list = adminService.findAdmins();
		request.setAttribute("admins", list);
		return "manager/customer-add";
	}

	/**
	 * 增加客户 path:${pageContext.request.contextPath }/customer/addCustomer.do
	 * 
	 * @return
	 */

	@RequestMapping(value = "/addCustomer")
	@TokenForm(remove = true)
	// @ResponseBody
	public String addAdmin(@RequestParam Map<String, Object> entity, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("增加客户：" + entity);
		// ServletOutputStream out=null;
		try {
			// out= response.getOutputStream();
			Map<String, Object> customer = customerService.addCustomer(entity);
			if (customer != null) {
				request.setAttribute("customer_add_msg", "增加客户成功！客户编号为" + customer.get("customer_id"));
				// out.print("<script> $(function() {layer.alert('添加成功')})</script>");
				return "forward:/customer/toCustomerAdd.do";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("customer_add_msg", "增加客户失败");

		return "forward:/customer/toCustomerAdd.do";
	}

	/**
	 * 跳转到客户类别 path：${pageContext.request.contextPath }/customer/toCustomerList.do
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toCustomerList")
	public String toCustomerList(@RequestParam Map<String, Object> condition, Integer index, HttpServletRequest request,
			HttpSession session) {
		logger.debug("-跳转到客户列表-" + condition + "，索引：" + index);
		try {
			if (index == null) {
				index = 0;
			}
			Map<String, Object> adminInfo = (Map<String, Object>) session.getAttribute("admin_info");
			Long iLong = 0l;
			Integer integer = 0;
			if (adminInfo.get("role_id") != null && !adminInfo.get("role_id").equals("")) {
				iLong = Long.valueOf(adminInfo.get("role_id").toString());
			}
			if (iLong == 1) {
				if (adminInfo.get("admin_id") != null && !adminInfo.get("admin_id").equals("")) {
					integer = Integer.valueOf(adminInfo.get("admin_id").toString());
				}
				condition.put("staff_no", integer);
			}
			Page page = customerService.findCustomerToPage(condition, index, Global.PAGE_SIZE);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/customerList";
	}

	@RequestMapping(value = "/deleteCustomer")
	public String deleteCustomer(Long customerId, HttpServletRequest request, HttpSession session) {
		logger.debug("-删除客户:" + customerId);
		customerService.deleteCustomerByIds(customerId);
		Map<String, Object> condition = new HashMap<>();
		Map<String, Object> adminInfo = (Map<String, Object>) session.getAttribute("admin_info");
		Long iLong = 0l;
		Integer integer = 0;
		if (adminInfo.get("role_id") != null && !adminInfo.get("role_id").equals("")) {
			iLong = Long.valueOf(adminInfo.get("role_id").toString());
		}
		if (iLong == 1) {
			if (adminInfo.get("admin_id") != null && !adminInfo.get("admin_id").equals("")) {
				integer = Integer.valueOf(adminInfo.get("admin_id").toString());
			}
			condition.put("staff_no", integer);
		}
		return this.toCustomerList(condition, 0, request, session);
	}

	@RequestMapping(value = "/deleteCustomers")
	public String deleteCustomers(Long[] customerId, HttpServletRequest request, HttpSession session) {
		logger.debug("-批量删除客户:" + customerId);
		customerService.deleteCustomerByIds((Object[]) customerId);
		Map<String, Object> condition = new HashMap<>();
		Map<String, Object> adminInfo = (Map<String, Object>) session.getAttribute("admin_info");
		Long iLong = 0l;
		Integer integer = 0;
		if (adminInfo.get("role_id") != null && !adminInfo.get("role_id").equals("")) {
			iLong = Long.valueOf(adminInfo.get("role_id").toString());
		}
		if (iLong == 1) {
			if (adminInfo.get("admin_id") != null && !adminInfo.get("admin_id").equals("")) {
				integer = Integer.valueOf(adminInfo.get("admin_id").toString());
			}
			condition.put("staff_no", integer);
		}
		return this.toCustomerList(condition, 0, request, session);
	}

	@RequestMapping("/toCustomerSearch")
	public String toSearchAdmin(@RequestParam Map<String, Object> condition, HttpServletRequest request, Integer index,
			HttpSession session) {
		try {
			if (index == null) {
				index = 0;
			}
			Map<String, Object> adminInfo = (Map<String, Object>) session.getAttribute("admin_info");
			Long iLong = 0l;
			Integer integer = 0;
			if (adminInfo.get("role_id") != null && !adminInfo.get("role_id").equals("")) {
				iLong = Long.valueOf(adminInfo.get("role_id").toString());
			}
			if (iLong == 1) {
				if (adminInfo.get("admin_id") != null && !adminInfo.get("admin_id").equals("")) {
					integer = Integer.valueOf(adminInfo.get("admin_id").toString());
				}
				condition.put("staff_no", integer);
			}
			Page page = customerService.findCustomerToPage(condition, index, Global.PAGE_SIZE);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/customerList";
	}

	@RequestMapping(value = "/toCustomerEdit")
	@TokenForm(create = true)
	public String toCustomerEdit(Long customerId, HttpServletRequest request) {
		logger.debug("跳转到修改客户页面--" + customerId);
		try {
			// 1.通过客户编号查询客户的信息
			Map<String, Object> customer = customerService.findCustomerById(customerId);
			request.setAttribute("customer", customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/customerEdit";
	}

	@RequestMapping(value = "/editCustomer")
	@TokenForm(remove = true)
	public String editCustomer(@RequestParam Map<String, Object> entity, HttpServletRequest request) {
		logger.debug("-编辑客户信息-" + entity);
		try {
			Map<String, Object> customer = customerService.editCustomer(entity);
			if (customer != null) {
				request.setAttribute("customer_edit_msg", "更新客户成功");
				// 更新成功，重设表单值
				request.setAttribute("customer", customer);

			} else {
				request.setAttribute("customer_edit_msg", "更新客户失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("customer_edit_msg", "更新客户失败");
		}

		// 需要通过toAdminEdit方法跳转
		// 默认Map里面的值的字符串类型的
		Long customerId = Long.valueOf((String) entity.get("customer_id"));
		return "forward:/customer/toCustomerEdit.do?customerId=" + customerId;
	}
}
