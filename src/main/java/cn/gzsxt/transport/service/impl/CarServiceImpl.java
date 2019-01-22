package cn.gzsxt.transport.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gzsxt.transport.mapper.CarMapper;
import cn.gzsxt.transport.mapper.NetworkMapper;
import cn.gzsxt.transport.service.CarService;
import cn.gzsxt.transport.utils.Page;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	private CarMapper carMapper;
	@Autowired
	private NetworkMapper nMapper;
	
	private static final Logger logger =LogManager.getLogger(CarServiceImpl.class);

	@Override
	public Page findCarToPage(Map<String, Object> condition, int index, int size) {
		logger.debug("模块分页查询");
		//1.通过条件查询记录数
		int count = carMapper.countByCondition(condition);
		//2.通过条件查询数据
		//注意：开始位置=索引*每页记录数
		int start=index*size;
		List<Map<String, Object>> cars = carMapper.findByConditionToPage(condition, start, size);
		for (Map<String, Object> map : cars) {
			if(map.get("net_id")!=null) {
				Map<String, Object> map2 = nMapper.findById(Integer.valueOf(map.get("net_id").toString()));
				map.put("networks", map2);
			}
		}
		Page page=new Page(index, size, count, cars);
		return page;
	}

	@Transactional
	@Override
	public Map<String, Object> addCar(Map<String, Object> entity) {
		int count = carMapper.insert(entity);
		if(count>0) {
			return entity;
		}
		return null;
	}

	
	@Override
	public Map<String, Object> findCarById(Object modularId) {
		Map<String, Object> map = carMapper.findById(modularId);
		if (map.get("network_id")!=null) {
			Map<String, Object> map2 = nMapper.findById(Integer.valueOf(map.get("network_id").toString()));
			map.put("map2", map2);
		}
		return map;
		 
	}

	@Transactional
	@Override
	public Map<String, Object> editCar(Map<String, Object> entity) {
		int count = carMapper.update(entity);
		if (count>0) {
			return carMapper.findById(entity.get("id"));
		}
		return null;
	}

	@Transactional
	@Override
	public int deleteCarByIds(Object... modularIds) {
		return carMapper.deleteById(modularIds);
	
	}

	@Override
	public List<Map<String, Object>> findAllCar() {
		return carMapper.findAll();
	}

	@Override
	public Map<String, Object> protectCar(Long carId) {
		Map<String, Object> entity=new HashMap<>();
		entity.put("id", carId);
		entity.put("car_status", 2);
		int i = carMapper.update(entity);
		if(i>0) {
			return entity;
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> findNetworks() {
		// TODO Auto-generated method stub
		return nMapper.findNetworks();
	}
	
	

}
