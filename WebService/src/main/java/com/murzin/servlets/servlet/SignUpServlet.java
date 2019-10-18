package com.murzin.servlets.servlet;

import com.murzin.dao.UsersDAO;
import com.murzin.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    public static final String PAGE_ERROR = "/error.jsp";
    public static final String PAGE_PROFILE = "/profile.jsp";
    public static final String ATTRIBUTE_ERROR = "error";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ATTRIBUTE_ERROR, "Bad Request");
        req.getRequestDispatcher(PAGE_ERROR).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if(login != null && password != null) {
            UsersDAO usersDAO = (UsersDAO) req.getServletContext().getAttribute("dao");
            if(usersDAO.getUserByLogin(login) != null){
                req.setAttribute(ATTRIBUTE_ERROR, "User already exists!");
                req.getRequestDispatcher(PAGE_ERROR).forward(req,resp);
            }
            User user = new User(usersDAO.getAllUsers().size(), login, password, User.ROLE.UNKNOWN);
            usersDAO.setUser(user);

            session.setAttribute("login", login);
            session.setAttribute("password", password);
            session.setAttribute("role", User.ROLE.UNKNOWN);
            req.setAttribute("user", user);
            req.getRequestDispatcher(PAGE_PROFILE).forward(req, resp);
        } else {
            req.setAttribute(ATTRIBUTE_ERROR, "Unauthorized, try again");
            req.getRequestDispatcher(PAGE_ERROR).forward(req,resp);
        }
    }
}
