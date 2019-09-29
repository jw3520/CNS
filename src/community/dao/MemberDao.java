package community.dao;

import java.util.List;

import community.vo.Member;

public interface MemberDao {
	List<Member> selectList() throws Exception;
	int insert(Member member) throws Exception;
	int delete(int mno) throws Exception;
	Member selectOne(int mno) throws Exception;
	Member selectOne_email(String email) throws Exception;
	int update(Member member) throws Exception;
	Member exist(String email, String password) throws Exception;
	int emailCheck(String email) throws Exception;
	int nicknameCheck(String nickname) throws Exception;
}