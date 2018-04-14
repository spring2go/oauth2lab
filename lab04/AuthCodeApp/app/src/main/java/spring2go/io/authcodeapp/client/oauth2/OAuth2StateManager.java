package spring2go.io.authcodeapp.client.oauth2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class OAuth2StateManager {

    private final SharedPreferences prefs;

    public OAuth2StateManager(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void saveState(String state) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("state", state);
        editor.commit();
    }

    public String getState() {
        return prefs.getString("state", "");
    }

    public boolean isValidState(String state) {
        return this.getState().equals(state);
    }
}
