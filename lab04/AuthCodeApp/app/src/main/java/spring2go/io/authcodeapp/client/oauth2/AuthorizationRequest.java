package spring2go.io.authcodeapp.client.oauth2;

import android.net.Uri;

import spring2go.io.authcodeapp.client.ClientAPI;

public class AuthorizationRequest {
    public static final String REDIRECT_URI
        = "oauth2://userinfo/callback";

    public static Uri createAuthorizationUri(String state) {
        return new Uri.Builder()
            .scheme("http")
            .encodedAuthority(ClientAPI.BASE_URL)
            .path("/oauth/authorize")
            .appendQueryParameter("client_id", "mobileclient")
            .appendQueryParameter("response_type", "code")
            .appendQueryParameter("redirect_uri", REDIRECT_URI)
            .appendQueryParameter("scope", "read_userinfo")
            .appendQueryParameter("state", state)
            .build();
    }

}
