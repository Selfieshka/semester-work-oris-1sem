package ru.kpfu.itis.kirillakhmetov.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.kirillakhmetov.service.SecurityService;

import java.io.IOException;

@WebFilter("/*")
public class SecurityFilter extends HttpFilter {
    private static final String[] SECURITY_PATHS = {"/login", "/registration",
            "/style/login.css", "/style/registration.css"};
    private SecurityService securityService;

    @Override
    public void init() throws ServletException {
        securityService = (SecurityService) getServletContext().getAttribute("securityService");
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        boolean matchPaths = false;
        for (String path : SECURITY_PATHS) {
            if (path.equals(req.getRequestURI().substring(getServletContext().getContextPath().length()))) {
                matchPaths = true;
                break;
            }
        }

        boolean ownerIsSigned = securityService.isSigned(req);
        if (ownerIsSigned && matchPaths) {
            res.sendRedirect(getServletContext().getContextPath() + "/profile");
        } else if (!ownerIsSigned && !matchPaths) {
            res.sendRedirect(getServletContext().getContextPath() + "/login");
        } else {
            chain.doFilter(req, res);
        }
    }
}
