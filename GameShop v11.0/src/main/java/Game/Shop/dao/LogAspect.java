package Game.Shop.dao;

import Game.Shop.controller.HomeController;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogAspect{


    @After("execution(* listClient(..))")
    public static String logBefore(){

        return HomeController.clientLogin.getUsername();
    }

    @After("execution(* logOut(..))")
    public static String logOut(){
        return HomeController.clientLogout.getUsername();
    }

}
