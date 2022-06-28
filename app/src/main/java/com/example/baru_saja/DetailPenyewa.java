package com.example.baru_saja;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import helper.DataHelper;
import com.example.baru_saja.R;

public class DetailPenyewa extends AppCompatActivity {

    String sNama, sAlamat, sTelp, sMerk, sHarga;
    int iSewa, iPromo, iTotal;
    double dTotal;

    protected Cursor cursor;
    DataHelper dbHelper;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_penyewa);

        dbHelper = new DataHelper(this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("select * from penyewa, matic, sewa where penyewa.nama = sewa.nama AND matic.merk = sewa.merk AND penyewa.nama = '" + getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            sNama = cursor.getString(0);
            sAlamat = cursor.getString(1);
            sTelp = cursor.getString(2);
            sMerk = cursor.getString(3);
            sHarga = cursor.getString(4);
            iPromo = cursor.getInt(7);
            iSewa = cursor.getInt(8);
            dTotal = cursor.getDouble(9);
        }

        TextView tvNama = findViewById(R.id.txtPenyewa);
        TextView tvAlamat = findViewById(R.id.txtAlamat);
        TextView tvHP = findViewById(R.id.txtTelp);

        TextView tvMerk = findViewById(R.id.txtMerk);
        TextView tvHarga = findViewById(R.id.txtHarga);

        TextView tvLama = findViewById(R.id.txtSewa);
        TextView tvPromo = findViewById(R.id.txtPromo);
        TextView tvTotal = findViewById(R.id.txtTotal);

        tvNama.setText("     " + sNama);
        tvAlamat.setText("     " + sAlamat);
        tvHP.setText("     " + sTelp);

        tvMerk.setText("     " + sMerk);
        tvHarga.setText("     Rp. " + sHarga);

        tvLama.setText("     " + iSewa + " hari");
        tvPromo.setText("     " + iPromo + "%");
        iTotal = (int) dTotal;
        tvTotal.setText("     Rp. " + iTotal);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
