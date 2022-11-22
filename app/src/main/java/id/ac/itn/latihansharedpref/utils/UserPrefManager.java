package id.ac.itn.latihansharedpref.utils;

import android.content.Context;
import android.content.SharedPreferences;

import id.ac.itn.latihansharedpref.model.UserLoginModel;

public class UserPrefManager {
    private static final String TAG = UserPrefManager.class.getSimpleName();
    private static UserPrefManager mInstance;
    private static Context mContext;

    private static final String SHARED_PREF_NAME = "latihan_session";
    private static final String KEY_LOGGEDIN = "is_logged_in";
    private static final String KEY_ID_USER = "key_id_user";
    private static final String KEY_NAMA_USER = "key_nama_user";
    private static final String KEY_EMAIL_USER = "key_email";
    private static final String KEY_ROLE = "key_role";


    private UserPrefManager(Context context) {
        mContext = context;
    }

    public static synchronized UserPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new UserPrefManager(context);
        }
        return mInstance;
    }

    public void setUserLogin(UserLoginModel userLogin, Boolean loggedIn) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ID_USER, userLogin.getIdUser());
        editor.putString(KEY_NAMA_USER, userLogin.getUserName());
        editor.putString(KEY_EMAIL_USER, userLogin.getUserMail());
        editor.putString(KEY_ROLE, userLogin.getRoleId());
        editor.putBoolean(KEY_LOGGEDIN, loggedIn);
        editor.apply();
    }

    public UserLoginModel getUserLogin() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new UserLoginModel(
                sharedPreferences.getString(KEY_ID_USER, null),
                sharedPreferences.getString(KEY_NAMA_USER, null),
                sharedPreferences.getString(KEY_EMAIL_USER, null),
                sharedPreferences.getString(KEY_ROLE, "0")
        );
    }

    public boolean isLoggedIn() {
        return mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE).getBoolean(KEY_LOGGEDIN, false);
    }

    public void logout() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
