package it.progettoweb.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Guest and Regular user cannot go through
 * @author Luca, Riccardo, Mario
 */
public class AdminOwnerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest)request).getSession();
        // If not administrator user or restaurant owner
        if((int)session.getAttribute("userType") == 0 || (int)session.getAttribute("userType") == 1 || session.getAttribute("user") == null){
            ((HttpServletResponse)response).sendRedirect("index.jsp");
        }else{
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

}
