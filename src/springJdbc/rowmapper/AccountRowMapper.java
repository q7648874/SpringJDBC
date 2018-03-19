package springJdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import springJdbc.domain.Account;


public class AccountRowMapper implements RowMapper<Account>{

	@Override
	public Account mapRow(ResultSet rs, int i) throws SQLException {
		Account a = new Account();
		a.setId(rs.getInt(1));//数据库中 表的第一列
		a.setName(rs.getString(2));//数据库 中表的第二列
		a.setMoney(rs.getFloat("money")); //数据库中 表的第三列  直接指定列名
		System.out.println(i);
		return a;
	}

}
