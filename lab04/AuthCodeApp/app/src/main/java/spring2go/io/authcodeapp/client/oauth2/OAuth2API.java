package spring2go.io.authcodeapp.client.oauth2;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface OAuth2API {

    @FormUrlEncoded
    @POST("oauth/token")
    Call<AccessToken> requestToken(@FieldMap Map<String, String> tokenRequest);

}
