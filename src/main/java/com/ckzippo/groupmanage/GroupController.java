package com.ckzippo.groupmanage;

import com.ckzippo.Enum.ActionEnum;
import com.ckzippo.util.InvokeHttpUtil;
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
        String groupid = request.getParameter("id");
        return "modGroup";
    }

    //TODO 查看群成员,是否需要定义群成员数据结构
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
        if (InvokeHttpUtil.modGroup(groupid, groupname, groupnote)) {
            return "success";
        } else {
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
        String groupid = request.getParameter("groupid");
        String userid = "";
        return null;
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
        if (InvokeHttpUtil.delGroupMember(memberid, groupid, memberid)) {
            return "success";
        } else {
            return "error";
        }
    }
}
