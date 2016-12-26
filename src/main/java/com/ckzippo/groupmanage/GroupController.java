package com.ckzippo.groupmanage;

import com.ckzippo.Enum.ActionEnum;
import com.ckzippo.util.InvokeHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

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
}
