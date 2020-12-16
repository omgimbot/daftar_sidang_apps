package omgimbot.app.sidangapps.features.auth.regist;

import java.util.List;

import omgimbot.app.sidangapps.features.admin.dosen.model.listPenguji;

public interface IRegistView {
    void onRegistSuccess();

    void onRegistFailed(String rm);

    void onDataReady(List<listPenguji> result);

    void onRegist();

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void goToLogin();
}
