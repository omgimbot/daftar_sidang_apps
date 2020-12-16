package omgimbot.app.sidangapps.features.dosen.tracer;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import omgimbot.app.sidangapps.Utils.CommonRespon;
import omgimbot.app.sidangapps.features.admin.mhs.IMhsAdminView;
import omgimbot.app.sidangapps.features.admin.mhs.tracerStudi.model.TracerStudi;
import omgimbot.app.sidangapps.features.mhs.model.daftarModel;
import omgimbot.app.sidangapps.network.NetworkService;
import omgimbot.app.sidangapps.network.RestService;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TracerStudiDosenPresenter {
    final ITracerStudiDosen view;
    public final Retrofit restService;

    public TracerStudiDosenPresenter(ITracerStudiDosen view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }


    public void getListTracer() {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).getListTracer()
                .enqueue(new Callback<List<TracerStudi>>() {
                    @Override
                    public void onResponse(retrofit2.Call<List<TracerStudi>> call, Response<List<TracerStudi>> response) {
                        view.hideLoadingIndicator();
                        view.onDataReady(response.body());
                    }

                    @Override
                    public void onFailure(retrofit2.Call<List<TracerStudi>> call, Throwable t) {
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
