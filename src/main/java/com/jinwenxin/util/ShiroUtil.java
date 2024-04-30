package com.jinwenxin.util;

import com.jinwenxin.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Account;

public class ShiroUtil {

    public static AccountProfile getProfile(){
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
