package cn.gzsxt.transport.service;

import java.util.List;
import java.util.Map;

import cn.gzsxt.transport.utils.Page;

public interface PowerService {
	
	/**
	 * 根据条件查询，分页返回数据
	 * @param condition 用于设置查询的条件
	 * @param index  当前索引
	 * @param size 每页记录数
	 * @return 返回分页对象
	 */
	 Page findPowerToPage(Map<String,Object> condition,int index,int size);
	
	/**
	 * 增加权限，
	 * @param entity
	 * @return 成功返回增加的记录，失败返回null
	 */
	 Map<String, Object> addPower(Map<String, Object> entity);
	
	 /**
	  * 通过权限编号查询权限记录
	  * @param powerId
	  * @return
	  */
	 Map<String, Object> findPowerById(Object powerId);
	 
	 /**
	  * 编辑权限，如果编辑成功返回编辑后的数据
	  * @param entity
	  * @return
	  */
	 Map<String, Object> editPower(Map<String, Object> entity);
	 
	 /**
	  * 通过权限编号删除权限，支持删除一个数组编号的权限
	  * @param powerIds
	  * @return
	  */
	int deletePowerByIds(Object... powerIds);
	
	/**
	 * 查询所有的权限
	 * @return
	 */
	List<Map<String, Object>> findAllPower();
	/**
	 * 模糊查询列出
	 * @return
	 */
	Page searchPowerInfo(Map<String, Object> entity, Integer index, int size);

}

