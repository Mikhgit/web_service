package com.murzin.servlets.filters;

import com.murzin.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminRootFilter extends BaseFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User.ROLE role = (User.ROLE) session.getAttribute("role");

        if (role != null && role.equals(User.ROLE.ADMIN)) {
            req.getRequestDispatcher("/SingleUser").forward(req, resp);
            return;
        }

        filterChain.doFilter(req, resp);
    }
}
