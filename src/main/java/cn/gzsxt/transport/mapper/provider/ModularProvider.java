package cn.gzsxt.transport.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ModularProvider {
	
	private static final Logger logger =LogManager.getLogger(ModularProvider.class);
	
	/**
	 * 模糊查询并且分页
	 * 注意事项：加上注解的对象，必须需要使用@Param绑定的key来获得值
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	public String findByConditionToPage(@Param("entity") Map<String, Object> entity ,@Param("start") int start,@Param("size") int size) {
		String sql="SELECT * FROM tb_modular WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("modular_id")!= null) {
				builder.append(" AND  modular_id=#{entity.modular_id}");
			}
			
			if(entity.get("modular_name")!= null) {
				builder.append(" AND  modular_name like CONCAT('%',#{entity.modular_name},'%')");
			}
		}
		builder.append(" LIMIT #{start},#{size}");
		
		
		logger.debug("模块模糊查询："+builder.toString());
		
		return builder.toString();
		
	}
	
	/**
	 * 根据条件统计记录数
	 * @param entity
	 * @return
	 */
	public String countByCondition(Map<String, Object> entity) {
		String sql="SELECT count(*) FROM tb_modular WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("modular_id")!= null) {
				builder.append(" AND  modular_id=#{modular_id}");
			}
			
			if(entity.get("modular_name")!= null) {
				builder.append(" AND  modular_name like CONCAT('%',#{modular_name},'%')");
			}
		}
		logger.debug("模块模糊查询："+builder.toString());
		
		return builder.toString();
	}
	
	/**
	 * 更新非空字段
	 * @param entity
	 * @return
	 */
	public String updateForNotnull(Map<String, Object> entity) {
		String sql = "UPDATE tb_modular SET ";
		StringBuilder builder = new StringBuilder(sql);
		if (entity.get("modular_name") != null) {
			builder.append("modular_name=#{modular_name},");
		}
		builder.delete(builder.length() - 1, builder.length());
		builder.append(" WHERE modular_id=#{modular_id}");
		logger.debug("模块更新："+builder.toString());
		return builder.toString();
	}
	
	/**
	 * 批量删除
	 * @param modularIdsMap
	 * @return
	 */
	public String deleteById(Map<String, Object[]> modularIdsMap) {
		Object[] modularIds = modularIdsMap.get("array");
		String idsStr = Arrays.toString(modularIds);
		String sql="DELETE FROM tb_modular WHERE modular_id in ";
		StringBuilder builder=new StringBuilder(sql);		
		builder.append(idsStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["), '(');
		builder.setCharAt(builder.indexOf("]"), ')');
	
		return builder.toString();
	}

}
