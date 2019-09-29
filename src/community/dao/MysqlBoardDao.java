package community.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import community.annotation.Component;
import community.dto.PageInfo;
import community.vo.Board;

@Component("boardDao")
public class MysqlBoardDao implements BoardDao {
	SqlSessionFactory sqlsessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlsessionFactory) {
		this.sqlsessionFactory = sqlsessionFactory;
	}
	
	@Override
	public List<Board> selectList(PageInfo pageInfo) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		HashMap<String, String> param = new HashMap<>();
		  param.put("startNum", pageInfo.getStartNum() + "");
		  param.put("amount", pageInfo.getAmount() + "");
		  param.put("sort", pageInfo.getSort() != null ? pageInfo.getSort() : pageInfo.setSort("new").getSort());
		  param.put("type", pageInfo.getType());
		  param.put("keyword", pageInfo.getKeyword());
		try {
			return sqlSession.selectList("community.dao.MysqlBoardDao.selectList", param);
		} finally {
			/* 쿼리 로그 출력
			String sql = sqlSession.getConfiguration().getMappedStatement("community.dao.MysqlBoardDao.selectList").getBoundSql(param).getSql();
	        List<ParameterMapping> parameterMappings = sqlSession.getConfiguration().getMappedStatement("community.dao.MysqlBoardDao.selectList").getBoundSql(param).getParameterMappings();
	        
	        for (ParameterMapping parameterMapping : parameterMappings) {
	            String params = param.get(parameterMapping.getProperty());
	            sql = sql.replaceFirst("\\?", "'" + params + "'");
	        }
	 
	        System.out.println(param.toString());
	        System.out.println("sql : " + sql);
	        */
			sqlSession.close();
		}
	}

	@Override
	public int insert(Board board) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			int count = sqlSession.insert("community.dao.MysqlBoardDao.insert", board);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int delete(int bno) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			int count = sqlSession.delete("community.dao.MysqlBoardDao.delete", bno);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public Board selectOne(int bno) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			return sqlSession.selectOne("community.dao.MysqlBoardDao.selectOne", bno);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int update(Board board) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			int count = sqlSession.update("community.dao.MysqlBoardDao.update", board);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int increase_vc(int bno) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			int count = sqlSession.update("community.dao.MysqlBoardDao.increase_vc", bno);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public int increase_uc(int bno) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			int count = sqlSession.update("community.dao.MysqlBoardDao.increase_uc", bno);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public int decrease_uc(int bno) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			int count = sqlSession.update("community.dao.MysqlBoardDao.decrease_uc", bno);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int increase_dc(int bno) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			int count = sqlSession.update("community.dao.MysqlBoardDao.increase_dc", bno);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int decrease_dc(int bno) throws Exception {
		SqlSession sqlSession = sqlsessionFactory.openSession();
		try {
			int count = sqlSession.update("community.dao.MysqlBoardDao.decrease_dc", bno);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
}