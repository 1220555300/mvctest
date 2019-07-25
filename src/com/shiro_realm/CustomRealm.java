package com.shiro_realm;

import com.dao.UserDao;
import com.vo.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {
//    Map<String ,String> userMap = new HashMap<String ,String>(16);
//    {
//        userMap.put("Mark","283538989cef48f3d7d8a1c1bdf2008f");
//        super.setName("customRealm");
//    }
    @Resource
    private UserDao userDao;

    @Override   //做授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        Set<String > roles = getRolesByUserName(userName);
        Set<String> permissions = getPermissionByUserName(userName);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    private Set<String> getPermissionByUserName(String userName) {
        Set<String> sets = new HashSet<String>();
        sets.add("user:delete");
        sets.add("user:add");
        return sets;
    }


    private Set<String> getRolesByUserName(String userName){
        Set<String> sets = new HashSet<String>();
        sets.add("admin");
        sets.add("user");
        return sets;
    }
    @Override  //做认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
      //1.从主体传过来的认证信息 获取用户名
        String userName = (String) authenticationToken.getPrincipal();
        //2.通过用户名大炮数据库中获取凭证
        String password = getPasswordByUserName(userName);
        if(password == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("Mark",password,"customRealm");
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("Mark")); //加盐
        return authenticationInfo;
    }
    private  String getPasswordByUserName(String userName){
        User user = userDao.getUserByUserName(userName);
        if(user != null){
            return user.getPassword();
        }
        return null;
      //  return userMap.get(userName);
    }

    public static void main(String[] args){
      //  Md5Hash md5Hash = new Md5Hash("123456");
        Md5Hash md5Hash = new Md5Hash("123456","Mark");
        System.out.println(md5Hash);
    }





}
