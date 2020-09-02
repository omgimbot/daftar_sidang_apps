package omgimbot.app.sidangapps.features.mhs.judul;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import omgimbot.app.sidangapps.Utils.CommonRespon;
import omgimbot.app.sidangapps.Utils.FileUtils;
import omgimbot.app.sidangapps.features.auth.login.model.Users;
import omgimbot.app.sidangapps.features.auth.regist.IRegistView;
import omgimbot.app.sidangapps.features.mhs.model.daftarModel;
import omgimbot.app.sidangapps.network.NetworkService;
import omgimbot.app.sidangapps.network.RestService;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class JudulMhsPresenter {
    final IJudulMhsView view;
    public final Retrofit restService;

    public JudulMhsPresenter(IJudulMhsView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }


    public void daftarJudul(Context context, daftarModel model, File myFile, String path, Uri uri) {
        view.showLoadingIndicator();
        File file = new File(path);
        RequestBody requestFile = RequestBody.create(MediaType.parse(context.getContentResolver().getType(uri)), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("myFile", myFile.getName(), requestFile);
        restService.create(NetworkService.class).uploadJudul(body, model)
                .enqueue(new Callback<CommonRespon>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonRespon> call, Response<CommonRespon> response) {
                        view.hideLoadingIndicator();
                        if (response.body().getSuccess())
                            view.onUploadSuccess();
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonRespon> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    public void cekJudul(String nim , String key) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).cekJudul(nim , key)
                .enqueue(new Callback<daftarModel>() {
                    @Override
                    public void onResponse(retrofit2.Call<daftarModel> call, Response<daftarModel> response) {
                        view.hideLoadingIndicator();
                        view.onCekJudul(response.body());
                    }

                    @Override
                    public void onFailure(retrofit2.Call<daftarModel> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }


    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
    }
}
