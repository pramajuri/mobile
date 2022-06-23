package com.example.baru_saja;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import helper.DataHelper;

public class SewaMatic extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText eNama, eAlamat, eTelp, eLama;
    RadioGroup rgmatic;
    RadioButton rbWeekend, rbWeekday;
    Button bSelesai;

    String sNama, sAlamat, sTelp, sMerk, sLama;
    double dPromo;
    int iLama, iPromo, iHarga;
    double dTotal;

    private Spinner spinner;
    DataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sewa);

        dbHelper    = new DataHelper(this);

        spinner       = findViewById(R.id.spnMerk);
        bSelesai    = findViewById(R.id.btnSelesai);
        eNama       = findViewById(R.id.textNama);
        eAlamat     = findViewById(R.id.textAlamat);
        eTelp       = findViewById(R.id.textTelp);
        eLama       = findViewById(R.id.textLama);
        rgmatic     = findViewById(R.id.rdmatic);
        rbWeekend   = findViewById(R.id.rdWeekend);
        rbWeekday   = findViewById(R.id.rdWeekday);

        spinner.setOnItemSelectedListener(this);

        loadSpinnerData();

        bSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sNama = eNama.getText().toString();
                sAlamat = eAlamat.getText().toString();
                sTelp = eTelp.getText().toString();
                sLama = eLama.getText().toString();
                if (sNama.isEmpty() || sAlamat.isEmpty() || sTelp.isEmpty() || sLama.isEmpty()) {
                    Toast.makeText(SewaMatic.this, "(*) Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (rbWeekend.isChecked()) {
                    dPromo = 0.25;
                } else if (rbWeekday.isChecked()) {
                    dPromo = 0.1;
                }

                if (sMerk.equals("Scoppy")) {
                    iHarga = 70000;
                } else if (sMerk.equals("Mio")) {
                    iHarga = 80000;
                } else if (sMerk.equals("NMAX")) {
                    iHarga = 100000;
                } else if (sMerk.equals("Beat")) {
                    iHarga = 50000;
                } else if (sMerk.equals("X-Ride")) {
                    iHarga = 60000;
                } else if (sMerk.equals("Next")) {
                    iHarga = 40000;
                } else if (sMerk.equals("Frego")) {
                    iHarga = 60000;
                } else if (sMerk.equals("Aerox")) {
                    iHarga = 100000;
                } else if (sMerk.equals("Frimavera")) {
                    iHarga = 100000;
                }

                iLama = Integer.parseInt(sLama);
                iPromo = (int) (dPromo * 100);
                dTotal = (iHarga * iLama) - (iHarga * iLama * dPromo);

                SQLiteDatabase dbH = dbHelper.getWritableDatabase();
                dbH.execSQL("INSERT INTO penyewa (nama, alamat, no_hp) VALUES ('" +
                        sNama + "', '" +
                        sAlamat + "','" +
                        sTelp + "');");
                dbH.execSQL("INSERT INTO sewa (merk, nama, promo, lama, total) VALUES ('" +
                        sMerk + "', '" +
                        sNama + "', '" +
                        iPromo + "', '" +
                        iLama + "', '" +
                        dTotal + "');");
                DaftarPenyewa.m.RefreshList();
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadSpinnerData() {
        DataHelper db = new DataHelper(getApplicationContext());
        List categories = db.getAllCategories();

        ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView parent, View view, int position, long id) {
        sMerk = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView parent) {

    }
}


