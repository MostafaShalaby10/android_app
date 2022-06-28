package com.example.faculty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class showdata extends AppCompatActivity {
ListView listView;
DB db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdata);
        db = new DB(this);
        listView = (ListView) findViewById(R.id.listview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList arrayList = db.getall();
        ArrayAdapter arrayAdapter = null;
        if (arrayList.isEmpty())
        {
            Toast.makeText(this, "There is no data", Toast.LENGTH_SHORT).show();
        }else
        {
            arrayAdapter = new ArrayAdapter(this,android.R.layout.select_dialog_item , arrayList);
            listView.setAdapter(arrayAdapter);
        }
    }

    public void update(View view)
    {
        Intent intent = new Intent(showdata.this , update.class);
        startActivity(intent);
    }
}