package community.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import community.annotation.Component;
import community.bind.DataBinding;
import community.crawler.Crawler;
import community.crawler.Crawler_clien;
import community.crawler.Crawler_daumCafe;
import community.crawler.Crawler_dcinside;
import community.crawler.Crawler_etoland;
import community.crawler.Crawler_fmkorea;
import community.crawler.Crawler_humoruniv;
import community.crawler.Crawler_natePann;
import community.crawler.Crawler_ppomppu;
import community.crawler.Crawler_ruliweb;
import community.crawler.Crawler_theqoo;
import community.dao.BoardDao;
import community.dao.ReplyDao;
import community.dto.PageInfo;
import community.vo.Board;
import community.vo.Member;

@Component("/main")
public class MainController implements Controller, DataBinding {
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
		return new Object[] { "refresh", String.class,
							  "slide", String.class };
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		HttpSession session = (HttpSession) model.get("session");
		session.removeAttribute("pwCheck");
		session.removeAttribute("code");
		Member member = (Member) session.getAttribute("member");
		if(member != null)
			model.put("member", member);
		
		ArrayList<Crawler> crawlerList = new ArrayList<>();
		try {
			crawlerList.add(new Crawler_daumCafe());	//다음 카페 TOP, 카페인기글
			crawlerList.add(new Crawler_dcinside());	//디시인사이드, 초개념 갤러리
			crawlerList.add(new Crawler_ruliweb());		//루리웹, 많이 본 글/힛갤
			crawlerList.add(new Crawler_natePann());	//네이트 판, 카페인기글
			crawlerList.add(new Crawler_fmkorea());		//에펨코리아, 포텐터진 게시판
			new Crawler_fmkorea().getArticles();
			crawlerList.add(new Crawler_ppomppu());		//뽐뿌, HOT게시글
			crawlerList.add(new Crawler_theqoo());		//더쿠, HOT카테고리
			crawlerList.add(new Crawler_clien());		//클리앙, 모두의공원
			crawlerList.add(new Crawler_etoland());		//이토랜드, 인기게시판
			crawlerList.add(new Crawler_humoruniv());	//웃긴대학, 오늘의 베스트
		} catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			model.put("e_msg", exceptionAsString);
		}
		model.put("crawlerList", crawlerList);
		
		List<Board> boardList = boardDao.selectList(new PageInfo().setSort("hot"));
		model.put("sort", "hot");
		if(boardList.size() < 10) {
			boardList = boardDao.selectList(new PageInfo().setSort("top"));
			model.put("sort", "top");
		}
		
		List<Integer> rcList = new ArrayList<>();
		if(boardList != null) {
			for (Board board : boardList) {
				rcList.add(replyDao.totalCount(board.getBno()));
			}
		}
		
		model.put("boardList", boardList);
		model.put("rcList", rcList);
		return "main.jsp";
	}
}