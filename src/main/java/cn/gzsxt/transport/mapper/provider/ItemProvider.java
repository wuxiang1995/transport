package cn.gzsxt.transport.mapper.provider;

import java.util.Map;

public class ItemProvider {
	public String findItemById(String entity) {
		String sql="SELECT * FROM tb_orderitem WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
				builder.append(" AND  order_id like CONCAT('%',#{entity},'%')");
		}
		return builder.toString();
	}
	public String findPickById(String entity) {
		String sql="SELECT * FROM tb_pick WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
				builder.append(" AND  order_id like CONCAT('%',#{entity},'%')");
		}
		return builder.toString();
	}
	//" pick_type=#{pick_type}, =#{}, =#{pick_transid}, =#{pick_name},=#{pick_address},=${pick_phone},=${pick_fee} where order_id=#{order_id}"
	public String updatePick(Map<String, Object> entity) {
		String sql="update  tb_pick set ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("pick_type")!= null) {
				builder.append("pick_type=#{pick_type},");
			}
			if(entity.get("pick_transcompany")!= null) {
				builder.append("pick_transcompany=#{pick_transcompany},");
			}
			if(entity.get("pick_transid")!= null) {
				builder.append("pick_transid=#{pick_transid},");
			}
			if(entity.get("pick_name")!= null) {
				builder.append("pick_name=#{pick_name},");
			}
			if(entity.get("pick_address")!= null) {
				builder.append("pick_address=#{pick_address},");
			}
			if(entity.get("pick_phone")!= null) {
				builder.append("pick_phone=#{pick_phone},");
			}
			if(entity.get("pick_fee")!= null) {
				builder.append("pick_fee=#{pick_fee},");
			}
			if(entity.get("pick_realfee")!= null) {
				builder.append("pick_realfee=#{pick_realfee},");
			}
		}
		builder.delete(builder.length() - 1, builder.length());
		builder.append("where order_id=#{order_id}");
		return builder.toString();
	}
	public String updateItem(Map<String, Object> entity) {
		String sql="update  tb_orderitem set ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("item_name")!= null) {
				builder.append("item_name=#{item_name},");
			}
			if(entity.get("item_num")!= null) {
				builder.append("item_num=#{item_num},");
			}
			if(entity.get("item_pruefee")!= null) {
				builder.append("item_pruefee=#{item_pruefee},");
			}
			if(entity.get("item_totalfee")!= null) {
				builder.append("item_totalfee=#{item_totalfee},");
			}
			if(entity.get("item_length")!= null) {
				builder.append("item_length=#{item_length},");
			}
			if(entity.get("item_width")!= null) {
				builder.append("item_width=#{item_width},");
			}
			if(entity.get("item_height")!= null) {
				builder.append("item_height=#{item_height},");
			}
			if(entity.get("item_truesize")!= null) {
				builder.append("item_truesize=#{item_truesize},");
			}
			if(entity.get("item_size")!= null) {
				builder.append("item_size=#{item_size},");
			}
			if(entity.get("item_trueweight")!= null) {
				builder.append("item_trueweight=#{item_trueweight},");
			}
			if(entity.get("item_weight")!= null) {
				builder.append("item_weight=#{item_weight},");
			}
			if(entity.get("item_remark")!= null) {
				builder.append("item_remark=#{item_remark},");
			}
			if(entity.get("item_unit")!= null) {
				builder.append("item_unit=#{item_unit},");
			}
			if(entity.get("index0")!= null) {
				builder.append("index0=#{index0},");
			}
		}
		builder.delete(builder.length() - 1, builder.length());
		builder.append("where id=#{id}");
		return builder.toString();
	}
}
