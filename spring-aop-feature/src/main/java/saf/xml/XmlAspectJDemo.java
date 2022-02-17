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

        XmlAspectJDemo xmlAspectJDemo = context.getBean(XmlAspectJDemo.class);
        System.out.println(xmlAspectJDemo.getClass());
        System.out.println("---------------------");
        context.close();
    }

}
