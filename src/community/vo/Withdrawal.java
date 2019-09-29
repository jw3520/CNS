package community.vo;

import java.util.Date;

public class Withdrawal {
	private int wno;
	private String email;
	private String nickname;
	private String cname;
	private String reason;
	private String detailed_reason;
	private Date reg_date;
	private Date withdrawal_date;
	
	public int getWno() {
		return wno;
	}
	public Withdrawal setWno(int wno) {
		this.wno = wno;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public Withdrawal setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getNickname() {
		return nickname;
	}
	public Withdrawal setNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}
	public String getCname() {
		return cname;
	}
	public Withdrawal setCname(String cname) {
		this.cname = cname;
		return this;
	}
	public String getReason() {
		return reason;
	}
	public Withdrawal setReason(String reason) {
		this.reason = reason;
		return this;
	}
	public String getDetailed_reason() {
		return detailed_reason;
	}
	public Withdrawal setDetailed_reason(String detailed_reason) {
		this.detailed_reason = detailed_reason;
		return this;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public Withdrawal setReg_date(Date reg_date) {
		this.reg_date = reg_date;
		return this;
	}
	public Date getWithdrawal_date() {
		return withdrawal_date;
	}
	public Withdrawal setWithdrawal_date(Date withdrawal_date) {
		this.withdrawal_date = withdrawal_date;
		return this;
	}
}