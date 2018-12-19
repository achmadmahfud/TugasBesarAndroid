package mahfud.desvin.ti3e_02_06_travel.Rest;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import mahfud.desvin.ti3e_02_06_travel.Models.ResultPembelian;
import mahfud.desvin.ti3e_02_06_travel.Models.ResultTiket;

public interface ApiInterface {
    //mendapatkan tiket dari base url + tiket/tiket dengan method get
    @GET("tiket/tiket")
    Call<ResultTiket> getTiket();
//mentransfer data berupa yang dibawah ini ke base url + pembelian/pembelian dengan method post
    @Multipart
    @POST("Pembelian/pembelian")
    Call<ResultPembelian> postPembelian(
            @Part("id_tiket") RequestBody id_tiket,
            @Part("tanggal") RequestBody tanggal,
            @Part("nama") RequestBody nama,
            @Part("alamat_jemput") RequestBody alamat_jemput,
            @Part("alamat_antar") RequestBody alamat_antar);
}
