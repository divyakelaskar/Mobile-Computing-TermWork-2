package com.example.termwork2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et_name, et_phoneno;
    private Button addData, viewData, updateData, deleteData;
    private static Dbhandler h1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = findViewById(R.id.et_name);
        et_phoneno = findViewById(R.id.et_phoneno);
        addData = findViewById(R.id.addData);
        viewData = findViewById(R.id.viewData);
        updateData = findViewById(R.id.updateData);
        deleteData = findViewById(R.id.deleteData);

        h1 = new Dbhandler(this);

        addData.setOnClickListener(v -> {

            // get data from edittxt
            String contactName = et_name.getText().toString();
            String contactPhone = et_phoneno.getText().toString();

            // txtfields are empty or not
            if(contactName.isEmpty() || contactPhone.isEmpty()){
                Toast.makeText(MainActivity.this, "Enter relevant contact data!",Toast.LENGTH_SHORT).show();
            }
            et_phoneno.setText("");
            et_name.setText("");

            // add new data thr' dbhandler
            h1.addStudent(contactName,contactPhone);

            Toast.makeText(MainActivity.this, "Contact data added successfully!", Toast.LENGTH_SHORT).show();
        });

        viewData.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, ViewContacts.class);
            startActivity(i);
        });

        updateData.setOnClickListener(v -> {
            // get data from edittxt
            String stdName = et_name.getText().toString();
            String stdRollNo = et_phoneno.getText().toString();

            // add new data through dbhandler and check the result
            boolean success = h1.updateStudent(stdRollNo, stdName);

            if (success) {
                Toast.makeText(getApplicationContext(), "Contact data updated successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Failed to update contact information.", Toast.LENGTH_SHORT).show();
            }
        });

        deleteData.setOnClickListener(v -> {
            // Get the roll number from user input
            String stdRollNo = et_phoneno.getText().toString();

            // Delete the student based on the roll number and check the result
            boolean success = h1.deleteStudent(stdRollNo);

            if (success) {
                Toast.makeText(getApplicationContext(), "Contact data deleted successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Failed to delete contact information.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}