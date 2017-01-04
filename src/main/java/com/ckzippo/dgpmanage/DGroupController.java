package com.ckzippo.dgpmanage;

import com.ckzippo.Enum.ActionEnum;
import com.ckzippo.groupmanage.Group;
import com.ckzippo.usermanage.User;
import com.ckzippo.util.InvokeHttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        if (InvokeHttpUtil.addDGroupMember(memid, dgroupid, memid)) {
            return "success";
        } else {
            return "error";
        }
    }
}
