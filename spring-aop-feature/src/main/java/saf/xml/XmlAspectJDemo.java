package saf.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author ISJINHAO
 * @Date 2021/3/9 18:18
 */
public class XmlAspectJDemo {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("classpath:/META-INF/simple-aop-context.xml");

        XmlAspectJDemo2 xmlAspectJDemo = context.getBean(XmlAspectJDemo2.class);
        System.out.println("---------------------");
        // ??? 为什么没有被代理
        System.out.println(xmlAspectJDemo.getClass());
        System.out.println("---------------------");
        xmlAspectJDemo.execute();
        System.out.println("---------------------");
        xmlAspectJDemo.execute2();
        context.close();
    }

    public void execute() {
        System.out.println("execute...");
    }

}
