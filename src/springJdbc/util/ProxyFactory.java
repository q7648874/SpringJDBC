package springJdbc.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
	
	public static Object getProxy(final Class clazz){
		
		//参数1  被代理对象相同的类加载器
		//参数2 被代理对象相同的接口
		//参数3 表示代理对象如何调用被代理对象的真实方法
		return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new InvocationHandler() {
			//代理对象调用任何方法是都会触发这个方法
			
			//参数1 代理对象本身的引用
			//参数2 被代理对象的真实方法
			//参数3 被代理对象调用真实方法的参数
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				//前置增强
				Object invoke = null;
				try {
					System.out.println("前置增强");
					invoke = method.invoke(clazz.newInstance(), args);
					//后置增强
					System.out.println("后置增强");
				} catch (Exception e) {
					System.out.println("异常增强");
				}
				return invoke;
			}
		});
		
		
	}
}
