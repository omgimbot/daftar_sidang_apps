package omgimbot.app.sidangapps.features.dosen.tracer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;

import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import omgimbot.app.sidangapps.App;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.features.admin.mhs.tracerStudi.TracerStudiActivity;
import omgimbot.app.sidangapps.features.admin.mhs.tracerStudi.model.TracerStudi;
import omgimbot.app.sidangapps.features.dosen.dashboard.DashboardDosenActivity;
import omgimbot.app.sidangapps.features.dosen.tracer.tracerdetail.DetailTracerActivity;
import omgimbot.app.sidangapps.ui.SweetDialogs;

public class TracerStudiDosenActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ITracerStudiDosen, TracerStudiDosenAdapter.onSelected {

    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.mPrint)
    Button mPrint;

    SweetAlertDialog sweetAlertDialog;
    public TracerStudiDosenAdapter adapter;
    public TracerStudiDosenPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracer_studi_dosen);

        ButterKnife.bind(this);
        presenter = new TracerStudiDosenPresenter(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Tracer Study");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        this.initView();

        presenter.getListTracer();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void initView() {
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.clearFocus();

    }

    @Override
    public void clearLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            activity.getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void showLoadingIndicator() {
        sweetAlertDialog.show();
    }

    @Override
    public void hideLoadingIndicator() {
        sweetAlertDialog.dismiss();
    }

    @Override
    public void onDataReady(List<TracerStudi> result) {
        Log.d("data" , new Gson().toJson(result));
        adapter = new TracerStudiDosenAdapter(result, this,this);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestFailed(String rm) {
        SweetDialogs.commonError(this, "Gagal Memuat Permintaan", rm, string -> {
            this.goToDashboard();
        });
    }

    @Override
    public void onNetworkError(String cause) {
        Log.d("Error", cause);
        SweetDialogs.endpointError(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void goToDashboard() {
        Intent a = new Intent(this, DashboardDosenActivity.class);
        startActivity(a);
        finish();
    }

    @Override
    public void refresh() {
        Intent a = new Intent(this, TracerStudiActivity.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed() {
        // ...

        this.goToDashboard();
        super.onBackPressed();

    }

    @OnClick(R.id.mPrint)
    void  generateExcel() {
        SweetDialogs.commonWarningWithIntent(this, "Download...", "Download Berhasil!!", view -> this.doDownload());
    }

    private void doDownload() {
        DownloadManager downloadmanager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        String uriDownload = App.getApplication().getString(R.string.end_point)+"exportexcel";
        Log.d("urlnya" , uriDownload);
        Uri uri = Uri.parse(uriDownload);

        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle("Tracer-Study");
        request.setDescription("Downloading");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setVisibleInDownloadsUi(false);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + "sidangApps/");
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"Tracer-Study.xlxs");

        downloadmanager.enqueue(request);
    }


    @Override
    public void onClickDetail( TracerStudi data ) {
        Intent a = new Intent(TracerStudiDosenActivity.this, DetailTracerActivity.class);
        a.putExtra("nama", data.getNama()) ;
        a.putExtra("nim", data.getNpm());
        startActivity(a);
        finish();

//        System.out.println(data.getNpm());
    }
}
