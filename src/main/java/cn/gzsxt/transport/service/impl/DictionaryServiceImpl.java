package cn.gzsxt.transport.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.transport.mapper.DictionaryMapper;
import cn.gzsxt.transport.service.DictionaryService;

@Service
public class DictionaryServiceImpl implements DictionaryService {
	
	@Autowired
	private DictionaryMapper dictionaryMapper;

	@Override
	public List<Map<String, Object>> findDictionaryByTypeCode(Object typeCode) {
		return dictionaryMapper.findByTypeCode(typeCode);
	}

}
