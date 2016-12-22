package com.ckzippo.login;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
    AdminService adminService;
    @Autowired
    AdminMapper adminMapper;

    @RequestMapping("/adminlogin")
    public String test() {
        String acc = "ckzippo";
        String password = "123";
        Admin admin = adminService.getAdminByAcc(acc);
        Admin admin1 = adminMapper.getAdminByAcc(acc);
        System.out.println(admin);
        System.out.println(admin1);
        return "success";
    }

}
