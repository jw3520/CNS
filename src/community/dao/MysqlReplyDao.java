package community.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import community.annotation.Component;
import community.vo.Reply;

@Component("replyDao")
public class MysqlReplyDao implements ReplyDao {
	SqlSessionFactory sqlsessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlsessionFactory) {
		this.sqlsessionFactory = sqlsessionFactory;
	}
	
	@Override
	public List<Reply> selectList(int bno) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		HashMap<String, String> param = new HashMap<>();
			param.put("bno", bno + "");
		try {
			return sqlSession.selectList("community.dao.MysqlReplyDao.selectList", param);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int insert(Reply reply) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			int count = sqlSession.insert("community.dao.MysqlReplyDao.insert", reply);
			if(count > 0 && reply.getMaster() == 0) {
				count = sqlSession.update("community.dao.MysqlReplyDao.update_master", reply);
			}
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int delete(int rno) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			int count = 0;
			int rereCount = sqlSession.selectOne("community.dao.MysqlReplyDao.rereCount", rno);
			if(--rereCount > 0) {
				count = sqlSession.update("community.dao.MysqlReplyDao.update_hide", rno);
			} else {
				count = sqlSession.delete("community.dao.MysqlReplyDao.delete", rno);
			}
			sqlSession.commit();
			return count > 0 ? rereCount : -1;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int delete2(int rno) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			int count = sqlSession.delete("community.dao.MysqlReplyDao.delete", rno);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int totalCount(int bno) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			return sqlSession.selectOne("community.dao.MysqlReplyDao.totalCount", bno);
		} finally {
			sqlSession.close();
		}
	}
}