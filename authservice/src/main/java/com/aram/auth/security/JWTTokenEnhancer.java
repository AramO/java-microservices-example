package com.aram.auth.security;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/**
 *
 * @author aram
 */
public class JWTTokenEnhancer implements TokenEnhancer {
    
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        String orgId =  "org id";

        additionalInfo.put("organizationId", orgId);

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
    
}
