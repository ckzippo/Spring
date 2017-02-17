package com.ckzippo.util;

import com.ckzippo.Enum.SessionEnum;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IDEA
 * USER:ckzippo
 * Date:17/2/10
 * TIME:上午9:27
 */
@Component
public class LoginInfo {
    public static String getLoginInfo(HttpServletRequest request) {
        return request.getSession().getAttribute(SessionEnum.USERNAME.getSessionName()).toString();
    }
}
