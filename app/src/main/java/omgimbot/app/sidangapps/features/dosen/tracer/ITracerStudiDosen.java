package omgimbot.app.sidangapps.features.dosen.tracer;

import android.app.Activity;

import java.util.List;

import omgimbot.app.sidangapps.Utils.CommonRespon;
import omgimbot.app.sidangapps.features.admin.mhs.tracerStudi.model.TracerStudi;
import omgimbot.app.sidangapps.features.mhs.model.daftarModel;

public interface ITracerStudiDosen {
    void initView();

    void clearLightStatusBar(Activity activity);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<TracerStudi> result);

    void onRequestFailed(String rm);

    void onNetworkError(String cause);

    void goToDashboard();

    void refresh();
}
