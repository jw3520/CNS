package community.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import community.annotation.Component;
import community.dao.MemberDao;

@Component("/auth/logOut")
public class LogOutController implements Controller {
	MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		HttpSession session = (HttpSession) model.get("session");
		session.invalidate();
		HttpServletRequest request = (HttpServletRequest) model.get("request");
		//이전 페이지
		return "redirect:" + request.getHeader("referer");
	}
}