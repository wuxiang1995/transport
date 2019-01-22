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

import cn.gzsxt.transport.mapper.provider.PowerProvider;

@Mapper
public interface PowerMapper {

	/**
	 * 条件分页的的实现
	 * 注意事项：如果多个参数，那么必须要所有的参数加上注解
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = PowerProvider.class, method = "findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") int start,@Param("size") int size);
	
	/**
	 * 通过条件统计记录数
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = PowerProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);
	
	/**
	 *  增加权限
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_power (power_name, power_action, power_parent, power_is_show, modular_id)	VALUES (#{power_name},#{power_action},#{power_parent},#{power_is_show},#{modular_id})")
	@Options(useGeneratedKeys=true,keyProperty="power_id",keyColumn="power_id")
	int insert(Map<String, Object> entity);
	
	
	/**
	 * 通过权限编号查询权限记录
	 * @param powerId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_power WHERE power_id=#{powerId}")
	Map<String, Object> findById(Object powerId);
	
	/**
	 * 更新权限非空的字段
	 * @param entity
	 * @return
	 */
	@UpdateProvider(type=PowerProvider.class,method="updateForNotnull")
	int updateForNotnull(Map<String, Object> entity);
	
	/**
	 * 通过编号删除权限
	 * @param powerId
	 * @return
	 */
	@DeleteProvider(type=PowerProvider.class,method="deleteById")
	int deleteById(Object... powerIds);
	
	/**
	 * 查询所有权限
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_power")
	List<Map<String, Object>> findAll();
	
	/**
	 * 通过一组权限编号操作一组权限记录
	 * @param ids
	 * @return
	 */
	@SelectProvider(type=PowerProvider.class,method="findByIds")
	List<Map<String, Object>> findByIds(Object... powerIds);
	/**
	 * 模糊查询分页的的实现
	 * 注意事项：如果多个参数，那么必须要所有的参数加上注解
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = PowerProvider.class, method = "searchByConditionToPage")
	List<Map<String, Object>> searchByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") int start,@Param("size") int size);
	/**
	 * 查询所有的信息数量
	 * @return
	 */
	@Select(value="SELECT count(*) FROM tb_power a JOIN tb_modular b ON a.modular_id=b.modular_id WHERE a.power_name LIKE CONCAT('%',#{search_thing},'%') OR b.modular_name LIKE CONCAT('%',#{search_thing},'%')")
	int countBySearch(Map<String, Object> entity);
}
