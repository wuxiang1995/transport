package cn.gzsxt.transport.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public class OrderProvider {
	public String findByConditionToPage(@Param("entity") Map<String, Object> entity ,@Param("start") int start,@Param("size") int size) {
		String sql="SELECT * FROM tb_order WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("order_id")!= null&&!entity.get("order_id").equals("")) {
				builder.append(" AND  order_id  like CONCAT('%',#{entity.order_id},'%')");
			}
			if(entity.get("order_status")!= null&&!entity.get("order_status").equals("")) {
				builder.append(" AND  order_status like CONCAT('%',#{entity.order_status},'%')");
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
		String sql="SELECT count(*) FROM tb_order WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("order_id")!= null&&!entity.get("order_id").equals("")) {
				builder.append(" AND  order_id  like CONCAT('%',#{order_id},'%')");
			}
			if(entity.get("order_status")!= null&&!entity.get("order_status").equals("")) {
				builder.append(" AND  order_status =#{order_status}");
			}
			if(entity.get("staff_no")!= null) {
				builder.append(" AND  staff_no = #{staff_no}");
			}
		}
		return builder.toString();
	}
	public String deleteById(Map<String, Object[]> orderIdsMap) {
		Object[] customerIds = orderIdsMap.get("array");
		String idsStr = Arrays.toString(customerIds);
		String sql="DELETE FROM tb_order WHERE order_id in ";
		StringBuilder builder=new StringBuilder(sql);		
		builder.append(idsStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["), '(');
		builder.setCharAt(builder.indexOf("]"), ')');
		return builder.toString();
	}
	public String update(Map<String, Object> entity) {
		String sql="UPDATE tb_order SET ";
		StringBuilder builder=new StringBuilder(sql);
		if(entity!=null) {
			if(entity.get("customer_id")!= null) {
				builder.append("customer_id=#{customer_id},");
			}
			if(entity.get("staff_no")!= null) {
				builder.append("staff_no=#{staff_no},");
			}
			if(entity.get("order_totalfee")!= null) {
				builder.append("order_totalfee=#{order_totalfee},");
			}
			if(entity.get("order_deliverytype")!= null) {
				builder.append("order_deliverytype=#{order_deliverytype},");
			}
			if(entity.get("remark")!= null) {
				builder.append("remark=#{remark},");
			}
			if(entity.get("order_destination")!= null) {
				builder.append("order_destination=#{order_destination},");
			}
			if(entity.get("order_createdate")!= null) {
				builder.append("order_createdate=#{order_createdate},");
			}
			if(entity.get("order_status")!= null) {
				builder.append("order_status=#{order_status},");
			}
			if(entity.get("order_prepay")!= null) {
				builder.append("order_prepay=#{order_prepay},");
			}
			if(entity.get("order_paytype")!= null) {
				builder.append("order_paytype=#{order_paytype},");
			}
			if(entity.get("order_postpay")!= null) {
				builder.append("order_postpay=#{order_postpay},");
			}
			if(entity.get("order_postpaydate")!= null) {
				builder.append("order_postpaydate=#{order_postpaydate},");
			}
			if(entity.get("order_currentpay")!= null) {
				builder.append("order_currentpay=#{order_currentpay},");
			}
			if(entity.get("order_recieverphone")!= null) {
				builder.append("order_recieverphone=#{order_recieverphone},");
			}
			if(entity.get("order_recievername")!= null) {
				builder.append("order_recievername=#{order_recievername},");
			}
			if(entity.get("order_repo")!= null) {
				builder.append("order_repo=#{order_repo},");
			}
			if(entity.get("order_recieveraddress")!= null) {
				builder.append("order_recieveraddress=#{order_recieveraddress},");
			}
			if(entity.get("car_id")!= null) {
				builder.append("car_id=#{car_id},");
			}
		}
		builder.delete(builder.length() - 1, builder.length());
		builder.append(" where order_id=#{order_id}");
		return builder.toString();
	}
}
