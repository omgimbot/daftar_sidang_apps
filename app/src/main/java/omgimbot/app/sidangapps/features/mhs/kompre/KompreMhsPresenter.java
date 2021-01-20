package omgimbot.app.sidangapps.features.mhs.kompre;

import omgimbot.app.sidangapps.Utils.CommonRespon;
import omgimbot.app.sidangapps.network.NetworkService;
import omgimbot.app.sidangapps.network.RestService;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class KompreMhsPresenter {
    final IKompreMhsView view;
    public final Retrofit restService;

    public KompreMhsPresenter(IKompreMhsView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    public void cekPenguji(String nim) {
        view.showLoadingIndicator();
//        Log.d("cekPenguji: ", nim);
        restService.create(NetworkService.class).cekPengujiMhs(nim)
                .enqueue(new Callback<CommonRespon>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonRespon> call, Response<CommonRespon> response) {
                        view.hideLoadingIndicator();
                        view.onCekPenguji(response.body().getSuccess());
                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonRespon> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }
}
