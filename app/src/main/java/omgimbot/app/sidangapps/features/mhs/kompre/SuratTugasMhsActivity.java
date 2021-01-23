package omgimbot.app.sidangapps.features.mhs.kompre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Timestamp;
import java.sql.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import omgimbot.app.sidangapps.App;
import omgimbot.app.sidangapps.Prefs;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.Utils.GsonHelper;
import omgimbot.app.sidangapps.features.auth.login.model.LoginResponse;
import omgimbot.app.sidangapps.features.mhs.dashboard.DashboardMhsActivity;
import omgimbot.app.sidangapps.features.mhs.judul.JudulMhsActivity;
import omgimbot.app.sidangapps.features.mhs.judul.JudulMhsPresenter;
import omgimbot.app.sidangapps.ui.SweetDialogs;
import omgimbot.app.sidangapps.ui.TopSnakbar;

public class SuratTugasMhsActivity extends AppCompatActivity implements IKompreMhsView{

    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;

    @BindView(R.id.mDaftarKompre)
    Button mDaftarKompre;
    @BindView(R.id.mDownloadSuratTugas)
    Button mDownloadSuratTugas;

    KompreMhsPresenter presenter;
    private LoginResponse mProfile;
    SweetAlertDialog sweetAlertDialog;
    String nim, namaMhs;

    DownloadManager downloadManager;


    String key = "Menu Kompre";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surat_tugas_mhs);
        ButterKnife.bind(this);


        presenter = new KompreMhsPresenter(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(key);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
        }
    }

    @OnClick(R.id.mDownloadSuratTugas)
    void generatePdf(){
        this.initViews();
    }

    @Override
    public void initViews() {
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Loading ...");

        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        namaMhs = (mProfile.getResult().getNama().contains(" "))
                ? mProfile.getResult().getNama() : mProfile.getResult().getNama();
        nim = (mProfile.getResult().getUsername().contains(" "))
                ? mProfile.getResult().getUsername() : mProfile.getResult().getUsername();
        presenter.cekPenguji(nim);
    }

    @Override
    public void onCekPenguji(Boolean ada) {
        if (ada.toString() == "true"){
            SweetDialogs.commonWarningWithIntent(this, "Download!!", "Download Sekarang?", String -> this.doDownload());
        } else {
            SweetDialogs.commonWarning(this, "Tombol Download Tidak Aktif!!", "Anda Belum Mendaftar Atau Dosen Penguji Belum Di Input Oleh Admin", false);
        }

    }


    private void doDownload() {

        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        String uriDownload = App.getApplication().getString(R.string.end_point)+"exportpdf/"+nim;
        Uri uri = Uri.parse(uriDownload);
        try {
            DownloadManager.Request request = new DownloadManager.Request(uri);
            //Setting title of request
            request.setTitle("Surat-Tugas-"+namaMhs+"-"+nim+".pdf");
            //Setting description of request
            request.setDescription("Your file is downloading");
            //set notification when download completed
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            //Set the local destination for the downloaded file to a path within the application's external files directory
            //request.setDestinationInExternalPublicDir(fileStorageDestinationUri, fileName);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"Surat-Tugas-"+namaMhs+"-"+nim+".pdf");
            request.allowScanningByMediaScanner();
            File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + "sidangApps/");
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }
            //Enqueue download and save the referenceId
            downloadManager.enqueue(request);
        } catch (IllegalArgumentException e) {
            Toast.makeText(this, "Download link is broken or not availale for download", Toast.LENGTH_SHORT).show();
        }
//        return downloadReference;

//        DownloadManager downloadmanager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);String uriDownload = App.getApplication().getString(R.string.end_point)+"exportpdf/"+nim;
////        Log.d("urlnya" , uriDownload);
//        Uri uri = Uri.parse(uriDownload);
//
//        DownloadManager.Request request = new DownloadManager.Request(uri);
//        request.setTitle("Surat-Tugas-"+namaMhs+"-"+nim+".pdf");
//        request.setDescription("Downloading");
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        request.setVisibleInDownloadsUi(false);
//        File wallpaperDirectory = new File(
//                Environment.getExternalStorageDirectory() + "sidangApps/");
//        // have the object build the directory structure, if needed.
//        if (!wallpaperDirectory.exists()) {
//            wallpaperDirectory.mkdirs();
//        }
//        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"Surat-Tugas-"+namaMhs+"-"+nim+".pdf");
//        try {
//            downloadmanager.enqueue(request);
//        }catch (Exception e)
//        {
//            Log.d("Errornya" , e.getLocalizedMessage());
//        }
    }

    @OnClick(R.id.mDaftarKompre)
    void goToKompre() {
        startActivity(new Intent(this , KompreMhsActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        // ...
        this.goToDashboard();
        super.onBackPressed();
    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.goToDashboard();
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToDashboard() {
        Intent i = new Intent(this, DashboardMhsActivity.class);
        startActivity(i);
        finish();
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
    public void onNetworkError(String cause) {
        Log.e("errornya", cause);
        SweetDialogs.endpointError(this);
    }
}