package cn.gzsxt.transport.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CarProvider {
	
	private static final Logger logger =LogManager.getLogger(CarProvider.class);
	
	/**
	 * 模糊查询并且分页
	 * 注意事项：加上注解的对象，必须需要使用@Param绑定的key来获得值
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	public String findByConditionToPage(@Param("entity") Map<String, Object> entity ,@Param("start") int start,@Param("size") int size) {
		String sql="SELECT * FROM tb_car WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("car_no")!= null) {
				builder.append(" AND  car_no=#{entity.car_no}");
			}
			
			if(entity.get("id")!= null) {
				builder.append(" AND  id =#{entity.id}");
			}
			if(entity.get("network_id")!= null) {
				builder.append(" AND  network_id =#{entity.network_id}");
			}
			if(entity.get("car_status")!= null) {
				builder.append(" AND  car_status =#{entity.car_status}");
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
		String sql="SELECT count(*) FROM tb_car WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("car_no")!= null) {
				builder.append(" AND  car_no=#{entity.car_no}");
			}
			if(entity.get("id")!= null) {
				builder.append(" AND  id =#{entity.id}");
			}
			if(entity.get("network_id")!= null) {
				builder.append(" AND  network_id =#{entity.network_id}");
			}
			if(entity.get("car_status")!= null) {
				builder.append(" AND  car_status =#{entity.car_status}");
			}
		}
		logger.debug("模块模糊查询："+builder.toString());
		
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
		String sql="DELETE FROM tb_car WHERE id in ";
		StringBuilder builder=new StringBuilder(sql);		
		builder.append(idsStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["), '(');
		builder.setCharAt(builder.indexOf("]"), ')');
	
		return builder.toString();
	}
	public String updateForNotnull(Map<String, Object> entity) {
		String sql = "UPDATE tb_car SET ";
		StringBuilder builder = new StringBuilder(sql);
		if (entity.get("car_no") != null) {
			builder.append("car_no=#{car_no},");
		}
		if (entity.get("car_type") != null) {
			builder.append("car_type=#{car_type},");
		}
		if (entity.get("car_license") != null) {
			builder.append("car_license=#{car_license},");
		}
		if (entity.get("car_dun") != null) {
			builder.append("car_dun=#{car_dun},");
		}
		if (entity.get("network_id") != null) {
			builder.append("network_id=#{network_id},");
		}
		if (entity.get("car_remark") != null) {
			builder.append("car_remark=#{car_remark},");
		}
		if (entity.get("car_status") != null) {
			builder.append("car_status=#{car_status},");
		}
		builder.delete(builder.length() - 1, builder.length());
		builder.append(" WHERE id=#{id}");
		logger.debug("车辆更新："+builder.toString());
		return builder.toString();
	}

}
