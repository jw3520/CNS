package community.dao;

import java.util.List;

import community.dto.PageInfo;
import community.vo.Board;

public interface BoardDao {
	List<Board> selectList(PageInfo pageInfo) throws Exception;
	int insert(Board board) throws Exception;
	int delete(int bno) throws Exception;
	Board selectOne(int bno) throws Exception;
	int update(Board board) throws Exception;
	int increase_vc(int bno) throws Exception;
	int increase_uc(int bno) throws Exception;
	int decrease_uc(int bno) throws Exception;
	int increase_dc(int bno) throws Exception;
	int decrease_dc(int bno) throws Exception;
}