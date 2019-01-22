package cn.gzsxt.transport.service;

import java.util.List;
import java.util.Map;

import cn.gzsxt.transport.utils.Page;

public interface CustomerService {

	 Map<String, Object> addCustomer(Map<String, Object> entity);

	Page findCustomerToPage(Map<String, Object> condition, Integer index, int pageSize);

	int deleteCustomerByIds(Object...customerId);

	Map<String, Object> findCustomerById(Long customerId);

	Map<String, Object> editCustomer(Map<String, Object> entity);

	List<Map<String, Object>> findCustomers(Map<String, Object> con);

}
