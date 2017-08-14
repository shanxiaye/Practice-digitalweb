package com.digitalweb.util;

public class Page {
	private int all;
	private int curPage; 
	private int pageTotal;
	private int pageCount;
	private int start;
	private int end;
	private int pre;
	private int next;
	public Page() {
		this.curPage = 1;
		this.pageCount = 10;
	}
	public Page(int all, int curPage, int pageCount) {
		this.all = all;
		this.curPage = curPage;
		this.pageTotal = (all%pageCount)==0?all/pageCount:(all/pageCount+1);
		this.pageCount = pageCount;
		this.start = (curPage-1)*pageCount;
		this.end = curPage*pageCount>all?all-1:curPage*pageCount-1;
		this.pre = curPage-1>0?curPage-1:1;
		this.next = curPage+1<pageTotal?curPage+1:pageTotal;
	}
	public int getAll() {
		return all;
	}
	public void setAll(int all) {
		this.all = all;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
		this.start = (curPage-1)*pageCount;
		this.end = curPage*pageCount>all?all:curPage*pageCount;
		this.pre = curPage-1>0?curPage-1:1;
		this.next = curPage+1<pageTotal?curPage+1:pageTotal;
	}
	public int getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getPre() {
		return pre;
	}
	public void setPre(int pre) {
		this.pre = pre;
	}
	public int getNext() {
		return next;
	}
	public void setNext(int next) {
		this.next = next;
	}
	
}
