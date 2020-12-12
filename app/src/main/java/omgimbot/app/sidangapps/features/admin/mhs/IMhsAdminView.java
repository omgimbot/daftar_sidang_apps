package omgimbot.app.sidangapps.features.admin.mhs;

import android.app.Activity;

import java.util.List;

import omgimbot.app.sidangapps.Utils.CommonRespon;
import omgimbot.app.sidangapps.features.mhs.model.daftarModel;

public interface IMhsAdminView {
    void initView();

    void clearLightStatusBar(Activity activity);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<daftarModel> result);

    void onRequestFailed(String rm);

    void onNetworkError(String cause);

    void goToDashboard();

    void refresh();

    void onAccSuccess(CommonRespon result);
}
