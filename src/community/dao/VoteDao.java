package community.dao;

public interface VoteDao {
	String exist(int bno, int mno) throws Exception;
	int insert(int bno, int mno, String nickname, String cname, int value) throws Exception;
	int delete(int bno, int mno) throws Exception;
}