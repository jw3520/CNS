package community.controller;

import java.util.Map;
import javax.servlet.http.HttpSession;
import community.annotation.Component;
import community.bind.DataBinding;
import community.dao.MemberDao;
import community.dao.WithdrawalDao;
import community.vo.Community;
import community.vo.Member;
import community.vo.Withdrawal;

@Component("/member/withdrawal")
public class MemberDeleteController implements Controller, DataBinding {
	MemberDao memberDao;
	WithdrawalDao withdrawalDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void setMemberDao(WithdrawalDao withdrawalDao) {
		this.withdrawalDao = withdrawalDao;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {"withdrawal", community.vo.Withdrawal.class};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		HttpSession session = (HttpSession) model.get("session");
		Withdrawal withdrawal = (Withdrawal) model.get("withdrawal");
		Member logInfo = (Member) session.getAttribute("logInfo");
		
		//세션이 끊긴 상태에서 요청 시
		if(logInfo == null) {
			return "redirect:../main";
		}
		
		if(withdrawal.getReason() == null) {
			return "member_delete.jsp";
		} else {
			withdrawal.setEmail(logInfo.getEmail())
					  .setNickname(logInfo.getNickname())
					  .setCname(((Community) session.getAttribute("myCommunity")).getCname())
					  .setReg_date(logInfo.getReg_date());
			
			memberDao.delete(logInfo.getMno());
			withdrawalDao.insert(withdrawal);
			session.invalidate();
			return "redirect:../main";
		}
	}
}