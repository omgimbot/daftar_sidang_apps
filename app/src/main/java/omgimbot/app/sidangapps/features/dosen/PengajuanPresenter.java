package omgimbot.app.sidangapps.features.dosen;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import omgimbot.app.sidangapps.Utils.CommonRespon;
import omgimbot.app.sidangapps.features.dosen.judul.IJudulDosenView;
import omgimbot.app.sidangapps.features.mhs.judul.IJudulMhsView;
import omgimbot.app.sidangapps.features.mhs.model.daftarModel;
import omgimbot.app.sidangapps.network.NetworkService;
import omgimbot.app.sidangapps.network.RestService;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PengajuanPresenter {
    final IJudulDosenView view;
    public final Retrofit restService;

    public PengajuanPresenter(IJudulDosenView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }


    public void getListPengajuan(String key , String nidn) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).listpengajuan(key , nidn)
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

    public void Acc(Context context,String path , daftarModel model , File myFile, Uri uri, String key) {
        File file = new File(path);
        RequestBody requestFile = RequestBody.create(MediaType.parse(context.getContentResolver().getType(uri)), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("myFile", myFile.getName(), requestFile);
        view.showLoadingIndicator();

        restService.create(NetworkService.class).acc(body , model)
                .enqueue(new Callback<CommonRespon>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonRespon>call, Response<CommonRespon> response) {
                        view.hideLoadingIndicator();
                        view.onAccSuccess(response.body());
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonRespon> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    public void tolak(String nim , String status , String key) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("nim", nim);
        params.put("status", status);
        params.put("key", key);
        view.showLoadingIndicator();

        restService.create(NetworkService.class).tolak(params)
                .enqueue(new Callback<CommonRespon>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonRespon>call, Response<CommonRespon> response) {
                        view.hideLoadingIndicator();
                        view.onAccSuccess(response.body());
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonRespon> call, Throwable t) {
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
