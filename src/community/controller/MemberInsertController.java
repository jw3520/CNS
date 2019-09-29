package community.controller;

import java.util.Map;

import community.annotation.Component;
import community.bind.DataBinding;
import community.dao.CommunityDao;
import community.dao.MemberDao;
import community.vo.Member;

@Component("/member/join")
public class MemberInsertController implements Controller, DataBinding {
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
		
		if(member.getEmail() == null) {
			model.put("commuList", communityDao.selectLIst());
			return "member_insert.jsp";
		} else {
			memberDao.insert(member);
			return "../auth/logIn";
		}
	}
}