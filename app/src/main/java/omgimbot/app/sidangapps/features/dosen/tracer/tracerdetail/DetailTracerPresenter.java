package omgimbot.app.sidangapps.features.dosen.tracer.tracerdetail;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import omgimbot.app.sidangapps.Utils.CommonRespon;
import omgimbot.app.sidangapps.features.admin.dosen.model.listPenguji;
import omgimbot.app.sidangapps.features.admin.mhs.model.ListPengujiMhs;
import omgimbot.app.sidangapps.features.admin.mhs.model.MPengujiMhs;
import omgimbot.app.sidangapps.features.admin.mhs.pilihpenguji.IPilihPengujiView;
import omgimbot.app.sidangapps.features.admin.mhs.tracerStudi.model.TracerStudi;
import omgimbot.app.sidangapps.features.dosen.tracer.tracerdetail.model.DetailTracerStudi;
import omgimbot.app.sidangapps.network.NetworkService;
import omgimbot.app.sidangapps.network.RestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailTracerPresenter {
    final IDetailTracerView view;
    public final Retrofit restService;

    public DetailTracerPresenter(IDetailTracerView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }
    public void getListTracerByNim( String nim) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).getTracerByNim(nim)
                .enqueue(new Callback<DetailTracerStudi>() {
                    @Override
                    public void onResponse(retrofit2.Call<DetailTracerStudi> call, Response<DetailTracerStudi>response) {
                        view.hideLoadingIndicator();
                        Log.d("DFDSKFDSHF", new Gson().toJson(response.body()));
                        view.onDataReady(response.body());
                    }

                    @Override
                    public void onFailure(retrofit2.Call<DetailTracerStudi> call, Throwable t) {
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


