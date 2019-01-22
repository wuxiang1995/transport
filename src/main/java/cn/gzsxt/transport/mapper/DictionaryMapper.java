package cn.gzsxt.transport.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DictionaryMapper {
	
	/**
	 * 通过类型编码获得数据字典的值
	 * @param code
	 * @return
	 */
	 @Select(value="SELECT * FROM tb_dictionary WHERE dic_type_code=#{code}")
	 List<Map<String,Object>> findByTypeCode(Object code);
	 
	 /**
	   *  通过字典类型编码与值确定唯一的记录
	  * @param value
	  * @param code
	  * @return
	  */
	 @Select(value="SELECT * FROM tb_dictionary WHERE dic_type_code=#{code} AND dic_value=#{value}")
	 Map<String,Object> findByTypeCodeAndValue(@Param("value") Object value,@Param("code") Object code);

}
