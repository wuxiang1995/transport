package cn.gzsxt.transport.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PowerProvider {
	
	private static final Logger logger =LogManager.getLogger(PowerProvider.class);
	
	/**
	 * 模糊查询并且分页
	 * 注意事项：加上注解的对象，必须需要使用@Param绑定的key来获得值
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	public String findByConditionToPage(@Param("entity") Map<String, Object> entity ,@Param("start") int start,@Param("size") int size) {
		String sql="SELECT * FROM tb_power WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("power_id")!= null) {
				builder.append(" AND  power_id=#{entity.power_id}");
			}
			
			if(entity.get("power_name")!= null) {
				builder.append(" AND  power_name like CONCAT('%',#{entity.power_name},'%')");
			}
			
			if(entity.get("power_action")!= null) {
				builder.append(" AND  power_action like CONCAT('%',#{entity.power_action},'%')");
			}
			
			if(entity.get("power_parent")!= null) {
				builder.append(" AND  power_parent=#{entity.power_parent}");
			}
			
			if(entity.get("power_is_show")!= null) {
				builder.append(" AND  power_is_show=#{entity.power_is_show}");
			}
			
			if(entity.get("modular_id")!= null) {
				builder.append(" AND  modular_id=#{entity.modular_id}");
			}
			
		}
		builder.append(" LIMIT #{start},#{size}");
		
		
		logger.debug("权限模糊查询："+builder.toString());
		
		return builder.toString();
		
	}
	
	/**
	 * 根据条件统计记录数
	 * @param entity
	 * @return
	 */
	public String countByCondition(Map<String, Object> entity) {
		String sql="SELECT count(*) FROM tb_power WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("power_id")!= null) {
				builder.append(" AND  power_id=#{power_id}");
			}
			
			if(entity.get("power_name")!= null) {
				builder.append(" AND  power_name like CONCAT('%',#{power_name},'%')");
			}
			
			if(entity.get("power_action")!= null) {
				builder.append(" AND  power_action like CONCAT('%',#{power_action},'%')");
			}
			
			if(entity.get("power_parent")!= null) {
				builder.append(" AND  power_parent=#{power_parent}");
			}
			
			if(entity.get("power_is_show")!= null) {
				builder.append(" AND  power_is_show=#{power_is_show}");
			}
			
			if(entity.get("modular_id")!= null) {
				builder.append(" AND  modular_id=#{entity.modular_id}");
			}
			
			
		}
		logger.debug("权限模糊查询："+builder.toString());
		
		return builder.toString();
	}
	
	/**
	 * 更新非空字段
	 * @param entity
	 * @return
	 */
	public String updateForNotnull(Map<String, Object> entity) {
		String sql = "UPDATE tb_power SET ";
		StringBuilder builder = new StringBuilder(sql);
		if (entity.get("power_name") != null) {
			builder.append("power_name=#{power_name},");
		}
		
		if(entity.get("power_action")!= null) {
			builder.append("power_action=#{power_action},");
		}
		
		if(entity.get("power_parent")!= null) {
			builder.append("power_parent=#{power_parent},");
		}
		
		if(entity.get("power_is_show")!= null) {
			builder.append("power_is_show=#{power_is_show},");
		}
		
		if(entity.get("modular_id")!= null) {
			builder.append("modular_id=#{modular_id},");
		}
		
		builder.delete(builder.length() - 1, builder.length());
		builder.append(" WHERE power_id=#{power_id}");
		logger.debug("权限更新："+builder.toString());
		return builder.toString();
	}
	
	/**
	 * 批量删除
	 * @param powerIdsMap
	 * @return
	 */
	public String deleteById(Map<String, Object[]> powerIdsMap) {
		Object[] powerIds = powerIdsMap.get("array");
		String idsStr = Arrays.toString(powerIds);
		String sql="DELETE FROM tb_power WHERE power_id in ";
		StringBuilder builder=new StringBuilder(sql);		
		builder.append(idsStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["), '(');
		builder.setCharAt(builder.indexOf("]"), ')');
	
		return builder.toString();
	}
	
	/**
	 * 通过一个数组查询权限数据
	 * @param ids
	 * @return
	 */
	public String findByIds(Map<String, Object[]> powerIdsMap){
		Object[] powerIds = powerIdsMap.get("array");
		String idsStr = Arrays.toString(powerIds);
		String sql="SELECT * FROM tb_power WHERE power_id in ";
		StringBuilder builder=new StringBuilder(sql);		
		builder.append(idsStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["), '(');
		builder.setCharAt(builder.indexOf("]"), ')');
	
		return builder.toString();
	 }
	 
			/**
			 * 模糊查询并且分页
			 * 注意事项：加上注解的对象，必须需要使用@Param绑定的key来获得值
			 * @param entity
			 * @param start
			 * @param size
			 * @return
			 */
			public String searchByConditionToPage(@Param("entity") Map<String, Object> entity ,@Param("start") int start,@Param("size") int size) {
				String sql="SELECT * FROM tb_power a JOIN tb_modular b ON a.modular_id=b.modular_id WHERE ";
				StringBuilder builder=new StringBuilder(sql);
				builder.append("a.power_name LIKE CONCAT('%',#{entity.search_thing},'%') OR b.modular_name LIKE CONCAT('%',#{entity.search_thing},'%')");
				builder.append(" LIMIT #{start},#{size}");
				logger.debug("权限模糊查询："+builder.toString());
				
				return builder.toString();
				
			}
}
