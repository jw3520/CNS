package community.dao;

import java.util.List;
import community.vo.Withdrawal;

public interface WithdrawalDao {
	List<Withdrawal> selectList() throws Exception;
	int insert(Withdrawal withdrawal) throws Exception;
	int delete(int wno) throws Exception;
}