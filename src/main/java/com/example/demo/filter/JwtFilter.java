package com.example.demo.filter;

import com.example.demo.util.ResultUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SignatureException;

public class JwtFilter extends GenericFilterBean {
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        // 从Http请求获取授权
        final String authHeader = request.getHeader("authorization");
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, res);
        }
        // Except OPTIONS, other request should be checked by JWT
        else {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new ServletException("非法访问");
            }
            // 从授权中获取JWT令牌
            final String token = authHeader.substring(7);
            // 使用JWT解析器检查签名是否与Key“secretkey”匹配
            final Claims claims = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody();
            //将声明添加到请求头部
            request.setAttribute("claims", claims);
            chain.doFilter(req, res);
        }
    }
}
