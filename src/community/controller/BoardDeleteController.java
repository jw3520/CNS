package community.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import community.annotation.Component;
import community.bind.DataBinding;
import community.dao.BoardDao;
import community.vo.Board;
import community.vo.Member;

@Component("/board/remove")
public class BoardDeleteController implements Controller, DataBinding {
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
		HttpSession session = (HttpSession) model.get("session");
		Member logInfo = (Member) session.getAttribute("logInfo");
		Board board = (Board) model.get("board");

		if(logInfo != null && logInfo.getMno() == board.getMno()) {
			boardDao.delete(board.getBno());
			return "redirect:list";
		}
		return "redirect:list";
	}
}