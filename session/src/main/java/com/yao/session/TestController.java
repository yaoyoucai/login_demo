package com.yao.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author huanyao
 * @version 1.0
 * @ClassName TestController.java
 * @Description TODO(用一句话描述该文件做什么)
 * @date 2021/3/12 1:49 下午
 */
@RestController
@RequestMapping("/session")
public class TestController {
    @Autowired
    public HttpServletRequest request;
    @Autowired
    public HttpServletResponse response;
    /**
     * @title setSession
     * @Description 设置session
     * @author huanyao
     * @date 2021/3/12 1:50 下午
     * @throws
     */
    @GetMapping("/setSession")
    public void setSession() throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        //设置Session最长超时时间为60秒，这里的单位是秒
        session.setMaxInactiveInterval(30);
        session.setAttribute("name","看完博客就要点赞");
        String sessionId = session.getId();
        //判断session是不是新创建的
        if (session.isNew()) {
            response.getWriter().print("session创建成功，session的id是："+sessionId);
        }else {
            response.getWriter().print("服务器已经存在该session了，session的id是："+sessionId);
        }
    }

    /**
     * @title getSession
     * @Description 获取session
     * @author huanyao
     * @date 2021/3/12 2:04 下午
     * @throws
     */
    @GetMapping("/getSession")
    public void  getSession() {
        HttpSession session = request.getSession();
        Object name = session.getAttribute("name");
        System.out.println(name);
    }
}
