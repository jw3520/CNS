package community.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import community.annotation.Component;
import community.bind.DataBinding;
import community.dao.BoardDao;
import community.dao.ReplyDao;
import community.dto.PageInfo;
import community.vo.Board;

@Component("/board/list")
public class BoardListController implements Controller, DataBinding {
	BoardDao boardDao;
	ReplyDao replyDao;
	
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	public void setReplyDao(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {"pageInfo", community.dto.PageInfo.class};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		List<Board> boardList = boardDao.selectList((PageInfo) model.get("pageInfo"));
		List<Integer> rcList = new ArrayList<>();
		if(boardList != null) {
			for (Board board : boardList) {
				rcList.add(replyDao.totalCount(board.getBno()));
			}
		}
		model.put("boardList", boardList);
		model.put("rcList", rcList);
		return "board_list.jsp";
	}
}