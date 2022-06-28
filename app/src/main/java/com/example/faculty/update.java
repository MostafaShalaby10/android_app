package com.example.faculty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class update extends AppCompatActivity {
    EditText nametxt, passtxt, conftxt, emailtxt, phonetxt , idtxt;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        db = new DB(this);
        idtxt = (EditText) findViewById(R.id.idtxt);
        nametxt = (EditText) findViewById(R.id.nametxt);
        passtxt = (EditText) findViewById(R.id.passtxt);
        conftxt = (EditText) findViewById(R.id.conftxt);
        emailtxt = (EditText) findViewById(R.id.emailtxt);
        phonetxt = (EditText) findViewById(R.id.phonetxt);

    }
    public void update(View view)
    {
        if (nametxt.getText().toString().isEmpty()
                || passtxt.getText().toString().isEmpty()
                || idtxt.getText().toString().isEmpty()
                || conftxt.getText().toString().isEmpty()
                || emailtxt.getText().toString().isEmpty()
                || phonetxt.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter the data", Toast.LENGTH_SHORT).show();
        } else {
            if (conftxt.getText().toString().equals(passtxt.getText().toString())) {
                Boolean result = db.update(idtxt.getText().toString(),nametxt.getText().toString(), passtxt.getText().toString(), emailtxt.getText().toString(), phonetxt.getText().toString());
                if (result) {
                    idtxt.setText("");
                    nametxt.setText("");
                    passtxt.setText("");
                    conftxt.setText("");
                    emailtxt.setText("");
                    phonetxt.setText("");
                    Toast.makeText(this, "updated successfully", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this, "updated failed", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            }
        }
    }
}