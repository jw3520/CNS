package community.vo;

import java.util.Date;

public class Vote {
	private int bno;
	private int mno;
	private String nickname;
	private String cname;
	private int value;
	private Date vote_date;
	
	public int getBno() {
		return bno;
	}
	public Vote setBno(int bno) {
		this.bno = bno;
		return this;
	}
	public int getMno() {
		return mno;
	}
	public Vote setMno(int mno) {
		this.mno = mno;
		return this;
	}
	public String getNickname() {
		return nickname;
	}
	public Vote setNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}
	public String getCname() {
		return cname;
	}
	public Vote setCname(String cname) {
		this.cname = cname;
		return this;
	}
	public int getValue() {
		return value;
	}
	public Vote setValue(int value) {
		this.value = value;
		return this;
	}
	public Date getVote_date() {
		return vote_date;
	}
	public Vote setVote_date(Date vote_date) {
		this.vote_date = vote_date;
		return this;
	}
}