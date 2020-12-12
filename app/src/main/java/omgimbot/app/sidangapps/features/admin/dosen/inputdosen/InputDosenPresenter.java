package omgimbot.app.sidangapps.features.admin.dosen.inputdosen;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import omgimbot.app.sidangapps.Utils.CommonRespon;
import omgimbot.app.sidangapps.features.admin.dosen.model.dosenPenguji;
import omgimbot.app.sidangapps.features.admin.dosen.model.listMk;
import omgimbot.app.sidangapps.features.admin.dosen.model.listPenguji;
import omgimbot.app.sidangapps.features.admin.dosen.model.mk;
import omgimbot.app.sidangapps.network.NetworkService;
import omgimbot.app.sidangapps.network.RestService;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class InputDosenPresenter {
    final IInDosView view;
    public final Retrofit restService;

    public InputDosenPresenter(IInDosView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    void inputDosen(dosenPenguji dosenPengujiModel) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).inputPenguji(dosenPengujiModel)
                .enqueue(new Callback<CommonRespon>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonRespon> call, Response<CommonRespon> response) {
                        view.hideLoadingIndicator();
                        if (response.body().getSuccess())
                            view.onSubmitSuccess();
                        else {
                            view.onSubmitFailed(response.body().getmRm());
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonRespon> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    public void getListMk() {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).getListMk()
                .enqueue(new Callback<List<listMk>>() {
                    @Override
                    public void onResponse(retrofit2.Call<List<listMk>> call, Response<List<listMk>> response) {
                        view.hideLoadingIndicator();
                        Log.d("respon" , new Gson().toJson(response.body()));
                        view.onDataReady(response.body());

                    }

                    @Override
                    public void onFailure(retrofit2.Call<List<listMk>> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    public void getListPenguji() {
        String data;
        view.showLoadingIndicator();
        restService.create(NetworkService.class).getListPenguji()
                .enqueue(new Callback<List<listPenguji>>() {
                    @Override
                    public void onResponse(retrofit2.Call<List<listPenguji>> call, Response<List<listPenguji>> response) {
                        view.hideLoadingIndicator();
                        Log.d("respon" , new Gson().toJson(response.body()));
                        view.onGetListDosen(response.body());

                    }

                    @Override
                    public void onFailure(retrofit2.Call<List<listPenguji>> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }
}


