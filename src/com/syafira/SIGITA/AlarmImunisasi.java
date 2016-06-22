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
        
        // Split Calender by "/" ==> dd/MM/yyyy into strings[0]/strings[1]/strings[2]
        String str[] = getBirthdayDate.split("/");
        int month = Integer.parseInt(str[1]);
        int year = Integer.parseInt(str[2]);

		// Create DateFormatter
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        
        // Get Today Calendar
        String getDate = dateFormatter.format(Calendar.getInstance().getTime());

        // Split Calender by "/" ==> dd/MM/yyyy into strings[0]/strings[1]/strings[2]
        String strings[] = getDate.split("/");
        int monthDate = Integer.parseInt(strings[1]);
        int yearDate = Integer.parseInt(strings[2]);

        // years = (today calendar's year) - (birthday's year)
        int years = yearDate - year;

        // months = (today calendar's month) - (birthday's month)
        int months = monthDate - month;

        // months not negative
        if (months < 0) {
            years--;
            months += 12;
        }

        // month for database
        int mon = months;
        int y = 0;
        
        while (y < years) {
            mon += 12;
            y += 1;
        }

        if (mon < 0)
            mon += 12;

		// Set Date Format into Text
        final String bulan_alarm = new DateFormatSymbols(new Locale("id")).getMonths()[monthDate];
        String bulan_imunisasi = bulan_alarm + " " + yearDate;

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

            // Create List
            final List<String> list = new ArrayList<>();
            if (!cursor.isAfterLast()) {
            	do {

            		// Put Data from Database into List
            		list.add(cursor.getString(cursor.getColumnIndex("list_vaksin")));
            	} while (cursor.moveToNext());

            	for (String aList : list) {

            		// if value is last element
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

	        // Set OnClickListener
	        ProfilLinearLayout.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                Intent profil = new Intent(AlarmImunisasi.this, Profil.class);
	                lastActivity = System.currentTimeMillis();
	                profil.putExtra("lastActivity", lastActivity);
	                profil.putExtra("pathbefore", "alarmimunisasi");
	                profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                startActivity(profil);
	                finish();
	            }
	        });

	        // Set ID for Alarm
	        final int IDs = Integer.parseInt(session.loadSession(this, "id"));

	        // Set Notification Time
	        final Calendar nextNotifTime = Calendar.getInstance();
	        nextNotifTime.set(Calendar.DATE, 1);
	        nextNotifTime.set(Calendar.YEAR, yearDate);
	        nextNotifTime.set(Calendar.MONTH, monthDate);
	        nextNotifTime.set(Calendar.HOUR_OF_DAY, 0);
	        nextNotifTime.set(Calendar.MINUTE, 0);
	        nextNotifTime.set(Calendar.SECOND, 0);

	        // Set Intent Alarm
	        final Intent intentAlarm = new Intent(this, AlarmReceiver.class);
	        intentAlarm.putExtra("alarm_nama", session.loadSession(this, "nama"));
	        intentAlarm.putExtra("alarm_vaksin", alarm_vaksin.getText().toString());
	        intentAlarm.putExtra("alarm_bulan", alarm_bulan.getText().toString());
	        intentAlarm.putExtra("IDs", IDs);

	        // Create Alarm Manager
	        final AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

	        // Switch Alarm OnClickListener
	        alarm_switch.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {

	            	// Create DateFormatter
	                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);
	                
	                // if switch turn ON
	                if (alarm_switch.isChecked()) {

	                	// Set Boolean
	                    boolean success = false;
	                    try {

	                    	// Create Pending Intent
	                        final PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmImunisasi.this, IDs, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT);
	                        
	                        // Repeating Alarm Everyday
	                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, nextNotifTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

	                        // Set Boolean
	                        success = true;
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }

	                    // Check Boolean
	                    if (success){
	                        Toast.makeText(AlarmImunisasi.this, "Alarm Imunisasi " + alarm_vaksin.getText().toString().replaceAll("\n", ", ") + " untuk " + session.loadSession(AlarmImunisasi.this, "nama") + " akan aktif pada " + dateFormatter.format(nextNotifTime.getTime()), Toast.LENGTH_LONG).show();
	                    }

	                } else { // if switch turn OFF

	                	// Set Boolean
	                	boolean success = false;

	                    try {

	                    	// Cancel Pending Intent
	                        PendingIntent.getBroadcast(AlarmImunisasi.this, IDs, intentAlarm, PendingIntent.FLAG_NO_CREATE).cancel();
	                        
	                        // Cancel Alarm Manager
	                        alarmManager.cancel(PendingIntent.getBroadcast(AlarmImunisasi.this, IDs, intentAlarm, PendingIntent.FLAG_NO_CREATE));

	                        // Set Boolean
	                        success = true;
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }

	                    // Check Boolean
	                    if (success){
	                    	Toast.makeText(AlarmImunisasi.this, "Alarm Imunisasi untuk " + session.loadSession(AlarmImunisasi.this, "nama") + " Berhasil Dinonaktifkan", Toast.LENGTH_SHORT).show();
	                    }
	                }
	            }
	        });

	        // Check Alarm in On / Off
	        boolean alarmUp = (PendingIntent.getBroadcast(AlarmImunisasi.this, IDs, intentAlarm, PendingIntent.FLAG_NO_CREATE) != null);
	        
	        // Alarm is On
	        if (alarmUp) {
	            alarm_switch.setChecked(true);
	        } else {
	            alarm_switch.setChecked(false);
	        }

	        alarm_bulan.setText(bulan_imunisasi);

	        // If list is empty
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

		// if no Session
	    } else {

	   		// Start Home Activity
	    	Intent imunisasi = new Intent(this, Imunisasi.class);
            long lastActivity = System.currentTimeMillis();
            imunisasi.putExtra("lastActivity", lastActivity);
            imunisasi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(imunisasi);
            finish();
        }
    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {

        // Start Home Activity
        Intent imunisasi = new Intent(this, Imunisasi.class);
        long lastActivity = System.currentTimeMillis();
        imunisasi.putExtra("lastActivity", lastActivity);
        imunisasi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
            splash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(splash);
            finish();
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
                imunisasi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(imunisasi);
                finish();
            }
        }
    }
}
