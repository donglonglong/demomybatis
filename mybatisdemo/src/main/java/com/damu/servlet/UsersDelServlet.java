package com.damu.servlet;

import com.damu.dao.UsersDAO;
import com.damu.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deluser")
public class UsersDelServlet extends HttpServlet {

    private UsersDAO usersDAO = new UsersDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取参数
        String id = req.getParameter("id");
        String type = req.getParameter("type");

        // 执行删除或者锁定
        if ("lock".equals(type)) {
            // 执行锁定操作：update操作
            Users user = new Users();
            user.setId(Integer.parseInt(id));
            user.setUserStatus(1);

            usersDAO.updateUsers(user);

        } else if ("del".equals(type)) {
            // 执行的删除操作：delete操作
            usersDAO.delUsers(Integer.parseInt(id));
        } else if("unlock".equals(type)) {
            // 执行解锁操作：update操作
            Users user = new Users();
            user.setId(Integer.parseInt(id));
            user.setUserStatus(0);

            usersDAO.updateUsers(user);
        }

        // 跳转到首页
        resp.sendRedirect("/index");
    }
}
