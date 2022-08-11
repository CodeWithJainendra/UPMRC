package com.shivam.metroproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.shivam.metroproject.R;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;
import java.util.List;

public class FareCalculation extends AppCompatActivity {
    TextView textresult;
    private Spinner spinner1;
    private Spinner spinner2;
    Button showfair,buttonback;
    private final String TAG="Admob";
    String source;
    String destination;
    int cost = 0;
    private TextView BkCostFld;
    private InterstitialAd mInterstitialAd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fare_calculation);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        textresult=findViewById(R.id.textresult);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        showfair = (Button) findViewById(R.id.showfair);
        buttonback = (Button) findViewById(R.id.buttonback);



        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-3647835717520066/8867703058", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");

                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when fullscreen content is dismissed.
                                Log.d("TAG", "The ad was dismissed.");
                                onBackPressed();
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when fullscreen content failed to show.
                                Log.d("TAG", "The ad failed to show.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when fullscreen content is shown.
                                // Make sure to set your reference to null so you don't
                                // show it a second time.
                                mInterstitialAd = null;
onBackPressed();
                            }

                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
                    }
                });

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),InsideContent.class);
                startActivity(intent);
            }
        });

        showfair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateCostt();
            }
        });


        BkCostFld = findViewById(R.id.BkCostFld);

        showfair = (Button) findViewById(R.id.showfair);


        List<String> locations = new ArrayList<String>();
        locations.add("Source Station");
        locations.add("IIT Kanpur");
        locations.add("Kalyanpur");
        locations.add("SPM Hospital");
        locations.add("CSJM University");
        locations.add("Gurudev Chauraha");
        locations.add("Geeta Nagar");
        locations.add("Rawatpur");
        locations.add("LLR Hospital");
        locations.add("Moti Jheel");


        Spinner dropdown1 = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapterSpinnerSource = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, locations);


        Spinner dropdown2 = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapterSpinnerDestination = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, locations);

        source = locations.get(0);
        destination = locations.get(0);

        // attaching data adapter to spinners
        spinner1.setAdapter(adapterSpinnerSource);
        spinner2.setAdapter(adapterSpinnerDestination);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Source location
                source = parent.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Destination location
                destination = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void calculateCostt() {

        if ((source.equals("IIT Kanpur") && destination.equals("Kalyanpur"))
                || (source.equals("Kalyanpur") && destination.equals("IIT Kanpur"))) {

            cost = 10;
        } else if ((source.equals("IIT Kanpur") && destination.equals("SPM Hospital"))
                || (source.equals("SPM Hospital") && destination.equals("IIT Kanpur"))) {

            cost = 15;
        } else if ((source.equals("IIT Kanpur") && destination.equals("CSJM University"))
                || (source.equals("CSJM University") && destination.equals("IIT Kanpur"))) {

            cost = 20;
        }
        else if ((source.equals("IIT Kanpur") && destination.equals("Gurudev Chauraha"))
                || (source.equals("Gurudev Chauraha") && destination.equals("IIT Kanpur"))) {

            cost = 20;
        }
        else if ((source.equals("IIT Kanpur") && destination.equals("Geeta Nagar"))
                || (source.equals("Geeta Nagar") && destination.equals("IIT Kanpur"))) {

            cost = 20;
        }
        else if ((source.equals("IIT Kanpur") && destination.equals("Rawatpur"))
                || (source.equals("Rawatpur") && destination.equals("IIT Kanpur"))) {

            cost = 20;
        }
        else if ((source.equals("IIT Kanpur") && destination.equals("LLR Hospital"))
                || (source.equals("LLR Hospital") && destination.equals("IIT Kanpur"))) {

            cost = 30;
        }
        else if ((source.equals("IIT Kanpur") && destination.equals("Moti Jheel"))
                || (source.equals("Moti Jheel") && destination.equals("IIT Kanpur"))) {

            cost = 30;
        }
        else if ((source.equals("Kalyanpur") && destination.equals("SPM Hospital"))
                || (source.equals("SPM Hospital") && destination.equals("Kalyanpur"))) {

            cost = 10;
        }
        else if ((source.equals("Kalyanpur") && destination.equals("CSJM University"))
                || (source.equals("CSJM University") && destination.equals("Kalyanpur"))) {

            cost = 15;
        }
        else if ((source.equals("Kalyanpur") && destination.equals("Gurudev Chauraha"))
                || (source.equals("Gurudev Chauraha") && destination.equals("Kalyanpur"))) {

            cost = 20;
        }
        else if ((source.equals("Kalyanpur") && destination.equals("Geeta Nagar"))
                || (source.equals("Geeta Nagar") && destination.equals("Kalyanpur"))) {

            cost = 20;
        }
        else if ((source.equals("Kalyanpur") && destination.equals("Rawatpur"))
                || (source.equals("Rawatpur") && destination.equals("Kalyanpur"))) {

            cost = 20;
        }
        else if ((source.equals("Kalyanpur") && destination.equals("LLR Hospital"))
                || (source.equals("LLR Hospital") && destination.equals("Kalyanpur"))) {

            cost = 30;
        }
        else if ((source.equals("Kalyanpur") && destination.equals("Moti Jheel"))
                || (source.equals("Moti Jheel") && destination.equals("Kalyanpur"))) {

            cost = 30;
        }
        else if ((source.equals("SPM Hospital") && destination.equals("CSJM University"))
                || (source.equals("CSJM University") && destination.equals("SPM Hospital"))) {

            cost = 10;
        }
        else if ((source.equals("SPM Hospital") && destination.equals("Gurudev Chauraha"))
                || (source.equals("Gurudev Chauraha") && destination.equals("SPM Hospital"))) {

            cost = 15;
        }
        else if ((source.equals("SPM Hospital") && destination.equals("Geeta Nagar"))
                || (source.equals("Geeta Nagar") && destination.equals("SPM Hospital"))) {

            cost = 15;
        }
        else if ((source.equals("SPM Hospital") && destination.equals("Rawatpur"))
                || (source.equals("Rawatpur") && destination.equals("SPM Hospital"))) {

            cost = 20;
        }
        else if ((source.equals("SPM Hospital") && destination.equals("LLR Hospital"))
                || (source.equals("LLR Hospital") && destination.equals("SPM Hospital"))) {

            cost = 20;
        }
        else if ((source.equals("SPM Hospital") && destination.equals("Moti Jheel"))
                || (source.equals("Moti Jheel") && destination.equals("SPM Hospital"))) {

            cost = 20;
        }
        else if ((source.equals("CSJM University") && destination.equals("Gurudev Chauraha"))
                || (source.equals("Gurudev Chauraha") && destination.equals("CSJM University"))) {

            cost = 10;
        }
        else if ((source.equals("CSJM University") && destination.equals("Geeta Nagar"))
                || (source.equals("Geeta Nagar") && destination.equals("CSJM University"))) {

            cost = 15;
        }
        else if ((source.equals("CSJM University") && destination.equals("Rawatpur"))
                || (source.equals("Rawatpur") && destination.equals("CSJM University"))) {

            cost = 20;
        }
        else if ((source.equals("CSJM University") && destination.equals("LLR Hospital"))
                || (source.equals("LLR Hsopital") && destination.equals("CSJM University"))) {

            cost = 20;
        }
        else if ((source.equals("CSJM University") && destination.equals("Moti Jheel"))
                || (source.equals("Moti Jheel") && destination.equals("CSJM University"))) {

            cost = 20;
        }
        else if ((source.equals("Gurudev Chauraha") && destination.equals("Geeta Nagar"))
                || (source.equals("Geeta Nagar") && destination.equals("Gurudev Chauraha"))) {

            cost = 10;
        }
        else if ((source.equals("Gurudev Chauraha") && destination.equals("Rawatpur"))
                || (source.equals("Rawatpur") && destination.equals("Gurudev Chauraha"))) {

            cost = 15;
        }
        else if ((source.equals("Gurudev Chauraha") && destination.equals("LLR Hospital"))
                || (source.equals("LLR Hospital") && destination.equals("Gurudev Chauraha"))) {

            cost = 20;
        }
        else if ((source.equals("Gurudev Chauraha") && destination.equals("Moti Jheel"))
                || (source.equals("Moti Jheel") && destination.equals("Gurudev Chauraha"))) {

            cost = 20;
        }
        else if ((source.equals("Geeta Nagar") && destination.equals("Rawatpur"))
                || (source.equals("Rawatpur") && destination.equals("Geeta Nagar"))) {

            cost = 10;
        }
        else if ((source.equals("Geeta Nagar") && destination.equals("LLR Hospital"))
                || (source.equals("LLR Hospital") && destination.equals("Geeta Nagar"))) {

            cost = 15;
        }
        else if ((source.equals("Geeta Nagar") && destination.equals("Moti Jheel"))
                || (source.equals("Moti Jheel") && destination.equals("Geeta Nagar"))) {

            cost = 20;
        }
        else if ((source.equals("Rawatpur") && destination.equals("LLR Hospital"))
                || (source.equals("LLR Hospital") && destination.equals("Rawatpur"))) {

            cost = 10;
        }
        else if ((source.equals("Rawatpur") && destination.equals("Moti Jheel"))
                || (source.equals("Moti Jheel") && destination.equals("Rawatpur"))) {

            cost = 10;
        }
        else if ((source.equals("LLR Hospital") && destination.equals("Moti Jheel"))
                || (source.equals("Moti Jheel") && destination.equals("LLR Hospital"))) {

            cost = 10;
        }
        // Output
        textresult.setText(String.valueOf(cost));
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(FareCalculation.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
    }
}
