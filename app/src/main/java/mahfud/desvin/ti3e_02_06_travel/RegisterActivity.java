package mahfud.desvin.ti3e_02_06_travel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mahfud.desvin.ti3e_02_06_travel.Helpers.DataHelper;

public class RegisterActivity extends AppCompatActivity {
//variabel menyimpan komponen layout
    EditText edtNama, edtEmail, edtPassword;
    Button btnRegister;
    DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//mendapatkan komponen dari layout
        edtNama = findViewById(R.id.editNama);
        edtEmail = findViewById(R.id.editEmail);
        edtPassword = findViewById(R.id.editPassword);
        btnRegister = findViewById(R.id.buttonRegister);
//mendapatkan objek dari data helper
        dataHelper = new DataHelper(this);
//memberikan fungsi pada button register
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mengambil inputan nama,email dan password
                String nama = edtNama.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                //jika inputan kosong
                if(nama.isEmpty() || nama.trim().isEmpty() || email.isEmpty() || email.trim().isEmpty() || password.isEmpty() || password.trim().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                    //jika email tidak mengandung @
                }else if(!email.contains("@")){
                    Toast.makeText(RegisterActivity.this, "Email tidak valid", Toast.LENGTH_SHORT).show();
                }else{
                    //menambahkan data ke database
                    dataHelper.addUser(nama, email, password);
                    finish();
                }
            }
        });
    }
}

