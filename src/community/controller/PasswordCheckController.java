package community.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import community.annotation.Component;
import community.bind.DataBinding;
import community.dao.MemberDao;
import community.vo.Member;

@Component("/auth/pwCheck")
public class PasswordCheckController implements Controller, DataBinding {
	MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "member", community.vo.Member.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member = (Member) model.get("member");//입력받은 값
		HttpSession session = (HttpSession) model.get("session");
		Member logInfo = (Member) session.getAttribute("logInfo");//세션의 로그인 정보 값
		
		//세션이 끊긴 상태에서 요청 시
		if(logInfo == null) {
			return "redirect:../main";
		}
		
		if(member.getEmail() == null) {
			model.put("email", logInfo.getEmail());
			return "auth_pwCheck.jsp";
		} else {
			if (member.getPassword().equals(logInfo.getPassword())) {
				session.setAttribute("pwCheck", true);
				return "redirect:../member/update";
			} else {
				model.put("fail", logInfo.getEmail());
				return "auth_pwCheck.jsp";
			}
		}
	}
}