package omgimbot.app.sidangapps.features.admin.mhs.tracerStudi;

import java.util.List;

import omgimbot.app.sidangapps.Utils.CommonRespon;
import omgimbot.app.sidangapps.features.admin.dosen.model.listPenguji;
import omgimbot.app.sidangapps.features.admin.mhs.model.ListPengujiMhs;

public interface ITracerStudiView {
    void initView();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause);

    void goToDashboard();

    void onSubmit();

    void onSubmitSuccess(CommonRespon result);

    void onSubmitFailed(String rm);
}
