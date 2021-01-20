package omgimbot.app.sidangapps.features.mhs.kompre;

import omgimbot.app.sidangapps.features.mhs.model.daftarModel;

public interface IKompreMhsView {
    void initViews();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onCekPenguji(Boolean ada);

    void onNetworkError(String cause);
}
