package spring2go.io.authcodeapp.presenter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.UUID;

import spring2go.io.authcodeapp.R;
import spring2go.io.authcodeapp.client.oauth2.AccessToken;
import spring2go.io.authcodeapp.client.oauth2.AuthorizationRequest;
import spring2go.io.authcodeapp.client.oauth2.OAuth2StateManager;
import spring2go.io.authcodeapp.client.oauth2.TokenStore;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Button userInfoButton;

    private TokenStore tokenStore;

    private OAuth2StateManager oauth2StateManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tokenStore = new TokenStore(this);
        oauth2StateManager = new OAuth2StateManager(MainActivity.this);

        userInfoButton = (Button) findViewById(R.id.userinfo_button);
        userInfoButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        AccessToken accessToken = tokenStore.getToken();
        if (accessToken != null && !accessToken.isExpired()) {
            Intent intent = new Intent(this, UserInfoActivity.class);
            startActivity(intent);
            return;
        }

        // create a state parameter to start the authorization flow
        String state = UUID.randomUUID().toString();
        oauth2StateManager.saveState(state);

        // creates the authorization URI to redirect user
        Uri authorizationUri = AuthorizationRequest
                .createAuthorizationUri(state);

        Intent authorizationIntent = new Intent(Intent.ACTION_VIEW);
        authorizationIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        authorizationIntent.setData(authorizationUri);
        startActivity(authorizationIntent);
    }
}
