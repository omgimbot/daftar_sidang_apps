package omgimbot.app.sidangapps.features.admin.dosen.inputdosen;

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
import omgimbot.app.sidangapps.features.admin.dosen.model.listPenguji;
import omgimbot.app.sidangapps.features.mhs.model.daftarModel;

public class InputDosenAdapter extends RecyclerView.Adapter<InputDosenAdapter.ViewHolder> {
    public List<listPenguji> ruts;
    //    private List<Rut> filterList;;
    private InputDosenAdapter.onSelected listener;
    Activity context;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;

    public interface onSelected {
        //        void onDetailData(List<KebutuhanSaprotan> kebutuhanSaprotans, List<BiayaTanam> biayaTanams, List<JadwalUsahaTani> jadwalUsahaTanis);
//        void onSubmit(daftarModel data, String status);
//        void onTolak(daftarModel data, String status);
//        void onDownload(daftarModel data);
    }

    public InputDosenAdapter(List<listPenguji> data, Activity context, InputDosenAdapter.onSelected listener) {
        this.ruts = data;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public InputDosenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_penguji, parent, false);
        InputDosenAdapter.ViewHolder viewHolder = new InputDosenAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final InputDosenAdapter.ViewHolder holder, final int position) {
        final listPenguji data = ruts.get(position);
        holder.mNamaDosen.setText(data.getNama());
        holder.mNamaMk.setText(data.mk.getNamaMk());
        holder.mCount.setText(String.valueOf(position + 1));

    }


    @Override
    public int getItemCount() {
        if (ruts == null)
            return 0;
        else
            return ruts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mNamaDosen, mNamaMk, mCount;
        Button mEdit, mHapus;

        ViewHolder(View view) {
            super(view);
            mCount = view.findViewById(R.id.mCount);
            mNamaDosen = view.findViewById(R.id.mNamaDosen);
            mNamaMk = view.findViewById(R.id.mNamaMk);
            mEdit = view.findViewById(R.id.mEdit);
            mHapus = view.findViewById(R.id.mhapus);

        }

    }
}
