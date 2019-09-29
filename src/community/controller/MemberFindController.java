package community.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import community.annotation.Component;
import community.bind.DataBinding;
import community.dao.CommunityDao;
import community.dao.MemberDao;
import community.vo.Member;

@Component("/member/find")
public class MemberFindController implements Controller, DataBinding {
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
		return new Object[] {"code", String.class,
							 "email", String.class};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		String email = (String) model.get("email");//입력한 이메일
		String code = (String) model.get("code");//입력한 코드
		HttpSession session = (HttpSession) model.get("session");
		
		if(email == null) {
			return "member_find.jsp";
		} else {
			if(code.equals((String) session.getAttribute("code"))) {
				Member member = (Member) memberDao.selectOne_email(email);
				session.setAttribute("logInfo", member);
				session.setAttribute("myCommunity", communityDao.selectOne(member.getCno()));
				session.setAttribute("pwCheck", "find");
				session.removeAttribute("code");
				return "redirect:update";
			} else {
				model.put("fail", email);
				return "member_find.jsp";
			}
		}
	}
}