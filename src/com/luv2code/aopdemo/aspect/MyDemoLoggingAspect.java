package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

//    @Before("execution(public void add*())")
//    @Before("execution(* add*(com.luv2code.aopdemo.Account))")
//    @Before("execution(* add*(com.luv2code.aopdemo.Account, ..))")
//    @Before("execution(* add*(..))")
    @Before("execution(* com.luv2code.aopdemo.dao.*.*(..))") //Match all methods in the package
    public void beforeAddAccountAdvice() {

        System.out.println("\n=======>>>>>>>>> Executing @Before advice on addAccount()");

    }


}