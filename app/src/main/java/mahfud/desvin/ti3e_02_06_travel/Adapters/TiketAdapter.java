package mahfud.desvin.ti3e_02_06_travel.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import mahfud.desvin.ti3e_02_06_travel.Models.Tiket;
import mahfud.desvin.ti3e_02_06_travel.R;
//untuk merubah tampilan pada recycle view
public class TiketAdapter extends RecyclerView.Adapter<TiketAdapter.CustomHolder> {
//untuk memyimpan variabel list tiket
    private List<Tiket> dataset;
    Context mContext;
//pada saat penbuatan objek menyertakan data set dan context
    public TiketAdapter(List<Tiket> dataset, Context mContext) {
        this.dataset = dataset;
        this.mContext = mContext;
    }
//mengambil layout list_menu
    @NonNull
    @Override
    public CustomHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.list_menu, viewGroup, false);
        CustomHolder viewHolder = new CustomHolder(view);
        return viewHolder;
    }
//merubah data yang ada disetiap list sesuai dengan index data set
    @Override
    public void onBindViewHolder(@NonNull CustomHolder customHolder, int i) {
        Tiket tm = dataset.get(i);
        customHolder.judul.setText(tm.getKota());
        customHolder.waktuBerangkat.setText(tm.getWaktu());
        customHolder.harga.setText(String.valueOf(tm.getHarga()));
        Picasso.with(mContext).load(tm.getPhotoId()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(customHolder.foto);

        //customHolder.foto.setImageResource(tm.getPhotoId());

    }
//untuk menumpuk method dari superclass
    @Override
    //mendaptkan jumlah item
    public int getItemCount() {
        return dataset.size();
    }

//mendaptkan komponen dari list menu
    public class CustomHolder extends RecyclerView.ViewHolder{
        TextView judul, waktuBerangkat, harga;
        ImageView foto;
        View listItem;

        public CustomHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.txtJudul);
            waktuBerangkat = itemView.findViewById(R.id.txtWaktuBerangkat);
            harga = itemView.findViewById(R.id.txtHarga);
            foto = itemView.findViewById(R.id.imgKota);
            listItem = itemView;
        }
    }

    }

