package omgimbot.app.sidangapps.features.auth.regist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.Utils.LinkedHashMapAdapter;
import omgimbot.app.sidangapps.features.admin.dosen.model.dosenPenguji;
import omgimbot.app.sidangapps.features.admin.dosen.model.listPenguji;
import omgimbot.app.sidangapps.features.auth.login.LoginActivity;
import omgimbot.app.sidangapps.features.auth.login.model.Users;
import omgimbot.app.sidangapps.ui.SweetDialogs;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener ,IRegistView{
    @BindView(R.id.mSpinner)
    Spinner mSpinner;
    @BindView(R.id.mFakultas)
    Spinner mFakultas;
    @BindView(R.id.mProdi)
    Spinner mProdi;
    @BindView(R.id.mListDosen)
    Spinner mListDosen;
    @BindView(R.id.layoutNIDN)
    LinearLayout layoutNIDN;
    @BindView(R.id.layoutNPM)
    LinearLayout layoutNPM;
    @BindView(R.id.layoutFakultas)
    LinearLayout layoutFakultas;
    @BindView(R.id.layoutProdi)
    LinearLayout layoutProdi;
    @BindView(R.id.layoutDosenBimbing)
    LinearLayout layoutDosenBimbing;
    @BindView(R.id.mSubmit)
    Button mSubmit;
    @BindView(R.id.mNama)
    EditText mNama;
    @BindView(R.id.mNpm)
    EditText mNpm;
    @BindView(R.id.mNidn)
    EditText mNidn;
    @BindView(R.id.mAlamat)
    EditText mAlamat;
    @BindView(R.id.mPassword)
    EditText mPassword;
    RegistPresenter presenter;
    SweetAlertDialog sweetAlertDialog;
    String username , role , nidnDosenPembimbing;
    private LinkedHashMap<String, String> listdosen;
    private LinkedHashMapAdapter<String, String> adapterDosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        presenter  = new RegistPresenter(this);

        this.initViews();
        presenter.getListDosen();
    }

    public void initViews(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.role, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapterFakultas = ArrayAdapter.createFromResource(this,
                R.array.fakultas, android.R.layout.simple_spinner_item);
        adapterFakultas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFakultas.setAdapter(adapterFakultas);
        mFakultas.setOnItemSelectedListener(this);

        mSubmit.setOnClickListener(view->onRegist());

        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Loading ...");
    }

    @Override
    public void onRegistSuccess(){
//        Toast.makeText(this, "Berhasil Memuat , Silahkan Login", Toast.LENGTH_SHORT).show();
        SweetDialogs.commonSuccessWithIntent(this , "Silahkan Login !",string -> this.goToLogin());
    }

    @Override
    public void onRegistFailed(String rm){
        SweetDialogs.commonError(this,rm , false);
    }

    @Override
    public void onDataReady(List<listPenguji> result){
        listdosen = new LinkedHashMap<String, String>();
        for (listPenguji data : result) {
            listdosen.put(data.getNim(), data.getNim()+" - "+data.getNama());
        }
        adapterDosen = new LinkedHashMapAdapter<String, String>(this, android.R.layout.simple_spinner_item, listdosen);
        adapterDosen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mListDosen.setAdapter(adapterDosen);
//        mListDosen.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.mSpinner:
                if (position != 0) {
                    layoutNIDN.setVisibility(View.VISIBLE);
                    layoutNPM.setVisibility(View.GONE);
                    layoutFakultas.setVisibility(View.GONE);
                    layoutProdi.setVisibility(View.GONE);
                    layoutDosenBimbing.setVisibility(View.GONE);
                } else {
                    layoutNIDN.setVisibility(View.GONE);
                    layoutNPM.setVisibility(View.VISIBLE);
                    layoutFakultas.setVisibility(View.VISIBLE);
                    layoutProdi.setVisibility(View.VISIBLE);
                    layoutDosenBimbing.setVisibility(View.GONE);

                }
                break;

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

//            case R.id.mListDosen:
//                Map.Entry<String, String> itemDosen = (Map.Entry<String, String>) mListDosen.getSelectedItem();
//                nidnDosenPembimbing = itemDosen.getKey();
//
////                Log.d("Desa", itemDesa.getKey());
//                break;
        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onRegist(){
        role  = mSpinner.getSelectedItem().toString();
        Users model = new Users();
        if(role.equals("DOSEN"))
            username = mNidn.getText().toString();
        else {
            username = mNpm.getText().toString();
            String fakultas = mFakultas.getSelectedItem().toString();
            String prodi = mProdi.getSelectedItem().toString();
            model.setFakultas(fakultas);
            model.setProdi(prodi);
            model.setDosen(nidnDosenPembimbing);
        }

        model.setNama(mNama.getText().toString());
        model.setUsername(username);
        model.setPassword(mPassword.getText().toString());
        model.setRole(role);
        model.setAlamat(mAlamat.getText().toString());
        presenter.signup(model);
    }

    @Override
    public void onNetworkError(String cause) {
        Log.e("errornya", cause);
        SweetDialogs.endpointError(this);
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
    public void goToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
