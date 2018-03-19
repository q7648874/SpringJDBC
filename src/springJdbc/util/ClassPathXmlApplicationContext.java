package springJdbc.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

//spring��ܽ���applicationContext.xmlԭ��
public class ClassPathXmlApplicationContext {
	
	//ioc���Ʒ�ת������������
	private Map map = new HashMap<>();
	//����xml�ļ�     ͨ�����췽������
	public ClassPathXmlApplicationContext(String xmlPath){
		//��жxml�ļ��Ķ���
		SAXReader sax = new SAXReader();
		try {
			Document read = sax.read("src/"+xmlPath);//����xml�ļ�·��
			List<Node> list = read.selectNodes("//bean");//����bean��ǩ�����ݶ�ȡ��list������
			for (Node node : list) {
				String id = node.valueOf("@id");//��� ��ǩ����Ϊid��ֵ
				String className = node.valueOf("@class");//��� ��ǩ����Ϊclass��ֵ
				Object obj = Class.forName(className).newInstance(); //ͨ��class��ֵͨ��������ʵ������
				map.put(id, obj);//��id��Ϊkey �������Ϊvalue �ŵ�ioc������
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//ͨ��id ��IOC ������ �õ���Ӧ�Ķ���
	public Object getBean(String id){
		return map.get(id);
	}
}
