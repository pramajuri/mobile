package com.example.baru_saja;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import helper.DataHelper;

public class DetailMatic extends AppCompatActivity {

    protected Cursor cursor;
    String sMerk, sHarga, sGambar;
    DataHelper dataHelper;

    @SuppressLint("SetText")
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_matic);

        Bundle terima = getIntent().getExtras();

        dataHelper = new DataHelper(this);
        Intent intent = getIntent();

        String merk = terima.getString("merk");

        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("select * from matic where merk = '" + merk + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            sMerk = cursor.getString(0);
            sHarga = cursor.getString(1);
        }

        if (sMerk.equals("Scoopy")) {
            sGambar = "scoppy";
        } else if (sMerk.equals("Mio")) {
            sGambar = "mio";
        } else if (sMerk.equals("NMAX")) {
            sGambar = "nmax";
        } else if (sMerk.equals("Beat")) {
            sGambar = "beat";
        } else if (sMerk.equals("X-Ride")) {
            sGambar = "xride";
        }

        ImageView ivGambar = findViewById(R.id.GambarMatic);
        TextView tvMerk = findViewById(R.id.MerkMatic);
        TextView tvHarga = findViewById(R.id.HargaMatic);

        tvMerk.setText(sMerk);
        ivGambar.setImageResource(getResources().getIdentifier(sGambar, "drawable", getPackageName()));
        tvHarga.setText("Rp. " + sHarga);

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
