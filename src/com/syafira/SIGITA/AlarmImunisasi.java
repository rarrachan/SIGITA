package com.syafira.SIGITA;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by syafira rarra on 05/14/2016.
 */
public class AlarmImunisasi extends Activity {

    // Declare
    private TextView button_alarmimunisasi;
    private TextView text_button_profil;
    private LinearLayout ProfilLinearLayout;
    private TextView text_alarm_vaksin;
    private TextView titikdua;
    private TextView alarm_vaksin;
    private TextView text_alarm_bulan;
    private TextView alarm_bulan;
    private TextView text_alarm_switch;
    private ToggleButton alarm_switch;
    private TextView text_footer;
    private SessionManager session;
    private DBHelper db;
    private long lastActivity;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.alarm_imunisasi);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        lastActivity = fetchID.getLongExtra("lastActivity", 1L);

        // Load Session Manager
        session = new SessionManager();
        int profilID = Integer.parseInt(session.loadSession(this, "id"));

        String getBirthdayDate = session.loadSession(AlarmImunisasi.this, "tanggallahir");
        String str[] = getBirthdayDate.split("/");
        int month = Integer.parseInt(str[1]);
        final int year = Integer.parseInt(str[2]);

        final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        String getDate = dateFormatter.format(Calendar.getInstance().getTime());
        String strings[] = getDate.split("/");
        int monthDate = Integer.parseInt(strings[1]);
        int yearDate = Integer.parseInt(strings[2]);

        final int years = yearDate - year;
        int mon = monthDate - month;
        int y = 0;
        while (y < years) {
            mon += 12;
            y += 1;
        }

        monthDate += 1;
        if (monthDate > 12) {
            monthDate = 1;
            yearDate += 1;
        }
        final String months = new DateFormatSymbols(new Locale("id")).getMonths()[monthDate - 1];
        String bulan_imunisasi = months + " " + yearDate;

        // Load Database
        db = new DBHelper(this);
        db.open();
        Cursor cursor = db.getListJoinRiwayatClosestValue(mon, profilID);
        cursor.moveToFirst();

        // Load Widget
        button_alarmimunisasi = (TextView) findViewById(R.id.button_alarmimunisasi);
        ProfilLinearLayout = (LinearLayout) findViewById(R.id.ProfilLinearLayout);
        text_button_profil = (TextView) findViewById(R.id.text_button_profil);
        text_footer = (TextView) findViewById(R.id.text_footer);
        text_alarm_vaksin = (TextView) findViewById(R.id.text_alarm_vaksin);
        alarm_vaksin = (TextView) findViewById(R.id.alarm_vaksin);
        text_alarm_bulan = (TextView) findViewById(R.id.text_alarm_bulan);
        alarm_bulan = (TextView) findViewById(R.id.alarm_bulan);
        text_alarm_switch = (TextView) findViewById(R.id.text_alarm_switch);
        alarm_switch = (ToggleButton) findViewById(R.id.alarm_switch);

        // Set Custtom Layout
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        button_alarmimunisasi.setTypeface(typeface);
        text_button_profil.setTypeface(typeface);
        text_footer.setTypeface(typeface);
        text_alarm_vaksin.setTypeface(typeface);
        alarm_vaksin.setTypeface(typeface);
        text_alarm_bulan.setTypeface(typeface);
        alarm_bulan.setTypeface(typeface);
        text_alarm_switch.setTypeface(typeface);

        // Check Session
        if (session.checkSession(this)) {
            // Set Profil Name
            text_button_profil.setText(session.loadSession(this, "nama"));
        }

        final List<String> list = new ArrayList<>();
        if (!cursor.isAfterLast()) {
            do {
                list.add(cursor.getString(cursor.getColumnIndex("list_vaksin")));
            } while (cursor.moveToNext());

            for (String aList : list) {
                if (aList.equals(list.get(list.size() - 1))) {
                    alarm_vaksin.append(aList);
                } else {
                    alarm_vaksin.append(aList);
                    alarm_vaksin.append("\n");
                }
            }
            // Close Databsae
            db.close();
            cursor.close();
        }

        ProfilLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profil = new Intent(AlarmImunisasi.this, Profil.class);
                lastActivity = System.currentTimeMillis();
                profil.putExtra("lastActivity", lastActivity);
                startActivity(profil);
            }
        });

        final int tahun = yearDate;
        final int bulan = monthDate;
        final int IDs = Integer.parseInt(session.loadSession(this, "id"));

        final Calendar nextNotifTime = Calendar.getInstance();
//        nextNotifTime.set(tahun, bulan, 1);
        nextNotifTime.set(Calendar.YEAR, tahun);
        nextNotifTime.set(Calendar.MONTH, bulan);

        final Intent intentAlarm = new Intent(this, AlarmReceiver.class);
        intentAlarm.putExtra("alarm_nama", session.loadSession(this, "nama"));
        intentAlarm.putExtra("alarm_vaksin", alarm_vaksin.getText().toString());
        intentAlarm.putExtra("alarm_bulan", alarm_bulan.getText().toString());
        intentAlarm.putExtra("IDs", IDs);

        final AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        alarm_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alarm_switch.isChecked()) {
                    final PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmImunisasi.this, IDs, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, nextNotifTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
//                        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (60 * 1000), pendingIntent);
//                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (60 * 1000), 60 * 1000, pendingIntent);
                } else {
//                        boolean success = false;
                    try {
                        PendingIntent.getBroadcast(AlarmImunisasi.this, IDs, intentAlarm, PendingIntent.FLAG_NO_CREATE).cancel();
                        alarmManager.cancel(PendingIntent.getBroadcast(AlarmImunisasi.this, IDs, intentAlarm, PendingIntent.FLAG_NO_CREATE));
//                            success = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//                        if (success){
//                            SharedPreferences pref = AlarmImunisasi.this.getSharedPreferences("SIGITA_PREF", Context.MODE_PRIVATE);
//                            SharedPreferences.Editor editor = pref.edit();
//                            editor.putString("session_alarm", alarm_switch.getText().toString());
//                            editor.apply();
//                            Toast.makeText(AlarmImunisasi.this, "sukses", Toast.LENGTH_SHORT).show();
//                        }
                }
            }
        });

        boolean alarmUp = (PendingIntent.getBroadcast(AlarmImunisasi.this, IDs, intentAlarm, PendingIntent.FLAG_NO_CREATE) != null);
        if (alarmUp) {
            alarm_switch.setChecked(true);
        } else {
            alarm_switch.setChecked(false);
        }

        alarm_bulan.setText(bulan_imunisasi);

        if (list.isEmpty()) {
            alarm_vaksin.setText("Tidak Ada");
            alarm_bulan.setText("Tidak Ada");
            alarm_switch.setChecked(false);
            alarm_switch.setClickable(false);
            PendingIntent.getBroadcast(AlarmImunisasi.this, IDs,
                    intentAlarm, PendingIntent.FLAG_NO_CREATE).cancel();
            alarmManager.cancel(PendingIntent.getBroadcast(AlarmImunisasi.this, IDs,
                    intentAlarm, PendingIntent.FLAG_NO_CREATE));
        }

    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {
        // Start Index Activity
        Intent imunisasi = new Intent(this, Imunisasi.class);
        long lastActivity = System.currentTimeMillis();
        imunisasi.putExtra("lastActivity", lastActivity);
        startActivity(imunisasi);

        // Close This Activity
        finish();
    }

    // Activity Resume
    @Override
    public void onResume() {
        super.onResume();
        long now = System.currentTimeMillis() - 30 * 60 * 1000;
        if (lastActivity < now) {
            finish();

            // Clear Session
            session.clearSession(AlarmImunisasi.this);

            Intent splash = new Intent(this, Splash.class);
            startActivity(splash);
        } else {
            // Check Session
            if (session.checkSession(this)) {
                // Set Profil Name
                text_button_profil.setText(session.loadSession(this, "nama"));
            } else {
                // Start Imunisasi Activity
                Intent imunisasi = new Intent(AlarmImunisasi.this, Imunisasi.class);
                lastActivity = System.currentTimeMillis();
                imunisasi.putExtra("lastActivity", lastActivity);
                startActivity(imunisasi);
                finish();
            }
        }
    }
}
