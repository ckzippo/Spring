package com.ckzippo.dgpmanage;

import com.ckzippo.Enum.ActionEnum;
import com.ckzippo.groupmanage.Group;
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
import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created with IDEA
 * USER:ckzippo
 * Date:16/12/29
 * TIME:上午11:00
 */
@Controller
@RequestMapping("/dgroup")
public class DGroupController {

    private static final Logger logger = Logger.getLogger(DGroupController.class.getName());

    /**
     * 查询讨论组
     * @param request
     * @return
     */
    @RequestMapping("/qryDGroup")
    public String qryGroup(HttpServletRequest request) {
        String keyword = request.getParameter("keyword");
        if (keyword != null) {
            ArrayList<DGroup> dgroups = InvokeHttpUtil.QryDGroupByName(keyword);
            Admin admin = Util.getAdminInfoByRequest(request);
            logger.info(TimeUtil.getCurrentTime() + "###" + admin.getAcc() + "根据关键字" + keyword + "查询了讨论组");
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(ActionEnum.QRYDGROUP.getActionName(), dgroups);
        }
        return "qryDGroup";
    }

    /**
     * 查询讨论组成员
     * @param request
     * @return
     */
    @RequestMapping("/qryDGroupMember")
    public String qryGroupMember(HttpServletRequest request) {
        String groupid = request.getParameter("id");
        LinkedList<DGroupMember> groupMemberLinkedList = InvokeHttpUtil.qryDGroupMember("29297", groupid);
        Admin admin = Util.getAdminInfoByRequest(request);
        logger.info(TimeUtil.getCurrentTime() + "###" + admin.getAcc() + "查询了讨论组" + groupid + "的成员" );
        if (groupMemberLinkedList != null) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(ActionEnum.QRYDGPMEM.getActionName(), groupMemberLinkedList);
        }
        return "qryDGroupMem";
    }

    /**
     * 增加讨论组群成员
     * @param request
     * @return
     */
    @RequestMapping("/addDGroupMember")
    public ModelAndView addGroupMember(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String dgoupid = request.getParameter("dgroupid");
        modelAndView.addObject("dgroupid", dgoupid);
        modelAndView.setViewName("addDGroupMem");
        return modelAndView;
    }

    /**
     * 查询人员
     * @param request
     * @return
     */
    @RequestMapping("/qryuser")
    public ModelAndView qryUser(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String keyword = request.getParameter("keyword");
        String dgroupid = request.getParameter("dgroupid");
        if (keyword != null) {
            ArrayList<User> queryResult = InvokeHttpUtil.QryUser(keyword);
            Admin admin = Util.getAdminInfoByRequest(request);
            logger.info(TimeUtil.getCurrentTime() + "###" + admin.getAcc() + "为了在讨论组" + dgroupid + "中添加成员,查询了关键字" + keyword + "的人员");
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(ActionEnum.QRYUSER.getActionName(), queryResult);
        }
        modelAndView.addObject("dgroupid", dgroupid);
        modelAndView.setViewName("addDGroupMemQryUser");
        return modelAndView;
    }

    /**
     * 调用HTTP接口增加讨论组成员
     * @param request
     * @return
     */
    @RequestMapping("/addDGroupMemInvoke")
    public String addGroupMemberInvoke(HttpServletRequest request) {
        String dgroupid = request.getParameter("dgroupid");
        String memid = request.getParameter("id");
        Admin admin = Util.getAdminInfoByRequest(request);
        String info = TimeUtil.getCurrentTime() + "###" + admin.getAcc() + "在讨论组"+ dgroupid + "中添加了新的成员" + memid;
        if (InvokeHttpUtil.addDGroupMember(memid, dgroupid, memid)) {
            logger.info(info + "成功");
            return "success";
        } else {
            logger.info(info + "失败");
            return "error";
        }
    }
}
