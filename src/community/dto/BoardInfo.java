package community.dto;

public class BoardInfo {
	private int bno;
	private String subject;
	private int mno;
	private String writer;
	private String content;
	private String link;
	private int view_count;
	private int up_count;
	private int down_count;
	private String reg_date;
	private String typeChangeDate;
	private String update_date;
	private int voteCase;
	private String thumb_url;
	private int reply_count;
	
	public int getBno() {
		return bno;
	}
	public BoardInfo setBno(int bno) {
		this.bno = bno;
		return this;
	}
	public String getSubject() {
		return subject;
	}
	public BoardInfo setSubject(String subject) {
		this.subject = subject;
		return this;
	}
	public int getMno() {
		return mno;
	}
	public BoardInfo setMno(int mno) {
		this.mno = mno;
		return this;
	}
	public String getWriter() {
		return writer;
	}
	public BoardInfo setWriter(String writer) {
		this.writer = writer;
		return this;
	}
	public String getContent() {
		return content;
	}
	public BoardInfo setContent(String content) {
		this.content = content;
		return this;
	}
	public String getLink() {
		return link;
	}
	public BoardInfo setLink(String link) {
		this.link = link;
		return this;
	}
	public int getView_count() {
		return view_count;
	}
	public BoardInfo setView_count(int view_count) {
		this.view_count = view_count;
		return this;
	}
	public int getUp_count() {
		return up_count;
	}
	public BoardInfo setUp_count(int up_count) {
		this.up_count = up_count;
		return this;
	}
	public int getDown_count() {
		return down_count;
	}
	public BoardInfo setDown_count(int down_count) {
		this.down_count = down_count;
		return this;
	}
	public String getReg_date() {
		return reg_date;
	}
	public BoardInfo setReg_date(String reg_date) {
		this.reg_date = reg_date;
		return this;
	}
	public String getTypeChangeDate() {
		return typeChangeDate;
	}
	public BoardInfo setTypeChangeDate(String typeChangeDate) {
		this.typeChangeDate = typeChangeDate;
		return this;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public BoardInfo setUpdate_date(String update_date) {
		this.update_date = update_date;
		return this;
	}
	public int getVoteCase() {
		return voteCase;
	}
	public BoardInfo setVoteCase(int voteCase) {
		this.voteCase = voteCase;
		return this;
	}
	public String getThumb_url() {
		return thumb_url;
	}
	public BoardInfo setThumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
		return this;
	}
	public int getReply_count() {
		return reply_count;
	}
	public BoardInfo setReply_count(int reply_count) {
		this.reply_count = reply_count;
		return this;
	}
}