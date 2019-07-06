package cn.gzsxt.transport.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.transport.mapper.AdminMapper;
import cn.gzsxt.transport.mapper.CustomerMapper;
import cn.gzsxt.transport.mapper.FeeMapper;
import cn.gzsxt.transport.mapper.ItemMapper;
import cn.gzsxt.transport.mapper.NetworkMapper;
import cn.gzsxt.transport.mapper.OrderMapper;
import cn.gzsxt.transport.service.OrderService;
import cn.gzsxt.transport.utils.Page;
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private FeeMapper feeMapper;
	@Autowired
	private NetworkMapper networkMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Override
	public Map<String, Object> addOrder(Map<String, Object> entity) {
		SimpleDateFormat adf=new SimpleDateFormat("yyyy-MM-dd");
		String format = adf.format(new Date());
		entity.put("order_createdate", format);
		entity.put("order_status", 1);
		int i=orderMapper.insert(entity);
		if(i>0) {
			return entity;
		}
		return null;
	}

	@Override
	public Page findOrderToPage(Map<String, Object> condition, Integer index, int size) {
		//System.out.println(condition);
		int count = orderMapper.countByCondition(condition);
		//2.通过条件查询数据
		//注意：开始位置=索引*每页记录数
		int start=index*size;
		List<Map<String, Object>> orders = orderMapper.findByConditionToPage(condition, start, size);
		Map<String, Object> customer=new HashMap<>();
		Integer id=0;
		for (Map<String, Object> map : orders) {
			if(map.get("customer_id")!=null&&!map.get("customer_id").equals("")) {
				id=Integer.valueOf(map.get("customer_id").toString());
			}
			Map<String, Object> map2 = customerMapper.findCustomerById(id);
			map.put("customer_name", map2.get("customer_name"));
		}
		Page page=new Page(index, size, count, orders);
		return page;
	}

	@Override
	public int deleteCustomerByIds(Object... orderId) {
		return orderMapper.deleteById(orderId);
	}

	@Override
	public Map<String, Object> toOrderEdit(String orderId) {
		Map<String, Object> order=orderMapper.findById(orderId);
		List<Map<String, Object>> items= itemMapper.findItemById(orderId);
		Map<String, Object> pick = itemMapper.findPickById(orderId);
		List<Map<String, Object>> customers = customerMapper.findCustomers(order);
		order.put("items", items);
		order.put("pick", pick);
		order.put("customers", customers);
		return order;
	}

	@Override
	public Map<String, Object> editOrder(Map<String, Object> entity) {
		int i=orderMapper.update(entity);
		if(i>0) {
			return entity;
		}
		return null;
	}

	@Override
	public Map<String, Object> findById(String orderId) {
		Map<String, Object> map = orderMapper.findById(orderId);
		if(map!=null ) {
			Map<String, Object> customer = customerMapper.findCustomerById(map.get("customer_id"));
			Map<String, Object> orderPick = itemMapper.findPickById(orderId);
			List<Map<String, Object>> orderItems = itemMapper.findItemById(orderId);
			Map<String, Object> admin = adminMapper.findById(map.get("staff_no"));
			Map<String, Object> orderfee = feeMapper.findByOrderId(map);
			Float size=0f;
			Float weight=0f;
			Float total=0f;
			Float sum=0f;
			Float sum1=0f;
			Float sum2=0f;
			for (Map<String, Object> map2 : orderItems) {
				if(map2.get("item_size")!=null&&map2.get("item_weight")!=null&&map2.get("item_totalfee")!=null) {
					size=Float.valueOf(map2.get("item_size").toString());
					weight=Float.valueOf(map2.get("item_weight").toString());
					total=Float.valueOf(map2.get("item_totalfee").toString());
					sum=sum+size;
					sum1=sum1+weight;
					sum2=sum2+total;
				}
			}
			List<Map<String, Object>> cars=null;
			if(map.get("net_id")!=null) {
				cars = networkMapper.findCars(0, map.get("net_id"));
			}
			List<Map<String, Object>> networks = networkMapper.findNetworks();
			map.put("cars", cars);
			map.put("networks", networks);
			map.put("totalSize", sum);
			map.put("totalWeight", sum1);
			map.put("totalWorth", sum2);
			map.put("customer", customer);
			map.put("orderPick", orderPick);
			map.put("orderItems", orderItems);
			map.put("admin", admin);
			map.put("orderFee", orderfee);
			return map;}
		else {
			return null;
		}
	}

	@Override
	public Map<String, Object> orderInstore(Map<String, Object> entity) {
		entity.put("order_status", 2);
		int i=0;
		if(entity.get("pick_realfee")!=null&&!entity.get("pick_realfee").equals(""))
		i= itemMapper.updatePick(entity);
		int a = orderMapper.update(entity);
		int c=feeMapper.insert(entity);
		int b=0;
		Map<String, Object> map=new HashMap<>();
		for (int j = 0; j < 10; j++) {
			if(entity.get("id"+j)!=null&&!entity.get("id"+j).equals("")) {
				map.put("id", entity.get("id"+j));
				if(entity.get("item_length"+j)!=null)
				map.put("item_length", entity.get("item_length"+j));
				if(entity.get("item_width"+j)!=null)
				map.put("item_width", entity.get("item_width"+j));
				if(entity.get("item_height"+j)!=null)
				map.put("item_height", entity.get("item_height"+j));
				if(entity.get("item_truesize"+j)!=null)
				map.put("item_truesize", entity.get("item_truesize"+j));
				if(entity.get("item_trueweight"+j)!=null) {
				map.put("item_trueweight", entity.get("item_trueweight"+j));}
				b=itemMapper.update(map);
			}
		}
		if(i*a*b*c>0) {
			return entity;
		}
		return null;
	}

	@Override
	public Map<String, Object> orderQuote(Map<String, Object> entity) {
		SimpleDateFormat adf=new SimpleDateFormat("yyyy-MM-dd");
		String format = adf.format(new Date());
		SimpleDateFormat adf1=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		String format1 = adf1.format(new Date());
		entity.put("order_status", 3);
		entity.put("order_prepaydate", format);
		entity.put("detiltime", format1);
		int d=orderMapper.insertdetil(entity);
		int i = itemMapper.updatePick(entity);
		int c=feeMapper.insert(entity);
		//Map<String, Object> feeresult = feeMapper.findByOrderId(entity);
		float order_fee=0f;
		float pick_fee=0f;
		float sui_fee=0f;
		float tiji_fee=0f;
		float weight_fee=0f;
		if (entity.get("order_fee")!=null) {
			order_fee=Float.valueOf(entity.get("order_fee").toString());
		}
		if(entity.get("order_id")!=null&&!entity.get("order_id").equals("")) {
			Map<String, Object> pick = itemMapper.findPickById(entity.get("order_id").toString());
			pick_fee=Float.valueOf(pick.get("pick_fee").toString());
			Map<String, Object> order = orderMapper.findById(entity.get("order_id").toString());
			if (order.get("order_destination")=="SPG"&&order.get("order_destination")=="海运"&&entity.get("totalWorth")!=null&&!entity.get("totalWorth").equals("")) {
				float fee=Float.valueOf(entity.get("totalWorth").toString());
				sui_fee= (float) (fee* 0.07);
			}
		}
		int b=0;
		Map<String, Object> map=new HashMap<>();
		for (int j = 0; j < 10; j++) {
			if(entity.get("id"+j)!=null&&!entity.get("id"+j).equals("")) {
				map.put("id", entity.get("id"+j));
				if(entity.get("item_size"+j)!=null&&entity.get("size_order_fee")!=null) {
					//float a=Float.valueOf(entity.get("item_size"+j).toString())*Float.valueOf(entity.get("size_order_fee").toString());
					map.put("item_size", entity.get("item_size"+j));
					//map.put("item_totalfee", a);
				}
				if(entity.get("item_weight"+j)!=null)
				map.put("item_weight", entity.get("item_weight"+j));
				b=itemMapper.update(map);
			}
		}
		if(entity.get("size_order_fee")!=null&&entity.get("totalSize")!=null) {
			float total=Float.valueOf(entity.get("totalSize").toString());
			tiji_fee=Float.valueOf(entity.get("size_order_fee").toString())*total;
			if(entity.get("totalWeight")!=null&&entity.get("weight_order_fee")!=null) {
				//orderdd.totalSize * 200 * orderdd.orderFee.weight_order_fee
				float weighttotal=Float.valueOf(entity.get("totalWeight").toString());
				float wof=Float.valueOf(entity.get("weight_order_fee").toString());
				if(total*50f >weighttotal) {
					weight_fee=total*50f*wof;
				}
				weight_fee=wof*weighttotal;
			}
		}
		entity.put("order_totalfee", order_fee+pick_fee+sui_fee+tiji_fee+weight_fee);
		int a = orderMapper.update(entity);
		if(a*i*b*c>0) {
			return entity;
		}
		return null;
	}

	@Override
	public Map<String, Object> orderReview(Map<String, Object> entity) {
		
		int a = orderMapper.update(entity);
		if(a>0) {
			return entity;
		}
		return null;
	}

	@Override
	public Map<String, Object> orderFahuo(Map<String, Object> entity) {
		int a = orderMapper.update(entity);
		Map<String, Object> detil = orderMapper.findDetil(entity);
		if(detil.get("zhandian")!=null&&detil.get("detil_status")!=null) {
			entity.put("zhandian", detil.get("zhandian"));
			entity.put("detil_status", "发货");
			entity.put("detil_remark", "离开"+detil.get("zhandian")+"仓库中心");
		}
		int i = orderMapper.insertdetil(entity);
		if(a*i>0) {
			return entity;
		}
		return null;
	}

	@Override
	public Map<String, Object> addorderWuliu(Map<String, Object> entity) {
		int a = orderMapper.update(entity);
		int i = orderMapper.insertdetil(entity);
		if(i*a>0) {
			return entity;
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> findCars(Integer valueOf) {
		return networkMapper.findCars(0, valueOf);
	}

}
