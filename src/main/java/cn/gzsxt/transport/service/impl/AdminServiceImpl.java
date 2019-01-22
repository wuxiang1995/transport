package cn.gzsxt.transport.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gzsxt.transport.mapper.AdminMapper;
import cn.gzsxt.transport.mapper.DictionaryMapper;
import cn.gzsxt.transport.mapper.ModularMapper;
import cn.gzsxt.transport.mapper.PowerMapper;
import cn.gzsxt.transport.mapper.RoleMapper;
import cn.gzsxt.transport.service.AdminService;
import cn.gzsxt.transport.utils.Page;

@Service
public class AdminServiceImpl implements AdminService {
	
	private static final Logger logger =LogManager.getLogger(AdminServiceImpl.class);
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private PowerMapper powerMapper;
	
	@Autowired
	private ModularMapper modularMapper;
	
	@Autowired
	private DictionaryMapper dictionaryMapper;

	@Transactional
	@Override
	public Map<String, Object> addAdmin(Map<String, Object> admin) {
		int count = adminMapper.insert(admin);
		if (count>0) {
			return admin;
		}
		return null;
	}
	
	@Override
	public Page findAdminToPage(Map<String, Object> condition, int index, int size) {
		logger.debug("管理员分页查询");
		//1.通过条件查询记录数
		int count = adminMapper.countByCondition(condition);
		//2.通过条件查询数据
		//注意：开始位置=索引*每页记录数
		int start=index*size;
		List<Map<String, Object>> admins = adminMapper.findByConditionToPage(condition, start, size);
		//1.将角色的数据也加入集合里面
		for (Map<String, Object> admin : admins) {
			//第一步：通过Admin的role_id获得角色信息
			Object roleId = admin.get("role_id");
			//第二步：通过role_id获得角色信息
			Map<String, Object> role = roleMapper.findById(roleId);
			//第三步：将角色的字段拼接到amdin里面
			//使用拼接在同一层的方式，不同表的字段名是不可以重复的
			//admin.putAll(role);
			//使用拼接在不同一层的方式，不同表的字段点名是可以重复的
			admin.put("role", role);
			
			//2.从数据字典获得管理员状态，管理员状态的类型编码type_code是1000
			 Map<String, Object> dic = dictionaryMapper.findByTypeCodeAndValue(admin.get("admin_status"), 1000);
			 admin.put("status", dic);
			 
			logger.debug("管理员拼接后的字段："+admin);
			
		}
	
		
		
		
		
		Page page=new Page(index, size, count, admins);
		return page;
	}



	
	@Override
	public Map<String, Object> findAdminById(Object adminId) {
		return adminMapper.findById(adminId);
		 
	}

	@Transactional
	@Override
	public Map<String, Object> editAdmin(Map<String, Object> entity) {
		int count = adminMapper.updateForNotnull(entity);
		if (count>0) {
			return adminMapper.findById(entity.get("admin_id"));
		}
		return null;
	}

	@Transactional
	@Override
	public int deleteAdminByIds(Object... adminIds) {
		return adminMapper.deleteById(adminIds);
	
	}

	@Override
	public Map<String, Object> loginAdmin(Map<String, Object> admin) {
		Object accountName = admin.get("admin_account");
		Object role_id = admin.get("role_id");
		//1.通过账号查询管理员信息
		Map<String, Object> resultAdmin = adminMapper.findByAccount(accountName,role_id);
		//如果找不账号，直接返回
		if (resultAdmin==null) {
			return null;
		}
		//2.验证密码
		if (admin.get("admin_pwd").equals(resultAdmin.get("admin_pwd"))) {
			//登录成功后
			//第一步：获得用户的角色信息
			Object roleId = resultAdmin.get("role_id");
			Map<String, Object> role = roleMapper.findById(roleId);
			resultAdmin.put("role", role);
			//第二步：获得权限的信息
			String rolePowers =(String) role.get("role_powers");
			List<Map<String, Object>> powers = powerMapper.findByIds((Object[])rolePowers.split(","));
			resultAdmin.put("powers", powers);
			//第三步：获得权限对应的模块信息
			List<Map<String, Object>> modulars=new ArrayList<Map<String,Object>>();
			for (Map<String, Object> power : powers) {
				Object modularId = power.get("modular_id");
				Map<String, Object> modular = modularMapper.findById(modularId);
	
				boolean flag=true;
				for (Map<String, Object> map : modulars) {
					//判断如果集合里面已经有相等的对象，就修改标识为false
//					if(map!=null&&!map.equals("")){
						if(modular.get("modular_id")==map.get("modular_id")) {
							flag=false;
							break;
						}
//					}
				}
				//必须标识变量为true才增加
				if(flag==true) {
					modulars.add(modular);
				}
			}
			
			
			
			resultAdmin.put("modulars", modulars);
			
			return resultAdmin;
		}
		
		return null;
	}
	@Transactional
	@Override
	public Map<String, Object> editAdminPassword(Map<String, Object> admin) {
		//注意事项：传入的参数必须只能有，admin_id和admin_pwd
		int count = adminMapper.updateForNotnull(admin);
		if (count>0) {
			//2.如果更新成功，通过admin_id,返回更新后的数据
			return adminMapper.findById(admin.get("admin_id"));
			
		}
		return null;
	}

	@Override
	public Page searchAdminInfo(Map<String, Object> entity, int index, int size) {
		int count = adminMapper.countBySearch(entity);
		int start=index*size;
		List<Map<String, Object>> admins = adminMapper.searchByConditionToPage(entity, start, size);
		//1.将角色的数据也加入集合里面
		for (Map<String, Object> admin : admins) {
			//第一步：通过Admin的role_id获得角色信息
			Object roleId = admin.get("role_id");
			//第二步：通过role_id获得角色信息
			Map<String, Object> role = roleMapper.findById(roleId);
			//第三步：将角色的字段拼接到amdin里面
			//使用拼接在同一层的方式，不同表的字段名是不可以重复的
			//admin.putAll(role);
			//使用拼接在不同一层的方式，不同表的字段点名是可以重复的
			admin.put("role", role);
			
			//2.从数据字典获得管理员状态，管理员状态的类型编码type_code是1000
			 Map<String, Object> dic = dictionaryMapper.findByTypeCodeAndValue(admin.get("admin_status"), 1000);
			 admin.put("status", dic);			
		}
		Page page=new Page(index, size, count, admins);
		return page;
	}

	@Override
	public List<Map<String, Object>> findAdmins() {
		List<Map<String, Object>> list =adminMapper.findAdmins();
		return list;
	}

}
