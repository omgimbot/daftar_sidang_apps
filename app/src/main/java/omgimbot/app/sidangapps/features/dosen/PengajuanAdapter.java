package omgimbot.app.sidangapps.features.dosen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import java.io.File;
import java.util.List;

import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.Utils.FileUtils;
import omgimbot.app.sidangapps.Utils.Utils;
import omgimbot.app.sidangapps.features.mhs.model.daftarModel;
import omgimbot.app.sidangapps.ui.TopSnakbar;

public class PengajuanAdapter extends RecyclerView.Adapter<PengajuanAdapter.ViewHolder> {
    public List<daftarModel> ruts;
    //    private List<Rut> filterList;;
    private PengajuanAdapter.onSelected listener;
    Activity context;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;


    public interface onSelected {
        //        void onDetailData(List<KebutuhanSaprotan> kebutuhanSaprotans, List<BiayaTanam> biayaTanams, List<JadwalUsahaTani> jadwalUsahaTanis);
        void onSubmit(daftarModel data, String status);
        void onTolak(daftarModel data, String status);
        void onDownload(daftarModel data);
    }

    public PengajuanAdapter(List<daftarModel> data, Activity context, onSelected listener) {
        this.ruts = data;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public PengajuanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_pengajuan, parent, false);
        PengajuanAdapter.ViewHolder viewHolder = new PengajuanAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final PengajuanAdapter.ViewHolder holder, final int position) {
        final daftarModel data = ruts.get(position);
        holder.mNama.setText(data.getNama());
        holder.mNim.setText(data.getNim());
        holder.mJudul.setText(data.getJudul());
        holder.mCount.setText(String.valueOf(position + 1));
        holder.mTime.setText(Utils.convertMongoDateWithoutTIme(data.getCreated_at()));
        if (data.getStatus().equals("acc")) {
            holder.mStatus.setText("Sudah Acc");
            holder.mIndicator.setImageResource(R.drawable.shape_indicator_active);
        }else if(data.getStatus().equals("tolak")){
            holder.mStatus.setText("Tidak Acc");
            holder.mIndicator.setImageResource(R.drawable.shape_indicator_orange);
        }else {
            holder.mStatus.setText("Pending");
            holder.mIndicator.setImageResource(R.drawable.shape_indicator_unactive);
        }
        holder.mDownload.setOnClickListener(view -> listener.onDownload(data));
        holder.mSubmit.setOnClickListener(view -> listener.onSubmit(data, "acc"));
        holder.mTolak.setOnClickListener(view -> listener.onTolak(data, "tolak"));
    }




    @Override
    public int getItemCount() {
        if (ruts == null)
            return 0;
        else
            return ruts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mJudul, mNama, mNim, mStatus, mTime, mCount;
        Button mDownload, mSubmit, mTolak;
        ImageView mIndicator;

        ViewHolder(View view) {
            super(view);
            mDownload = view.findViewById(R.id.mDownloadFile);
            mSubmit = view.findViewById(R.id.mSubmit);
            mTolak = view.findViewById(R.id.mTolak);
            mCount = view.findViewById(R.id.mCount);
            mTime = view.findViewById(R.id.mTime);
            mJudul = view.findViewById(R.id.mJudul);
            mNama = view.findViewById(R.id.mNama);
            mNim = view.findViewById(R.id.mNim);
            mStatus = view.findViewById(R.id.mStatus);
            mIndicator = view.findViewById(R.id.indicator);

        }

    }


}
