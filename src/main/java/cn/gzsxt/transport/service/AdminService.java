package cn.gzsxt.transport.service;

import java.util.List;
import java.util.Map;

import cn.gzsxt.transport.utils.Page;

public interface AdminService {
	
	/**
	 * 插入管理员
	 * @param admin
	 * @return
	 */
	Map<String, Object> addAdmin(Map<String, Object> admin);
	
	/**
	 * 管理员登录
	 * @param admin
	 * @return
	 */
	Map<String, Object> loginAdmin(Map<String, Object> admin);
	
	/**
	 * 修改管理员密码
	 * 如果修改成功返回，更新后的管理员
	 * 如果修改失败，返回null
	 * @param admin  传入的参数必须只能是:admin_pwd 和 admin_id,不能包括其他参数
	 * @return 返回修改后的管理员信息
	 */
	Map<String, Object> editAdminPassword(Map<String, Object> admin);
	
	/**
	 * 根据条件查询，分页返回数据
	 * @param condition 用于设置查询的条件
	 * @param index  当前索引
	 * @param size 每页记录数
	 * @return 返回分页对象
	 */
	 Page findAdminToPage(Map<String,Object> condition,int index,int size);
	

	
	 /**
	  * 通过管理员编号查询管理员记录
	  * @param adminId
	  * @return
	  */
	 Map<String, Object> findAdminById(Object adminId);
	 
	 /**
	  * 编辑管理员，如果编辑成功返回编辑后的数据
	  * @param entity
	  * @return
	  */
	 Map<String, Object> editAdmin(Map<String, Object> entity);
	 
	 /**
	  * 通过管理员编号删除管理员，支持删除一个数组编号的管理员
	  * @param adminIds
	  * @return
	  */
	int deleteAdminByIds(Object... adminIds);
	/**
	  * 模糊查询账号与账号名
	  * @param adminIds
	  * @return
	  */
	Page searchAdminInfo(Map<String, Object> entity,int index, int size);

	List<Map<String, Object>> findAdmins();

}
