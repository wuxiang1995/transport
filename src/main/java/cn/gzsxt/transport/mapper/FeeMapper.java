package cn.gzsxt.transport.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FeeMapper {
	@Insert(value="INSERT INTO tb_orderfee	(order_id, size_check_fee, weight_check_fee, size_order_fee, weight_order_fee,other_fee) VALUES (#{order_id}, #{size_check_fee}, #{weight_check_fee}, #{size_order_fee}, #{weight_order_fee}, #{other_fee})")
	int insert(Map<String, Object> entity);
	@Select(value="select * from tb_orderfee where order_id=#{order_id}")
	Map<String, Object> findByOrderId(Map<String, Object> entity);
}
