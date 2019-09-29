package community.dto;

public class PageInfo {
	private int pageNum;
	private int amount;//한 페이지에 보여주는 게시물 수
	private String sort;
	private String type;
	private String keyword;
	
	public PageInfo() {
		this(1, 10);
	}
	
	public PageInfo(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String getListLink(String sign) {
		String pageNum = this.getPageNum() + "";
		String amount = this.getAmount() + "";
		String sort = this.getSort();
		String type = this.getType();
		String keyword = this.getKeyword();
		String result = sign;
		
		if(pageNum != null && !pageNum.equals("")) {
			result += "pageNum=" + pageNum;
		} if(amount != null && !amount.equals("")) {
			result += "&amount=" + amount;
		} if(sort != null && !sort.equals("")) {
			result += "&sort=" + sort;
		} if(type != null && !type.equals("")) {
			result += "&type=" + type;
		} if(keyword != null && !keyword.equals("")) {
			result += "&keyword=" + keyword;
		}
		
		return result;
	}
	
	public int getStartNum() {
		return (pageNum - 1) * amount;
	}

	public int getPageNum() {
		return pageNum;
	}
	public PageInfo setPageNum(int pageNum) {
		this.pageNum = pageNum;
		return this;
	}
	public int getAmount() {
		return amount;
	}
	public PageInfo setAmount(int amount) {
		this.amount = amount;
		return this;
	}
	public String getSort() {
		return sort;
	}
	public PageInfo setSort(String sort) {
		this.sort = sort;
		return this;
	}
	public String getType() {
		return type;
	}
	public PageInfo setType(String type) {
		this.type = type;
		return this;
	}
	public String getKeyword() {
		return keyword;
	}
	public PageInfo setKeyword(String keyword) {
		this.keyword = keyword;
		return this;
	}
}