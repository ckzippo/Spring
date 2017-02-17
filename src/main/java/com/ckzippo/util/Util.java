package com.ckzippo.util;

import com.ckzippo.Enum.SessionEnum;
import com.ckzippo.login.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IDEA
 * USER:ckzippo
 * Date:17/2/16
 * TIME:上午9:25
 */
public class Util {

    /**
     * 获取管理员信息
     * 通过request 获取session, 再获取保存在session中的管理员的登录信息
     * @param request
     * @return
     */
    public static Admin getAdminInfoByRequest(HttpServletRequest request) {
        Admin admin = new Admin();
        HttpSession session = request.getSession();
        String acc = (String) session.getAttribute(SessionEnum.USERNAME.getSessionName());
        String password = (String) session.getAttribute(SessionEnum.PASSWORD.getSessionName());
        int deptid = (Integer) session.getAttribute(SessionEnum.DEPTID.getSessionName());

        admin.setAcc(acc);
        admin.setPassword(password);
        admin.setDeptid(deptid);

        return admin;
    }
}
