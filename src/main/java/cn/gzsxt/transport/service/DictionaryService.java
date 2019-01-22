package cn.gzsxt.transport.service;

import java.util.List;
import java.util.Map;

/**
 *  数据字典服务
 * @author ranger
 *
 */
public interface DictionaryService {

	/**
	 * 通过类型编码获得数据字典的值列表
	 * 
	 * @param typeCode
	 * @return
	 */
	public List<Map<String, Object>> findDictionaryByTypeCode(Object typeCode);
	
	

}
