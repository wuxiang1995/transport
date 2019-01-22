package cn.gzsxt.transport.service;

import java.util.List;
import java.util.Map;
import cn.gzsxt.transport.utils.Page;

public interface OrderService {

	Map<String, Object> addOrder(Map<String, Object> entity);

	Page findOrderToPage(Map<String, Object> condition, Integer index, int size);

	int deleteCustomerByIds(Object ... orderId);

	Map<String, Object> toOrderEdit(String orderId);

	Map<String, Object> editOrder(Map<String, Object> entity);

	Map<String, Object> findById(String orderId);

	Map<String, Object> orderInstore(Map<String, Object> entity);

	Map<String, Object> orderQuote(Map<String, Object> entity);

	Map<String, Object> orderReview(Map<String, Object> entity);

	Map<String, Object> orderFahuo(Map<String, Object> entity);

	Map<String, Object> addorderWuliu(Map<String, Object> entity);

	List<Map<String, Object>> findCars(Integer valueOf);

	
}
