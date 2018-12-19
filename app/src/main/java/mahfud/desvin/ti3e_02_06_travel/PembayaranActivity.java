package mahfud.desvin.ti3e_02_06_travel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import mahfud.desvin.ti3e_02_06_travel.Models.ResultPembelian;
import mahfud.desvin.ti3e_02_06_travel.Rest.ApiInterface;
import mahfud.desvin.ti3e_02_06_travel.Rest.ApiClient;

public class PembayaranActivity extends AppCompatActivity {

    ImageView imgGambar;
    TextView txtIdTiket;
    TextView edtTanggal;
    TextView edtNama;
    TextView edtAlamatJemput;
    TextView edtAlamatAntar;

    Button btnAddGambar;
    Button btnAddPembayaran;

    String imagePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        txtIdTiket = findViewById(R.id.txtIdTiket);
        imgGambar = findViewById(R.id.imgGambar);
        edtTanggal = findViewById(R.id.edtTanggal);
        edtNama = findViewById(R.id.edtNama);
        edtAlamatJemput = findViewById(R.id.edtAlamatJemput);
        edtAlamatAntar = findViewById(R.id.edtAlamatAntar);
        btnAddPembayaran = findViewById(R.id.btnAddPembayaran);

        imgGambar.setImageResource(R.drawable.logoandro);
        final Intent mIntent = getIntent();

        txtIdTiket.setText(String.valueOf(mIntent.getStringExtra("id_tiket")));


        btnAddPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mendapatkan client dari server
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
//mendapatkan inputan dari edit text
                RequestBody regIdTiket = MultipartBody.create(MediaType.parse("multipart/form-data"), mIntent.getStringExtra("id_tiket"));
                RequestBody regTanggal = MultipartBody.create(MediaType.parse("multipart/form-data"), (edtTanggal.getText().toString().isEmpty())?"":edtTanggal.getText().toString());
                RequestBody regNama = MultipartBody.create(MediaType.parse("multipart/form-data"), (edtNama.getText().toString().isEmpty())?"":edtNama.getText().toString());
                RequestBody regAlamatJemput = MultipartBody.create(MediaType.parse("multipart/form-data"), (edtAlamatJemput.getText().toString().isEmpty())?"":edtAlamatJemput.getText().toString());
                RequestBody regAlamatAntar = MultipartBody.create(MediaType.parse("multipart/form-data"), (edtAlamatAntar.getText().toString().isEmpty())?"":edtAlamatAntar.getText().toString());
                //memanggil post pembelian
                Call<ResultPembelian> mPembeliCall = mApiInterface.postPembelian(regIdTiket,regTanggal,regNama,regAlamatJemput,regAlamatAntar);
                mPembeliCall.enqueue(new Callback<ResultPembelian>() {
                    @Override
                    public void onResponse(Call<ResultPembelian> call, Response<ResultPembelian> response) {
                        Log.d("Insert Retrofit",response.body().getStatus());
                        Toast.makeText(PembayaranActivity.this,":"+response.body().getMessage(), Toast.LENGTH_LONG).show();

                    }
                    @Override
                    public void onFailure(Call<ResultPembelian> call, Throwable t) {
                        Log.d("Insert Retrofit", t.getMessage());
                        Toast.makeText(PembayaranActivity.this,":"+t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
