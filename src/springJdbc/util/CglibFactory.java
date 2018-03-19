package springJdbc.util;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CglibFactory {
	
	public static Object getProxy(final Class clazz){
		//����������
		Enhancer en = new Enhancer();
		//���ø���
		en.setSuperclass(clazz);
		//���ûص�����
		en.setCallback(new MethodInterceptor() {
			
			public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
				
				Object invoke=null;
				try {
					System.out.println("ǰ����ǿ");
					invoke = arg1.invoke(clazz.newInstance(), arg2);
					System.out.println("������ǿ");
				} catch (Exception e) {
					System.out.println("�쳣��ǿ");
				}
				
				return invoke;
			}
		});
		return en.create();
	}
}
