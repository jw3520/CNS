package community.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import community.annotation.Component;
import community.vo.Vote;

@Component("voteDao")
public class MysqlVoteDao implements VoteDao {
	SqlSessionFactory sqlsessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlsessionFactory) {
		this.sqlsessionFactory = sqlsessionFactory;
	}

	@Override
	public String exist(int bno, int mno) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		Vote vote = new Vote().setBno(bno).setMno(mno);
		try {
			return sqlSession.selectOne("community.dao.MysqlVoteDao.exist", vote);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int insert(int bno, int mno, String nickname, String cname, int value) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		Vote voteInfo = new Vote().setBno(bno).setMno(mno).setNickname(nickname).setCname(cname).setValue(value);
		try {
			int count = sqlSession.insert("community.dao.MysqlVoteDao.insert", voteInfo);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int delete(int bno, int mno) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		Vote voteInfo = new Vote().setBno(bno).setMno(mno);
		try {
			int count = sqlSession.delete("community.dao.MysqlVoteDao.delete", voteInfo);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
}