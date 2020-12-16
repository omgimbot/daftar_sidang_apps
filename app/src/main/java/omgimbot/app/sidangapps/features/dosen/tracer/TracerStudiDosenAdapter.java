package omgimbot.app.sidangapps.features.dosen.tracer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.features.admin.mhs.tracerStudi.model.TracerStudi;

public class TracerStudiDosenAdapter extends RecyclerView.Adapter<TracerStudiDosenAdapter.ViewHolder> {

    public List<TracerStudi> ruts;
    //    private List<Rut> filterList;;
    private TracerStudiDosenAdapter.onSelected listener;
    Activity context;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    String nama;

    public interface onSelected {
        //        void onDetailData(List<KebutuhanSaprotan> kebutuhanSaprotans, List<BiayaTanam> biayaTanams, List<JadwalUsahaTani> jadwalUsahaTanis);
//        void onSubmit(daftarModel data, String status);
//        void onTolak(daftarModel data, String status);
//        void onDownload(daftarModel data);
        void onClickDetail(TracerStudi data);
    }

    public TracerStudiDosenAdapter(List<TracerStudi> data, Activity context, onSelected listener) {
        this.ruts = data;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public TracerStudiDosenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_tracer, parent, false);
        TracerStudiDosenAdapter.ViewHolder viewHolder = new TracerStudiDosenAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final TracerStudiDosenAdapter.ViewHolder holder, final int position) {
        final TracerStudi data = ruts.get(position);
        holder.mNama.setText(data.getNama());
        holder.mNim.setText(data.getNpm());
        holder.mCount.setText(String.valueOf(position + 1));

        holder.mDetail.setOnClickListener(View->listener.onClickDetail(data));

    }


    @Override
    public int getItemCount() {
        if (ruts == null)
            return 0;
        else
            return ruts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mNama, mNim, mCount;
        Button mDetail;

        ViewHolder(View view) {
            super(view);
            mCount = view.findViewById(R.id.mCount);
            mNama = view.findViewById(R.id.mNama);
            mNim = view.findViewById(R.id.mNim);
            mDetail= view.findViewById(R.id.mDetail);

        }

    }
}
