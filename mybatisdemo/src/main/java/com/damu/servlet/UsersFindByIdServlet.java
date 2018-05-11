package com.damu.servlet;

import com.damu.dao.UsersDAO;
import com.damu.entity.Users;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/detail")
public class UsersFindByIdServlet extends HttpServlet {

    /*
    创建对应的日志记录对象
    通过不同的级别进行日志的记录【DEBUG\WARN\INFO]
     */
    private Logger log = Logger.getLogger(UsersFindByIdServlet.class);


    private UsersDAO usersDAO = new UsersDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        log.info("获取到查询参数id--》" + id);

        Users user = usersDAO.findById(Integer.parseInt(id));

        log.info("查询数完成，查询到的数据：" + user);

        req.setAttribute("user", user);

        req.getRequestDispatcher("detail.jsp").forward(req, resp);
    }
}