package cn.gzsxt.transport.service;

import java.util.List;
import java.util.Map;

import cn.gzsxt.transport.utils.Page;

public interface RoleService {
	
	/**
	 * 根据条件查询，分页返回数据
	 * @param condition 用于设置查询的条件
	 * @param index  当前索引
	 * @param size 每页记录数
	 * @return 返回分页对象
	 */
	 Page findRoleToPage(Map<String,Object> condition,int index,int size);
	
	/**
	 * 增加角色，
	 * @param entity
	 * @return 成功返回增加的记录，失败返回null
	 */
	 Map<String, Object> addRole(Map<String, Object> entity);
	
	 /**
	  * 通过角色编号查询角色记录
	  * @param roleId
	  * @return
	  */
	 Map<String, Object> findRoleById(Object roleId);
	 
	 /**
	  * 编辑角色，如果编辑成功返回编辑后的数据
	  * @param entity
	  * @return
	  */
	 Map<String, Object> editRole(Map<String, Object> entity);
	 
	 /**
	  * 通过角色编号删除角色，支持删除一个数组编号的角色
	  * @param roleIds
	  * @return
	  */
	int deleteRoleByIds(Object... roleIds);
	
	/**
	 * 查询所有的角色
	 * @return
	 */
	List<Map<String, Object>> findAllRole();
	/**
	 * 模糊查询列出
	 * @return
	 */
	Page searchRoleInfo(Map<String, Object> entity, Integer index, int size);

}

