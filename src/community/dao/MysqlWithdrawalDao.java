package community.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import community.annotation.Component;
import community.vo.Withdrawal;

@Component("withdrawalDao")
public class MysqlWithdrawalDao implements WithdrawalDao {
	SqlSessionFactory sqlsessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlsessionFactory) {
		this.sqlsessionFactory = sqlsessionFactory;
	}

	@Override
	public List<Withdrawal> selectList() throws Exception {
		return null;
	}

	@Override
	public int insert(Withdrawal withdrawal) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			int count = sqlSession.insert("community.dao.MysqlWithdrawalDao.insert", withdrawal);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int delete(int wno) throws Exception {
		return 0;
	}
}