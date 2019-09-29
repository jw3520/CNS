package community.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import community.annotation.Component;
import community.dao.BoardDao;
import community.dao.CommunityDao;
import community.dao.MemberDao;
import community.dao.ReplyDao;
import community.dao.VoteDao;
import community.dto.BoardInfo;
import community.dto.PageInfo;
import community.dto.ReplyInfo;
import community.util.Util;
import community.vo.Board;
import community.vo.Member;
import community.vo.Reply;

@Component("/ajax")
public class AjaxController implements Controller {
	MemberDao memberDao;
	CommunityDao communityDao;
	BoardDao boardDao;
	VoteDao voteDao;
	ReplyDao replyDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void setCommunityDao(CommunityDao communityDao) {
		this.communityDao = communityDao;
	}
	
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	public void setVoteDao(VoteDao voteDao) {
		this.voteDao = voteDao;
	}
	
	public void setReplyDao(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	public String execute(Map<String, Object> model) throws Exception {
		HttpServletResponse response = (HttpServletResponse) model.get("response");
		HttpSession session = (HttpSession) model.get("session");
		PrintWriter out = response.getWriter();
		String email = (String) model.get("email");
		String nickname = (String) model.get("nickname");
		String info = (String) model.get("info");
		String send_email = (String) model.get("send_email");
		String vote = (String) model.get("vote");
		String history = (String) model.get("history");
		PageInfo pageInfo = (PageInfo) model.get("pageInfo");
		Reply reply = (Reply) model.get("reply");
		String rno = (String) model.get("rno");
		String rno2 = (String) model.get("rno2");
		String reply_bno = (String) model.get("bno");
		
		if(email != null) {
			out.print(memberDao.emailCheck(email));
		} else if(nickname != null) {
			out.print(memberDao.nicknameCheck(nickname));
		} else if(info != null) {
			Member logInfo = memberDao.selectOne_email(info);
			session.setAttribute("logInfo", logInfo);
			session.setAttribute("myCommunity", communityDao.selectOne(logInfo.getCno()));
			out.print(logInfo.getPoint());
		} else if(send_email != null) {
			try {
				StringBuffer rnd_str = new StringBuffer();
				Random rnd = new Random();
				for (int i = 0; i < 10; i++) {
				    switch (rnd.nextInt(3)) {
				    case 0: // a-z
				    	rnd_str.append((char) ((int) (rnd.nextInt(26)) + 97));
				        break;
				    case 1: // A-Z
				    	rnd_str.append((char) ((int) (rnd.nextInt(26)) + 65));
				        break;
				    case 2: // 0-9
				    	rnd_str.append((rnd.nextInt(10)));
				        break;
				    }
				}
				
				session.setAttribute("code", rnd_str.toString());
				
				String to = send_email; // 수신자
				String msg = "코드 : " + rnd_str; // 내용
				String subject = "CNS : 아이디/비밀번호 찾기";// 제목
             
				// 메일보내기
				Util.postMail(to, subject, msg, "CNS(커뮤니티 네트워크 서비스)");
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		} else if(vote != null) {
			String order = vote.substring(0, 2);
			int bno = Integer.parseInt(vote.substring(2, vote.length()));
			Member logInfo = (Member) session.getAttribute("logInfo");
			int mno = logInfo.getMno();
			String nick = logInfo.getNickname();
			String cname = communityDao.selectOne(logInfo.getCno()).getCname();

			if(order.equals("iu")) {
				boardDao.increase_uc(bno);
				voteDao.insert(bno, mno, nick, cname, 1);
			} else if(order.equals("du")) {
				boardDao.decrease_uc(bno);
				voteDao.delete(bno, mno);
			} else if(order.equals("id")) {
				boardDao.increase_dc(bno);
				voteDao.insert(bno, mno, nick, cname, -1);
			} else if(order.equals("dd")) {
				boardDao.decrease_dc(bno);
				voteDao.delete(bno, mno);
			}
		} else if(history != null) {
			session.setAttribute("history", history);
		} else if(pageInfo != null) {
			List<Board> originals = boardDao.selectList(pageInfo);
			List<BoardInfo> list = new ArrayList<BoardInfo>();
			for (Board origin : originals) {
				list.add(new BoardInfo().setBno(origin.getBno())
										.setSubject(origin.getSubject())
										.setMno(origin.getMno())
										.setWriter(origin.getWriter())
										.setContent(origin.getContent())
										.setLink(origin.getLink())
										.setView_count(origin.getView_count())
										.setUp_count(origin.getUp_count())
										.setDown_count(origin.getDown_count())
										.setReg_date(Util.dateTypeChange2(origin.getReg_date()))
										.setTypeChangeDate(origin.getTypeChangeDate())
										.setUpdate_date(Util.dateTypeChange2(origin.getUpdate_date()))
										.setVoteCase(origin.getVoteCase())
										.setThumb_url(origin.getThumb())
										.setReply_count(replyDao.totalCount(origin.getBno())));
			}
			out.print(new Gson().toJson(list));
		} else if(reply != null) {
			out.print(replyDao.insert(reply));
		} else if(rno != null) {
			out.print(replyDao.delete(Integer.parseInt(rno)));
		} else if(rno2 != null) {
			out.print(replyDao.delete2(Integer.parseInt(rno2)));
		} else if(reply_bno != null) {
			List<Reply> originals = replyDao.selectList(Integer.parseInt(reply_bno));
			List<ReplyInfo> list = new ArrayList<ReplyInfo>();
			for (Reply origin : originals) {
				list.add(new ReplyInfo().setRno(origin.getRno())
										.setBno(origin.getBno())
										.setMaster(origin.getMaster())
										.setMno(origin.getMno())
										.setWriter(origin.getWriter())
										.setContent(origin.getContent())
										.setReg_date(Util.dateTypeChange2(origin.getReg_date()))
										.setTypeChangeDate(origin.getTypeChangeDate()));
			}
			out.print(new Gson().toJson(list));
		}
		out.flush();
		return null;
	}
}