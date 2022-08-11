package com.shivam.metroproject.fragment;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

import com.shivam.metroproject.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TwoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button skippedd;
    private EditText reenter;
    private EditText enterpasword;
    private EditText dobb;
    private EditText email;
    private EditText Mobileno;
    private EditText name;
    private TextView Name,Mobile,Passwordd,dob,Passworddd,Passwordddd;
    private ImageView loginss;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FirebaseAuth objectFirebaseAuth;

    TextView MainActivityConfirmPassError,MainActivityPasswordError;

    private EditText UserEmail, UserPassword, UserConfirmPassword;
    private Button CreateAccountButton;
    DatePickerDialog.OnDateSetListener setListener;


    EditText MainActivityEmail,MainActivityPassword,MainActivityConfirmPassword;





    View TwoFragment;

    public TwoFragment() {



        // Required empty public constructor
    }
    public static TwoFragment newInstance(String param1, String param2) {
        TwoFragment fragment = new TwoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }
    public void createuser()
    {
        if (!name.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && !enterpasword.getText().toString().isEmpty() && !dobb.getText().toString().isEmpty() && !Mobileno.getText().toString().isEmpty() && !reenter.getText().toString().isEmpty()) {
            {
                objectFirebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), enterpasword.getText()
                        .toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>()
                {

                    @Override
                    public void onSuccess(AuthResult authResult) {
                        if (!enterpasword.getText().toString().equals(reenter.getText().toString())) {
                            Toast.makeText(getContext(), "Please Check Your Password...", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {

                            FirebaseUser user = objectFirebaseAuth.getCurrentUser();
                            user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Toast.makeText(getContext(), "Verification Mail Sent.. ", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(), "onFailure: Email not Sent..", Toast.LENGTH_SHORT).show();
                                }
                            });
                            Toast.makeText(getContext(), "User Registered Scuccessfully..😊", Toast.LENGTH_SHORT).show();
                            if (objectFirebaseAuth.getCurrentUser() != null) {
                                objectFirebaseAuth.signOut();
                            }
                        }
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Please Fill-out the Field's", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }


    private void checkUserExists()
    {
        try {
            if(objectFirebaseAuth!=null && !email.getText().toString().isEmpty())

            {
                objectFirebaseAuth.fetchSignInMethodsForEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        boolean chechResult=!task.getResult().getSignInMethods().isEmpty();
                        if(!chechResult)
                        {
                            createuser();
                        }
                        else {
                            Toast.makeText(getContext(),"User Already been Created",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }
        catch (Exception e)
        {
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private void attachToXML()
    {
        try {
            reenter=TwoFragment.findViewById(R.id.reenter);
            enterpasword=TwoFragment.findViewById(R.id.enterpasword);
            dobb=TwoFragment.findViewById(R.id.dobb);
            email=TwoFragment.findViewById(R.id.email);
            Mobileno=TwoFragment.findViewById(R.id.Mobileno);
            name=TwoFragment.findViewById(R.id.name);
            objectFirebaseAuth=FirebaseAuth.getInstance();
            skippedd=TwoFragment.findViewById(R.id.skippedd);

            Calendar calendar =Calendar.getInstance();

            DatePickerDialog.OnDateSetListener date=new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int dayofMonth) {
                    calendar.set(Calendar.YEAR,year);
                    calendar.set(Calendar.MONDAY,month);
                    calendar.set(Calendar.DAY_OF_MONTH,dayofMonth);

                    updateCalender();
                }
                private void updateCalender()
                {
                    String Format ="dd/MM/yyyy";
                    SimpleDateFormat sdf =new SimpleDateFormat(Format, Locale.US);
                    dobb.setText(sdf.format(calendar.getTime()));
                }
            };
            dobb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new DatePickerDialog(TwoFragment.getContext(),date,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            });

            skippedd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkUserExists();
                }
            });


        }
        catch (Exception e)
        {
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        TwoFragment=inflater.inflate(R.layout.fragment_two, container, false);
        attachToXML();

        // Inflate the layout for this fragment
        return TwoFragment;

    }
}