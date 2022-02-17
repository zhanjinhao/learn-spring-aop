package saf.xml;

import org.aspectj.lang.ProceedingJoinPoint;

public class XmlAspectConfiguration {

    public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("@Around before any public method : " + pjp.getSignature());
        Object proceed = pjp.proceed();
        System.out.println("@Around after any public method : " + pjp.getSignature() + ", return : " + proceed);
        return proceed;
    }

    public void beforeAnyPublicMethod() {
        System.out.println("@Before any public method.");
    }

    public void finalizeAnyPublicMethod() {
        System.out.println("@After any public method.");
    }

    public void afterAnyPublicMethod() {
        System.out.println("@AfterReturning any public method.");
    }

    public void afterThrowingAnyPublicMethod() {
        System.out.println("@AfterThrowing any public method.");
    }
}