package io.spring2go.oauth2.clientstate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration @EnableOAuth2Client
public class OAuth2Configuration {

    @Bean
    public OAuth2ProtectedResourceDetails authorizationCode() {
        AuthorizationCodeResourceDetails details =
            new AuthorizationCodeResourceDetails();
        details.setId("oauth2server");
        details.setClientId("clientapp");
        details.setClientSecret("112233");
        details.setUseCurrentUri(true);
        details.setUserAuthorizationUri("http://localhost:8080/oauth/authorize");
        details.setAccessTokenUri("http://localhost:8080/oauth/token");
        return details;
    }

    @Bean
    public OAuth2RestTemplate restTemplate(OAuth2ClientContext context) {
        return new OAuth2RestTemplate(authorizationCode(), context);
    }

}
