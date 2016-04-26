package com.syafira.SIGITA;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by syafira rarra on 04/20/2016.
 */
public class JadwalImunisasi extends Activity {

    private SessionManager session;
    private DBHelper db;

    // Start Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.jadwal_imunisasi);

        // Session Manager
        session = new SessionManager();

        // Load Database
        db = new DBHelper(this);
        db.open();

        TableLayout jadwal_imunisasi = (TableLayout) findViewById(R.id.jadwal_imunisasi);

        // Get Data from Database
        Cursor cursor = db.getList();
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                TableRow row= new TableRow(this);
                TableRow.LayoutParams list_imunisasi = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(list_imunisasi);

                TextView vaksin = new TextView(this);
                TextView umur = new TextView(this);
                TextView desc = new TextView(this);
                TextView status = new TextView(this);

                vaksin.setText(cursor.getString(cursor.getColumnIndex("list_vaksin")));
                umur.setText(cursor.getString(cursor.getColumnIndex("list_umur")));
                desc.setText(cursor.getString(cursor.getColumnIndex("list_desc")));
                status.setText(cursor.getString(cursor.getColumnIndex("list_status")));

                row.addView(vaksin);
                row.addView(umur);
                row.addView(desc);
                row.addView(status);
                jadwal_imunisasi.addView(row);
            } while (cursor.moveToNext());

            // Close Databsae
            db.close();
            cursor.close();
        }
    }
}
