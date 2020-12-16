package omgimbot.app.sidangapps.features.admin.mhs.tracerStudi;


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
import omgimbot.app.sidangapps.network.NetworkService;
import omgimbot.app.sidangapps.network.RestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TracerStudiPresenter {
    final ITracerStudiView view;
    public final Retrofit restService;

    public TracerStudiPresenter(ITracerStudiView view) {
        this.view =  view;
        restService = RestService.getRetrofitInstance();
    }




    public void createTracerStudi(TracerStudi TracerStudiModel) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).inputTracerStudy(TracerStudiModel)
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

}


