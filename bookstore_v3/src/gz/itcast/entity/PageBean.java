package gz.itcast.entity;

import java.util.List;

public class PageBean<T> {
	private List<T> data;//当前页数据
	private int firstPage;//首页
	private int prePage;//上一页
	private int nextPage;//下一页
	private int totalPage;//总页数、末页
	private int curPage;//当前页
	private int totalCount;//总记录数
	private int pageSize=6;//每页记录数
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public int getFirstPage() {
		return 1;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	//上一页算法：当前页为第一页，则为1，否则为当前页-1
	public int getPrePage() {
		return this.getCurPage()==this.getFirstPage()
				? 1
				: this.getCurPage()-1;
	}		
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	//下一页算法：当前页为末页，则为末页，否则为当前页+1
	public int getNextPage() {
		return this.getCurPage()==this.getTotalPage()
				? this.getTotalPage()
				: this.getCurPage()+1;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	//末页、总页数算法：如果总记录数%每页记录数==0
	//		               ？总记录数/每页记录数
	// 			  : 总记录数/每页记录数+1
	public int getTotalPage() {
		return this.getTotalCount()%this.getPageSize()==0
				? this.getTotalCount()/this.getPageSize()
				: this.getTotalCount()/this.getPageSize()+1;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	
}

