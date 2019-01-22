package cn.gzsxt.transport.service;

import java.util.List;
import java.util.Map;

import cn.gzsxt.transport.utils.Page;

public interface ModularService {
	
	/**
	 * 根据条件查询，分页返回数据
	 * @param condition 用于设置查询的条件
	 * @param index  当前索引
	 * @param size 每页记录数
	 * @return 返回分页对象
	 */
	 Page findModularToPage(Map<String,Object> condition,int index,int size);
	
	/**
	 * 增加模块，
	 * @param entity
	 * @return 成功返回增加的记录，失败返回null
	 */
	 Map<String, Object> addModular(Map<String, Object> entity);
	
	 /**
	  * 通过模块编号查询模块记录
	  * @param modularId
	  * @return
	  */
	 Map<String, Object> findModularById(Object modularId);
	 
	 /**
	  * 编辑模块，如果编辑成功返回编辑后的数据
	  * @param entity
	  * @return
	  */
	 Map<String, Object> editModular(Map<String, Object> entity);
	 
	 /**
	  * 通过模块编号删除模块，支持删除一个数组编号的模块
	  * @param modularIds
	  * @return
	  */
	int deleteModularByIds(Object... modularIds);
	
	/**
	 * 查询所有的模块
	 * @return
	 */
	List<Map<String, Object>> findAllModular();

}
