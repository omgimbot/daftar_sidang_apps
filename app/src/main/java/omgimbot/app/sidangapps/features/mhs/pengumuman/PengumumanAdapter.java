package omgimbot.app.sidangapps.features.mhs.pengumuman;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.Utils.Utils;
import omgimbot.app.sidangapps.features.dosen.PengajuanAdapter;
import omgimbot.app.sidangapps.features.mhs.model.daftarModel;

public class PengumumanAdapter extends RecyclerView.Adapter<PengumumanAdapter.ViewHolder> {
    public List<daftarModel> ruts;
    Activity context;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    private PengumumanAdapter.onSelected listener;
    public interface onSelected {
        void onDownload(daftarModel data);
    }


    public PengumumanAdapter(List<daftarModel> data, Activity context, onSelected listener) {
        this.ruts = data;
        this.context = context;
        this.listener = listener ;
    }

    @Override
    public PengumumanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pengumuman, parent, false);
        PengumumanAdapter.ViewHolder viewHolder = new PengumumanAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final PengumumanAdapter.ViewHolder holder, final int position) {
        final daftarModel data = ruts.get(position);
        holder.mJudul.setText(data.getJudul());
        holder.mJenis.setText("Pengajuan "+data.getKey());
        holder.mCount.setText(String.valueOf(position + 1));
        holder.mTime.setText(Utils.convertMongoDateWithoutTIme(data.getCreated_at()));
        if (data.getStatus().equals("acc")) {
            holder.mStatus.setText("Sudah Acc");
            holder.mIndicator.setImageResource(R.drawable.shape_indicator_active);
            holder.layoutDownload.setVisibility(View.VISIBLE);
        }else if(data.getStatus().equals("tolak")){
            holder.mStatus.setText("Tidak Acc");
            holder.mIndicator.setImageResource(R.drawable.shape_indicator_unactive);
            holder.layoutDownload.setVisibility(View.GONE);
        }else {
            holder.mStatus.setText("Pending");
            holder.mIndicator.setImageResource(R.drawable.shape_indicator_orange);
            holder.layoutDownload.setVisibility(View.GONE);
        }
        holder.mDownload.setOnClickListener(view -> listener.onDownload(data));

    }

    @Override
    public int getItemCount() {
        if (ruts == null)
            return 0;
        else
            return ruts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mJudul, mJenis,mStatus,  mTime, mCount;
        ImageView mIndicator;
        RelativeLayout layoutDownload;
        Button mDownload;

        ViewHolder(View view) {
            super(view);
            mCount = view.findViewById(R.id.mCount);
            mTime = view.findViewById(R.id.mTime);
            mJudul = view.findViewById(R.id.mJudul);
            mIndicator = view.findViewById(R.id.indicator);
            mJenis = view.findViewById(R.id.mJenis);
            mStatus = view.findViewById(R.id.mStatus);
            layoutDownload = view.findViewById(R.id.layoutDownload);
            mDownload = view.findViewById(R.id.mDownloadFile);

        }

    }


}
