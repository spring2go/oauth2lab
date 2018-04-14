package spring2go.io.authcodeapp.presenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import spring2go.io.authcodeapp.R;
import spring2go.io.authcodeapp.client.ClientAPI;
import spring2go.io.authcodeapp.client.oauth2.TokenStore;
import spring2go.io.authcodeapp.client.userinfo.UserInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfoActivity extends AppCompatActivity {

    private TextView textName;
    private TextView textEmail;
    private TokenStore tokenStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        tokenStore = new TokenStore(this);

        textName = (TextView) findViewById(R.id.userinfo_name);
        textEmail = (TextView) findViewById(R.id.userinfo_email);

        Call<UserInfo> call = ClientAPI
                .userInfo().token(tokenStore.getToken().getValue());

        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                UserInfo userInfo = response.body();
                textName.setText(userInfo.getName());
                textEmail.setText(userInfo.getEmail());
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Log.e("UserInfoActivity", "Error trying to retrieve user info", t);
            }
        });

    }

}