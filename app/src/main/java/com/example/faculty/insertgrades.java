package com.example.faculty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class insertgrades extends AppCompatActivity {
EditText idtxt , grade1txt , grade2txt , grade3txt , grade4txt , grade5txt , grade6txt ;
DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertgrades);
        db = new DB(this);
        idtxt = (EditText) findViewById(R.id.idtxt);
        grade1txt = (EditText) findViewById(R.id.grade1txt);
        grade2txt = (EditText) findViewById(R.id.grade2txt);
        grade3txt = (EditText) findViewById(R.id.grade3txt);
        grade4txt = (EditText) findViewById(R.id.grade4txt);
        grade5txt = (EditText) findViewById(R.id.grade5txt);
        grade6txt = (EditText) findViewById(R.id.grade6txt);

    }
    public void insertgrades(View view)
    {
       if (idtxt.getText().toString().isEmpty() || grade1txt.getText().toString().isEmpty()
           || grade2txt.getText().toString().isEmpty()|| grade3txt.getText().toString().isEmpty()
           || grade4txt.getText().toString().isEmpty()|| grade5txt.getText().toString().isEmpty()
           || grade6txt.getText().toString().isEmpty())
           Toast.makeText(this, "Please enter the data", Toast.LENGTH_SHORT).show();
       else
       {
           boolean result = db.insertgrades(idtxt.getText().toString() , grade1txt.getText().toString(), grade2txt.getText().toString(), grade3txt.getText().toString(), grade4txt.getText().toString(), grade5txt.getText().toString(), grade6txt.getText().toString());
           if (result)
           {
               Toast.makeText(this, "inserted successfully", Toast.LENGTH_SHORT).show();
                idtxt.setText("");
                grade1txt.setText("");
                grade2txt.setText("");
                grade3txt.setText("");
                grade4txt.setText("");
                grade5txt.setText("");
                grade6txt.setText("");

           }
           else
               Toast.makeText(this, "inserted failed", Toast.LENGTH_SHORT).show();
       }

    }
}