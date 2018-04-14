package spring2go.io.authcodeapp.client.oauth2;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class TokenStore {
    private final SharedPreferences prefs;

    public TokenStore(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void save(AccessToken accessToken) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("authorized", true);
        editor.putString("access_token", accessToken.getValue());
        editor.putString("scope", accessToken.getScope());
        editor.putString("token_type", accessToken.getTokenType());
        editor.putLong("expires_in", accessToken.getExpiresIn());
        editor.putLong("issued_at", accessToken.getIssuedAt());
        editor.commit();
    }

    public AccessToken getToken() {
        AccessToken token = null;

        boolean authorized = prefs.getBoolean("authorized", false);
        if (authorized) {
            token = new AccessToken();
            token.setValue(prefs.getString("access_token", null));
            token.setScope(prefs.getString("scope", ""));
            token.setTokenType(prefs.getString("token_type", "bearer"));
            token.setExpiresIn(prefs.getLong("expires_in", -1)); // prevents / 0
            token.setIssuedAt(prefs.getLong("issued_at", -1)); // prevents / 0
        }
        return token;
    }
}
