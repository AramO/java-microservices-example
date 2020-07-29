package com.aram.zuul.filters;

import com.aram.zuul.config.ServiceConfig;
import com.aram.zuul.utils.FilterUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author aram
 */
@Component
@Slf4j
public class TrackingFilter extends ZuulFilter {

    @Autowired
    private ServiceConfig serviceConfig;
    
    private static final int      FILTER_ORDER =  1;
    private static final boolean  SHOULD_FILTER=true;

    @Autowired
    FilterUtil filterUtil;

    @Override
    public String filterType() {
        return FilterUtil.PRE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    private boolean isCorrelationIdPresent(){
      return this.filterUtil.getCorrelationId() != null;
    }

    private String generateCorrelationId(){
        return java.util.UUID.randomUUID().toString();
    }
    
    private String getOrganizationId(){

        String result="";
        if (filterUtil.getAuthToken() !=null ){

            String authToken = filterUtil.getAuthToken().replace("Bearer ","");
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(this.serviceConfig.getJwtSigningKey().getBytes("UTF-8"))
                        .parseClaimsJws(authToken).getBody();
                result = (String) claims.get("organizationId");
            }
            catch (Exception e){
                log.info("getOrganizationId: exception {}. ", e.getLocalizedMessage());
            }
        }
        return result;
    }

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        
        if (isCorrelationIdPresent()) {
            log.info("tmx-correlation-id found in tracking filter: {}. ", this.filterUtil.getCorrelationId());
        } else {
            this.filterUtil.setCorrelationId(generateCorrelationId());
            log.info("tmx-correlation-id generated in tracking filter: {}.", this.filterUtil.getCorrelationId());
        }
        
        this.filterUtil.setOrgId(this.getOrganizationId());

        log.info("Processing incoming request for {}.",  ctx.getRequest().getRequestURI());
        return null;
    }
}