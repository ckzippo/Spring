package com.ckzippo.login;

import com.ckzippo.Enum.SessionEnum;
import com.ckzippo.util.TimeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IDEA
 * USER:ckzippo
 * Date:16/12/21
 * TIME:下午2:58
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private static final Logger logger =
            Logger.getLogger(LoginController.class.getName());

    @Autowired
    AdminMapper adminMapper;

    @RequestMapping("/adminlogin")
    public String adminLogin(HttpServletRequest request) {
        String acc = request.getParameter("acc");
        String password = request.getParameter("password");
        Admin admin = adminMapper.getAdminByAcc(acc);
        if (admin == null && password == null) {
            return "loginfirst";
        } else if (admin == null || !admin.getPassword().equals(password)) {
            return "errorPassword";
        } else {
            HttpSession session = request.getSession();
            session.setAttribute(SessionEnum.USERNAME.getSessionName(), acc);
            session.setAttribute(SessionEnum.PASSWORD.getSessionName(), password);
            session.setAttribute(SessionEnum.AUTHENTICATION.getSessionName(), "true");
            session.setAttribute(SessionEnum.DEPTID.getSessionName(), admin.getDeptid());
            logger.info(TimeUtil.getCurrentTime() + "###" + acc + " 登录了后台管理系统 ");
            return "main";
        }
    }

}
