package community.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import community.annotation.Component;
import community.bind.DataBinding;
import community.dao.BoardDao;
import community.dao.ReplyDao;
import community.dao.VoteDao;
import community.dto.PageInfo;
import community.vo.Community;
import community.vo.Member;

@Component("/board/read")
public class BoardReadController implements Controller, DataBinding {
	BoardDao boardDao;
	VoteDao voteDao;
	ReplyDao replyDao;
	
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	public void setVoteDao(VoteDao voteDao) {
		this.voteDao = voteDao;
	}
	
	public void setReplyDao(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {"bno", Integer.class,
							 "pageInfo", community.dto.PageInfo.class};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		if(boardDao.selectOne((Integer) model.get("bno")) != null) {
			HttpSession session = (HttpSession) model.get("session");
			Member logInfo = (Member) session.getAttribute("logInfo");
			Community community = (Community) session.getAttribute("myCommunity");
			
			if(session.getAttribute("history") != null) {
				model.put("history", (String) session.getAttribute("history"));
				session.removeAttribute("history");
			}
			
			if(logInfo != null) {
				model.put("logInfo", logInfo);
				model.put("vote", voteDao.exist((Integer) model.get("bno"), logInfo.getMno()));
				model.put("logInfo_cname", community.getCname());
			}
			
			boardDao.increase_vc((Integer) model.get("bno"));
			model.put("board", boardDao.selectOne((Integer) model.get("bno")));
			model.put("replyList", replyDao.selectList((Integer) model.get("bno")));
			model.put("reply_count", replyDao.totalCount((Integer) model.get("bno")));
			return "board_read.jsp";
		} else {
			return "redirect:list" + ((PageInfo) model.get("pageInfo")).getListLink("?");
		}
	}
}