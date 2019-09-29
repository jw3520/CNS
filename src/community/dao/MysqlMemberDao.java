package community.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import community.vo.Member;
import community.annotation.Component;

@Component("memberDao")
public class MysqlMemberDao implements MemberDao {
	SqlSessionFactory sqlsessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlsessionFactory) {
		this.sqlsessionFactory = sqlsessionFactory;
	}

	@Override
	public List<Member> selectList() throws Exception {
		return null;
	}

	@Override
	public int insert(Member member) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			int count = sqlSession.insert("community.dao.MysqlMemberDao.insert", member);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int delete(int mno) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			int count = sqlSession.delete("community.dao.MysqlMemberDao.delete", mno);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public Member selectOne(int mno) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			return sqlSession.selectOne("community.dao.MysqlMemberDao.selectOne", mno);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public Member selectOne_email(String email) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			return sqlSession.selectOne("community.dao.MysqlMemberDao.selectOne_email", email);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int update(Member member) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			int count = sqlSession.update("community.dao.MysqlMemberDao.update", member);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public Member exist(String email, String password) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			return sqlSession.selectOne("community.dao.MysqlMemberDao.exist",
					new Member().setEmail(email).setPassword(password));
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int emailCheck(String email) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			return sqlSession.selectOne("community.dao.MysqlMemberDao.emailCheck", email);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int nicknameCheck(String nickname) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			return sqlSession.selectOne("community.dao.MysqlMemberDao.nicknameCheck", nickname);
		} finally {
			sqlSession.close();
		}
	}
}