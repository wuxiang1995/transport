package cn.gzsxt.transport.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RoleProvider {
	
	private static final Logger logger =LogManager.getLogger(RoleProvider.class);
	
	/**
	 * 模糊查询并且分页
	 * 注意事项：加上注解的对象，必须需要使用@Param绑定的key来获得值
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	public String findByConditionToPage(@Param("entity") Map<String, Object> entity ,@Param("start") int start,@Param("size") int size) {
		String sql="SELECT * FROM tb_role WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("role_id")!= null) {
				builder.append(" AND  role_id=#{entity.role_id}");
			}
			
			if(entity.get("role_name")!= null) {
				builder.append(" AND  role_name like CONCAT('%',#{entity.role_name},'%')");
			}
			
			if(entity.get("role_powers")!= null) {
				builder.append(" AND  role_powers like CONCAT('%',#{entity.role_powers},'%')");
			}
		}
		builder.append(" LIMIT #{start},#{size}");
		
		
		logger.debug("角色模糊查询："+builder.toString());
		
		return builder.toString();
		
	}
	
	/**
	 * 根据条件统计记录数
	 * @param entity
	 * @return
	 */
	public String countByCondition(Map<String, Object> entity) {
		String sql="SELECT count(*) FROM tb_role WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("role_id")!= null) {
				builder.append(" AND  role_id=#{role_id}");
			}
			
			if(entity.get("role_name")!= null) {
				builder.append(" AND  role_name like CONCAT('%',#{role_name},'%')");
			}
			
			if(entity.get("role_powers")!= null) {
				builder.append(" AND  role_powers like CONCAT('%',#{role_powers},'%')");
			}
		}
		logger.debug("角色模糊查询："+builder.toString());
		
		return builder.toString();
	}
	
	/**
	 * 更新非空字段
	 * @param entity
	 * @return
	 */
	public String updateForNotnull(Map<String, Object> entity) {
		String sql = "UPDATE tb_role SET ";
		StringBuilder builder = new StringBuilder(sql);
		if (entity.get("role_name") != null) {
			builder.append("role_name=#{role_name},");
		}
		if (entity.get("role_powers") != null) {
			builder.append("role_powers=#{role_powers},");
		}
		builder.delete(builder.length() - 1, builder.length());
		builder.append(" WHERE role_id=#{role_id}");
		logger.debug("角色更新："+builder.toString());
		return builder.toString();
	}
	
	/**
	 * 批量删除
	 * @param roleIdsMap
	 * @return
	 */
	public String deleteById(Map<String, Object[]> roleIdsMap) {
		Object[] roleIds = roleIdsMap.get("array");
		String idsStr = Arrays.toString(roleIds);
		String sql="DELETE FROM tb_role WHERE role_id in ";
		StringBuilder builder=new StringBuilder(sql);		
		builder.append(idsStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["), '(');
		builder.setCharAt(builder.indexOf("]"), ')');
	
		return builder.toString();
	}
	/**
	 * 批量查询
	 * @return
	 */
	public String searchByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") int start,@Param("size") int size) {
		String sqlString="select * from tb_role where role_name like CONCAT('%',#{entity.search_thing},'%') ";
		StringBuilder builder=new StringBuilder(sqlString);
		builder.append(" LIMIT #{start},#{size}");
		logger.debug("管理员分页模糊查询："+builder.toString());
		return builder.toString();
	}

}
