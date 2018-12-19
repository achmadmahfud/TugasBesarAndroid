package mahfud.desvin.ti3e_02_06_travel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import mahfud.desvin.ti3e_02_06_travel.Models.Tiket;

public class DetailActivity extends AppCompatActivity {

    TextView txtKota,txtWaktu,txtHarga;
    ImageView imgGambar;
    Button btnPembayaran;
    Button btnMaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btnPembayaran = findViewById(R.id.btnPembayaran);
        btnMaps = findViewById(R.id.btnMaps);
        txtKota = findViewById(R.id.txtKota);
        txtWaktu = findViewById(R.id.txtWaktu);
        txtHarga = findViewById(R.id.txtHarga);

        imgGambar = findViewById(R.id.imgGambar);


//mendapatkan intent
        Intent mIntent = getIntent();
//mendapatkan data tiket dari extra
        final Tiket mTiket = (Tiket) mIntent.getSerializableExtra("tiket_data");
//set tulisan sesuai dengan data tiket
        txtKota.setText(mTiket.getKota());
        txtWaktu.setText(mTiket.getWaktu());
        txtHarga.setText(String.valueOf(mTiket.getHarga()));
//merubah gambar sesuai dari url
        Picasso.with(getApplicationContext()).load(mTiket.getPhotoId()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(imgGambar);

//memberikan fungsi click pada button pembayaran
        btnPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //berpindah ke pembayaran activity
                Intent i = new Intent(DetailActivity.this,PembayaranActivity.class);
                i.putExtra("id_tiket",String.valueOf(mTiket.getIdTiket()));
                startActivity(i);
            }
        });
//berpindah ke maps activity
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailActivity.this,MapsActivity.class);
                startActivity(i);
            }
        });
    }
}
