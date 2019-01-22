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

import cn.gzsxt.transport.mapper.provider.CarProvider;

@Mapper
public interface CarMapper {

	/**
	 * 条件分页的的实现
	 * 注意事项：如果多个参数，那么必须要所有的参数加上注解
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = CarProvider.class, method = "findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") int start,@Param("size") int size);
	
	/**
	 * 通过条件统计记录数
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = CarProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);
	
	/**
	 *  增加模块
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_modular (modular_name)	VALUES (#{modular_name})")
	@Options(useGeneratedKeys=true,keyProperty="modular_id",keyColumn="modular_id")
	int insert(Map<String, Object> entity);
	
	
	/**
	 * 通过模块编号查询模块记录
	 * @param modularId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_modular WHERE modular_id=#{modularId}")
	Map<String, Object> findById(Object modularId);
	
	/**
	 * 更新模块非空的字段
	 * @param entity
	 * @return
	 */
	@UpdateProvider(type=CarProvider.class,method="updateForNotnull")
	int update(Map<String, Object> entity);
	
	/**
	 * 通过编号删除模块
	 * @param modularId
	 * @return
	 */
	@DeleteProvider(type=CarProvider.class,method="deleteById")
	int deleteById(Object... modularIds);
	
	/**
	 * 查询所有模块的数据
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_car")
	List<Map<String, Object>> findAll();

}
