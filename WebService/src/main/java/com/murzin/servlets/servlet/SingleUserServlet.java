package com.murzin.servlets.servlet;

import com.murzin.dao.UsersDAO;
import com.murzin.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SingleUserServlet extends HttpServlet {
    public static final String PAGE_USER = "/user.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        UsersDAO usersDAO = (UsersDAO) req.getServletContext().getAttribute("dao");
        User user = usersDAO.getUserByID(id);
        moveToView(req,resp,"user",user,PAGE_USER);
    }

    private void moveToView(HttpServletRequest req, HttpServletResponse resp, String attrName, Object attrValue, String page) throws ServletException, IOException {
        req.setAttribute(attrName, attrValue);
        req.getRequestDispatcher(page).forward(req,resp);
    }
}
