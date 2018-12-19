package mahfud.desvin.ti3e_02_06_travel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import mahfud.desvin.ti3e_02_06_travel.Adapters.ClickListener;
import mahfud.desvin.ti3e_02_06_travel.Adapters.RecycleTouchListener;
import mahfud.desvin.ti3e_02_06_travel.Adapters.TiketAdapter;
import mahfud.desvin.ti3e_02_06_travel.Helpers.SessionManagement;
import mahfud.desvin.ti3e_02_06_travel.Models.ResultTiket;
import mahfud.desvin.ti3e_02_06_travel.Models.Tiket;
import mahfud.desvin.ti3e_02_06_travel.Rest.ApiClient;
import mahfud.desvin.ti3e_02_06_travel.Rest.ApiInterface;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private List<Tiket> dataset = new ArrayList<>();
    private SessionManagement sessionManagement;

    List<Tiket> listPembeli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManagement = new SessionManagement(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

//merubah adapter dari recycle view
        mAdapter = new TiketAdapter(dataset, this);
        mRecyclerView.setAdapter(mAdapter);
//mendapatkan client dari server
        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        //memanggil fungsi get tittle
        Call<ResultTiket> mPembeliCall = mApiInterface.getTiket();
        mPembeliCall.enqueue(new Callback<ResultTiket>() {
            //jika server merespon
            @Override
            public void onResponse(Call<ResultTiket> call,
                                   Response<ResultTiket> response) {
                Log.d("Get Pembeli",response.body().getStatus());
                //mengganti adapter sesuai dengan data yang telah didapatkan dari server
                listPembeli = response.body().getResult();
                mAdapter = new TiketAdapter(listPembeli, getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);

                Toast.makeText(MainActivity.this, ":Berhasil ", Toast.LENGTH_SHORT).show();

            }
            //jika server tidak merespon
            @Override
            public void onFailure(Call<ResultTiket> call, Throwable t) {
                Log.d("Get Pembeli",t.getMessage());

                Toast.makeText(MainActivity.this, ":Gagal  "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
//membrikan fungsi click pada recycle view
        mRecyclerView.addOnItemTouchListener(new RecycleTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //berpindah ke detail activity
                Intent i = new Intent(getApplicationContext(), DetailActivity.class);
                i.putExtra("tiket_data",listPembeli.get(position));
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
//untuk menampilkan menu di pojok kanan atas logout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mIntent;
        switch (item.getItemId()) {
            //jika di click logout maka
            case R.id.menuLogout:
                //logout user
                sessionManagement.logoutUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
