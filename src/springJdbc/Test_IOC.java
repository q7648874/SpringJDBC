package springJdbc;

import java.beans.PropertyVetoException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import springJdbc.util.CglibFactory;
import springJdbc.util.ProxyFactory;

public class Test_IOC {
	
	
	//模拟String框架的容器
	@Test
	public void testSpring(){
		//创建解析xml文件的工厂
		springJdbc.util.ClassPathXmlApplicationContext app = new springJdbc.util.ClassPathXmlApplicationContext("applicationContext.xml");
		User u = (User) app.getBean("text");
		u.save();
	}
	//模拟spring框架事物增强
	@Test
	public void testProxy(){
//		User proxy = new UserDao();  //正常运行
//		User proxy = (User) ProxyFactory.getProxy(UserDao.class);	//基于 动态代理方式增强
		UserDao proxy = (UserDao) CglibFactory.getProxy(UserDao.class);	//基于 子类实现方式增强
		proxy.save();
	}
	@Test
	public void test1() throws PropertyVetoException{
		//创建一个dataSource对象 连接池对象
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql:///spring_day04");
//		dataSource.setUsername("root");
//		dataSource.setPassword("root");
		
		//配置dbcp连接池
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql:///spring_day04");
//		dataSource.setUsername("root");
//		dataSource.setPassword("root");
		//配置c3p0
//		ComboPooledDataSource dataSource = new ComboPooledDataSource();
//		dataSource.setDriverClass("com.jdbc.mysql.Driver");
//		dataSource.setJdbcUrl("jdbc:mysql:///spring_day04");
//		dataSource.setUser("root");
//		dataSource.setPassword("root");
		
		//创建一个jdbc模版
//		JdbcTemplate jdbc = new JdbcTemplate();
//		jdbc.setDataSource(dataSource);
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbc = (JdbcTemplate) ac.getBean("jdbc");
		
		//执行操作
		jdbc.update("insert into account values(null,?,?)", "adqwe",100000);
	}
}
