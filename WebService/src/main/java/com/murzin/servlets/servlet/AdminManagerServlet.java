package com.murzin.servlets.servlet;

import com.murzin.dao.UsersDAO;
import com.murzin.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminManagerServlet extends HttpServlet {

    public static final String PAGE_MANAGE = "/manage.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        UsersDAO usersDAO = (UsersDAO) req.getServletContext().getAttribute("dao");
        User user = usersDAO.getUserByID(id);
        String strRole = req.getParameter("role");
        if(strRole != null) {
            user.setRole(strRole);
            changeSessionAttributeRole(req, user);
        }
        req.setAttribute("user", user);
        req.getRequestDispatcher(PAGE_MANAGE).forward(req, resp);
    }

    private void changeSessionAttributeRole(HttpServletRequest req, User user){
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        if(user.getLogin().equals(login))
            session.setAttribute("role", user.getRole());
    }
}
