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
 * Only guest can go through
 * @author Luca, Riccardo, Mario
 */
public class GuestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest)request).getSession();
        // If authenticated
        if((int)session.getAttribute("userType") != 0){
            ((HttpServletResponse)response).sendRedirect("index.jsp");
        }else{
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

}
