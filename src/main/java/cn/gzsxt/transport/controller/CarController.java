package cn.gzsxt.transport.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.gzsxt.transport.annotation.TokenForm;
import cn.gzsxt.transport.service.CarService;
import cn.gzsxt.transport.utils.Global;
import cn.gzsxt.transport.utils.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="/car")
public class CarController {
	
	private static final Logger logger =LogManager.getLogger(CarController.class);
	
	@Autowired
	private  CarService carService;

	@RequestMapping(value="/toCarList")
	public String toCarList(@RequestParam Map<String, Object> condition,Integer index,HttpServletRequest request) {
		logger.debug("-跳转到车辆列表-"+condition+"，索引："+index);
		try {
			if (index==null) {
				index=0;
			}
			
			Page page = carService.findCarToPage(condition, index, Global.PAGE_SIZE);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/carList";
	}
	
	/**
	 * 跳转到增加车辆页面
	 * path:${pageContext.request.contextPath }/car/toCarAdd.do
	 * @return
	 */
	@TokenForm(create=true)
	@RequestMapping(value="/toCarAdd")
	public String toCarAdd(HttpServletRequest request) {
		logger.debug("跳转到增加车辆页面--");
		List<Map<String, Object>> nets=carService.findNetworks();
		request.setAttribute("nets", nets);
		return "manager/carAdd";
	}
	
	/**
	 * 增加车辆
	 * path:${pageContext.request.contextPath }/car/carAdd.do
	 * @return
	 */
	@TokenForm(remove=true)
	@RequestMapping(value="/addCar")
	public String addCar(@RequestParam Map<String,Object> entity,HttpServletRequest request) {
		logger.debug("增加车辆："+entity);
		try {
			Map<String, Object> car = carService.addCar(entity);
			if (car!=null) {
				request.setAttribute("car_add_msg", "增加车辆成功！");
				return "forward:/car/toCarAdd.do";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("car_add_msg", "增加车辆失败");
		
		return "forward:/car/toCarAdd.do";
	}
	@TokenForm(remove=true)
	@RequestMapping(value="/protectCar")
	public String protectCar(Long carId,HttpServletRequest request,HttpServletResponse response) {
		logger.debug("维修车辆："+carId);
		try {
			Map<String, Object> car = carService.protectCar(carId);
			if (car!=null) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script language='javascript'>alert('维修成功!!!');history.back();</script>");
				return null;
			}
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script language='javascript'>alert('维修失败！！！);history.back();</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 跳转到编辑车辆页面
	 * path:${pageContext.request.contextPath }/car/toCarEdit.do
	 * @return
	 */
	@RequestMapping(value="/toCarEdit")
	@TokenForm(create=true)
	public String toCarEdit(Long carId,HttpServletRequest request ) {
		logger.debug("跳转到更新车辆页面--"+carId);
		//通过车辆编号查询车辆的信息
		try {
			Map<String, Object> car = carService.findCarById(carId);
			List<Map<String, Object>> nets=carService.findNetworks();
			car.put("nets", nets);
			request.setAttribute("car", car);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/carEdit";
	}
	
	/**
	 * 编辑车辆
	 * path:${pageContext.request.contextPath }/car/editCar.do
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/editCar")
	@TokenForm(remove=true)
	public String editCar(@RequestParam Map<String, Object> entity,HttpServletRequest request ) {
		logger.debug("-编辑车辆-"+entity);
		try {
			Map<String, Object> car = carService.editCar(entity);
			if (car!=null) {
				request.setAttribute("car_edit_msg", "更新车辆成功");
				//更新成功，重设表单值
				request.setAttribute("car", car);
			}else {
				request.setAttribute("car_edit_msg", "更新车辆失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("car_edit_msg", "更新车辆失败");
		}
		Long carId=Long.valueOf((String)entity.get("car_id"));
		return "forward:/car/toCarEdit.do?carId="+carId;
	}
	/**
	 * 删除车辆
	 * path:${pageContext.request.contextPath }/car/deleteCar.do?carId=${car.car_id}
	 * @param carId
	 * @return
	 */
	@RequestMapping(value="/deleteCar")
	public String deleteCar(Long carId,HttpServletRequest request) {
		logger.debug("-删除车辆:"+carId);
		carService.deleteCarByIds(carId);
		return this.toCarList(null, 0, request);
	}
	
	/**
	 * 批量删除车辆
	 * path:${pageContext.request.contextPath }/car/deleteCars.do?carId=1&carId=2
	 * @param carId
	 * @return
	 */
	@RequestMapping(value="/deleteCars")
	public String deleteCars(Long[] carId,HttpServletRequest request) {
		logger.debug("-批量删除车辆:"+carId);
		carService.deleteCarByIds((Object[])carId);
		return this.toCarList(null, 0, request);
	}
	
	

}
