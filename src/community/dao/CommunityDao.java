package community.dao;

import java.util.List;

import community.vo.Community;

public interface CommunityDao {
	List<Community> selectLIst() throws Exception;
	Community selectOne(int cno) throws Exception;
}
