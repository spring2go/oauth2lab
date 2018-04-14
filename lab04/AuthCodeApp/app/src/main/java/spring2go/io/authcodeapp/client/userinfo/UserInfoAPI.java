package spring2go.io.authcodeapp.client.userinfo;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface UserInfoAPI {

    @GET("api/userinfo")
    Call<UserInfo> token(@Header("Authorization") String accessToken);

}
