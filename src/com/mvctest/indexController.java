package com.mvctest;

import com.shiro_realm.CustomRealm;
import com.vo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import com.services.loginService;
import java.util.Map;
@Controller
public class indexController {
    private static final transient Logger log = LoggerFactory.getLogger(indexController.class);

    @Autowired
    private loginService loginService;
    private User userService;
    @RequestMapping("/login")
    public String login(){
        return "login";

    }
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser(){
        //simpleAccountRealm.addAccount("Mark","123456");
    }
    @Test  //测试
    public void testAuthentication(){
        CustomRealm customRealm = new CustomRealm();
        //构建SecurityManager 环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);

        //做加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);//设置加密次数
        customRealm.setCredentialsMatcher(matcher);
        //主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("Mark","123456");


        try{
            subject.login(token);
            System.out.println(subject.isAuthenticated());
            //   Map<String,Object> memberInfo = loginService.getMember(username);

        }catch (AuthenticationException e){
            System.out.println( e.getMessage());

            // e.printStackTrace();
        }

        System.out.println(subject.isAuthenticated());
        subject.checkRole("admin");
        subject.checkPermissions("user:add","user:delete");
    }

    @RequestMapping(value="login/auth",method = RequestMethod.POST)
    public String doLogin(String username,String password){


        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try{
            subject.login(token);
            subject.getSession().setAttribute("member_name","123");
            System.out.println(subject.isAuthenticated());
         //   Map<String,Object> memberInfo = loginService.getMember(username);

        }catch (AuthenticationException e){
            System.out.println( e.getMessage());
           return  e.getMessage();
            // e.printStackTrace();
        }

        return "list";
    }
    //捕获异常
    @RequestMapping(value = "/error")
    public String error(){
        int a = 1/0;
        int b = 2/0;
        int c = 2/0;
        return "list";
    }
}
