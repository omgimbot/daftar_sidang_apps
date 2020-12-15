package omgimbot.app.sidangapps.features.admin.dosen.inputdosen;

import java.util.List;

import omgimbot.app.sidangapps.Utils.CommonRespon;
import omgimbot.app.sidangapps.features.admin.dosen.model.listMk;
import omgimbot.app.sidangapps.features.admin.dosen.model.listPenguji;

public interface IInDosView {

    void onDataReady(List<listMk> model);

    void onSubmit();

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onSubmitSuccess(CommonRespon result);

    void onSubmitFailed(String rm);

    void onGetListDosen(List<listPenguji> result);

    void onDeleteSuccess(CommonRespon result);

    void onDeleteFailed(String rm);
}
