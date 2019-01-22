package cn.gzsxt.transport.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gzsxt.transport.mapper.RoleMapper;
import cn.gzsxt.transport.service.RoleService;
import cn.gzsxt.transport.utils.Page;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	
	private static final Logger logger =LogManager.getLogger(RoleServiceImpl.class);

	@Override
	public Page findRoleToPage(Map<String, Object> condition, int index, int size) {
		logger.debug("角色分页查询");
		//1.通过条件查询记录数
		int count = roleMapper.countByCondition(condition);
		//2.通过条件查询数据
		//注意：开始位置=索引*每页记录数
		int start=index*size;
		List<Map<String, Object>> roles = roleMapper.findByConditionToPage(condition, start, size);
		
		Page page=new Page(index, size, count, roles);
		return page;
	}

	@Transactional
	@Override
	public Map<String, Object> addRole(Map<String, Object> entity) {
		int count = roleMapper.insert(entity);
		if(count>0) {
			return entity;
		}
		return null;
	}

	
	@Override
	public Map<String, Object> findRoleById(Object roleId) {
		return roleMapper.findById(roleId);
		 
	}

	@Transactional
	@Override
	public Map<String, Object> editRole(Map<String, Object> entity) {
		int count = roleMapper.updateForNotnull(entity);
		if (count>0) {
			return roleMapper.findById(entity.get("role_id"));
		}
		return null;
	}

	@Transactional
	@Override
	public int deleteRoleByIds(Object... roleIds) {
		return roleMapper.deleteById(roleIds);
	
	}

	@Override
	public List<Map<String, Object>> findAllRole() {
		return roleMapper.finaAll();
	}
	/**
	 * 查询结果分页
	 */
	@Override
	public Page searchRoleInfo(Map<String, Object> entity, Integer index, int size) {
		int count = roleMapper.countBySearch(entity);
		//2.通过条件查询数据
		//注意：开始位置=索引*每页记录数
		int start=index*size;
		List<Map<String, Object>> roles = roleMapper.searchByConditionToPage(entity, start, size);
		
		Page page=new Page(index, size, count, roles);
		return page;
	}
	
	

}
