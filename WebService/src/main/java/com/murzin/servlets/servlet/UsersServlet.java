package com.murzin.servlets.servlet;

import com.murzin.dao.UsersDAO;
import com.murzin.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsersServlet extends HttpServlet {

    public static final String PAGE_LIST_USERS = "/listUsers.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersDAO usersDAO = (UsersDAO) req.getServletContext().getAttribute("dao");
        List<User> list = usersDAO.getAllUsers();
        moveToView(req, resp, "list", list, PAGE_LIST_USERS);
    }

    private void moveToView(HttpServletRequest req, HttpServletResponse resp, String attrName, Object attrValue, String page) throws ServletException, IOException {
        req.setAttribute(attrName, attrValue);
        req.getRequestDispatcher(page).forward(req,resp);
    }

}
