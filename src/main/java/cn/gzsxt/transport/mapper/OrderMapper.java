package cn.gzsxt.transport.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.gzsxt.transport.mapper.provider.CustomerProvider;
import cn.gzsxt.transport.mapper.provider.OrderProvider;

@Mapper
public interface OrderMapper {
	@Insert(value="INSERT INTO tb_order	(order_id, customer_id, staff_no, order_totalfee, remark,order_deliverytype,order_destination,order_createdate,order_status,order_paytype,order_recieveraddress,order_recievername,order_recieverphone,net_id) VALUES (#{order_id}, #{customer_id}, #{staff_no}, #{order_totalfee}, #{remark}, #{order_deliverytype}, #{order_destination}, #{order_createdate}, #{order_status}, #{order_paytype}, #{order_recieveraddress}, #{order_recievername}, #{order_recieverphone},#{net_id})")
	@Options(useGeneratedKeys=true,keyProperty="order_id",keyColumn="order_id")
	int insert(Map<String, Object> entity);
	
	@Insert(value="INSERT INTO tb_orderdetil (order_id, detiltime, detil_remark, zhandian, detil_status) VALUES (#{order_id}, #{detiltime}, #{detil_remark}, #{zhandian}, #{detil_status})")
	int insertdetil(Map<String, Object> entity);
	
	@Select(value="select * from tb_orderdetil where order_id=#{order_id}")
	Map<String, Object> findDetil(Map<String, Object> entity);

	@SelectProvider(type = OrderProvider.class, method = "findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") int start,@Param("size") int size);
	
	@SelectProvider(type = OrderProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);
	@DeleteProvider(type=OrderProvider.class,method="deleteById")
	int deleteById(Object[] orderId);

	@Select(value="SELECT *	FROM tb_order  WHERE order_id = #{orderId}")
	Map<String, Object> findById(String orderId);
	
	@UpdateProvider(type = OrderProvider.class, method = "update")
	int update(Map<String, Object> entity);
	
}
