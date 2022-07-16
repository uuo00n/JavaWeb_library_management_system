package com.uuo_book.action;

import com.uuo_book.bean.User;
import com.uuo_book.biz.UserBiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author uuo00_n(Hwang Jumbo)
 * @Date 2022/7/15 14:30
 */
@WebServlet("/user.let")
public class UserServlet extends HttpServlet {
    //构建UserBiz类
    UserBiz userBiz = new UserBiz();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
    //user.let?type=login 登录
    //user.let?type=exit 安全退出
    //user.let?type=modifyPwd 修改密码

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        //1.判读用户请求的类型为Login
        String method = req.getParameter("type");
        switch (method) {
            case "login":

                //2.从login.html中获取用户名和密码
                String name = req.getParameter("name");
                String pwd = req.getParameter("pwd");

                //3.调用UserBiz的getUser方法，根据用户名和密码获取对应的用户对象
                User user = userBiz.getUser(name, pwd);

                //4.判断用户对象是否为null:
                if (user == null) {
                    //4.1如果是nul表示用户名或有码不正确，提示错误，回到登灵页面
                    out.println("<script>alert('用户名或密码不存在');location.href = 'login.html';</script>");
                } else {
                    //4.2 非空:表示登录成功，将用户对象保存session中，提示登录成功后，将页而跳转到index.jsp
                    out.println("<script>alert('登陆成功');location.href='index.jsp';</script>");

                }
                break;

        }

    }
}
