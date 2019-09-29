package community.vo;

import java.util.Date;

import community.util.Util;

public class Board {
	private int bno;
	private String subject;
	private int mno;
	private String writer;
	private String content;
	private String link;
	private int view_count;
	private int up_count;
	private int down_count;
	private Date reg_date;
	private Date update_date;
	
	public int getBno() {
		return bno;
	}
	public Board setBno(int bno) {
		this.bno = bno;
		return this;
	}
	public String getSubject() {
		return subject;
	}
	public Board setSubject(String subject) {
		this.subject = subject;
		return this;
	}
	public int getMno() {
		return mno;
	}
	public Board setMno(int mno) {
		this.mno = mno;
		return this;
	}
	public String getWriter() {
		return writer;
	}
	public Board setWriter(String writer) {
		this.writer = writer;
		return this;
	}
	public String getContent() {
		return content;
	}
	public Board setContent(String content) {
		this.content = content;
		return this;
	}
	public String getLink() {
		return link;
	}
	public Board setLink(String link) {
		this.link = link;
		return this;
	}
	public int getView_count() {
		return view_count;
	}
	public Board setView_count(int view_count) {
		this.view_count = view_count;
		return this;
	}
	public int getUp_count() {
		return up_count;
	}
	public Board setUp_count(int up_count) {
		this.up_count = up_count;
		return this;
	}
	public int getDown_count() {
		return down_count;
	}
	public Board setDown_count(int down_count) {
		this.down_count = down_count;
		return this;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public Board setReg_date(Date reg_date) {
		this.reg_date = reg_date;
		return this;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public Board setUpdate_date(Date update_date) {
		this.update_date = update_date;
		return this;
	}
	public String getTypeChangeDate() {
		return Util.dateTypeChange(reg_date.toString(), "yyyy-MM-dd hh:mm:ss");
	}
	public int getVoteCase() {
		return Util.voteCase(up_count, down_count);
	}
	public String getThumb() {
		if(content.indexOf("img") != -1) { //img 태그가 있을 경우
			int beginIndex = content.indexOf("src=") + 5;
			int endIndex = content.indexOf("style=") - 2;
			return content.substring(beginIndex, endIndex);
		} else if(content.indexOf("iframe") != -1) { //img 태그가 없고 iframe(유튜브) 태그가 있을 경우
			return "../css/images/board_youtube.png";
		} else { //텍스트만 있을 경우
			return "../css/images/board_picture.png";
		}
	}
}