package com.example.baru_saja;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import helper.DataHelper;

import static android.R.layout.*;

public class DaftarPenyewa extends AppCompatActivity {

    String[] daftar;
    int[] id;
    ListView listsewa;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static DaftarPenyewa m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_penyewa);

        Button tambah = findViewById(R.id.tambahpenyewa);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(DaftarPenyewa.this, SewaMatic.class);
                startActivity(p);
            }
        });

        m = this;
        dbcenter = new DataHelper(this);

        RefreshList();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM penyewa", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(0);
        }


        listsewa = findViewById(R.id.listsewa);
        listsewa.setAdapter(new ArrayAdapter(this, simple_list_item_1, daftar));
        listsewa.setSelected(true);
        listsewa.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Lihat Data", "Hapus Data"};
                AlertDialog.Builder builder = new AlertDialog.Builder(DaftarPenyewa.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0: {
                                Intent i = new Intent(DaftarPenyewa.this, DetailPenyewa.class);
                                i.putExtra("nama", selection);
                                startActivity(i);
                                break;
                            }
                            case 1: {
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("DELETE FROM penyewa where nama = '" + selection + "'");
                                db.execSQL("DELETE FROM sewa where nama = '" + selection + "'");
                                RefreshList();
                                break;
                            }
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) listsewa.getAdapter()).notifyDataSetInvalidated();

    }
}

