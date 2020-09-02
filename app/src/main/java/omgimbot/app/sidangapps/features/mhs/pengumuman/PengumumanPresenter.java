package omgimbot.app.sidangapps.features.mhs.pengumuman;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import omgimbot.app.sidangapps.Utils.CommonRespon;
import omgimbot.app.sidangapps.features.mhs.judul.IJudulMhsView;
import omgimbot.app.sidangapps.features.mhs.model.daftarModel;
import omgimbot.app.sidangapps.network.NetworkService;
import omgimbot.app.sidangapps.network.RestService;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PengumumanPresenter {
    final IPengumumanView view;
    public final Retrofit restService;

    public PengumumanPresenter(IPengumumanView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    public void listpengumuman(String nidn) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).listpengumuman(nidn)
                .enqueue(new Callback<List<daftarModel>>() {
                    @Override
                    public void onResponse(retrofit2.Call<List<daftarModel>> call, Response<List<daftarModel>> response) {
                        view.hideLoadingIndicator();
                        view.onDataReady(response.body());
                    }

                    @Override
                    public void onFailure(retrofit2.Call<List<daftarModel>> call, Throwable t) {
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
