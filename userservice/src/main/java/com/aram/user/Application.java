package com.aram.user;

import com.aram.user.utils.UserContextInterceptor;
import java.util.Collections;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@RefreshScope
@EnableResourceServer
@EnableBinding(Sink.class)
@EnableRedisRepositories("com.aram.user.repository")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
        
    /**
     * The @LoadBalanced annotation
     * tells Spring Cloud to create a
     * Ribbon backed RestTemplate class.
     * @return
     */
    @LoadBalanced
    @Bean
    @Primary
    public RestTemplate getRestTemplate() {
        RestTemplate template = new RestTemplate();
//        Adding the UserContextInterceptor
//        to the RestTemplate instance
//        that has been created
        List interceptors = template.getInterceptors();
        if( interceptors == null ) {
            template.setInterceptors(
                Collections.singletonList(new UserContextInterceptor())
            );
        } else {
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors(interceptors);
        }
        return template;
    }
}
