package community.dao;

import java.util.List;

import community.vo.Reply;

public interface ReplyDao {
	List<Reply> selectList(int bno) throws Exception;
	int insert(Reply reply) throws Exception;
	int delete(int rno) throws Exception;
	int delete2(int rno) throws Exception;
	int totalCount(int bno) throws Exception;
}