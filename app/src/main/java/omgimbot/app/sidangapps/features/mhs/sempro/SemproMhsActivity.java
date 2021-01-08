package omgimbot.app.sidangapps.features.mhs.sempro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import omgimbot.app.sidangapps.App;
import omgimbot.app.sidangapps.Prefs;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.Utils.FileUtils;
import omgimbot.app.sidangapps.Utils.GsonHelper;
import omgimbot.app.sidangapps.features.auth.login.model.LoginResponse;
import omgimbot.app.sidangapps.features.mhs.dashboard.DashboardMhsActivity;
import omgimbot.app.sidangapps.features.mhs.judul.IJudulMhsView;
import omgimbot.app.sidangapps.features.mhs.judul.JudulMhsPresenter;
import omgimbot.app.sidangapps.features.mhs.model.daftarModel;
import omgimbot.app.sidangapps.ui.SweetDialogs;
import omgimbot.app.sidangapps.ui.TopSnakbar;

public class SemproMhsActivity extends AppCompatActivity implements IJudulMhsView {
    @BindView(R.id.mNama)
    TextView mNama;
    @BindView(R.id.mNim)
    TextView mNim;
    @BindView(R.id.mProdi)
    TextView mProdi;
    @BindView(R.id.mFakultas)
    TextView mFakultas;
    @BindView(R.id.mJudul)
    EditText mJudul;
    @BindView(R.id.mFile)
    TextView mFile;
    @BindView(R.id.mChosefile)
    ImageButton mChosefile;
    private LoginResponse mProfile;
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    SweetAlertDialog sweetAlertDialog;
    JudulMhsPresenter presenter;
    File myFile;
    String path, path2, nim, dosen;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sempro_mhs);
        ButterKnife.bind(this);
        presenter = new JudulMhsPresenter(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Pendaftaran Sempro");
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
        String nama = (mProfile.getResult().getNama().contains(" "))
                ? mProfile.getResult().getNama() : mProfile.getResult().getNama();
        nim = (mProfile.getResult().getUsername().contains(" "))
                ? mProfile.getResult().getUsername() : mProfile.getResult().getUsername();
        String prodi = (mProfile.getResult().getProdi().contains(" "))
                ? mProfile.getResult().getProdi() : mProfile.getResult().getProdi();
        String fakultas = (mProfile.getResult().getFakultas().contains(" "))
                ? mProfile.getResult().getFakultas() : mProfile.getResult().getFakultas();
        dosen = (mProfile.getResult().getDosen().contains(" "))
                ? mProfile.getResult().getDosen() : mProfile.getResult().getDosen();
        mNama.setText(nama);
        mNim.setText(nim);
        mFakultas.setText(fakultas);
        mProdi.setText(prodi);
        mNama.setEnabled(false);
        mNim.setEnabled(false);
        mFakultas.setEnabled(false);
        mProdi.setEnabled(false);
        mFile.setEnabled(false);
//        presenter.cekJudul(nim, "sempro");

    }

    @OnClick(R.id.mChosefile)
    void choseFile() {
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

//        Intent i = new Intent(Intent.ACTION_PICK, MediaStore..Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(i, 1);
    }

    @OnClick(R.id.mSubmit)
    void uploadFile() {
        String file = mFile.getText().toString();
        String judul = mJudul.getText().toString();
        if (!file.equals("") && !judul.equals("")) {
            daftarModel model = new daftarModel();
            model.setNama(mNama.getText().toString());
            model.setJudul(mJudul.getText().toString());
            model.setNim(mNim.getText().toString());
            model.setFile(mFile.getText().toString());
            model.setStatus("pending");
            model.setDosen(dosen);
            model.setKey("sempro");
            presenter.daftarJudul(this, model, myFile, path2, uri);
        } else
            TopSnakbar.showWarning(this, "Pastikan anda sudah memilih file dan mengisi judul");

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            uri = data.getData();
            path = FileUtils.getFileName(this, uri);
            myFile = new File(path);
            path2 = FileUtils.getFilePathFromURI(this, uri);
            if (path != null) {
                mFile.setText(myFile.getName());
            }

        }

    }

    @Override
    public void onUploadSuccess() {
//        Log.e("errornya", cause);
        SweetDialogs.commonSuccess(this, "File anda berhasil diupload", true);
    }

    @Override
    public void onNetworkError(String cause) {
        Log.e("errornya", cause);
        SweetDialogs.endpointError(this);
    }

    @Override
    public void onCekJudul(daftarModel data) {
//        Log.d("statusnya" ,new Gson().toJson(data));
        if (data != null) {
            if (!data.getStatus().equals("acc")) {
                SweetDialogs.commonWarningWithIntent(this, "Gagal memuat permintaan", "Anda harus mendapatkan Acc judul terlebih dahulu", view -> this.goToDashboard());
            } else {
                mJudul.setText(data.getJudul());
                mJudul.setEnabled(false);
            }
        } else {
            SweetDialogs.commonWarningWithIntent(this, "Gagal memuat permintaan", "Anda harus mengajukan judul terlebih dahulu", view -> this.goToDashboard());
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

    @Override
    public void goToDashboard() {
        Intent i = new Intent(this, DashboardMhsActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10001: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permisson Granted", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(this, "Permission Denied ", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            }
        }
    }
}
