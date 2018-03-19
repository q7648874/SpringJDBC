package springJdbc.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
	
	public static Object getProxy(final Class clazz){
		
		//����1  �����������ͬ���������
		//����2 �����������ͬ�Ľӿ�
		//����3 ��ʾ���������ε��ñ�����������ʵ����
		return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new InvocationHandler() {
			//�����������κη����Ƕ��ᴥ���������
			
			//����1 ��������������
			//����2 ������������ʵ����
			//����3 ��������������ʵ�����Ĳ���
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				//ǰ����ǿ
				Object invoke = null;
				try {
					System.out.println("ǰ����ǿ");
					invoke = method.invoke(clazz.newInstance(), args);
					//������ǿ
					System.out.println("������ǿ");
				} catch (Exception e) {
					System.out.println("�쳣��ǿ");
				}
				return invoke;
			}
		});
		
		
	}
}
