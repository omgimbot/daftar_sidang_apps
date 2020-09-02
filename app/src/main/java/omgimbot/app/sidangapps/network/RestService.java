package omgimbot.app.sidangapps.network;

import omgimbot.app.sidangapps.App;
import omgimbot.app.sidangapps.R;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {
    private static final String BASK_URL = App.getApplication().getString(R.string.end_point);
    private static Retrofit retrofit;
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASK_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

//    public static RestService getAPIService(){
//        return RetrofitClient.getClient(BASK_URL).create(RestService.class);
//
//    }
}
