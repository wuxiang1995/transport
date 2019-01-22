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
import org.apache.ibatis.annotations.UpdateProvider;
import cn.gzsxt.transport.mapper.provider.CustomerProvider;
@Mapper
public interface CustomerMapper {
	/**
	 * 插入客户记录
	 * @param entity 数据实体
	 * @return 插入影响行号
	 */
	@Insert(value="INSERT INTO tb_customer	(customer_name, customer_phone, customer_address, customer_email, customer_remark,customer_destination,customer_date,staff_no,customer_gender,customer_identity) VALUES (#{customer_name}, #{customer_phone}, #{customer_address}, #{customer_email}, #{customer_remark}, #{customer_destination}, #{customer_date}, #{staff_no}, #{customer_gender}, #{customer_identity})")
	@Options(useGeneratedKeys=true,keyProperty="customer_id",keyColumn="customer_id")
	int insert(Map<String, Object> entity);
	/**
	 * 条件分页的的实现
	 * 注意事项：如果多个参数，那么必须要所有的参数加上注解
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = CustomerProvider.class, method = "findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") int start,@Param("size") int size);
	/**
	 * 通过条件统计记录数
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = CustomerProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);
	
	@DeleteProvider(type=CustomerProvider.class,method="deleteById")
	int deleteById(Object... adminIds);
	
	@Select(value="SELECT *	FROM tb_customer  WHERE customer_id = #{customerId}")
	Map<String, Object> findCustomerById(Object customerId);
	
	@UpdateProvider(type=CustomerProvider.class,method="updateForNotnull")
	int updateForNotnull(Map<String, Object> entity);
	
	@SelectProvider(type = CustomerProvider.class, method = "findCustomers")
	List<Map<String, Object>> findCustomers(Map<String, Object> entity);
}
