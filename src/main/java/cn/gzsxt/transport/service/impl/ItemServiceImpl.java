package cn.gzsxt.transport.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.transport.mapper.ItemMapper;
import cn.gzsxt.transport.mapper.OrderMapper;
import cn.gzsxt.transport.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Override
	public int addItems(Map<String, Object> entity) {
		Map<String, Object> items=new HashMap<>();
		for (int j = 0; j < 10; j++) {
			if(entity.get("item_name"+j)!=null&&!entity.get("item_name"+j).equals("")) {
				items.put("order_id", entity.get("order_id"));
				items.put("item_name", entity.get("item_name"+j));
				items.put("item_remark", entity.get("item_remark"+j));
				items.put("index0", j);
				if(entity.get("item_num"+j)!=null&&!entity.get("item_num"+j).equals("")){
					items.put("item_num", Integer.valueOf(entity.get("item_num"+j).toString()));
				}
				if(entity.get("item_pruefee"+j)!=null&&!entity.get("item_pruefee"+j).equals("")){
					items.put("item_pruefee", Float.valueOf(entity.get("item_pruefee"+j).toString()));
				}
				if(entity.get("item_totalfee"+j)!=null&&!entity.get("item_totalfee"+j).equals("")){
					items.put("item_totalfee", Float.valueOf(entity.get("item_totalfee"+j).toString()));
				}
				int a=itemMapper.insert(items);
				if(a<1) {return 0;}
			}
		}
		return 1;
	}
	@Override
	public int addPick(Map<String, Object> entity) {
		int i=itemMapper.insertPick(entity);
		return i;
	}
	@Override
	public int editItems(Map<String, Object> entity) {
		int size=0;
		String order_id = null;
		if(entity.get("order_id")!=null) {
			List<Map<String, Object>> map = itemMapper.findItemById(entity.get("order_id").toString());
			size=map.size();
			order_id=entity.get("order_id").toString();
		}
		int b=0;
		Map<String, Object> map=new HashMap<>();
		for (int j = 0; j < size; j++) {
			if(entity.get("id"+j)!=null&&!entity.get("id"+j).equals("")) {
				map.put("id", entity.get("id"+j));
				if(entity.get("item_name"+j)!=null)
				map.put("item_name", entity.get("item_name"+j));
				if(entity.get("item_num"+j)!=null)
				map.put("item_num", entity.get("item_num"+j));
				if(entity.get("item_unit"+j)!=null)
				map.put("item_unit", entity.get("item_unit"+j));
				if(entity.get("item_pruefee"+j)!=null)
				map.put("item_pruefee", entity.get("item_pruefee"+j));
				if(entity.get("item_totalfee"+j)!=null) 
				map.put("item_totalfee", entity.get("item_totalfee"+j));
				if(entity.get("item_remark"+j)!=null) 
					map.put("item_remark", entity.get("item_remark"+j));
				b=itemMapper.update(map);
			}
		}
		int a=0;
		for (int i = size; i <10; i++) {
			if(entity.get("id"+i)!=null&&!entity.get("id"+i).equals("")) {
				map.put("id", entity.get("id"+i));
				if(entity.get("item_name"+i)!=null)
				map.put("item_name", entity.get("item_name"+i));
				if(entity.get("item_num"+i)!=null)
				map.put("item_num", entity.get("item_num"+i));
				if(entity.get("item_unit"+i)!=null)
				map.put("item_unit", entity.get("item_unit"+i));
				if(entity.get("item_pruefee"+i)!=null)
				map.put("item_pruefee", entity.get("item_pruefee"+i));
				if(entity.get("item_totalfee"+i)!=null) 
				map.put("item_totalfee", entity.get("item_totalfee"+i));
				if(entity.get("item_remark"+i)!=null) 
					map.put("item_remark", entity.get("item_remark"+i));
				entity.put("order_id", order_id);
				a=itemMapper.insert(map);
			}else {
				break;
			}
		}
		
		//int i=itemMapper.update(entity);
		return b*a;
	}
	@Override
	public int editPick(Map<String, Object> entity) {
		int i=itemMapper.updatePick(entity);
		return i;
	}

}
