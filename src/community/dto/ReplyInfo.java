package community.dto;

public class ReplyInfo {
	private int rno;
	private int bno;
	private int master;
	private int mno;
	private String writer;
	private String content;
	private String reg_date;
	private String typeChangeDate;
	
	public int getRno() {
		return rno;
	}
	public ReplyInfo setRno(int rno) {
		this.rno = rno;
		return this;
	}
	public int getBno() {
		return bno;
	}
	public ReplyInfo setBno(int bno) {
		this.bno = bno;
		return this;
	}
	public int getMaster() {
		return master;
	}
	public ReplyInfo setMaster(int master) {
		this.master = master;
		return this;
	}
	public int getMno() {
		return mno;
	}
	public ReplyInfo setMno(int mno) {
		this.mno = mno;
		return this;
	}
	public String getWriter() {
		return writer;
	}
	public ReplyInfo setWriter(String writer) {
		this.writer = writer;
		return this;
	}
	public String getContent() {
		return content;
	}
	public ReplyInfo setContent(String content) {
		this.content = content;
		return this;
	}
	public String getReg_date() {
		return reg_date;
	}
	public ReplyInfo setReg_date(String reg_date) {
		this.reg_date = reg_date;
		return this;
	}
	public String getTypeChangeDate() {
		return typeChangeDate;
	}
	public ReplyInfo setTypeChangeDate(String typeChangeDate) {
		this.typeChangeDate = typeChangeDate;
		return this;
	}
}