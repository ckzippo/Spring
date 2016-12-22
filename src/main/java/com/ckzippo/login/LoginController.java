package com.ckzippo.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IDEA
 * USER:ckzippo
 * Date:16/12/21
 * TIME:下午2:58
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    AdminMapper adminMapper;

    @RequestMapping("/test")
    public String test() {
        String acc = "ckzippo";
        String password = "123";
        Admin admin1 = adminMapper.getAdminByAcc(acc);
        System.out.println(admin1);
        return "success";
    }

    @RequestMapping("/adminlogin")
    public String adminLogin(HttpServletRequest request) {
        String acc = request.getParameter("acc");
        String password = request.getParameter("password");
        System.out.println("acc: " + acc + " password: " + password);
        Admin admin = adminMapper.getAdminByAcc(acc);
        if (admin == null || !admin.getPassword().equals(password)) {
            return "error";
        } else {
            return "success";
        }
    }

}
