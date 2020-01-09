package com.my.jike;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author sunjinwei
 * @Date 2019-12-17 19:46
 * @Description
 **/
public class ProxyTest {
}


interface UserServer {
    public void select();
}

class UserServiceImpl implements UserServer {

    @Override
    public void select() {
        System.out.println("查询 select");
    }
}

/**
 * 静态代理增强
 **/
class UserServiceProxy implements UserServer {

    private UserServer target; //被代理的对象

    public UserServiceProxy(UserServer target) {
        this.target = target;
    }

    @Override
    public void select() {
        System.out.println("select 之前");
        target.select();
        System.out.println("select 之后");
    }
}

class Client1 {
    public static void main(String[] args) {
        UserServer userServer = new UserServiceImpl();
        UserServiceProxy serviceProxy = new UserServiceProxy(userServer);

        serviceProxy.select();
    }
}

/**
 * JDK动态代理
 * 两个关键类
 * java.lang.reflect.Proxy
 * java.lang.reflect.InvocationHandler
 **/
class LogHandler implements InvocationHandler {

    private Object target;

    //将被代理对象添加到 InvocationHandler 中
    public LogHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("代理之前 before");

        Object result = method.invoke(target, args);

        System.out.println("代理之前 after");
        return result;
    }
}

interface UserServer2 {
    public void select();
}

class UserServiceImpl2 implements UserServer2 {

    @Override
    public void select() {
        System.out.println("查询 select");
    }
}

class Client {
    public static void main(String[] args) {
        //2. 被实现类
        UserServiceImpl2 userServiceImpl2 = new UserServiceImpl2();
        LogHandler handler = new LogHandler(userServiceImpl2);

        //3. 获取classLoader,注意这里的 classLoader 是具体实现类的
        ClassLoader loader = userServiceImpl2.getClass().getClassLoader();

        //4. 获取接口
        Class<?>[] interfaces = userServiceImpl2.getClass().getInterfaces();

        //1. 使用 Proxy 创建代理类
        UserServer2 userServerProxy = (UserServer2) Proxy.newProxyInstance(loader, interfaces, handler);

        userServerProxy.select();

        // 保存JDK动态代理生成的代理类，类名保存为 UserServiceProxy
        ProxyUtils.generateClassFile(userServiceImpl2.getClass(), "UserServiceProxy");
    }
}


class ProxyUtils {
    /**
     * 将根据类信息动态生成的二进制字节码保存到硬盘中，默认的是clazz目录下
     * params: clazz 需要生成动态代理类的类
     * proxyName: 为动态生成的代理类的名称
     */
    public static void generateClassFile(Class clazz, String proxyName) {
        // 根据类信息和提供的代理类名称，生成字节码
        byte[] classFile = ProxyGenerator.generateProxyClass(proxyName, clazz.getInterfaces());
        String paths = clazz.getResource(".").getPath();
        System.out.println(paths);
        FileOutputStream out = null;
        try {
            //保留到硬盘中
            out = new FileOutputStream(paths + proxyName + ".class");
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


class UserDao {
    public void select() {
        System.out.println("UserDao 查询 select");
    }
}

/**
 * 使用cglib动态代理
 */
class LogInterceptor implements MethodInterceptor {

    /**
     * @param o           要增强的对象
     * @param method      表示拦截的方法
     * @param objects     参数列表，包装类型
     * @param methodProxy 方法的代理，invokeSuper 表示对被代理（父类）方法调用
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("拦截的方法:" + method.getName());

        System.out.println("方法之前");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("方法之后");

        return result;
    }
}

class CglibTest{
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserDao.class); //设置超类（父类），cglib通过继承实现
        enhancer.setCallback(new LogInterceptor());

        UserDao userDao = (UserDao) enhancer.create(); //创建代理类
        userDao.select();
    }
}


class StringTest2{
    public static void main(String[] args) {
        Integer num = null;
        System.out.println(String.valueOf(num));
        System.out.println(num.toString());
    }
}






















