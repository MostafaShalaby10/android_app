package com.example.faculty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class signin extends AppCompatActivity {
    EditText nametxt , passtxt;
    DB db ; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        db = new DB(this);
        nametxt= (EditText) findViewById(R.id.nametxt);
        passtxt = (EditText) findViewById(R.id.passtxt);
    }
    public void onSignupClick(View view)
    {
        Intent intent = new Intent(signin.this , signup.class);
        startActivity(intent);
    }
    public  void signin(View view)
    {
        if (nametxt.getText().toString().isEmpty() || passtxt.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please enter data", Toast.LENGTH_SHORT).show();
        }
        else
        {
            boolean result = db.login(nametxt.getText().toString(),passtxt.getText().toString());
            if (result)
            {
                Intent intent = new Intent(signin.this , home.class);
                startActivity(intent);
                nametxt.setText("");
                passtxt.setText("");
            }
            else
                Toast.makeText(this, "Incorrect name or password", Toast.LENGTH_SHORT).show();
        }
    }
}