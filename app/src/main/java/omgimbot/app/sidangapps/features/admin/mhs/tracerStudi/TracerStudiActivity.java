package omgimbot.app.sidangapps.features.admin.mhs.tracerStudi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.mindorks.placeholderview.ViewAdapter;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.Utils.CommonRespon;
import omgimbot.app.sidangapps.features.admin.dashboard.DashboardAdminActivity;
import omgimbot.app.sidangapps.features.admin.dosen.model.listPenguji;
import omgimbot.app.sidangapps.features.admin.mhs.model.ListPengujiMhs;
import omgimbot.app.sidangapps.features.admin.mhs.tracerStudi.model.TracerStudi;
import omgimbot.app.sidangapps.features.auth.login.model.Users;
import omgimbot.app.sidangapps.features.mhs.dashboard.DashboardMhsActivity;
import omgimbot.app.sidangapps.ui.SweetDialogs;
import omgimbot.app.sidangapps.ui.TopSnakbar;

public class TracerStudiActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ITracerStudiView {
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @BindView(R.id.mNama)
    EditText mNama;
    @BindView(R.id.mNpm)
    EditText mNpm;
    @BindView(R.id.mTempatLahir)
    EditText mTempatLahir;
    @BindView(R.id.mTglLahir)
    EditText mTglLahir;
    @BindView(R.id.mBtnDate)
    ImageButton mBtnDate;
    @BindView(R.id.mGender)
    EditText mGender;
    @BindView(R.id.mSuku)
    EditText mSuku;
    @BindView(R.id.mAlamat)
    EditText mAlamat;
    @BindView(R.id.mNohp)
    EditText mNohp;
    @BindView(R.id.mFakultas)
    Spinner mFakultas;
    @BindView(R.id.mProdi)
    Spinner mProdi;
    @BindView(R.id.mTglMasuk)
    EditText mTglMasuk;
    @BindView(R.id.mBtnDateMasuk)
    ImageButton mBtnDateMasuk;
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
    @BindView(R.id.mBtnDateLulus)
    ImageButton mBtnDateLulus;
    @BindView(R.id.mIpk)
    EditText mIpk;
    @BindView(R.id.mPredikat)
    EditText mPredikat;
    @BindView(R.id.mPekerjaan)
    EditText mPekerjaan;
    @BindView(R.id.mTglMasukKerja)
    EditText mTglMasukKerja;
    @BindView(R.id.mBtnDateMasukKerja)
    ImageButton mBtnMasukKerja;
    @BindView(R.id.mSubmit)
    Button mSubmit;

    SweetAlertDialog sweetAlertDialog;
    TracerStudiPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracer_studi);
        ButterKnife.bind(this);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Input Biodata Anda");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        this.initView();

        presenter = new TracerStudiPresenter(this);

        this.initView();
    }


    @Override
    public void initView() {
        ArrayAdapter<CharSequence> adapterFakultas = ArrayAdapter.createFromResource(this,
                R.array.fakultas, android.R.layout.simple_spinner_item);
        adapterFakultas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFakultas.setAdapter(adapterFakultas);
        mFakultas.setOnItemSelectedListener(this);

        mSubmit.setOnClickListener(View-> onSubmit());

        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Loading ...");

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

    }

    @Override
    public void goToDashboard() {
        Intent a = new Intent(this, DashboardMhsActivity.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onSubmit() {

        if (!mNama.equals("") && !mNpm.equals("") && !mTempatLahir.equals("") && !mTglLahir.equals("") && !mGender.equals("") && !mSuku.equals("") && !mAlamat.equals("")
                && !mNohp.equals("") && !mFakultas.equals("") && !mProdi.equals("") && !mTglMasuk.equals("") && !mPrestasi.equals("") && !mJudulSkripsi.equals("")
                && !mTglMasuk.equals("") && !mPrestasi.equals("") && !mJudulSkripsi.equals("") && !mPembibing1.equals("") && !mPembimbing2.equals("") && !mTglLulus.equals("")
                && !mIpk.equals("") && !mPredikat.equals("") && !mPekerjaan.equals("") && !mTglMasukKerja.equals("")) {

            TracerStudi model = new TracerStudi();

            model.setNama(mNama.getText().toString());
            model.setNpm(mNpm.getText().toString());
            model.setTempatLahir(mTempatLahir.getText().toString());
            model.setTanggalLahir(mTglLahir.getText().toString());
            model.setGender(mGender.getText().toString());
            model.setSuku(mSuku.getText().toString());
            model.setAlamat(mAlamat.getText().toString());
            model.setNoHp(mNohp.getText().toString());
            model.setFak(mFakultas.getSelectedItem().toString());
            model.setProdi(mProdi.getSelectedItem().toString());
            model.setTglMasuk(mTglMasuk.getText().toString());
            model.setPrestasi(mPrestasi.getText().toString());
            model.setJudulSkripsi(mJudulSkripsi.getText().toString());
            model.setPembimbing1(mPembibing1.getText().toString());
            model.setPembimbing2(mPembimbing2.getText().toString());
            model.setTglLulus(mTglLulus.getText().toString());
            model.setIpk(mIpk.getText().toString());
            model.setPredikat(mPredikat.getText().toString());
            model.setPekerjaan(mPekerjaan.getText().toString());
            model.setTglMasukKerja(mTglMasukKerja.getText().toString());

            presenter.createTracerStudi(model);

        } else {
            TopSnakbar.showWarning(this, "Data Tidak Boleh Kosong !");
        }

//        presenter.signup(model);

    }

    @Override
    public void onSubmitSuccess(CommonRespon body) {
        SweetDialogs.commonSuccessWithIntent(this , "" , string -> this.goToDashboard());
    }

    @Override
    public void onSubmitFailed(String rm) {
        SweetDialogs.commonError(this,rm , false);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.mFakultas:

                int list = 0 ;
                if(mFakultas.getSelectedItem().toString().equals("Fakultas Adab"))
                    list = R.array.adab;
                else  if(mFakultas.getSelectedItem().toString().equals("Fakultas Tarbiyah dan Keguruan"))
                    list = R.array.tarbiyah;
                else  if(mFakultas.getSelectedItem().toString().equals("Fakultas Ekonomi dan Bisnis Islam"))
                    list = R.array.ekonomi;
                else  if(mFakultas.getSelectedItem().toString().equals("Dakwah dan Ilmu Komunikasi"))
                    list = R.array.dakwah;
                else  if(mFakultas.getSelectedItem().toString().equals("Fakultas Ushuluddin dan Studi Agama"))
                    list = R.array.ushuluddin;
                else  if(mFakultas.getSelectedItem().toString().equals("Fakultas Syariah"))
                    list = R.array.syariah;
                else  if(mFakultas.getSelectedItem().toString().equals("Program S2"))
                    list = R.array.s2;
                else  if(mFakultas.getSelectedItem().toString().equals("Program S3"))
                    list = R.array.s3;

                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        list, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mProdi.setAdapter(adapter);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void showDateDialog(View v) {

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                if (v == mBtnDate)
                    mTglLahir.setText(dateFormatter.format(newDate.getTime()));
                else if (v == mBtnDateMasuk)
                    mTglMasuk.setText(dateFormatter.format(newDate.getTime()));
                else if (v == mBtnDateLulus)
                    mTglLulus.setText(dateFormatter.format(newDate.getTime()));
                else if (v == mBtnMasukKerja)
                    mTglMasukKerja.setText(dateFormatter.format(newDate.getTime()));

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }

    @Override
    public void onBackPressed() {
        // ...

        this.goToDashboard();
        super.onBackPressed();
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

}
