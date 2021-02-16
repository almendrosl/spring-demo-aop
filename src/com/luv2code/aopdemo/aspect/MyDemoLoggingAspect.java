package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountAdvice(JoinPoint theJoinPoint) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n======>>> Executing @After (finally)  on method: "
                + method);


    }

    @AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc"
    )
    public void afterThrowingFindAccountsAdvice(
            JoinPoint theJoinPoint, Throwable theExc) {

        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n======>>> Executing @AfterThrowing on method: " + method);


        System.out.println("\n======>>> The exception is: " + theExc);

    }


    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(
            JoinPoint theJoinPoint, List<Account> result) {

        String method = theJoinPoint.getSignature().toShortString();

        System.out.println("\n======>>> Executing @AfterReturning on method: " + method);

        System.out.println("\n======>>> result is: " + result);

        convertAccountNamesToUpperCase(result);

        System.out.println("\n======>>> result is: " + result);
    }

    //Modifica el resultado antes de retornarlo
    private void convertAccountNamesToUpperCase(List<Account> result) {
        for (Account account : result) {
            String theUpperName = account.getName().toUpperCase(Locale.ROOT);
            account.setName(theUpperName);
        }
    }


    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvices(JoinPoint theJoinPoint) {
        System.out.println("\n=======>>>>>>>>> Executing @Before advice on addAccount()");

        MethodSignature methodSignature =
                (MethodSignature) theJoinPoint.getSignature();

        System.out.println("Method: " + methodSignature);

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArgs : args) {
            System.out.println(tempArgs);

            if (tempArgs instanceof Account) {

                Account theAccount = (Account) tempArgs;

                System.out.println("account name: " + theAccount.getName());
                System.out.println("account level: " + theAccount.getLevel());

            }
        }


    }
}
