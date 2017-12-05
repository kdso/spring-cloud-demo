package com.kdso.demo;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kdso on 2017/12/4.
 */
@Component
public class TokenFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(TokenFilter.class);

    @Override
    public String filterType() {
        /*
        返回一个字符串代表过滤器的类型
        pre：路由之前
        routing：路由之时
        post： 路由之后
        error：发送错误调用*/
        return "pre";
    }

    @Override
    //过滤的顺序
    public int filterOrder() {
        return 0;
    }

    @Override
    //是否要过滤，本文true,永远过滤,可以写逻辑判断
    public boolean shouldFilter() {
        return true;
    }

    @Override
    //过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问
    //此测试验证有没有token
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        if (accessToken == null) {
            log.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            } catch (Exception e) {
            }

            return null;
        }
        log.info("ok");
        return null;
    }


}
