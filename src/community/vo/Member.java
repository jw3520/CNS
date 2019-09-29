package community.vo;

import java.util.Date;

public class Member {
	private int mno;
	private String email;
	private String password;
	private String nickname;
	private int cno;
	private Date reg_date;
	private int point;
	
	public int getMno() {
		return mno;
	}

	public Member setMno(int mno) {
		this.mno = mno;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Member setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public String getPassword() {
		return password;
	}

	public Member setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getNickname() {
		return nickname;
	}

	public Member setNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public int getCno() {
		return cno;
	}

	public Member setCno(int cno) {
		this.cno = cno;
		return this;
	}
	
	public int getPoint() {
		return point;
	}

	public Member setPoint(int point) {
		this.point = point;
		return this;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public Member setReg_date(Date reg_date) {
		this.reg_date = reg_date;
		return this;
	}
}