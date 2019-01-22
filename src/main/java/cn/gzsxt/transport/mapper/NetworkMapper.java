package cn.gzsxt.transport.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface NetworkMapper {
	//@Insert(value="INSERT INTO tb_orderfee	(order_id, size_check_fee, weight_check_fee, size_order_fee, weight_order_fee,other_fee) VALUES (#{order_id}, #{size_check_fee}, #{weight_check_fee}, #{size_order_fee}, #{weight_order_fee}, #{other_fee})")
	//int insert(Map<String, Object> entity);
	@Select(value="select * from tb_network where id=#{id}")
	Map<String, Object> findById(Object id);
	@Select(value="select * from tb_network")
	List<Map<String, Object>> findNetworks();
	@Select(value="select * from tb_car where car_status=#{status} and network_id=#{netId}")
	List<Map<String, Object>> findCars(@Param(value = "status") Object status,@Param(value = "netId")Object netId);
}
