package community.dto;

public class ReplyPageInfo {
	private int pageNum;//현재 페이지 번호
	private int totalCount; //총 댓글 수
	private int amount;//한 페이지에 보여주는 댓글 수
	private int totalPage; //총 페이지 수
	private int amountPage; //한 그룹에 출력될 페이지 수
	private int startPage; //현재 페이지 그룹의 시작 페이지
	private int endPage; //현재 페이지 그룹의 마지막 페이지
	
	public ReplyPageInfo(int totalCount) {
		this(20, 5);
		this.totalPage = totalCount/amount + (totalCount % amount == 0 ? 0 : 1);
		this.setPageNum(totalPage);
	}
	
	public ReplyPageInfo(int amount, int amountPage) {
		this.amount = amount;
		this.amountPage = amountPage;
	}
	
	public String getListLink(String sign) {
		String pageNum = this.getPageNum() + "";
		String amount = this.getAmount() + "";
		String result = sign;
		
		if(pageNum != null && !pageNum.equals("")) {
			result += "pageNum=" + pageNum;
		} if(amount != null && !amount.equals("")) {
			result += "&amount=" + amount;
		}
		
		return result;
	}
	
	public int getStartNum() {
		return (pageNum - 1) * amount;
	}
	
	public int[] getPagingGroup() {
		int[] pagingGroup = new int[endPage - startPage + 1];
		for(int i = 0; i < pagingGroup.length; i++) {
			pagingGroup[i] = startPage + i;
		}
		return pagingGroup;
	}
	
	public int getNextPage() {
		return pageNum + 1;
	}
	
	public int getPreviousPage() {
		return pageNum - 1;
	}
	
	public boolean isFirstGroup() {
		return startPage == 1 ? true : false;
	}
	
	public boolean isLastGroup() {
		return endPage == totalPage ? true : false;
	}

	/*--------------------- Getter/Setter ---------------------*/
	public int getPageNum() {
		return pageNum;
	}
	public ReplyPageInfo setPageNum(int pageNum) {
		if(pageNum > totalPage) {
			pageNum = totalPage;
		} else if(pageNum < 1) {
			this.pageNum = 1;
		} else {
			this.pageNum = pageNum;
		}
		
		startPage = ((pageNum - 1) / amountPage) * amountPage + 1;
		endPage = startPage + amountPage - 1;
		
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		return this;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public ReplyPageInfo setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		return this;
	}
	public int getAmount() {
		return amount;
	}
	public ReplyPageInfo setAmount(int amount) {
		this.amount = amount;
		return this;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public ReplyPageInfo setTotalPage(int totalPage) {
		this.totalPage = totalPage;
		return this;
	}
	public int getAmountPage() {
		return amountPage;
	}
	public ReplyPageInfo setAmountPage(int amountPage) {
		this.amountPage = amountPage;
		return this;
	}
	public int getStartPage() {
		return startPage;
	}
	public ReplyPageInfo setStartPage(int startPage) {
		this.startPage = startPage;
		return this;
	}
	public int getEndPage() {
		return endPage;
	}
	public ReplyPageInfo setEndPage(int endPage) {
		this.endPage = endPage;
		return this;
	}
}