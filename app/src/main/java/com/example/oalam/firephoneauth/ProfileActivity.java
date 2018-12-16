package com.example.oalam.firephoneauth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class ProfileActivity extends AppCompatActivity {
    Button buttonSend;
    //public String newToken;
    EditText editTextpH;
    static boolean calledAlready = false;
    DatabaseReference databaseArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if (!calledAlready) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }
//        FirebaseMessaging.getInstance().subscribeToTopic("notif_test");
        findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseArtists = FirebaseDatabase.getInstance().getReference("Data/" + userId);
        editTextpH = (EditText) findViewById(R.id.editTextpH);
        buttonSend = (Button) findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addArtist();
            }
        });
    }


    private void addArtist() {
        //MainActivity t=new MainActivity();
        //String token=t.mytoken;
        //Log.v("ProfileActivityToken:",token);
        String phText = editTextpH.getText().toString();
        String tokenz = new MainActivity().getMytoken();
        //Log.v("ProfileActivity Token:",tokenz);

        //cheking if pH is empty or not
        if (!TextUtils.isEmpty(phText)) {
            String id = databaseArtists.push().getKey();


            datapH artist = new datapH(id, phText, tokenz);
            //Log.v("ProfileActivity Token:",new MainActivity().getMytoken());
            databaseArtists.child(id).setValue(artist);
            Toast.makeText(ProfileActivity.this, "Data sent", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(ProfileActivity.this, "You should enter a value", Toast.LENGTH_SHORT).show();
        }
    }
}
