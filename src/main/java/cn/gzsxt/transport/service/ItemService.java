package cn.gzsxt.transport.service;

import java.util.Map;

import org.springframework.stereotype.Service;


public interface ItemService {

	int addItems(Map<String, Object> entity);

	int addPick(Map<String, Object> entity);

	int editItems(Map<String, Object> entity);

	int editPick(Map<String, Object> entity);

}
