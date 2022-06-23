package com.example.baru_saja;

import android.R.layout;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import helper.DataHelper;

public class DaftarMatic extends AppCompatActivity {

    String[] daftar;
    ListView listmtr;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static DaftarMatic m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_motor);

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
        cursor = db.rawQuery("SELECT * FROM matic", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(0);
        }
        listmtr = findViewById(R.id.listsewa);
        listmtr.setAdapter(new ArrayAdapter(this, layout.simple_list_item_1, daftar));
        listmtr.setSelected(true);
        listmtr.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                Intent i = new Intent(DaftarMatic.this, DetailMatic.class);
                i.putExtra("merk", selection);
                startActivity(i);
            }
        });

        ((ArrayAdapter) listmtr.getAdapter()).notifyDataSetInvalidated();

    }
}
