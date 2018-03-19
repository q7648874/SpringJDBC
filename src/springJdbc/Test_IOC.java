package springJdbc;

import java.beans.PropertyVetoException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import springJdbc.util.CglibFactory;
import springJdbc.util.ProxyFactory;

public class Test_IOC {
	
	
	//ģ��String��ܵ�����
	@Test
	public void testSpring(){
		//��������xml�ļ��Ĺ���
		springJdbc.util.ClassPathXmlApplicationContext app = new springJdbc.util.ClassPathXmlApplicationContext("applicationContext.xml");
		User u = (User) app.getBean("text");
		u.save();
	}
	//ģ��spring���������ǿ
	@Test
	public void testProxy(){
//		User proxy = new UserDao();  //��������
//		User proxy = (User) ProxyFactory.getProxy(UserDao.class);	//���� ��̬����ʽ��ǿ
		UserDao proxy = (UserDao) CglibFactory.getProxy(UserDao.class);	//���� ����ʵ�ַ�ʽ��ǿ
		proxy.save();
	}
	@Test
	public void test1() throws PropertyVetoException{
		//����һ��dataSource���� ���ӳض���
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql:///spring_day04");
//		dataSource.setUsername("root");
//		dataSource.setPassword("root");
		
		//����dbcp���ӳ�
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql:///spring_day04");
//		dataSource.setUsername("root");
//		dataSource.setPassword("root");
		//����c3p0
//		ComboPooledDataSource dataSource = new ComboPooledDataSource();
//		dataSource.setDriverClass("com.jdbc.mysql.Driver");
//		dataSource.setJdbcUrl("jdbc:mysql:///spring_day04");
//		dataSource.setUser("root");
//		dataSource.setPassword("root");
		
		//����һ��jdbcģ��
//		JdbcTemplate jdbc = new JdbcTemplate();
//		jdbc.setDataSource(dataSource);
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbc = (JdbcTemplate) ac.getBean("jdbc");
		
		//ִ�в���
		jdbc.update("insert into account values(null,?,?)", "adqwe",100000);
	}
}
