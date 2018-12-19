package mahfud.desvin.ti3e_02_06_travel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mahfud.desvin.ti3e_02_06_travel.Helpers.DataHelper;
import mahfud.desvin.ti3e_02_06_travel.Helpers.SessionManagement;

public class LoginActivity extends AppCompatActivity {

    //variabel menyimpan komponen layout
    EditText edtEmail, edtPassword;
    Button btnLogin, btnRegister;
    SessionManagement sessionManagement;
    DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);//rest activity_login
//mendapatkan komponen dari layout
        edtEmail = findViewById(R.id.editEmail);
        edtPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        btnRegister = findViewById(R.id.buttonRegister);
//mendapatkan objek dari data helper
        dataHelper = new DataHelper(this);
        //mendapatkan data dari sessionmanagemen
        sessionManagement = new SessionManagement(this);
        //jika sudah login maka berpindah activity
        if (sessionManagement.isLoggedIn()) {
            goToActivity();
        }
        //memberikan fungsi click pada button login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mendapatkan inputan email dan password
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                //validasi jika email dan password kosong
                if(email.matches("") || email.trim().isEmpty() || password.matches("") || password.trim().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Email dan Password tidak boleh kosong/space", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    //mengecek apakah email dan password ada di database
                    if(dataHelper.autentikasi(email, password)) {
                        //menyimpan email dan password kedalam sessionmanagement
                        sessionManagement.createLoginSession(email, password);
                        goToActivity();
                    }else{
                        //menampilkan pesan email tidak terdaftar
                        Toast.makeText(LoginActivity.this, "Email tidak terdaftar", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
//jika di click button register maka berpindah ke register activity
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }
//berpindah ke main activity
    private void goToActivity(){
        Intent mIntent = new Intent(getApplicationContext(),
                MainActivity.class);
        startActivity(mIntent);
    }
}
