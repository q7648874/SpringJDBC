package springJdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import springJdbc.domain.Account;


public class AccountRowMapper implements RowMapper<Account>{

	@Override
	public Account mapRow(ResultSet rs, int i) throws SQLException {
		Account a = new Account();
		a.setId(rs.getInt(1));//���ݿ��� ��ĵ�һ��
		a.setName(rs.getString(2));//���ݿ� �б�ĵڶ���
		a.setMoney(rs.getFloat("money")); //���ݿ��� ��ĵ�����  ֱ��ָ������
		System.out.println(i);
		return a;
	}

}
