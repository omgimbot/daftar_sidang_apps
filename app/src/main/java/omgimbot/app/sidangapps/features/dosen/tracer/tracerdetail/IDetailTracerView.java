package omgimbot.app.sidangapps.features.dosen.tracer.tracerdetail;

import java.util.List;

import omgimbot.app.sidangapps.Utils.CommonRespon;
import omgimbot.app.sidangapps.features.admin.dosen.model.listPenguji;
import omgimbot.app.sidangapps.features.admin.mhs.model.ListPengujiMhs;
import omgimbot.app.sidangapps.features.admin.mhs.tracerStudi.model.TracerStudi;
import omgimbot.app.sidangapps.features.dosen.tracer.tracerdetail.model.DetailTracerStudi;

public interface IDetailTracerView {
    void initView();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(DetailTracerStudi result);

    void onNetworkError(String cause);

    void goToDashboard();
}
