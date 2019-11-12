package cn.coderstory.springboot.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class TokenFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(TokenFilter.class);
    private HttpServletRequest request;

    public TokenFilter(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String filterType() {
        // pre 转发前 routing 执行路由操作 post 返回前  error 过滤器执行出错时 四个阶段
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // 优先级 值越小 越靠前执行
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // 判断是否要执行过 可以把request注入进来
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 过滤器执行的本体
        String token = request.getHeader("token");
        if (token == null) {
            log.warn("request must have token head");
            RequestContext requestContext = RequestContext.getCurrentContext();
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            try {
                requestContext.getResponse().getWriter().write("token is missing");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
