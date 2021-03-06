package mahfud.desvin.ti3e_02_06_travel.Helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
//untuk membuat database sqlite bernama login.db
public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "login.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        //membuat table user dengan kolom nomor,nama dan email
        String sql = "create table user(nomor integer primary key AUTOINCREMENT, nama text null, email " +
                "text null, password text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("Drop Table if Exists 'user'");
        onCreate(db);
        // TODO Auto-generated method stub
    }
//menambah data user
    public void addUser(String nama, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO user (nama, email, password) VALUES ('"+nama+"', '"+email+"', '"+password+"');";
        db.execSQL(sql);
    }
//mengecek apakah email dan password sudah ada
    public boolean autentikasi(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        //menambil data user dengan where email dan password
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE email= '"+email+"' AND password = '"+password+"' ", null);
        cursor.moveToFirst();
        //jika lebih dari 0 return true
        if( cursor.getCount()>0)
        {
            return true;
        }
        return false;
    }
}
