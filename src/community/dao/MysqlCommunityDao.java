package community.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import community.annotation.Component;
import community.vo.Community;

@Component("communityDao")
public class MysqlCommunityDao implements CommunityDao {
	SqlSessionFactory sqlsessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlsessionFactory) {
		this.sqlsessionFactory = sqlsessionFactory;
	}

	@Override
	public List<Community> selectLIst() throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			return sqlSession.selectList("community.dao.MysqlCommunityDao.selectList");
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public Community selectOne(int cno) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			return sqlSession.selectOne("community.dao.MysqlCommunityDao.selectOne", cno);
		} finally {
			sqlSession.close();
		}
	}
}