package community.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import community.annotation.Component;
import community.bind.DataBinding;
import community.dao.CommunityDao;
import community.dao.MemberDao;
import community.vo.Member;

@Component("/auth/logIn")
public class LogInController implements Controller, DataBinding {
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
		Member member = (Member) model.get("member");
		HttpSession session = (HttpSession) model.get("session");
		session.removeAttribute("code");
		
		//로그인 후에 로그인 페이지로 접속을 시도할 경우
		if(session.getAttribute("logInfo") != null) {
			return "redirect:../main";
		}
		
		if(member.getEmail() == null) {
			HttpServletRequest request = (HttpServletRequest) model.get("request");
			session.setAttribute("referer", request.getHeader("referer"));
			return "auth_logIn.jsp";
		} else {
			Member logInfo = memberDao.exist(member.getEmail(), member.getPassword());

			if (logInfo != null) {
				session.setAttribute("logInfo", logInfo);
				session.setAttribute("myCommunity", communityDao.selectOne(logInfo.getCno()));
				
				//이전 페이지
				String referer = (String) session.getAttribute("referer");
				if(referer != null) {
					return "redirect:" + session.getAttribute("referer");
				} else {
					return "redirect:../main";
				}
			} else {
				model.put("fail", member.getEmail());
				return "auth_logIn.jsp";
			}
		}
	}
}