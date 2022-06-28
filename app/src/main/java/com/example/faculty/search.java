package com.example.faculty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class search extends AppCompatActivity {
EditText idtxt;
ListView listView;
DB db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        db = new DB(this);
        idtxt = (EditText) findViewById(R.id.idtxt);
        listView = (ListView) findViewById(R.id.listview);
    }
    public void search(View view)
    {
        if (idtxt.getText().toString().isEmpty())
            Toast.makeText(this, "Please enter the id", Toast.LENGTH_SHORT).show();
        else
        {
            ArrayList arrayList = db.search(idtxt.getText().toString());
            ArrayAdapter arrayAdapter = null;
            if (arrayList.isEmpty())
            {
                listView.setAdapter(arrayAdapter);
                Toast.makeText(this, "Can't find this id", Toast.LENGTH_SHORT).show();
            }
            else
            {
                arrayAdapter = new ArrayAdapter(this,android.R.layout.select_dialog_item , arrayList);
                listView.setAdapter(arrayAdapter);
            }
            idtxt.setText("");
        }
    }
}