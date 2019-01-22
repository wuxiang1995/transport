package cn.gzsxt.transport.utils;


import java.util.List;
import java.util.Map;

public class Page {
	
	//1.当前索引
	private int index=0;
	//2.每页记录数据
	private int pageSize;
	//3.总记录数
	private int count;
	//4.数据
	private List<Map<String, Object>> data;
	//5.总页数
	private int pageNum;
	

	public Page() {
		super();
	}

	public Page(int index, int pageSize, int count, List<Map<String, Object>> data) {
		this.index = index;
		this.pageSize = pageSize;
		this.count = count;
		this.data = data;
		//计算总页面
		if(count%pageSize==0) {
			this.pageNum=count/pageSize;
		}else {
			this.pageNum=count/pageSize+1;
		}
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<Map<String, Object>> getData() {
		return data;
	}
	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	/**
	 * 是否有上一页
	 * @return 如果有上一页返回true,否则返回false
	 */
	public boolean isPrevious() {
		if (index>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 是否有下一页
	 * @return
	 */
	public boolean isNext() {
		//为什么要减1，以为索引从0开始计算，pagenum是从1开始计算。所以需要减1
		if(index<(pageNum-1)) {
			return true;
		}
		return false;
	}
	
	
	
	

}
