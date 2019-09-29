package community.vo;

import java.util.Date;

import community.util.Util;

public class Reply {
	private int rno;
	private int bno;
	private int master;
	private int mno;
	private String writer;
	private String content;
	private Date reg_date;
	private int last_rno;
	
	public int getRno() {
		return rno;
	}
	public Reply setRno(int rno) {
		this.rno = rno;
		return this;
	}
	public int getBno() {
		return bno;
	}
	public Reply setBno(int bno) {
		this.bno = bno;
		return this;
	}
	public int getMaster() {
		return master;
	}
	public Reply setMaster(int master) {
		this.master = master;
		return this;
	}
	public int getMno() {
		return mno;
	}
	public Reply setMno(int mno) {
		this.mno = mno;
		return this;
	}
	public String getWriter() {
		return writer;
	}
	public Reply setWriter(String writer) {
		this.writer = writer;
		return this;
	}
	public String getContent() {
		return content;
	}
	public Reply setContent(String content) {
		this.content = content;
		return this;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public Reply setReg_date(Date reg_date) {
		this.reg_date = reg_date;
		return this;
	}
	public int getLast_rno() {
		return last_rno;
	}
	public Reply setLast_rno(int last_rno) {
		this.last_rno = last_rno;
		return this;
	}
	public String getTypeChangeDate() {
		return Util.dateTypeChange(reg_date.toString(), "yyyy-MM-dd hh:mm:ss");
	}
}