package com.ckzippo.usermanage;

import com.ckzippo.Enum.ActionEnum;
import com.ckzippo.Enum.SessionEnum;
import com.ckzippo.deptmanage.Department;
import com.ckzippo.util.InvokeHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IDEA
 * USER:ckzippo
 * Date:16/12/22
 * TIME:上午10:28
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private InvokeHttpUtil invokeHttpUtil;

    @RequestMapping("/qryuser")
    public String qryUser(HttpServletRequest request) {
        String keyword = request.getParameter("keyword");
        if (keyword != null) {
            ArrayList<User> queryResult = invokeHttpUtil.QryUser(keyword);
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(ActionEnum.QRYUSER.getActionName(), queryResult);
        }
        return "qryUser";
    }

    /**
     * 在查询用户界面点击修改
     */
    @RequestMapping("/moduserFromadd")
    public String moduserFromadd(HttpServletRequest request) {
        return "modeUser";
    }

    /**
     * 调用HTTP接口修改用户信息
     * @param request
     * @return
     */
    @RequestMapping("/moduser")
    public String moduser(HttpServletRequest request) {

        String acc = request.getParameter("acc");
        String username = request.getParameter("username");
        String mobile = request.getParameter("mobile");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");
        String id = request.getParameter("id");

        User user = new User();
        user.setUseracc(acc);
        user.setUsername(username);
        user.setMobilephone(mobile);
        user.setTelephone(telephone);
        user.setEmail(email);
        user.setUserid(id);

        if (invokeHttpUtil.ModUser(user)) {
            return "success";
        } else {
            return "error";
        }
    }

    /**
     * 非OA用户重置密码。
     * @param request
     * @return
     */
    @RequestMapping("/resetpassword")
    public String resetpassword(HttpServletRequest request) {
        String id = request.getParameter("id");
        String acc = request.getParameter("acc");

        if (InvokeHttpUtil.resetPassword(id, acc)) {
            return "success";
        } else {
            return "error";
        }
    }

    /**
     * 增加建群权限
     * @param request
     * @return
     */
    @RequestMapping("/addCreateGroupAuth")
    public String addCreateGroupAuth(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (InvokeHttpUtil.grantUser(id, "3")) {
            return "success";
        } else {
            return "error";
        }
    }


    /**
     * 增加用户时,展示组织架构
     * @param request
     * @return
     */
    @RequestMapping("/showDept")
    public ModelAndView showDept(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();
        String deptid = request.getParameter("deptid");
        if (deptid == null) {
            deptid = String.valueOf(session.getAttribute(SessionEnum.DEPTID.getSessionName()));
        }
        List<Department> departmentList = InvokeHttpUtil.QryChildDeptById(deptid);
        List<User> userList = InvokeHttpUtil.QryChildMemById(deptid);

        if (departmentList != null) {
            modelAndView.addObject("departmentlist", departmentList);
        }

        if (userList != null) {
            modelAndView.addObject("userlist", userList);
        }

        modelAndView.setViewName("qryDept");
        return modelAndView;
    }

    /**
     * 增加用户。跳转到增加用户界面
     * @param request
     * @return
     */
    @RequestMapping("/addUsershow")
    public ModelAndView addUsershow(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String deptid = request.getParameter("deptid");
        modelAndView.setViewName("addUser");
        return modelAndView;
    }

    /**
     * 调用接口增加用户
     * @param request
     * @return
     */
    @RequestMapping("/addUserinvoke")
    public ModelAndView addUserinvoke(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        String deptid = request.getParameter("deptid");
        String useracc = request.getParameter("useracc");
        String username = request.getParameter("username");
        String mobile = request.getParameter("mobilephone");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");

        User user = new User();
        user.setUseracc(useracc);
        user.setUsername(username);
        user.setMobilephone(mobile);
        user.setTelephone(telephone);
        user.setEmail(email);
        user.setDeptid(deptid);
        System.out.println(user);

        // 新增用户。先调用AddUser接口在Def_person中增加人员信息,再调用AddUserDept接口在
        // RL_STAFFDEPT表中增加映射关系
        // TODO: AddUser成功,但是AddUserDept失败,新增失败重试次数。如果多次尝试后依然失败,调用删除人员接口
        String userid = InvokeHttpUtil.AddUser("29297", user);
        if (userid != null) {
            if (InvokeHttpUtil.AddUserDept("29297", deptid, userid)) {
                modelAndView.setViewName("success");
            } else {
                // 如果在组织架构中添加失败,需要把DEF_PERSON表中的记录删除
                InvokeHttpUtil.DeleteUser("29297", userid);
                modelAndView.setViewName("error");
            }
        } else {
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }
}
