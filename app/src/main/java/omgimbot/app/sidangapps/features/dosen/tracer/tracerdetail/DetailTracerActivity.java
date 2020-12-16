package omgimbot.app.sidangapps.features.dosen.tracer.tracerdetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import omgimbot.app.sidangapps.App;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.features.admin.mhs.model.ListPengujiMhs;
import omgimbot.app.sidangapps.features.admin.mhs.pilihpenguji.PilihPengujiPresenter;
import omgimbot.app.sidangapps.features.admin.mhs.tracerStudi.model.TracerStudi;
import omgimbot.app.sidangapps.features.dosen.tracer.TracerStudiDosenActivity;
import omgimbot.app.sidangapps.features.dosen.tracer.tracerdetail.model.DetailTracerStudi;
import omgimbot.app.sidangapps.ui.SweetDialogs;

public class DetailTracerActivity extends AppCompatActivity implements IDetailTracerView, AdapterView.OnItemSelectedListener{

    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;

    @BindView(R.id.mNama)
    EditText mNama;
    @BindView(R.id.mNpm)
    EditText mNpm;
    @BindView(R.id.mTempatLahir)
    EditText mTempatLahir;
    @BindView(R.id.mTglLahir)
    EditText mTglLahir;
    @BindView(R.id.mGender)
    EditText mGender;
    @BindView(R.id.mSuku)
    EditText mSuku;
    @BindView(R.id.mAlamat)
    EditText mAlamat;
    @BindView(R.id.mNohp)
    EditText mNohp;
    @BindView(R.id.mFakultas)
    EditText mFakultas;
    @BindView(R.id.mProdi)
    EditText mProdi;
    @BindView(R.id.mTglMasuk)
    EditText mTglMasuk;
    @BindView(R.id.mPrestasi)
    EditText mPrestasi;
    @BindView(R.id.mJudulSkripsi)
    EditText mJudulSkripsi;
    @BindView(R.id.mPembibing1)
    EditText mPembibing1;
    @BindView(R.id.mPembimbing2)
    EditText mPembimbing2;
    @BindView(R.id.mTglLulus)
    EditText mTglLulus;
    @BindView(R.id.mIpk)
    EditText mIpk;
    @BindView(R.id.mPredikat)
    EditText mPredikat;
    @BindView(R.id.mPekerjaan)
    EditText mPekerjaan;
    @BindView(R.id.mTglMasukKerja)
    EditText mTglMasukKerja;

    SweetAlertDialog sweetAlertDialog;
    public DetailTracerPresenter presenter;

    String namaMhs, nim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tracer);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            namaMhs = extras.getString("nama");
            nim = extras.getString("nim");
        }

//        System.out.println(nim);
        presenter = new DetailTracerPresenter(this);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle(namaMhs);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        this.initView();
        presenter.getListTracerByNim(nim);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void initView() {
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);

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
    public void showLoadingIndicator() {
        sweetAlertDialog.show();
    }

    @Override
    public void hideLoadingIndicator() {
        sweetAlertDialog.dismiss();
    }

    @Override
    public void onDataReady(DetailTracerStudi result) {
        Log.d("tracer", new Gson().toJson(result));
//        for (DetailTracerStudi result : result) {
            mNama.setText(result.getNama());
            mNpm.setText(result.getNpm());
            mTempatLahir.setText(result.getTempatLahir());
            mTglLahir.setText(result.getTanggalLahir());
            mGender.setText(result.getGender());
            mSuku.setText(result.getSuku());
            mAlamat.setText(result.getAlamat());
            mNohp.setText(result.getNoHp());
            mFakultas.setText(result.getFak());
            mProdi.setText(result.getProdi());
            mTglMasuk.setText(result.getTglMasuk());
            mPrestasi.setText(result.getPrestasi());
            mJudulSkripsi.setText(result.getJudulSkripsi());
            mPembibing1.setText(result.getPembimbing1());
            mPembimbing2.setText(result.getPembimbing2());
            mTglLulus.setText(result.getTglLulus());
            mIpk.setText(result.getIpk());
            mPredikat.setText(result.getPredikat());
            mPekerjaan.setText(result.getPekerjaan());
            mTglMasukKerja.setText(result.getTglMasukKerja());
//        }
    }

    @Override
    public void onNetworkError(String cause) {
        Log.d("Error", cause);
        SweetDialogs.endpointError(this);
    }

    @Override
    public void goToDashboard() {
        Intent a = new Intent(DetailTracerActivity.this, TracerStudiDosenActivity.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed() {
        // ...

        this.goToDashboard();
        super.onBackPressed();
    }
}
