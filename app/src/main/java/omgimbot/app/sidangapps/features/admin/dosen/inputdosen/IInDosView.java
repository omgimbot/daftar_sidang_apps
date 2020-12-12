package omgimbot.app.sidangapps.features.admin.dosen.inputdosen;

import java.util.List;

import omgimbot.app.sidangapps.features.admin.dosen.model.listMk;
import omgimbot.app.sidangapps.features.admin.dosen.model.listPenguji;

public interface IInDosView {

    void onDataReady(List<listMk> model);

    void onSubmit();

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onSubmitSuccess();

    void onSubmitFailed(String rm);

    void onGetListDosen(List<listPenguji> result);
}
