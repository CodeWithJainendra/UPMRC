package com.shivam.metroproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.shivam.metroproject.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth objectFirebaseAuth;
    TextView signout,username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    objectFirebaseAuth=FirebaseAuth.getInstance();

    if(objectFirebaseAuth!=null)
    {
        String currentUser=objectFirebaseAuth.getCurrentUser().getEmail();
        username.setText("This App is Created By SHIVAM SINGH");
    }
signout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (objectFirebaseAuth!=null) {
            objectFirebaseAuth.signOut();
            startActivity(new Intent(HomeActivity.this,MainActivity.class));
            finish();
        }
    }
});


    }
}