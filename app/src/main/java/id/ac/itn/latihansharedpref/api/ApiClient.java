package id.ac.itn.latihansharedpref.api;

import id.ac.itn.latihansharedpref.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static ApiClient mInstannce;
    private final Retrofit retrofit;

    private ApiClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BaseURL).addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ApiClient getInstance(){
        if(mInstannce==null){
            mInstannce=new ApiClient();
        }
        return mInstannce;
    }

    public ApiService getApi(){
        return retrofit.create(ApiService.class);
    }
}
