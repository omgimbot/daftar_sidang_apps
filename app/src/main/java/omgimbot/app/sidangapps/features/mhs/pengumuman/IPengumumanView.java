package omgimbot.app.sidangapps.features.mhs.pengumuman;

import android.app.Activity;

import java.util.List;

import omgimbot.app.sidangapps.features.mhs.model.daftarModel;

public interface IPengumumanView {
    void initView();

    void clearLightStatusBar(Activity activity);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<daftarModel> result);

    void onNetworkError(String cause);

    void goToDashboard();
}
