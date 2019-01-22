package cn.gzsxt.transport.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public class CustomerProvider {
	/**
	 * 模糊查询并且分页
	 * 注意事项：加上注解的对象，必须需要使用@Param绑定的key来获得值
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	public String findByConditionToPage(@Param("entity") Map<String, Object> entity ,@Param("start") int start,@Param("size") int size) {
		String sql="SELECT * FROM tb_customer WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("customer_id")!= null&&!entity.get("customer_id").equals("")) {
				builder.append(" AND  customer_id=#{entity.customer_id}");
			}
			
			if(entity.get("customer_name")!= null&&!entity.get("customer_name").equals("")) {
				builder.append(" AND  customer_name like CONCAT('%',#{entity.customer_name},'%')");
			}
			if(entity.get("customer_phone")!= null&&!entity.get("customer_phone").equals("")) {
				builder.append(" AND  customer_phone like CONCAT('%',#{entity.customer_phone},'%')");
			}
			if(entity.get("staff_no")!= null) {
				builder.append(" AND  staff_no = #{entity.staff_no}");
			}
		}
		builder.append(" LIMIT #{start},#{size}");
		return builder.toString();
	}
	/**
	 * 根据条件统计记录数
	 * @param entity
	 * @return
	 */
	public String countByCondition(Map<String, Object> entity) {
		String sql="SELECT count(*) FROM tb_customer WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("customer_id")!= null&&!entity.get("customer_id").equals("")) {
				builder.append(" AND  customer_id=#{customer_id}");
			}
			
			if(entity.get("customer_name")!= null&&!entity.get("customer_name").equals("")) {
				builder.append(" AND  customer_name like CONCAT('%',#{customer_name},'%')");
			}
			if(entity.get("customer_phone")!= null&&!entity.get("customer_phone").equals("")) {
				builder.append(" AND  customer_phone like CONCAT('%',#{customer_phone},'%')");
			}
			if(entity.get("staff_no")!= null) {
				builder.append(" AND  staff_no = #{staff_no}");
			}
		}
		return builder.toString();
	}
	/**
	 * 批量删除
	 * @param customerIdsMap
	 * @return
	 */
	public String deleteById(Map<String, Object[]> customerIdsMap) {
		Object[] customerIds = customerIdsMap.get("array");
		String idsStr = Arrays.toString(customerIds);
		String sql="DELETE FROM tb_customer WHERE customer_id in ";
		StringBuilder builder=new StringBuilder(sql);		
		builder.append(idsStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["), '(');
		builder.setCharAt(builder.indexOf("]"), ')');
		return builder.toString();
	}
	public String updateForNotnull(Map<String, Object> entity) {
		String sql = "UPDATE tb_customer SET ";
		StringBuilder builder = new StringBuilder(sql);
		if (entity.get("customer_name") != null) {
			builder.append("customer_name=#{customer_name},");
		}
		if (entity.get("customer_phone") != null) {
			builder.append("customer_phone=#{customer_phone},");
		}

		if (entity.get("customer_address") != null) {
			builder.append("customer_address=#{customer_address},");
		}

		if (entity.get("customer_email") != null) {
			builder.append("customer_email=#{customer_email},");
		}

		if (entity.get("customer_remark") != null) {
			builder.append("customer_remark=#{customer_remark},");
		}
		if (entity.get("customer_destination") != null) {
			builder.append("customer_destination=#{customer_destination},");
		}
		if (entity.get("customer_date") != null) {
			builder.append("customer_date=#{customer_date},");
		}
		if (entity.get("staff_no") != null) {
			builder.append("staff_no=#{staff_no},");
		}
		if (entity.get("customer_gender") != null) {
			builder.append("customer_gender=#{customer_gender},");
		}
		if (entity.get("customer_identity") != null) {
			builder.append("customer_identity=#{customer_identity},");
		}
		builder.delete(builder.length() - 1, builder.length());
		builder.append("WHERE customer_id=#{customer_id}");
		return builder.toString();
	}
	public String findCustomers(Map<String, Object> entity) {
		String sql="SELECT * FROM tb_customer WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("staff_no")!= null) {
				builder.append(" AND  staff_no = #{staff_no}");
			}
		}
		return builder.toString();
	}
	
}
