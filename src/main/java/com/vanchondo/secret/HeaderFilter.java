package com.vanchondo.secret;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(
    filterName = "headerFilter",
    urlPatterns = "/*"
)
public class HeaderFilter implements Filter {

    private final String secretKey;
    private final StringEncryptor encryptor;

    public HeaderFilter(@Value("${com.vanchondo.secret.secret-key}") String secretKey, StringEncryptor encryptor){
        this.secretKey = secretKey;
        this.encryptor = encryptor;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String headerValue = ((HttpServletRequest)request).getHeader("Secret-Key");
        if (!StringUtils.isEmpty(headerValue)) {
            headerValue = encryptor.decrypt(headerValue);
        }
        
        if (secretKey.equals(headerValue)) {
            chain.doFilter(request, response);
        }
        else {
            HttpServletResponse res = (HttpServletResponse)response;
            res.setStatus(HttpStatus.UNAUTHORIZED.value());
            res.getWriter().write(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
    }
}
