package org.bbs.page;


public class Criteria {
	private int pageNum; //현재 페이지
	
	private int amount; // 한페이지당 보여질 게시물 갯수
	
	private int skip;
	
	public Criteria() {
		this(1, 10);
		this.skip = 0;
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.skip = (pageNum-1) * amount;
	}


	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.skip = (pageNum - 1) * this.amount;
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.skip = (this.pageNum - 1) * amount;
		this.amount = amount;
	}

	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}

	@Override
	public String toString() {
		return String.format("Criteria [pageNum=%s, amount=%s, skip=%s]", pageNum, amount, skip);
	}

	
}
