package omgimbot.app.sidangapps.features.admin.mhs.pilihpenguji;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import omgimbot.app.sidangapps.Utils.CommonRespon;
import omgimbot.app.sidangapps.features.admin.dosen.inputdosen.IInDosView;
import omgimbot.app.sidangapps.features.admin.dosen.model.dosenPenguji;
import omgimbot.app.sidangapps.features.admin.dosen.model.listMk;
import omgimbot.app.sidangapps.features.admin.dosen.model.listPenguji;
import omgimbot.app.sidangapps.features.admin.mhs.model.ListPengujiMhs;
import omgimbot.app.sidangapps.features.admin.mhs.model.MPengujiMhs;
import omgimbot.app.sidangapps.network.NetworkService;
import omgimbot.app.sidangapps.network.RestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PilihPengujiPresenter {
    final IPilihPengujiView view;
    public final Retrofit restService;

    public PilihPengujiPresenter(IPilihPengujiView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }


    public void getListPengujiMhs(String nim) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).getListPengujiMhs(nim)
                .enqueue(new Callback<List<ListPengujiMhs>>() {
                    @Override
                    public void onResponse(Call<List<ListPengujiMhs>> call, Response<List<ListPengujiMhs>> response) {
                        view.hideLoadingIndicator();
                        Log.d("list penguji mhs", new Gson().toJson(response.body()));
                        view.onGetlistPenguji(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<ListPengujiMhs>> call, Throwable t) {
                        view.onNetworkError(t.getLocalizedMessage());

                    }
                });
    }

    public void inputPengujiMhs(MPengujiMhs MPengujiModel) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).inputPengujiMhs(MPengujiModel)
                .enqueue(new Callback<CommonRespon>() {
                    @Override
                    public void onResponse(Call<CommonRespon> call, Response<CommonRespon> response) {
                        view.hideLoadingIndicator();
                        if (response.body().getSuccess())
                            view.onSubmitSuccess(response.body());
                        else {
                            view.onSubmitFailed(response.body().getmRm());
                        }
                    }

                    @Override
                    public void onFailure(Call<CommonRespon> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    public void getListPenguji() {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).getListPenguji()
                .enqueue(new Callback<List<listPenguji>>() {
                    @Override
                    public void onResponse(Call<List<listPenguji>> call, Response<List<listPenguji>> response) {
                        view.hideLoadingIndicator();
                        Log.d("list penguji" , new Gson().toJson(response.body()));
                        view.onDataReady(response.body());

                    }

                    @Override
                    public void onFailure(Call<List<listPenguji>> call, Throwable t) {
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


