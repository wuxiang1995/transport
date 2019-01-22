package cn.gzsxt.transport.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.gzsxt.transport.mapper.provider.ItemProvider;
import cn.gzsxt.transport.mapper.provider.OrderProvider;

@Mapper
public interface ItemMapper {
	@Insert(value="INSERT INTO tb_orderitem	(order_id, item_name, item_num, item_unit,item_pruefee, item_totalfee,item_remark,index0) VALUES (#{order_id}, #{item_name}, #{item_num},#{item_unit},#{item_pruefee}, #{item_totalfee}, #{item_remark},#{index0})")
	int insert(Map<String, Object> entity);
	
	@Insert(value="INSERT INTO tb_pick	(order_id, pick_type, pick_transcompany, pick_transid, pick_name,pick_address,pick_phone) VALUES (#{order_id}, #{pick_type}, #{pick_transcompany}, #{pick_transid}, #{pick_name}, #{pick_address},#{pick_phone})")
	int insertPick(Map<String, Object> entity);

	@SelectProvider(type=ItemProvider.class,method="findItemById")
	List<Map<String, Object>> findItemById(String orderId);
	
	@SelectProvider(type=ItemProvider.class,method="findPickById")
	Map<String, Object> findPickById(String orderId);

	@UpdateProvider(type=ItemProvider.class,method="updatePick")
	int updatePick(Map<String, Object> entity);
	@UpdateProvider(type=ItemProvider.class,method="updateItem")
	int update(Map<String, Object> entity);
}
