package omgimbot.app.sidangapps.features.admin.mhs.pilihpenguji;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import omgimbot.app.sidangapps.App;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.Utils.CommonRespon;
import omgimbot.app.sidangapps.Utils.LinkedHashMapAdapter;
import omgimbot.app.sidangapps.features.admin.MhsKompreAdapter;
import omgimbot.app.sidangapps.features.admin.MhsKomprePresenter;
import omgimbot.app.sidangapps.features.admin.dosen.model.dosenPenguji;
import omgimbot.app.sidangapps.features.admin.dosen.model.listMk;
import omgimbot.app.sidangapps.features.admin.dosen.model.listPenguji;
import omgimbot.app.sidangapps.features.admin.mhs.IMhsAdminView;
import omgimbot.app.sidangapps.features.admin.mhs.MahasiswaAdminActivity;
import omgimbot.app.sidangapps.features.admin.mhs.model.ListPengujiMhs;
import omgimbot.app.sidangapps.features.admin.mhs.model.MPengujiMhs;
import omgimbot.app.sidangapps.features.mhs.model.daftarModel;
import omgimbot.app.sidangapps.ui.SweetDialogs;
import omgimbot.app.sidangapps.ui.TopSnakbar;

public class PilihPengujiMhsActivity extends AppCompatActivity implements IPilihPengujiView, AdapterView.OnItemSelectedListener {

    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    @BindView(R.id.mListDosenmk1)
    Spinner mListDosenmk1;
    @BindView(R.id.mListDosenmk2)
    Spinner mListDosenmk2;
    @BindView(R.id.mListDosenmk3)
    Spinner mListDosenmk3;
    @BindView(R.id.mListDosenmk4)
    Spinner mListDosenmk4;
    @BindView(R.id.mListDosenmk5)
    Spinner mListDosenmk5;
    @BindView(R.id.mListDosenmk6)
    Spinner mListDosenmk6;
    @BindView(R.id.mSubmit)
    Button mSubmit;

    @BindView(R.id.mMk1)
    TextView mMk1;
    @BindView(R.id.mMk2)
    TextView mMk2;
    @BindView(R.id.mMk3)
    TextView mMk3;
    @BindView(R.id.mMk4)
    TextView mMk4;
    @BindView(R.id.mMk5)
    TextView mMk5;
    @BindView(R.id.mMk6)
    TextView mMk6;

    SweetAlertDialog sweetAlertDialog;
    public MhsKompreAdapter adapter;
    public PilihPengujiPresenter presenter;

    String namaMhs, nimMhs, nidn1 ,nidn2 , nidn3,nidn4,nidn5,nidn6;
    private LinkedHashMap<String, String>  listdosen1,listdosen2,listdosen3,listdosen4,listdosen5,listdosen6;
    private LinkedHashMapAdapter<String, String> adapterDosen1,adapterDosen2,adapterDosen3,adapterDosen4,adapterDosen5,adapterDosen6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_penguji_mhs);

        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            namaMhs = extras.getString("nama");
            nimMhs = extras.getString("nim");
            // and get whatever type user account id is
        }
        presenter = new PilihPengujiPresenter(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(namaMhs);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        this.initView();
        presenter.getListPenguji();
        presenter.getListPengujiMhs(nimMhs);
    }

    @Override
    public void initView() {
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);

        mSubmit.setOnClickListener(view -> onSubmit());
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDataReady(List<listPenguji> result) {
        List <listPenguji> d ;
        listdosen1 = new LinkedHashMap<String, String>();
        listdosen2 = new LinkedHashMap<String, String>();
        listdosen3 = new LinkedHashMap<String, String>();
        listdosen4 = new LinkedHashMap<String, String>();
        listdosen5 = new LinkedHashMap<String, String>();
        listdosen6 = new LinkedHashMap<String, String>();
        for (listPenguji dosen : result){
            if(dosen.getKodeMk().equals("mk1"))
                listdosen1.put(dosen.getNim(), dosen.getNama());
            else if(dosen.getKodeMk().equals("mk2"))
                listdosen2.put(dosen.getNim(), dosen.getNama());
            else if(dosen.getKodeMk().equals("mk3"))
                listdosen3.put(dosen.getNim(), dosen.getNama());
            else if(dosen.getKodeMk().equals("mk4"))
                listdosen4.put(dosen.getNim(), dosen.getNama());
            else if(dosen.getKodeMk().equals("mk5"))
                listdosen5.put(dosen.getNim(), dosen.getNama());
            else if(dosen.getKodeMk().equals("mk6"))
                listdosen6.put(dosen.getNim(), dosen.getNama());

        }

        adapterDosen1 = new LinkedHashMapAdapter<String, String>(this, android.R.layout.simple_spinner_item, listdosen1);
        adapterDosen1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterDosen2 = new LinkedHashMapAdapter<String, String>(this, android.R.layout.simple_spinner_item, listdosen2);
        adapterDosen2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterDosen3 = new LinkedHashMapAdapter<String, String>(this, android.R.layout.simple_spinner_item, listdosen3);
        adapterDosen3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterDosen4 = new LinkedHashMapAdapter<String, String>(this, android.R.layout.simple_spinner_item, listdosen4);
        adapterDosen4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterDosen5 = new LinkedHashMapAdapter<String, String>(this, android.R.layout.simple_spinner_item, listdosen5);
        adapterDosen5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterDosen6 = new LinkedHashMapAdapter<String, String>(this, android.R.layout.simple_spinner_item, listdosen6);
        adapterDosen1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterDosen2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterDosen3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterDosen4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterDosen5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterDosen6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mListDosenmk1.setAdapter(adapterDosen1);
        mListDosenmk2.setAdapter(adapterDosen2);
        mListDosenmk3.setAdapter(adapterDosen3);
        mListDosenmk4.setAdapter(adapterDosen4);
        mListDosenmk5.setAdapter(adapterDosen5);
        mListDosenmk6.setAdapter(adapterDosen6);

        mListDosenmk1.setOnItemSelectedListener(this);
        mListDosenmk2.setOnItemSelectedListener(this);
        mListDosenmk3.setOnItemSelectedListener(this);
        mListDosenmk4.setOnItemSelectedListener(this);
        mListDosenmk5.setOnItemSelectedListener(this);
        mListDosenmk6.setOnItemSelectedListener(this);

    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {

            case R.id.mListDosenmk1:
                Map.Entry<String, String> item1 = (Map.Entry<String, String>) mListDosenmk1.getSelectedItem();
                 nidn1 = item1.getKey();
                break;
            case R.id.mListDosenmk2:
                Map.Entry<String, String> item2 = (Map.Entry<String, String>) mListDosenmk2.getSelectedItem();
                nidn2 = item2.getKey();
                break;
            case R.id.mListDosenmk3:
                Map.Entry<String, String> item3 = (Map.Entry<String, String>) mListDosenmk3.getSelectedItem();
                nidn3 = item3.getKey();
                break;
            case R.id.mListDosenmk4:
                Map.Entry<String, String> item4 = (Map.Entry<String, String>) mListDosenmk4.getSelectedItem();
                nidn4 = item4.getKey();
                break;
            case R.id.mListDosenmk5:
                Map.Entry<String, String> item5 = (Map.Entry<String, String>) mListDosenmk5.getSelectedItem();
                nidn5 = item5.getKey();
                break;
            case R.id.mListDosenmk6:
                Map.Entry<String, String> item6 = (Map.Entry<String, String>) mListDosenmk6.getSelectedItem();
                nidn6 = item6.getKey();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onNetworkError(String cause) {
        Log.d("Error", cause);
        SweetDialogs.endpointError(this);
    }

    @Override
    public void goToDashboard() {
        Intent a = new Intent(PilihPengujiMhsActivity.this, MahasiswaAdminActivity.class);
            startActivity(a);
            finish();
    }

    @Override
    public void onSubmit() {
        String nama, nim;
        nama = namaMhs;
        nim = nimMhs;



        MPengujiMhs model = new MPengujiMhs();
        model.setNamaMhs(nama);
        model.setNim(nim);
        model.setMk1(nidn1);
        model.setMk2(nidn2);
        model.setMk3(nidn3);
        model.setMk4(nidn4);
        model.setMk5(nidn5);
        model.setMk6(nidn6);
        presenter.inputPengujiMhs(model);
    }

    @Override
    public void onSubmitSuccess(CommonRespon result) {
        SweetDialogs.commonSuccessWithIntent(this , "" , string -> this.goToDashboard());
    }

    @Override
    public void onSubmitFailed(String rm) {
        SweetDialogs.commonError(this,rm , false);
    }

    @Override
    public void onGetlistPenguji(List<ListPengujiMhs> result) {
        Log.d("get list penguji", new Gson().toJson(result));
        String penguji = "Penguji : ";
        for (ListPengujiMhs data : result) {
            mMk1.setText(penguji + data.Mk1.getNama());
            mMk2.setText(penguji + data.Mk2.getNama());
            mMk3.setText(penguji + data.Mk3.getNama());
            mMk4.setText(penguji + data.Mk4.getNama());
            mMk5.setText(penguji + data.Mk5.getNama());
            mMk6.setText(penguji + data.Mk6.getNama());
        }

    }

    @Override
    public void onBackPressed() {
        // ...

        this.goToDashboard();
        super.onBackPressed();
    }
}