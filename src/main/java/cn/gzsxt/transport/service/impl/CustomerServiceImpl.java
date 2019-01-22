package cn.gzsxt.transport.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gzsxt.transport.mapper.CustomerMapper;
import cn.gzsxt.transport.service.CustomerService;
import cn.gzsxt.transport.utils.Page;
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerMapper customerMapper;
	
	@Transactional
	@Override
	public Map<String, Object> addCustomer(Map<String, Object> entity) {
		SimpleDateFormat adf=new SimpleDateFormat("yy-MM-dd");
		String format = adf.format(new Date());
		entity.put("customer_date", format);
		int i = customerMapper.insert(entity);
		if(i>0) {
			return entity;
		}
		return null;
	}
	@Override
	public Page findCustomerToPage(Map<String, Object> condition, Integer index, int size) {
		//1.通过条件查询记录数
		//System.out.println(condition);
		int count = customerMapper.countByCondition(condition);
		//2.通过条件查询数据
		//注意：开始位置=索引*每页记录数
		int start=index*size;
		List<Map<String, Object>> customers = customerMapper.findByConditionToPage(condition, start, size);
		Page page=new Page(index, size, count, customers);
		return page;
	}
	@Transactional
	@Override
	public int deleteCustomerByIds(Object... customerId) {
		return customerMapper.deleteById(customerId);
		
	}
	@Override
	public Map<String, Object> findCustomerById(Long customerId) {
		// TODO Auto-generated method stub
		return customerMapper.findCustomerById(customerId);
	}
	@Transactional
	@Override
	public Map<String, Object> editCustomer(Map<String, Object> entity) {
		SimpleDateFormat adf=new SimpleDateFormat("yy-MM-dd");
		String format = adf.format(new Date());
		entity.put("customer_date", format);
		int count = customerMapper.updateForNotnull(entity);
		if (count>0) {
			return customerMapper.findCustomerById(entity.get("customer_id"));
		}
		return null;
	}
	@Override
	public List<Map<String, Object>> findCustomers(Map<String, Object> con) {
		// TODO Auto-generated method stub
		return customerMapper.findCustomers(con);
	}

}
