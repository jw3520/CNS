package community.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import community.annotation.Component;
import community.bind.DataBinding;
import community.dao.CommunityDao;
import community.dao.MemberDao;
import community.vo.Member;

@Component("/member/update")
public class MemberUpdateController implements Controller, DataBinding {
	MemberDao memberDao;
	CommunityDao communityDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void setCommunityDao(CommunityDao communityDao) {
		this.communityDao = communityDao;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {"member", community.vo.Member.class};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member = (Member) model.get("member"); //입력받은 값
		HttpSession session = (HttpSession) model.get("session");
		Member logInfo = (Member) session.getAttribute("logInfo"); //세션의 로그인 정보 값
		
		if(session.getAttribute("pwCheck") == null) { //비밀번호 체크하지 않은 상태에서 요청 시
			return "redirect:../auth/pwCheck";
		} else {
			model.put("pwCheck", session.getAttribute("pwCheck"));
		}
		
		//세션이 끊긴 상태에서 요청 시
		if(logInfo == null) {
			return "redirect:../main";
		}
		
		if(member.getEmail() == null) {
			model.put("logInfo", logInfo);
			model.put("commuList", communityDao.selectLIst());
			return "member_update.jsp";
		} else {
			session.setAttribute("logInfo", member.setPoint(logInfo.getPoint()).setReg_date(logInfo.getReg_date()));
			session.setAttribute("myCommunity", communityDao.selectOne(member.getCno()));
			memberDao.update(member);
			session.removeAttribute("pwCheck");
			return "redirect:../main";
		}
	}
}