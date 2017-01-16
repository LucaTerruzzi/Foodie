/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoweb.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Initialises the session.
 * If userType parameter is not present, create it.
 * @author Luca, Riccardo, Mario
 */
public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Get the session. If not present, create it.
        HttpSession session = ((HttpServletRequest)request).getSession();
        // If userType is not present, create it
        if(session.getAttribute("userType") == null){
            session.setAttribute("userType", 0);
        }

        // Forward the request
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
    
    
}
