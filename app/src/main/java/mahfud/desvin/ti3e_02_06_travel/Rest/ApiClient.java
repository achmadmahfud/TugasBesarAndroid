package mahfud.desvin.ti3e_02_06_travel.Rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//untuk menyambungkan antara android dengan rest server
public class ApiClient {
    //untuk menyimpan url dari server
    public static final String BASE_URL = "http://192.168.100.10:8080/travel_server/index.php/";
    //untuk menyimpan variabel retrovit
    private static Retrofit retrofit = null;
    //untuk mendapatkan client dari server
    public static Retrofit getClient() {
        if (retrofit==null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
