package com.shivam.metroproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.shivam.metroproject.EnterringOTP;
import com.shivam.metroproject.InsideContent;
import com.shivam.metroproject.MainContent;
import com.shivam.metroproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OneFragment extends Fragment {

    private View objectSignInFragment;
    private FirebaseAuth objectFirebaseAuth;
    private EditText email, passwordlogin;
    private TextView forgetpass;
    private Button skipped,skippede;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OneFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OneFragment newInstance(String param1, String param2) {
        OneFragment fragment = new OneFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void initializeVariable() {
        try {
            objectFirebaseAuth = FirebaseAuth.getInstance();
            email = objectSignInFragment.findViewById(R.id.email);
            passwordlogin = objectSignInFragment.findViewById(R.id.passwordlogin);
            skipped = objectSignInFragment.findViewById(R.id.skipped);
            skippede=objectSignInFragment.findViewById(R.id.skippede);
            forgetpass=objectSignInFragment.findViewById(R.id.forgetpass);
            FloatingActionButton fab=objectSignInFragment.findViewById(R.id.fab);


            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(getActivity(), EnterringOTP.class);
                    startActivity(intent);
                }
            });

            forgetpass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), MainContent.class);
                    startActivity(intent);
                }
            });
            skippede.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), InsideContent.class);
                    startActivity(intent);
                }
            });

            skipped.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    signinUser();
                }
            });
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void signinUser() {
        try {
            if (!email.getText().toString().isEmpty() && !passwordlogin.getText().toString().isEmpty()) {
                if (objectFirebaseAuth != null) {
                    objectFirebaseAuth.signInWithEmailAndPassword(email.getText().toString(),
                            passwordlogin.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            if (objectFirebaseAuth.getCurrentUser().isEmailVerified()) {
                                startActivity(new Intent(getActivity().getApplicationContext(), InsideContent.class));
                                getActivity().finish();
                            } else {
                                Toast.makeText(getContext(), "Please Verify Your Email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            } else {
                Toast.makeText(getContext(), "Please Fill Both Fields", Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        objectSignInFragment = inflater.inflate(R.layout.fragment_one, container, false);
        initializeVariable();
        return objectSignInFragment;
    }
}