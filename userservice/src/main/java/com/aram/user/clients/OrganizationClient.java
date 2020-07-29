package com.aram.user.clients;

import com.aram.user.dto.OrganizationDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author aram
 */
@Component
@Slf4j
public class OrganizationClient {
    
    @Autowired
    private RestTemplate restTemplate;

//    @HystrixCommand(
//        fallbackMethod = "buildFallbackOrganization"
//        threadPoolKey = "organizationThreadPool",
//        threadPoolProperties = {
//            @HystrixProperty(name = "coreSize",value="30"),
//            @HystrixProperty(name="maxQueueSize", value="10")
//        },
//        commandProperties={         
//            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
//            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="75"),
//            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="7000"),
//            @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="15000"),
//            @HystrixProperty(name="metrics.rollingStats.numBuckets", value="5")
//        }
//    )
    public Optional<OrganizationDTO> getOrganization( Long organizationId ) {
        log.info("getOrganization organizationId " + organizationId);
        ResponseEntity<OrganizationDTO> restExchange =
            restTemplate.exchange(
                "http://zuulserver/organization/v1/organizations/{organizationId}",
                HttpMethod.GET,
                null,
                OrganizationDTO.class,
                organizationId
            );
        log.info("getOrganization " + restExchange);
        if( restExchange.getStatusCode().equals(HttpStatus.OK) ) {
            return Optional.of(restExchange.getBody());
        }

        return Optional.empty();
    }
    
    private Optional<OrganizationDTO> buildFallbackOrganization( Long organizationId ) {
        log.info("buildFallbackOrganization something went wrong to get organizationId" + organizationId);
        return Optional.empty();
    }
    
}
