package com.shivam.metroproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.shivam.metroproject.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class InsideContent extends AppCompatActivity  {
    private static final String TAG = "NavigationActivity";


    private AlertDialog.Builder dialoguebuilder;
    private AlertDialog dialog;
    Dialog myDialog;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;





    private Bundle bundle;
    TextView textresult;
    Button showPopupBtn, closePopupBtn;
    PopupWindow popupWindow;
    LinearLayout linearLayout1;


    private Spinner bkFromStationSpnr, bkToStationSpnr;
    private RadioButton bksingle, bkreturn;
    private TextView BkCostFld;
    private RadioGroup bkJournyTypeRB;

    private EditText datepicker, timepicker;
    private Spinner departt;
    private Spinner spinner1;
    private Spinner spinner2;
    private TextView Start,alertTextView;
    private Button showfair,alertButton,nextcontent;
    FirebaseAuth objectFirebaseAuth;
    private ImageButton imageone;

    Button selectDate;
    TextView date;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    EditText chooseTime;
    TimePickerDialog timePickerDialog;
    int currentHour;
    int currentMinute;
    String amPm;


    String source;
    String destination;
    int cost = 0;
    Dialog mdialog;

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDatesetListener;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private TextView textViewUserEmail;
    private TextView textViewUserName;
    private RadioButton shortestroute;
    private ImageButton imagethree;
    ImageButton imagetwo,imagefour;
    FloatingActionButton farecal,farecall,farecalll;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_content);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        objectFirebaseAuth=FirebaseAuth.getInstance();
        datepicker = (EditText) findViewById(R.id.datepicker);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        showfair = (Button) findViewById(R.id.showfair);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        objectFirebaseAuth = FirebaseAuth.getInstance();
        datepicker = (EditText) findViewById(R.id.datepicker);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        showfair = (Button) findViewById(R.id.showfair);
        EditText chooseTime = findViewById(R.id.timepicker);
        shortestroute=findViewById(R.id.shortestroute);
        imageone=findViewById(R.id.imageone);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        objectFirebaseAuth = FirebaseAuth.getInstance();
        datepicker = (EditText) findViewById(R.id.datepicker);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        showfair = (Button) findViewById(R.id.showfair);
        shortestroute = findViewById(R.id.shortestroute);
        imageone = findViewById(R.id.imageone);
        nextcontent=(Button)findViewById(R.id.nextcontent);
        BkCostFld=(TextView)findViewById(R.id.BkCostFld);
        imagetwo=(ImageButton)findViewById(R.id.imagetwo);
        imagefour=(ImageButton)findViewById(R.id.imagefour);
        imagethree=(ImageButton)findViewById(R.id.imagethree);
        textresult=findViewById(R.id.textresult);
        farecal=(FloatingActionButton) findViewById(R.id.farecal);
        farecall=(FloatingActionButton) findViewById(R.id.farecall);
        farecalll=findViewById(R.id.farecalll);



        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);












        farecal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(getApplicationContext(),FareCalculation.class);
                    startActivity(intent);
                }

            });






        farecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),TourGuide.class);
                startActivity(intent);
            }
        });

        farecalll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(InsideContent.this,"Contact on Provided Number for More Information...",Toast.LENGTH_SHORT).show();

            }
        });



        imageone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SearchStation.class);
                startActivity(intent);

            }
        });

        imagetwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NearestMetro.class);
                startActivity(intent);

            }
        });

        imagefour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FirstLastMetroTime.class);
                startActivity(intent);

            }
        });

        imagethree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(InsideContent.this,"Under Maintenance",Toast.LENGTH_SHORT).show();

            }
        });





        mdialog=new Dialog(this);
        myDialog = new Dialog(this);


        showfair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateCostt();
            }
        });

        shortestroute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Uri gmmIntentUri = Uri.parse("geo:0,0?q=");
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        startActivity(mapIntent);
                    }
                }, 1000);
            }
        });

        chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(InsideContent.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        chooseTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });

        datepicker = findViewById(R.id.datepicker);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayofMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONDAY, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayofMonth);

                updateCalender();

            }

            private void updateCalender() {
                String Format = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(Format, Locale.US);
                datepicker.setText(sdf.format(calendar.getTime()));

            }
        };
        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(InsideContent.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

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
    public void setAds()

    {
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        InterstitialAd.load(this,"ca-app-pub-3647835717520066/9063144170", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });

    }

}







