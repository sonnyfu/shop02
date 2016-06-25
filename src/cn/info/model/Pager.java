package cn.info.model;

import java.util.List;
//model根据功能需要创建类（泛型可以装任何类型），（不一定对应表，但是从表的查询数据中得来），比如以下的分页技术
public class Pager<E> {//存放数据，再放到具体字段中
	private int pageIndex;//页码
	private int pageSize;//每页条数
	private int totalRecord;//总共多少记录
	private int totalPage;//总共多少页
	private List<E> dates;//放置具体数据的列表
	private int pageOffset;//分页开始值
	public int getPageOffset() {
		return pageOffset;
	}
	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<E> getDates() {
		return dates;
	}
	public void setDates(List<E> dates) {
		this.dates = dates;
	}

}
