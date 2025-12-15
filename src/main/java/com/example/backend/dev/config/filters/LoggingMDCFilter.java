package com.example.backend.dev.config.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Order
@Component
public class LoggingMDCFilter implements Filter {

    /**
     * HTTP Headers to get IP Address Information
     */
    private static final String[] IP_HEADER_CANDIDATES = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR" };

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        // add username
        String USER_KEY = "username";
        boolean successfulUserRegistration = registerKey(USER_KEY, getCurrentUserName());
        // add remote address
        String REMOTE_ADDR = "remoteAddr";
        boolean successfulRemoteAddrRegistration = registerKey(REMOTE_ADDR, getClientIpAddress(request));
        try {
            chain.doFilter(request, response);
        } finally {
            if (successfulUserRegistration) MDC.remove(USER_KEY);
            if (successfulRemoteAddrRegistration) MDC.remove(REMOTE_ADDR);
        }

    }

    /**
     * Register the user in the MDC under USER_KEY.
     * @param key Key name
     * @param value Value
     * @return true if the user can be successfully registered
     */
    private boolean registerKey(String key, String value) {
        if (value != null && !value.trim().isEmpty()) {
            MDC.put(key, value);
            return true;
        }
        return false;
    }

    /**
     * Get Username from Security Context
     *
     * @return String representation of username
     */
    private String getCurrentUserName() {
        String currentUserName;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();

        } else {
            currentUserName = "ANONYMOUS";
        }
        return currentUserName;
    }

    /**
     * Get IP Address from request
     *
     * @param request Servlet Request
     * @return String representation of Device ID
     */
    private String getClientIpAddress(HttpServletRequest request) {
        for (String header : IP_HEADER_CANDIDATES) {
            String ip = request.getHeader(header);
            if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    @Override
    public void init(FilterConfig arg0) {
    }

}
