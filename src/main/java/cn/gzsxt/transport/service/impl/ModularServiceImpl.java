package cn.gzsxt.transport.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gzsxt.transport.mapper.ModularMapper;
import cn.gzsxt.transport.service.ModularService;
import cn.gzsxt.transport.utils.Page;

@Service
public class ModularServiceImpl implements ModularService {
	
	@Autowired
	private ModularMapper modularMapper;
	
	private static final Logger logger =LogManager.getLogger(ModularServiceImpl.class);

	@Override
	public Page findModularToPage(Map<String, Object> condition, int index, int size) {
		logger.debug("模块分页查询");
		//1.通过条件查询记录数
		int count = modularMapper.countByCondition(condition);
		//2.通过条件查询数据
		//注意：开始位置=索引*每页记录数
		int start=index*size;
		List<Map<String, Object>> modulars = modularMapper.findByConditionToPage(condition, start, size);
		
		Page page=new Page(index, size, count, modulars);
		return page;
	}

	@Transactional
	@Override
	public Map<String, Object> addModular(Map<String, Object> entity) {
		int count = modularMapper.insert(entity);
		if(count>0) {
			return entity;
		}
		return null;
	}

	
	@Override
	public Map<String, Object> findModularById(Object modularId) {
		return modularMapper.findById(modularId);
		 
	}

	@Transactional
	@Override
	public Map<String, Object> editModular(Map<String, Object> entity) {
		int count = modularMapper.updateForNotnull(entity);
		if (count>0) {
			return modularMapper.findById(entity.get("modular_id"));
		}
		return null;
	}

	@Transactional
	@Override
	public int deleteModularByIds(Object... modularIds) {
		return modularMapper.deleteById(modularIds);
	
	}

	@Override
	public List<Map<String, Object>> findAllModular() {
		return modularMapper.findAll();
	}
	
	

}
