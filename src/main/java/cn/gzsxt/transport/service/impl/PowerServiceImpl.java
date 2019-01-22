package cn.gzsxt.transport.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gzsxt.transport.mapper.DictionaryMapper;
import cn.gzsxt.transport.mapper.ModularMapper;
import cn.gzsxt.transport.mapper.PowerMapper;
import cn.gzsxt.transport.service.PowerService;
import cn.gzsxt.transport.utils.Page;

@Service
public class PowerServiceImpl implements PowerService {
	
	@Autowired
	private PowerMapper powerMapper;
	
	@Autowired
	private ModularMapper modularMapper;
	
	@Autowired
	private DictionaryMapper dictionaryMapper;
	
	private static final Logger logger =LogManager.getLogger(PowerServiceImpl.class);

	@Override
	public Page findPowerToPage(Map<String, Object> condition, int index, int size) {
		logger.debug("权限分页查询");
		//1.通过条件查询记录数
		int count = powerMapper.countByCondition(condition);
		//2.通过条件查询数据
		//注意：开始位置=索引*每页记录数
		int start=index*size;
		List<Map<String, Object>> powers = powerMapper.findByConditionToPage(condition, start, size);
		//3.拼接需要显示的数据
		for (Map<String, Object> power : powers) {
			
			//1.显示所属模块
			Object modularId = power.get("modular_id");
			Map<String, Object> modular = modularMapper.findById(modularId);
			power.put("modular", modular);
			
			//2.显示隐藏显示状态
			Map<String, Object> isShow = dictionaryMapper.findByTypeCodeAndValue(power.get("power_is_show"), 1001);
			power.put("isShow", isShow);
			
			//3.显示父权限
			Object parentPowerId = power.get("power_parent");
			if ((Long)parentPowerId==0) {
				Map<String, Object> parent=new HashMap<>();
				parent.put("power_name", "顶级菜单");
				power.put("parent", parent);
			}else {
				Map<String, Object> parent = powerMapper.findById(parentPowerId);
				power.put("parent", parent);
			}
			
		}
		
		Page page=new Page(index, size, count, powers);
		return page;
	}

	@Transactional
	@Override
	public Map<String, Object> addPower(Map<String, Object> entity) {
		int count = powerMapper.insert(entity);
		if(count>0) {
			return entity;
		}
		return null;
	}

	
	@Override
	public Map<String, Object> findPowerById(Object powerId) {
		return powerMapper.findById(powerId);
		 
	}

	@Transactional
	@Override
	public Map<String, Object> editPower(Map<String, Object> entity) {
		int count = powerMapper.updateForNotnull(entity);
		if (count>0) {
			return powerMapper.findById(entity.get("power_id"));
		}
		return null;
	}

	@Transactional
	@Override
	public int deletePowerByIds(Object... powerIds) {
		return powerMapper.deleteById(powerIds);
	
	}

	@Override
	public List<Map<String, Object>> findAllPower() {
		List<Map<String, Object>> powers = powerMapper.findAll();
		return powers;
	}

	@Override
	public Page searchPowerInfo(Map<String, Object> entity, Integer index, int size) {
		logger.debug("权限分页查询");
		//1.通过条件查询记录数
		int count = powerMapper.countBySearch(entity);
		//2.通过条件查询数据
		//注意：开始位置=索引*每页记录数
		int start=index*size;
		List<Map<String, Object>> powers = powerMapper.searchByConditionToPage(entity, start, size);
		//3.拼接需要显示的数据
		for (Map<String, Object> power : powers) {
			
			//1.显示所属模块
			Object modularId = power.get("modular_id");
			Map<String, Object> modular = modularMapper.findById(modularId);
			power.put("modular", modular);
			
			//2.显示隐藏显示状态
			Map<String, Object> isShow = dictionaryMapper.findByTypeCodeAndValue(power.get("power_is_show"), 1001);
			power.put("isShow", isShow);
			
			//3.显示父权限
			Object parentPowerId = power.get("power_parent");
			if ((Long)parentPowerId==0) {
				Map<String, Object> parent=new HashMap<>();
				parent.put("power_name", "顶级菜单");
				power.put("parent", parent);
			}else {
				Map<String, Object> parent = powerMapper.findById(parentPowerId);
				power.put("parent", parent);
			}
			
		}
		
		Page page=new Page(index, size, count, powers);
		return page;
	}
	
	

}
