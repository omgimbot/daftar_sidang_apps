package omgimbot.app.sidangapps.features.mhs.judul;

import omgimbot.app.sidangapps.features.mhs.model.daftarModel;

public interface IJudulMhsView {
    void initViews();

    void onUploadSuccess();

    void onNetworkError(String cause);

    void onCekJudul(daftarModel data);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void goToDashboard();
}
