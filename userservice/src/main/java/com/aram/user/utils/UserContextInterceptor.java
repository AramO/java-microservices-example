package com.aram.user.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author aram
 */
@Slf4j
public class UserContextInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution
    ) throws IOException {

        HttpHeaders headers = request.getHeaders();
        log.info("intercept CORRELATION_ID {} ", UserContextHolder.getContext().getCorrelationId());
        headers.add(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());
        log.info("intercept AUTH_TOKEN {} ", UserContextHolder.getContext().getAuthToken());
        headers.add(UserContext.AUTH_TOKEN, UserContextHolder.getContext().getAuthToken());

        return execution.execute(request, body);
    }
    
}
