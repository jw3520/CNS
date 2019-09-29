package community.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import community.annotation.Component;
import community.bind.DataBinding;
import community.dao.BoardDao;
import community.vo.Board;
import community.vo.Community;
import community.vo.Member;

@Component("/board/write")
public class BoardInsertController implements Controller, DataBinding {
	BoardDao boardDao;
	
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {"board", community.vo.Board.class};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Board board = (Board) model.get("board");
		
		if(board.getSubject() == null) {
			HttpSession session = (HttpSession) model.get("session");
			if(session.getAttribute("logInfo") != null && session.getAttribute("myCommunity") != null) {
				model.put("mno", (((Member) session.getAttribute("logInfo"))).getMno());
				model.put("writer", (((Member) session.getAttribute("logInfo"))).getNickname() + "[" + ((Community) session.getAttribute("myCommunity")).getCname() + "]");
				return "board_insert.jsp";
			} else {
				return "../auth/logIn";
			}
		} else {
			boardDao.insert(board);
			return "redirect:list";
		}
	}
}