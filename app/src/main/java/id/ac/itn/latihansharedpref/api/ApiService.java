package id.ac.itn.latihansharedpref.api;

import id.ac.itn.latihansharedpref.BuildConfig;
import id.ac.itn.latihansharedpref.model.UserLoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @Headers("app-token: " + BuildConfig.APIToken)
    @FormUrlEncoded
    @POST("sm_auth")
    Call<UserLoginResponse> signIn(@Field("mail") String mailUser, @Field("passw") String passUser);
}
