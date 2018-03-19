package springJdbc.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

//spring框架解析applicationContext.xml原理
public class ClassPathXmlApplicationContext {
	
	//ioc控制反转储存对象的容器
	private Map map = new HashMap<>();
	//解析xml文件     通过构造方法解析
	public ClassPathXmlApplicationContext(String xmlPath){
		//接卸xml文件的对象
		SAXReader sax = new SAXReader();
		try {
			Document read = sax.read("src/"+xmlPath);//设置xml文件路径
			List<Node> list = read.selectNodes("//bean");//将有bean标签的数据读取到list集合中
			for (Node node : list) {
				String id = node.valueOf("@id");//获得 标签属性为id的值
				String className = node.valueOf("@class");//获得 标签属性为class的值
				Object obj = Class.forName(className).newInstance(); //通过class的值通过反射获得实例对象
				map.put(id, obj);//将id作为key 反射对象为value 放到ioc容器中
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//通过id 在IOC 容器中 得到对应的对象
	public Object getBean(String id){
		return map.get(id);
	}
}
