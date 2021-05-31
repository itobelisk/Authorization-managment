package com.auth.server.security;

import com.auth.server.util.CookieUtils;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Optional;

@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private static final String COOKIE_NAME = "Set-Cookie";
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                Long userId = tokenProvider.getUserIdFromToken(jwt);

                UserDetails userDetails = customUserDetailsService.loadUserById(userId);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }
        if (request.getHeader(COOKIE_NAME) == null) {
            filterChain.doFilter(request, response);
        }
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String accessTokens = "";
        if (request.getHeader(COOKIE_NAME) != null) {
            accessTokens = request.getHeader(COOKIE_NAME);
            log.info("accessTokens {} ", accessTokens);
        } else {
            Optional<Cookie> cookies = CookieUtils.getCookie(request, COOKIE_NAME);
            accessTokens = CookieUtils.deserialize(cookies.get().toString());
            log.info("accessTokens {} ", accessTokens);
        }
        String finalAccessToken = "Bearer " + accessTokens;
        log.info("finalAccessToken {} ", finalAccessToken);
        if (StringUtils.hasText(finalAccessToken) && finalAccessToken.startsWith("Bearer ")) {
            return finalAccessToken.substring(7, finalAccessToken.length());
        }
        return null;
    }
}
