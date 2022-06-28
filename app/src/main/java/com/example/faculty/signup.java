package com.example.faculty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    EditText nametxt, passtxt, conftxt, emailtxt, phonetxt ,idtxt;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        db = new DB(this);
        nametxt = (EditText) findViewById(R.id.nametxt);
        passtxt = (EditText) findViewById(R.id.passtxt);
        conftxt = (EditText) findViewById(R.id.conftxt);
        emailtxt = (EditText) findViewById(R.id.emailtxt);
        phonetxt = (EditText) findViewById(R.id.phonetxt);
        idtxt = (EditText) findViewById(R.id.idtxt);
    }

    public void onSigninClick(View view) {
        Intent intent = new Intent(signup.this, signin.class);
        startActivity(intent);
    }

    public void signup(View view) {
        if (idtxt.getText().toString().isEmpty()||nametxt.getText().toString().isEmpty()
                || passtxt.getText().toString().isEmpty()
                || conftxt.getText().toString().isEmpty()
                || emailtxt.getText().toString().isEmpty()
                || phonetxt.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter the data", Toast.LENGTH_SHORT).show();
        } else {
            if (conftxt.getText().toString().equals(passtxt.getText().toString())) {
                boolean result = db.insert(idtxt.getText().toString(),nametxt.getText().toString(), passtxt.getText().toString(), emailtxt.getText().toString(), phonetxt.getText().toString());
                if (result) {
                    Toast.makeText(this, "Inserted successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(signup.this, home.class);
                    startActivity(intent);
                } else
                    Toast.makeText(this, "Inserted failed", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            }
        }
    }
}