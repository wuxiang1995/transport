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
import cn.gzsxt.transport.mapper.provider.RoleProvider;

@Mapper
public interface RoleMapper {

	/**
	 * 条件分页的的实现
	 * 注意事项：如果多个参数，那么必须要所有的参数加上注解
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = RoleProvider.class, method = "findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") int start,@Param("size") int size);
	
	/**
	 * 通过条件统计记录数
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = RoleProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);
	
	/**
	 *  增加角色
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_role (role_name,role_powers)	VALUES (#{role_name},#{role_powers})")
	@Options(useGeneratedKeys=true,keyProperty="role_id",keyColumn="role_id")
	int insert(Map<String, Object> entity);
	
	
	/**
	 * 通过角色编号查询角色记录
	 * @param roleId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_role WHERE role_id=#{roleId}")
	Map<String, Object> findById(Object roleId);
	
	/**
	 * 更新角色非空的字段
	 * @param entity
	 * @return
	 */
	@UpdateProvider(type=RoleProvider.class,method="updateForNotnull")
	int updateForNotnull(Map<String, Object> entity);
	
	/**
	 * 通过编号删除角色
	 * @param roleId
	 * @return
	 */
	@DeleteProvider(type=RoleProvider.class,method="deleteById")
	int deleteById(Object... roleIds);
	
	/**
	 * 查询所有的角色信息
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_role")
	List<Map<String, Object>> finaAll();
	/**
	 * 查询分页信息
	 * @return
	 */
	@SelectProvider(type = RoleProvider.class, method = "searchByConditionToPage")
	List<Map<String, Object>> searchByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") int start,@Param("size") int size);
	/**
	 * 查询角色的信息数量
	 * @return
	 */
	@Select(value="select count(*) from tb_role where role_name like CONCAT('%',#{search_thing},'%')")
	int countBySearch(Map<String, Object> entity);
}
