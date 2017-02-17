package com.ckzippo.deptmanage;

import com.ckzippo.Enum.ActionEnum;
import com.ckzippo.Enum.SessionEnum;
import com.ckzippo.login.Admin;
import com.ckzippo.usermanage.User;
import com.ckzippo.util.InvokeHttpUtil;
import com.ckzippo.util.TimeUtil;
import com.ckzippo.util.Util;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IDEA
 * USER:ckzippo
 * Date:17/2/8
 * TIME:下午2:42
 */
@Controller
@RequestMapping("/dept")
public class DeptController {

    private static final Logger logger = Logger.getLogger(DeptController.class.getName());
    /**
     * 查看组织架构
     * @param request
     * @return
     */
    @RequestMapping("/showDept")
    public ModelAndView qryDept(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String deptid = request.getParameter("deptid");
        // 如果未带参数,跳转到根组织架构
        if (deptid == null) {
            deptid = "0";
        }

        List<Department> departmentList = InvokeHttpUtil.QryChildDeptById(deptid);
        List<User> userList = InvokeHttpUtil.QryChildMemById(deptid);

        if (departmentList != null) {
            modelAndView.addObject("departmentlist", departmentList);
        }

        if (userList != null) {
            modelAndView.addObject("userlist", userList);
        }

        modelAndView.setViewName("showDeptment");

        return modelAndView;
    }

    /**
     * 调整组织架构成员
     * @param request
     * @return
     */
    @RequestMapping("adjustDeptMember")
    public ModelAndView adjustDeptMember(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String keyword = request.getParameter("keyword");
        if (keyword != null) {
            ArrayList<User> users = InvokeHttpUtil.QryUser(keyword);
            if (users != null) {
                request.getSession().setAttribute(ActionEnum.ADJDEPTMEMQRYUSER.getActionName(), users);
            }
        }
        modelAndView.setViewName("AdjustDeptMemqryUser");
        return modelAndView;
    }

    /**
     * 查询需要调整组织架构的成员
     * @param request
     * @return
     */
    @RequestMapping("adjustDeptmemberQryuser")
    public ModelAndView qryUser(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    /**
     * 选择组织架构
     * @param request
     * @return
     */
    @RequestMapping("selectDept")
    public ModelAndView selectDept(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String userid = request.getParameter("id");
        String olddeptid = request.getParameter("olddeptid");

        if (userid != null) {
            request.getSession().setAttribute(ActionEnum.ADJDEPTMEMUSERID.getActionName(), userid);
        }

        String deptid = request.getParameter("deptid");
        // 如果未带参数,跳转到根组织架构
        if (deptid == null) {
            deptid = "0";
        }

        List<Department> departmentList = InvokeHttpUtil.QryChildDeptById(deptid);
        List<User> userList = InvokeHttpUtil.QryChildMemById(deptid);

        if (departmentList != null) {
            modelAndView.addObject("departmentlist", departmentList);
        }

        if (userList != null) {
            modelAndView.addObject("userlist", userList);
        }

        modelAndView.setViewName("selectDeptment");
        return modelAndView;
    }

    /**
     * 调用 HTTP 接口, 调整用户组织架构
     * 如果成员以前没有部门,则添加到新部门;如果以前有部门,则需要先删除老部门再添加到新的部门
     * @param request
     * @return
     */
    @RequestMapping("invoke")
    public ModelAndView invoke(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String deptid = request.getParameter("deptid");
        String userid = request.getParameter("userid");

        User user = InvokeHttpUtil.QryUserById(userid);
        String olddeptid = user.getDeptid();

        modelAndView.setViewName("error");

        Admin admin = Util.getAdminInfoByRequest(request);

        String info = TimeUtil.getCurrentTime() + "###" + admin.getAcc() + "调整组织架构人员关系";

        if (olddeptid == null) {
            if (InvokeHttpUtil.AddUserDept("29297", deptid, userid)) {
                logger.info(info + "原部门为空,新部门为"+ deptid + "成功");
                modelAndView.setViewName("success");
            } else {
                logger.info(info + "原部门为空,新部门为" + deptid + "失败");
                modelAndView.setViewName("error");
            }
        } else {
            if (InvokeHttpUtil.delUserDept("29297", olddeptid, userid)) {
                logger.info(TimeUtil.getCurrentTime() + "###" + admin.getAcc()+ "删除人员与组织架构关系成功,原部门为" + olddeptid + "人员ID"+userid);
                if (InvokeHttpUtil.AddUserDept("29297", deptid, userid)) {
                    logger.info(TimeUtil.getCurrentTime() + "###" + admin.getAcc() + "调整至新部门成功,新部门为" +deptid + "人员ID"+userid);
                    modelAndView.setViewName("success");
                } else {
                    logger.info(TimeUtil.getCurrentTime() + "###" + admin.getAcc() + "调整至新部门失败,新部门为" +deptid + "人员ID"+userid);
                }
            } else {
                logger.info(TimeUtil.getCurrentTime() + "###" + "删除人员与组织架构关系失败,原部门为" + olddeptid + "人员ID"+userid);
            }
        }

        logger.info("%n");
        return modelAndView;
    }
}
