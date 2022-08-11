package com.shivam.metroproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.shivam.metroproject.R;


public class PoopUp extends Activity  {
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;


        getWindow().setLayout((int) (width * .8), (int) (height * .5));
    }
}
