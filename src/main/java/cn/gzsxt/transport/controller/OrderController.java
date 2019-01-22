package cn.gzsxt.transport.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.transport.annotation.TokenForm;
import cn.gzsxt.transport.service.AdminService;
import cn.gzsxt.transport.service.CustomerService;
import cn.gzsxt.transport.service.ItemService;
import cn.gzsxt.transport.service.OrderService;
import cn.gzsxt.transport.utils.Global;
import cn.gzsxt.transport.utils.Page;
@Controller
@Scope(value = "request")
@RequestMapping(value = "/order")
public class OrderController {
	private static final Logger logger = LogManager.getLogger(OrderController.class);
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CustomerService customerService;
	@RequestMapping(value = "/toOrderAdd")
	@TokenForm(create = true)
	public String toAdminAdd(HttpServletRequest request,HttpSession session) {
		logger.debug("跳转到增加订单页面--");
		Map<String, Object> condition=new HashMap<>();
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
		List<Map<String, Object>> list = adminService.findAdmins();
		request.setAttribute("admins", list);
		List<Map<String, Object>> list1 = customerService.findCustomers(condition);
		request.setAttribute("customers", list1);
		return "manager/order-add";
	}
	@RequestMapping(value = "/addOrder",produces="text/html; charset=UTF-8")
	@TokenForm(remove = true)
	//@ResponseBody
	public String addOrder(@RequestParam Map<String, Object> entity, HttpServletRequest request,
			HttpServletResponse response,HttpSession session) {
		logger.debug("增加订单：" + entity);
		try {
			Map<String, Object> adminInfo = (Map<String, Object>) session.getAttribute("admin_info");
			if(adminInfo.get("no") != null&&entity.get("order_destination")!=null) {
			entity.put("order_id", adminInfo.get("no").toString()+new Date().getTime()+entity.get("order_destination"));}
			if (adminInfo.get("net_id")!=null) {
				entity.put("net_id", Integer.valueOf(adminInfo.get("net_id").toString()));
			}
			Map<String, Object> i=orderService.addOrder(entity);
			int b=itemService.addItems(entity);
			int a=itemService.addPick(entity);
			if(i!=null&&b>0&&a>0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script language='javascript'>alert('提交成功！编号为："+i.get("order_id")
				+"');window.location.href='http://localhost/transport/order/toOrderAdd.html'</script>");
			}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script language='javascript'>alert('提交失败！！！);history.back();</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/toOrderList")
	public String toOrderList(@RequestParam Map<String, Object> condition, Integer index, HttpServletRequest request,
			HttpSession session) {
		logger.debug("-跳转到订单列表-" + condition + "，索引：" + index);
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
			condition.put("order_status", 1);
			Page page = orderService.findOrderToPage(condition, index, Global.PAGE_SIZE);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/orderList";
	}
	@RequestMapping(value = "/toOrderSearch")
	public String toOrderSearch(@RequestParam Map<String, Object> condition, Integer index, HttpServletRequest request,
			HttpSession session) {
		logger.debug("-查询订单列表-" + condition + "，索引：" + index);
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
			condition.put("order_status", 1);
			Page page = orderService.findOrderToPage(condition, index, Global.PAGE_SIZE);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/orderList";
	}
	
	@RequestMapping(value = "/deleteOrder")
	public String deleteCustomer(String orderId, HttpServletRequest request, HttpSession session) {
		logger.debug("-删除订单:" + orderId);
		orderService.deleteCustomerByIds(orderId);
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
		condition.put("order_status", 1);
		return this.toOrderList(condition, 0, request, session);
	}
	@RequestMapping(value = "/toOrderEdit")
	@TokenForm(create = true)
	public String toOrderEdit(String orderId, HttpServletRequest request,
			HttpSession session) {
		logger.debug("-To编辑订单-" + orderId );
		try {
			Map<String, Object>order=orderService.toOrderEdit(orderId);
			request.setAttribute("order", order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/orderEdit";
	}
	@RequestMapping(value = "/editOrder")
	@TokenForm(remove = true)
	public String editOrder(@RequestParam Map<String, Object> entity, HttpServletRequest request,HttpServletResponse response,
			HttpSession session) {
		logger.debug("-编辑订单-" + entity );
		try {
			Map<String, Object> i=orderService.editOrder(entity);
			int b=0;
			if (entity.get("pick_transcompany")!=null||entity.get("pick_address")!=null) {
				b=itemService.editPick(entity);
			}
			int a=itemService.editItems(entity);
			if(i!=null&&b>0&&a>0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script language='javascript'>alert('修改成功！编号为："+entity.get("order_id")
				+"');window.location.href='http://localhost/transport/order/toOrderList.html'</script>");
			}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script language='javascript'>alert('修改失败！！！);history.back();</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		//return this.toOrderEdit(orderId, request, session);
	}
	@RequestMapping(value = "/deleteOrders")
	public String deleteCustomers(String[] orderId, HttpServletRequest request, HttpSession session) {
		logger.debug("-批量删除订单:" + orderId);
		orderService.deleteCustomerByIds((Object[]) orderId);
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
		condition.put("order_status", 1);
		return this.toOrderList(condition, 0, request, session);
	}
	@RequestMapping(value = "/toOrderInstoreList")
	public String toOrderInstoreList(@RequestParam Map<String, Object> condition, Integer index, HttpServletRequest request,
			HttpSession session) {
		logger.debug("-跳转到入库订单列表-" + condition + "，索引：" + index);
		try {
			if (index == null) {
				index = 0;
			}
			condition.put("order_status", 1);
			Page page = orderService.findOrderToPage(condition, index, Global.PAGE_SIZE);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/order";
	}
	@RequestMapping(value = "/toOrderQuoteList")
	public String toOrderQuoteList(@RequestParam Map<String, Object> condition, Integer index, HttpServletRequest request,
			HttpSession session) {
		logger.debug("-跳转到业务订单列表-" + condition + "，索引：" + index);
		try {
			if (index == null) {
				index = 0;
			}
			condition.put("order_status", 2);
			Page page = orderService.findOrderToPage(condition, index, Global.PAGE_SIZE);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/order";
	}
	@RequestMapping(value = "/toOrderReviewList")
	public String toOrderReviewList(@RequestParam Map<String, Object> condition, Integer index, HttpServletRequest request,
			HttpSession session) {
		logger.debug("-跳转到财务订单列表-" + condition + "，索引：" + index);
		try {
			if (index == null) {
				index = 0;
			}
			condition.put("order_status", 3);
			Page page = orderService.findOrderToPage(condition, index, Global.PAGE_SIZE);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/order";
	}
	@RequestMapping(value = "/toOrderFahuoList")
	public String toOrderFahuoList(@RequestParam Map<String, Object> condition, Integer index, HttpServletRequest request,
			HttpSession session) {
		logger.debug("-跳转到发货订单列表-" + condition + "，索引：" + index);
		try {
			if (index == null) {
				index = 0;
			}
			condition.put("order_status", 4);
			Page page = orderService.findOrderToPage(condition, index, Global.PAGE_SIZE);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/order";
	}
	@RequestMapping(value = "/OrderSearch")
	public String OrderSearch(@RequestParam Map<String, Object> condition, Integer index, HttpServletRequest request,
			HttpSession session) {
		logger.debug("-查询订单列表-" + condition + "，索引：" + index);
		try {
			if (index == null) {
				index = 0;
			}
			//condition.put("order_status", 1);
			Page page = orderService.findOrderToPage(condition, index, Global.PAGE_SIZE);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/order";
	}
	@RequestMapping(value = "/toOrderInstore")
	@TokenForm(create = true)
	public String toOrderInstore(String orderId, HttpServletRequest request,
			HttpSession session) {
		logger.debug("-跳转到核对入库-" + orderId);
		try {
			Map<String, Object> order = orderService.findById(orderId);
			request.setAttribute("orderdd", order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/order-instore";
	}
	@RequestMapping(value = "/orderInstore")
	@TokenForm(remove = true)
	public String orderInstore(@RequestParam Map<String, Object> entity, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("-核对入库-" + entity);
		try {
			Map<String, Object> order = orderService.orderInstore(entity);
			if(order!=null) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script language='javascript'>alert('核对成功！编号为："+entity.get("order_id")
				+"');window.location.href='http://localhost/transport/order/toOrderInstoreList.html'</script>");
			}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script language='javascript'>alert('核对失败！！！);history.back();</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/toOrderQuote")
	@TokenForm(create = true)
	public String toOrderQuote(String orderId, HttpServletRequest request,
			HttpSession session) {
		logger.debug("-跳转到业务报价-" + orderId);
		try {
			Map<String, Object> order = orderService.findById(orderId);
			request.setAttribute("orderdd", order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/order-quote";
	}
	@RequestMapping(value = "/orderQuote")
	@TokenForm(remove = true)
	public String orderQuote(@RequestParam Map<String, Object> entity, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("-业务报价-" + entity);
		try {
			Map<String, Object> order = orderService.orderQuote(entity);
			if(order!=null) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script language='javascript'>alert('报价成功！编号为："+entity.get("order_id")
				+"');window.location.href='http://localhost/transport/order/toOrderQuoteList.html'</script>");
			}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script language='javascript'>alert('报价失败！！！);history.back();</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/toOrderReview")
	@TokenForm(create = true)
	public String toOrderReview(String orderId, HttpServletRequest request,
			HttpSession session) {
		logger.debug("-跳转到财务审核-" + orderId);
		try {
			Map<String, Object> order = orderService.findById(orderId);
			request.setAttribute("orderdd", order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/order-review";
	}
	@RequestMapping(value = "/orderReview")
	@TokenForm(remove = true)
	public String orderReview(@RequestParam Map<String, Object> entity,HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("-财务审核-");
		try {
			entity.put("order_status", 4);
			Map<String, Object> order = orderService.orderReview(entity);
			if(order!=null) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script language='javascript'>alert('审核成功！编号为："+entity.get("order_id")
				+"');window.location.href='http://localhost/transport/order/toOrderReviewList.html'</script>");
			}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script language='javascript'>alert('审核失败！！！);history.back();</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/toOrderFahuo")
	@TokenForm(create = true)
	public String toOrderFahuo(String orderId, HttpServletRequest request,
			HttpSession session) {
		logger.debug("-跳转到发货-" + orderId);
		try {
			Map<String, Object> order = orderService.findById(orderId);
			request.setAttribute("orderdd", order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/order-review";
	}
	@RequestMapping(value = "/orderFahuo")
	@TokenForm(remove = true)
	public String orderFahuo(@RequestParam Map<String, Object> entity,HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("-发货-");
		try {
			entity.put("order_status", 5);
			Map<String, Object> order = orderService.orderFahuo(entity);
			
			if(order!=null) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script language='javascript'>alert('发货成功！编号为："+entity.get("order_id")
				+"');window.location.href='http://localhost/transport/order/toOrderReviewList.html'</script>");
			}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script language='javascript'>alert('发货失败！！！);history.back();</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/toOrderInfoList")
	public String toOrderInfoList() {
		logger.debug("-跳转到物流信息-");
		return "manager/order-info";
	}
	@RequestMapping(value = "/toOrderInfo")
	@TokenForm(create = true)
	public String toOrderInfo(@RequestParam Map<String, Object> entity, HttpServletRequest request,
			HttpServletResponse response,HttpSession session) {
		try {
		if(entity.get("order_id")!=null&&!entity.get("order_id").equals("")) {
			List<Map<String, Object>> condition = null;
			Map<String, Object> adminInfo = (Map<String, Object>) session.getAttribute("admin_info");
			if(adminInfo.get("net_id")!=null) {
				condition=orderService.findCars(Integer.valueOf(adminInfo.get("net_id").toString()));
			}
			Map<String, Object> map = orderService.findById(entity.get("order_id").toString());
			if(map==null) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script language='javascript'>alert('无此订单编号！！！');history.back();</script>");
				return null;
			}else {
				map.put("condition", condition);
				request.setAttribute("orderdd", map);
				return "manager/order-wuliu";
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/orderInfo")
	@TokenForm(remove = true)
	public String orderInfo(@RequestParam Map<String, Object> entity, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Map<String, Object> order=orderService.addorderWuliu(entity);
			if(order!=null) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script language='javascript'>alert('添加成功！编号为："+entity.get("order_id")
				+"');window.location.href='http://localhost/transport/admin/toIndex.do'</script>");
			}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script language='javascript'>alert('添加失败！！！);history.back();</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
