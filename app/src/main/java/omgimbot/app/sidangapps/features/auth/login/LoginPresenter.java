package omgimbot.app.sidangapps.features.auth.login;

import android.util.Base64;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import omgimbot.app.sidangapps.App;
import omgimbot.app.sidangapps.Prefs;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.features.auth.login.model.LoginResponse;
import omgimbot.app.sidangapps.network.NetworkService;
import omgimbot.app.sidangapps.network.RestService;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginPresenter {
    final ILoginView view;
    public final Retrofit restService;
    public LoginPresenter(ILoginView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    boolean isLoggedIn() {
        return App.getPref().getBoolean(Prefs.PREF_IS_LOGEDIN, false);
    }

    void storeAccessToken(String token) {
        App.getPref().put(Prefs.PREF_ACCESS_TOKEN, token);
    }

    void storeProfile(String data) {
        App.getPref().put(Prefs.PREF_STORE_PROFILE, data);
        App.getPref().put(Prefs.PREF_IS_LOGEDIN, true);
    }

    void login(String username, String password) {
        String credentials = username + ":" + password;
        String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        System.out.println(username);
        System.out.println(password);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Authorization", basic)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).signin().enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(retrofit2.Call<LoginResponse> call, Response<LoginResponse> response) {
                view.hideLoadingIndicator();
                if (response.body().getSuccess()) {
                    App.getPref().put(Prefs.PREF_IS_LOGEDIN, true);
                    view.onSigninSuccess(response.body());
                } else
                    view.onSigninFailed(response.body().getRm());
            }

            @Override
            public void onFailure(retrofit2.Call<LoginResponse> call, Throwable t) {
                view.hideLoadingIndicator();
                view.onNetworkError(t.getLocalizedMessage());
            }
        });
    }
}
