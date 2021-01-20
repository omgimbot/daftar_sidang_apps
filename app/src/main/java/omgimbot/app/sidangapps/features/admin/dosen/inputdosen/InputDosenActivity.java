package omgimbot.app.sidangapps.features.admin.dosen.inputdosen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.io.File;
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
import omgimbot.app.sidangapps.features.admin.dashboard.DashboardAdminActivity;
import omgimbot.app.sidangapps.features.admin.dosen.model.dosenPenguji;
import omgimbot.app.sidangapps.features.admin.dosen.model.listMk;
import omgimbot.app.sidangapps.features.admin.dosen.model.listPenguji;
import omgimbot.app.sidangapps.features.mhs.model.daftarModel;
import omgimbot.app.sidangapps.ui.SweetDialogs;
import omgimbot.app.sidangapps.ui.TopSnakbar;

public class InputDosenActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, IInDosView, InputDosenAdapter.onSelected {
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.layoutSubmit)
    LinearLayout layoutSubmit;
    @BindView(R.id.mSubmit)
    Button mSubmit;
    @BindView(R.id.layoutUpdate)
    LinearLayout layoutUpdate;
    @BindView(R.id.mUpdate)
    Button mUpdate;
    @BindView(R.id.mCancel)
    Button mCancel;
    @BindView(R.id.mNama)
    EditText mNama;
    @BindView(R.id.mNim)
    EditText mNim;
    @BindView(R.id.mListMk)
    Spinner mListmk;

    public  InputDosenPresenter presenter;
    SweetAlertDialog sweetAlertDialog;
    String kodeMk, mk, mataUji, idPenguji;
    public InputDosenAdapter adapter;
    private LinkedHashMap<String, String> listmk, listdosen;
    private LinkedHashMapAdapter<String, String> adaptermk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_dosen);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Input Dosen Penguji");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new InputDosenPresenter(this);

        this.initViews();
        presenter.getListMk();
        presenter.getListPenguji();
    }

    private void initViews() {
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.clearFocus();

        mSubmit.setOnClickListener(view->onSubmit());
        mUpdate.setOnClickListener(view->doUpdate());
        mCancel.setOnClickListener(view->refresh());

        layoutUpdate.setVisibility(View.GONE);
        layoutSubmit.setVisibility(View.VISIBLE);

        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Loading ...");
    }


    private void doUpdate() {

        String id, nama, nim;
        nama = mNama.getText().toString();
        nim = mNim.getText().toString();


        if (!nama.equals("") && !nim.equals("") && !kodeMk.equals("0")){
            mk  = mListmk.getSelectedItem().toString();
            dosenPenguji model = new dosenPenguji();
            id = idPenguji;
            model.setNama(nama);
            model.setNim(nim);
            model.setKodeMk(kodeMk);
            model.setNamaMk(mataUji);
            presenter.updatePenguji(id,model);
        } else {
            TopSnakbar.showWarning(this, "Data Tidak Boleh Kosong !");
        }
    }

    @Override
    public void onDataReady(List<listMk> model){
        listmk = new LinkedHashMap<String, String>();
        for (listMk mk : model) {
            listmk.put(mk.getKodeMk(), mk.getNamaMK());
        }
        adaptermk = new LinkedHashMapAdapter<String, String>(this, android.R.layout.simple_spinner_item, listmk);
        adaptermk.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mListmk.setAdapter(adaptermk);
        mListmk.setOnItemSelectedListener(this);
    }



    @Override
    public void onSubmit() {
        String nama, nim;
        nama = mNama.getText().toString();
        nim = mNim.getText().toString();

        if (!nama.equals("") && !nim.equals("") && !kodeMk.equals("0")){
            mk  = mListmk.getSelectedItem().toString();
            dosenPenguji model = new dosenPenguji();
            model.setNama(nama);
            model.setNim(nim);
            model.setKodeMk(kodeMk);
            model.setNamaMk(mataUji);
            presenter.inputDosen(model);
        } else {
            TopSnakbar.showWarning(this, "Data Tidak Boleh Kosong !");
        }
    }

    @Override
    public void onEditPenguji( listPenguji data) {
        layoutSubmit.setVisibility(View.GONE);
        layoutUpdate.setVisibility(View.VISIBLE);
        mNama.setText(data.getNama());
        mNim.setText(data.getNim());
        idPenguji = data.get_id();

//        String nama, nim;
//        nama = mNama.getText().toString();
//        nim = mNim.getText().toString();
//
//        if (!nama.equals("") && !nim.equals("")){
//            mk  = mListmk.getSelectedItem().toString();
//            dosenPenguji model = new dosenPenguji();
//            model.setNama(nama);
//            model.setNim(nim);
//            model.setKodeMk(kodeMk);
//            presenter.inputDosen(model);
//        } else {
//            TopSnakbar.showWarning(this, "Data Tidak Boleh Kosong !");
//        }
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
    public void onSubmitSuccess(CommonRespon result) {
        SweetDialogs.commonSuccessWithIntent(this , "" , string -> this.refresh());
    }

    @Override
    public void onSubmitFailed(String rm) {

        SweetDialogs.commonError(this,rm , false);
    }

    @Override
    public void onGetListDosen(List<listPenguji> result) {
        Log.d("data dosen" , new Gson().toJson(result));
        adapter = new InputDosenAdapter(result, this, this);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDeleteSuccess(CommonRespon result) {
        SweetDialogs.commonSuccessWithIntent(this , "" , string -> this.refresh());
    }

    private void refresh() {
        Intent a = new Intent(this, InputDosenActivity.class );
        startActivity(a);
        finish();
    }

    @Override
    public void onDelete(listPenguji data) {
        presenter.hapusPenguji(data.get_id());
    }

    @Override
    public void onDeleteFailed(String rm) {
        SweetDialogs.commonError(this,rm , false);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {

            case R.id.mListMk:
                Map.Entry<String, String> itemMk = (Map.Entry<String, String>) mListmk.getSelectedItem();
                kodeMk = itemMk.getKey();
                mataUji = itemMk.getValue();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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


    public void goToDashboard() {
        Intent a = new Intent(this, DashboardAdminActivity.class);
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