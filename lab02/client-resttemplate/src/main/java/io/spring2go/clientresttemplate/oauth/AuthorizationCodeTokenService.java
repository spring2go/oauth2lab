package io.spring2go.clientresttemplate.oauth;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthorizationCodeTokenService {
    @Autowired
    private AuthorizationCodeConfiguration configuration;

    public String getAuthorizationEndpoint() {
        String endpoint = "http://localhost:8080/oauth/authorize";

        Map<String, String> authParameters = new HashMap<>();
        authParameters.put("client_id", "clientapp");
        authParameters.put("response_type", "code");
        authParameters.put("redirect_uri",
                getEncodedUrl("http://localhost:9001/callback"));
        authParameters.put("scope", getEncodedUrl("read_userinfo"));

        return buildUrl(endpoint, authParameters);
    }

    private String buildUrl(String endpoint, Map<String, String> parameters) {
        List<String> paramList = new ArrayList<>(parameters.size());

        parameters.forEach((name, value) -> {
            paramList.add(name + "=" + value);
        });

        return endpoint + "?" + paramList.stream()
              .reduce((a, b) -> a + "&" + b).get();
    }

    private String getEncodedUrl(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public OAuth2Token getToken(String authorizationCode) {
        RestTemplate rest = new RestTemplate();
        String authBase64 = configuration.encodeCredentials("clientapp",
                "112233");

        RequestEntity<MultiValueMap<String, String>> requestEntity = new RequestEntity<>(
            configuration.getBody(authorizationCode),
            configuration.getHeader(authBase64), HttpMethod.POST,
            URI.create("http://localhost:8080/oauth/token"));

        ResponseEntity<OAuth2Token> responseEntity = rest.exchange(
                requestEntity, OAuth2Token.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }

        throw new RuntimeException("error trying to retrieve access token");
    }
}
