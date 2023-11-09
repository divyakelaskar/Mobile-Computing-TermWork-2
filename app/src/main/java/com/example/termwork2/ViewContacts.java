package com.example.termwork2;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewContacts extends AppCompatActivity {
    ArrayList<ContactModal> courseModalArrayList;
    Dbhandler dbHandler;
    ContactRVAdapter contactRVAdapter;
    RecyclerView contactsRV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);
        courseModalArrayList = new ArrayList<>();
        dbHandler = new Dbhandler(ViewContacts.this);

        courseModalArrayList = dbHandler.readStudents();
        contactRVAdapter = new ContactRVAdapter(courseModalArrayList,
                ViewContacts.this);
        contactsRV = findViewById(R.id.idRVStudents);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewContacts.this,
                RecyclerView.VERTICAL, false);
        contactsRV.setLayoutManager(linearLayoutManager);
        contactsRV.setAdapter(contactRVAdapter);
    }
}
