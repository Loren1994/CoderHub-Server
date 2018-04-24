package pers.loren.coderhub.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;
import pers.loren.coderhub.constant.Constants;

import javax.security.auth.login.LoginException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends GenericFilterBean {

    @Autowired
    private Audience audience;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        //请求头信息authorization
        final String authHeader = httpRequest.getHeader(Constants.AUTHOR_HEADER);
        if ("OPTIONS".equals(httpRequest.getMethod())) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request, response);
        } else {
            try {
                //无token或无效token
                if (authHeader == null || !authHeader.startsWith(Constants.AUTHOR_HEADER_PREFIX)) {
                    httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    throw new LoginException(Constants.INVALID_TOKEN);
                } else {
                    final String token = authHeader.substring(7);
                    if (audience == null) {
                        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
                        audience = (Audience) factory.getBean("audience");
                    }
                    final Claims claims = JWTHelper.parseJWT(token, audience.getBase64Secret());
                    //过期token
                    if (claims == null) {
                        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        throw new LoginException(Constants.EXPIRED_TOKEN);
                    }
                    //setAttribute - 这个只是JWT的定义标准，不强制使用(payload部分)
                    //request.setAttribute(Constants.CLAIMS, claims);
                }
            } catch (Exception e) {
                e.printStackTrace();
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            chain.doFilter(request, response);
        }
    }

}
