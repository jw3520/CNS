package community.vo;

public class Community {
	private int cno;
	private String cname;
	private String url;
	
	public int getCno() {
		return cno;
	}
	public Community setCno(int cno) {
		this.cno = cno;
		return this;
	}
	public String getCname() {
		return cname;
	}
	public Community setCname(String cname) {
		this.cname = cname;
		return this;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
