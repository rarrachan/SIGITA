package com.syafira.SIGITA;

/**
 * Created by syafira rarra on 04/03/2016.
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class TumbuhKembang extends Activity implements OnClickListener {

    // Declare
    private Button button_profil;
    private TextView text_button_profil;
    private LinearLayout ProfilLinearLayout;
    private TextView text_footer;
    private TextView text_button_galeritumbang;
    private ImageView button_galeritumbang;
    private LinearLayout GaleriTumbangLinearLayout;
    private TextView text_button_tahapantumbang;
    private ImageView button_tahapantumbang;
    private LinearLayout TahapanTumbangLinearLayout;
    private SessionManager session;

    // Start Activity
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load Layout
        setContentView(R.layout.tumbuh_kembang);

        // Load Session Manager
        session = new SessionManager();

        // Load Widget
        ProfilLinearLayout = (LinearLayout) findViewById(R.id.ProfilLinearLayout);
        text_button_profil = (TextView) findViewById(R.id.text_button_profil);
        text_footer = (TextView) findViewById(R.id.text_footer);
        TahapanTumbangLinearLayout = (LinearLayout) findViewById(R.id.TahapanTumbangLinearLayout);
        text_button_tahapantumbang= (TextView) findViewById(R.id.text_button_tahapantumbang);
        GaleriTumbangLinearLayout = (LinearLayout) findViewById(R.id.GaleriTumbangLinearLayout);
        text_button_galeritumbang= (TextView) findViewById(R.id.text_button_galeritumbang);

        // Check Session
        if (session.checkSession(this)) {
            // Set Profil Name
            text_button_profil.setText(session.loadSession(this, "nama"));
        }

        // Set OnClickListener
        ProfilLinearLayout.setOnClickListener(this);
        TahapanTumbangLinearLayout.setOnClickListener(this);
        GaleriTumbangLinearLayout.setOnClickListener(this);

        // Set Custom Font
        Typeface typeface  = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
        text_button_profil.setTypeface(typeface);
        text_button_tahapantumbang.setTypeface(typeface);
        text_button_galeritumbang.setTypeface(typeface);
        text_footer.setTypeface(typeface);

    }

    // OnClick Activity
    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            // Profil
            case R.id.ProfilLinearLayout :
                Intent profil = new Intent(this, Profil.class);
                startActivity(profil);
                break;
            // Tahapan Tumbuh Kembang
            case R.id.TahapanTumbangLinearLayout :
                Intent tahap_tumbang = new Intent(this, TahapanTumbang.class);
                startActivity(tahap_tumbang);
                finish();
                break;
            // Galeri Tumbuh Kembang
            case R.id.GaleriTumbangLinearLayout :
                // Check Session
                if (session.checkSession(this)) {
                    Intent galeri = new Intent(this, GaleriTumbang.class);
                    startActivity(galeri);
                    finish();
                } else {
                    final Dialog dialog = new Dialog(TumbuhKembang.this);
                    dialog.setContentView(R.layout.alert_akses);
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.show();

                    // Load Dialog Widget
                    TextView alert_warning = (TextView) dialog.findViewById(R.id.alert_warning);
                    TextView alert_akses = (TextView) dialog.findViewById(R.id.alert_akses);
                    ImageView button_ok = (ImageView) dialog.findViewById(R.id.button_ok);

                    // Set Custom Font Dialog
                    Typeface typeface = Typeface.createFromAsset(getAssets(), "teen-webfont.ttf");
                    alert_akses.setTypeface(typeface);
                    alert_warning.setTypeface(typeface);

                    // Set OnClickListener Dialog
                    button_ok.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Close Dialog
                            dialog.dismiss();
                        }
                    });
                }
                break;
        }
    }

    // Pressed Back Button
    @Override
    public void onBackPressed() {
        // Start Index Activity
        Intent index = new Intent(this, Index.class);
        startActivity(index);

        // Close This Activity
        finish();
    }

    // Activity Resume
    @Override
    public void onResume() {
        super.onResume();
        // Check Session
        if (session.checkSession(this)) {
            // Set Profil Name
            text_button_profil.setText(session.loadSession(this, "nama"));
        }
    }

}
