package omgimbot.app.sidangapps.features.admin.mhs.pilihpenguji;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import omgimbot.app.sidangapps.Utils.CommonRespon;
import omgimbot.app.sidangapps.features.admin.dosen.model.listPenguji;
import omgimbot.app.sidangapps.features.mhs.model.daftarModel;

public interface IPilihPengujiView {
    void initView();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<listPenguji> result);

    void onNetworkError(String cause);

    void goToDashboard();

}
