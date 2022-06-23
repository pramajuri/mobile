package com.example.baru_saja;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Beranda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beranda);

        ImageView gambarmatic    = findViewById(R.id.matic);
        ImageView sewamatic      = findViewById(R.id.sewa);

        gambarmatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Beranda.this, DaftarMatic.class);
                startActivity(i);
            }
        });

        sewamatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Beranda.this, DaftarPenyewa.class);
                startActivity(i);
            }
        });
    }
}
