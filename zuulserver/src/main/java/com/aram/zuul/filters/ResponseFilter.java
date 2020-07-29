package com.aram.zuul.filters;

import com.aram.zuul.utils.FilterUtil;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author aram
 */
@Component
public class ResponseFilter extends ZuulFilter {
    
    private static final int  FILTER_ORDER = 1;
    private static final boolean  SHOULD_FILTER = true;

    @Autowired
    FilterUtil filterUtil;

    @Override
    public String filterType() {
        return FilterUtil.POST_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();

        ctx.getResponse().addHeader(FilterUtil.CORRELATION_ID, filterUtil.getCorrelationId());

        return null;
    }
    
}
