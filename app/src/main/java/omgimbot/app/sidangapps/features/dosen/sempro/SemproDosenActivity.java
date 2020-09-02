package omgimbot.app.sidangapps.features.dosen.sempro;

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
import android.widget.Toast;

import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import omgimbot.app.sidangapps.App;
import omgimbot.app.sidangapps.Prefs;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.Utils.CommonRespon;
import omgimbot.app.sidangapps.Utils.FileUtils;
import omgimbot.app.sidangapps.Utils.GsonHelper;
import omgimbot.app.sidangapps.features.auth.login.model.LoginResponse;
import omgimbot.app.sidangapps.features.dosen.PengajuanAdapter;
import omgimbot.app.sidangapps.features.dosen.PengajuanPresenter;
import omgimbot.app.sidangapps.features.dosen.dashboard.DashboardDosenActivity;
import omgimbot.app.sidangapps.features.dosen.judul.IJudulDosenView;
import omgimbot.app.sidangapps.features.dosen.judul.JudulDosenActivity;
import omgimbot.app.sidangapps.features.mhs.model.daftarModel;
import omgimbot.app.sidangapps.ui.SweetDialogs;

public class SemproDosenActivity extends AppCompatActivity implements IJudulDosenView ,PengajuanAdapter.onSelected {
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    SweetAlertDialog sweetAlertDialog;
    public PengajuanAdapter adapter;
    public PengajuanPresenter presenter;
    String key = "sempro" ;
    daftarModel models;
    File myFile;
    String path, path2, dosen, statuss;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sempro_dosen);
        ButterKnife.bind(this);
        presenter = new PengajuanPresenter(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Pengajuan Sempro");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        this.initView();
        LoginResponse mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        String nim = (mProfile.getResult().getUsername().contains(" "))
                ? mProfile.getResult().getUsername() : mProfile.getResult().getUsername();
        presenter.getListPengajuan(key,nim);

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
    public void onDataReady(List<daftarModel> result) {
        Log.d("data" , new Gson().toJson(result));
        adapter = new PengajuanAdapter(result, this,this);
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
        Intent a = new Intent(this, SemproDosenActivity.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed() {
        // ...

        this.goToDashboard();
        super.onBackPressed();
    }

    @Override
    public void onSubmit(daftarModel data, String status) {
        models = data;
        models.setStatus(status);

        choseFile();

    }

    @Override
    public void onTolak(daftarModel data, String status) {
        presenter.tolak(data.getNim(),status,key);

    }

    public void choseFile() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    1);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            uri = data.getData();
            path = FileUtils.getFileName(this, uri);
            myFile = new File(path);
            path2 = FileUtils.getFilePathFromURI(this, uri);
            presenter.Acc(this, path2, models, myFile, uri, key);
            Log.d("DATANYA", new Gson().toJson(models));
        }


    }

    @Override
    public void onAccSuccess(CommonRespon result) {
        SweetDialogs.commonSuccessWithIntent(this , "" , string -> this.refresh());
    }

    @Override
    public void onDownload(daftarModel data) {
        DownloadManager downloadmanager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        String uriDownload = App.getApplication().getString(R.string.end_point)+data.getFilename();
        Log.d("urlnya" , uriDownload);
        Uri uri = Uri.parse(uriDownload);

        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle(data.getNama()+"-"+data.getNim());
        request.setDescription("Downloading");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setVisibleInDownloadsUi(false);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + "sidangApps/");
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,data.getNama()+"-"+data.getNim()+".pdf");

        downloadmanager.enqueue(request);
    }

}