package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class Activity3 extends AppCompatActivity {
    private ListView display;



    @SuppressLint({"MissingInflatedId", "SuspiciousIndentation"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        display = (ListView) findViewById(R.id.listView);



                db.collection("user")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    List<String> data = new ArrayList<>();
                                    for (QueryDocumentSnapshot document : task.getResult()) {

                                        String Firstname = document.getString("First Name");
                                        String Lastname = document.getString("Last Name");
                                        String Password = document.getString("Password");
                                        data.add("Username is " + Firstname + " " + Lastname +  " - " + "Password is " + Password);

                                    }
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Activity3.this,
                                            android.R.layout.simple_list_item_1, android.R.id.text1, data);
                                    display.setAdapter(adapter);
                                } else {

                                }
                            }
                        });

    }
}