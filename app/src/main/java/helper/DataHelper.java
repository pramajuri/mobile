package helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bangjek.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL("create table penyewa (" +
                "nama text," +
                "alamat text," +
                "no_hp text," +
                "primary key(nama)" +
                ");" +
                "");
        db.execSQL("create table matic(" +
                "merk text," +
                "harga int," +
                "primary key(merk)" +
                ");" +
                "");
        db.execSQL("create table sewa(" +
                "merk text," +
                "nama text," +
                "promo int," +
                "lama int," +
                "total double," +
                "foreign key(merk) references matic (merk), " +
                "foreign key(nama) references penyewa (nama) " +
                ");" +
                "");

        db.execSQL("insert into matic values (" +
                "'Scoopy'," +
                "70000" +
                ");" +
                "");
        db.execSQL("insert into matic values (" +
                "'Mio'," +
                "80000" +
                ");" +
                "");
        db.execSQL("insert into matic values (" +
                "'NMAX'," +
                "100000" +
                ");" +
                "");
        db.execSQL("insert into matic values (" +
                "'Beat'," +
                "50000" +
                ");" +
                "");
        db.execSQL("insert into matic values (" +
                "'X-Ride'," +
                "60000" +
                ");" +
                "");
        db.execSQL("insert into matic values (" +
                "'Next'," +
                "40000" +
                ");" +
                "");
        db.execSQL("insert into matic values (" +
                "'Frego'," +
                "60000" +
                ");" +
                "");
        db.execSQL("insert into matic values (" +
                "'Aerox'," +
                "100000" +
                ");" +
                "");
        db.execSQL("insert into matic values (" +
                "'Frimavera'," +
                "100000" +
                ");" +
                "");
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<String>();
        String selectQuery = "select * from matic";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                categories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return categories;
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

}