package com.syafira.SIGITA;

/**
 * Created by syafira rarra on 04/18/2016.
 */

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;

public class ImageZoom extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fetch Intent Extra
        Intent fetchID = getIntent();
        String foto_path = fetchID.getStringExtra("foto_path");

        // Declare ImageZoomer Class
        ImageZoomer img = new ImageZoomer(this);

        // Load Image
        img.setImageDrawable(Drawable.createFromPath(foto_path));
        img.setMaxZoom(4f);
        setContentView(img);
    }
}