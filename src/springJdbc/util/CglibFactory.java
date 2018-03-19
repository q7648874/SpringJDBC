package springJdbc.util;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CglibFactory {
	
	public static Object getProxy(final Class clazz){
		//创建工具类
		Enhancer en = new Enhancer();
		//设置父类
		en.setSuperclass(clazz);
		//设置回调方法
		en.setCallback(new MethodInterceptor() {
			
			public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
				
				Object invoke=null;
				try {
					System.out.println("前置增强");
					invoke = arg1.invoke(clazz.newInstance(), arg2);
					System.out.println("后置增强");
				} catch (Exception e) {
					System.out.println("异常增强");
				}
				
				return invoke;
			}
		});
		return en.create();
	}
}
