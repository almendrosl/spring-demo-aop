package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);

        MembershipDAO membershipDAO =
                context.getBean("membershipDAO", MembershipDAO.class);

        Account myAccount = new Account();
        theAccountDAO.addAccount(myAccount, true);
        theAccountDAO.doWork();

        theAccountDAO.setName("foobar");
        theAccountDAO.setServiceCode("silver");

        String name = theAccountDAO.getName();
        String code = theAccountDAO.getServiceCode();



        membershipDAO.addSillyMember();
        membershipDAO.goToSleep();

        context.close();
    }
}
