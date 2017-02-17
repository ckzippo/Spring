package com.ckzippo.groupmanage;

import com.ckzippo.Enum.ActionEnum;
import com.ckzippo.login.Admin;
import com.ckzippo.usermanage.User;
import com.ckzippo.util.InvokeHttpUtil;
import com.ckzippo.util.TimeUtil;
import com.ckzippo.util.Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created with IDEA
 * USER:ckzippo
 * Date:16/12/23
 * TIME:下午2:47
 */
@Controller
@RequestMapping("/group")
public class GroupController {
    private static final Logger logger = Logger.getLogger(GroupController.class.getName());

    @Autowired
    InvokeHttpUtil invokeHttpUtil;

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping("/qryGroup")
    public String qryGroup(HttpServletRequest request) {
        String keyword = request.getParameter("keyword");
        if (keyword != null) {
            ArrayList<Group> groups = InvokeHttpUtil.QryGroupByName(keyword);
            Admin admin = Util.getAdminInfoByRequest(request);
            logger.info(TimeUtil.getCurrentTime() + "###" + admin.getAcc() + "根据关键字" + keyword + "查询了群信息");
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(ActionEnum.QRYGROUP.getActionName(), groups);
        }
        return "qryGroup";
    }

    /**
     * 修改群组信息(群名称、群公告)
     * @param request
     * @return
     */
    @RequestMapping("/modGroup")
    public String modGroup(HttpServletRequest request) {
        return "modGroup";
    }

    /**
     * 调用 HTTP 接口修改群信息
     * @param request
     * @return
     */
    @RequestMapping("/modGroupInvoke")
    public String modGroupInvoke(HttpServletRequest request) {
        String groupname = request.getParameter("name");
        String groupnote = request.getParameter("note");
        String groupid = request.getParameter("id");

        Admin admin = Util.getAdminInfoByRequest(request);
        String info = TimeUtil.getCurrentTime() + "###" + admin.getAcc() + "修改了群资料为"
                + "{" + groupid + ", " + groupname + ", " + groupnote + "}";

        if (InvokeHttpUtil.modGroup(groupid, groupname, groupnote)) {
            logger.info(info + "成功");
            return "success";
        } else {
            logger.info(info + "失败");
            return "error";
        }
    }

    /**
     * 查看群成员
     * @param request
     * @return
     */
    @RequestMapping("/qryGroupMember")
    public String qryGroupMember(HttpServletRequest request) {
        String groupid = request.getParameter("id");
        LinkedList<GroupMember> groupMemberLinkedList = InvokeHttpUtil.qryGroupMember("29297", groupid);
        if (groupMemberLinkedList != null) {
            Admin admin = Util.getAdminInfoByRequest(request);
            logger.info(TimeUtil.getCurrentTime() + "###" + admin.getAcc() + "查看了群" + groupid + "的成员");
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(ActionEnum.QRYGRPMEM.getActionName(), groupMemberLinkedList);
        }
        return "qryGroupMem";
    }

    /**
     * 增加群成员
     * @param request
     * @return
     */
    @RequestMapping("/addGroupMember")
    public String addGroupMember(HttpServletRequest request) {
        String goupid = request.getParameter("groupid");
        return "addGroupMem";
    }

    @RequestMapping("/addGroupMemInvoke")
    public String addGroupMemberInvoke(HttpServletRequest request) {
        String groupid = request.getParameter("groupid");
        String memid = request.getParameter("id");
        Admin admin = Util.getAdminInfoByRequest(request);
        String info = TimeUtil.getCurrentTime() + "###" + admin.getAcc() + "为群"+groupid + "增加了成员"+memid;
        if (InvokeHttpUtil.addGroupMember(memid, groupid, memid)) {
            logger.info(info + "成功");
            return "success";
        } else {
            logger.info(info + "失败");
            return "error";
        }
    }

    /**
     * 删除群成员
     * @param request
     * @return
     */
    @RequestMapping("/delGroupMember")
    public String delGroupMember(HttpServletRequest request) {
        String groupid = request.getParameter("groupid");
        String memberid = request.getParameter("memberid");

        Admin admin = Util.getAdminInfoByRequest(request);
        String info = TimeUtil.getCurrentTime() + "###" + admin.getAcc() + "为群"+groupid + "删除了成员"+memberid;

        if (InvokeHttpUtil.delGroupMember(memberid, groupid, memberid)) {
            logger.info(info + "成功");
            return "success";
        } else {
            logger.info(info + "失败");
            return "error";
        }
    }

    @RequestMapping("/delGroup")
    public String delGroup(HttpServletRequest request) {
        String groupid = request.getParameter("id");
        Admin admin = Util.getAdminInfoByRequest(request);
        String info = TimeUtil.getCurrentTime() + "###" + admin.getAcc() + "删除了群" + groupid;
        if (InvokeHttpUtil.delGroup("29297", groupid)) {
            logger.info(info + "成功");
            return "success";
        } else {
            logger.info(info + "失败");
            return "error";
        }
    }

    @RequestMapping("/qryuser")
    public String qryUser(HttpServletRequest request) {
        String keyword = request.getParameter("keyword");
        if (keyword != null) {
            ArrayList<User> queryResult = invokeHttpUtil.QryUser(keyword);
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(ActionEnum.QRYUSER.getActionName(), queryResult);
        }
        return "addGroupMemQryUser";
    }
}
