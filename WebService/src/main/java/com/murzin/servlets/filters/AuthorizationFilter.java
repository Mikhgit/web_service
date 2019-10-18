package com.murzin.servlets.filters;

import com.murzin.dao.impl.UsersDAOImpl;
import com.murzin.dao.UsersDAO;
import com.murzin.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter extends BaseFilter {

    public static final String PAGE_ADMIN = "/admin.jsp";
    public static final String PAGE_PROFILE = "/profile.jsp";
    public static final String PAGE_LOGIN = "/login.jsp";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        filterConfig.getServletContext().setAttribute("dao", new UsersDAOImpl());
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        String loginFromSession = (String) session.getAttribute("login");
        String passwordFromSession = (String) session.getAttribute("password");

        UsersDAO usersDAO = (UsersDAO) request.getServletContext().getAttribute("dao");

        if(loginFromSession != null &&
                passwordFromSession != null) {
            User user = usersDAO.getUser(loginFromSession, passwordFromSession);
            moveTo(request, response, user);

        } else if(usersDAO.getUser(login,password) != null) {
            User user = usersDAO.getUser(login, password);

            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("password", password);
            request.getSession().setAttribute("role", user.getRole());
            moveTo(request,response,user);
        } else {
            request.getRequestDispatcher(PAGE_LOGIN).forward(request,response);
        }
    }

    private void moveTo(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        req.setAttribute("user", user);
        if(user.getRole().equals(User.ROLE.ADMIN)){
            req.getRequestDispatcher(PAGE_ADMIN).forward(req,resp);
        } else if(user.getRole().equals(User.ROLE.USER) || user.getRole().equals(User.ROLE.UNKNOWN)) {
            req.getRequestDispatcher(PAGE_PROFILE).forward(req, resp);
        } else {
            req.getRequestDispatcher(PAGE_LOGIN).forward(req,resp);
        }
    }


    @Override
    public void destroy() {
        //NOP
    }
}
