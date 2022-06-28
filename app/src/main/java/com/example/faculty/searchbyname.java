package com.example.faculty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class searchbyname extends AppCompatActivity {
EditText nametxt ;
ListView listView;
DB db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbyname);

        db = new DB(this);
        nametxt = (EditText) findViewById(R.id.nametxt);
        listView = (ListView) findViewById(R.id.listview);
    }

    public void searchbyname(View view)
    {
        if (nametxt.getText().toString().isEmpty())
            Toast.makeText(this, "Please enter the name", Toast.LENGTH_SHORT).show();
        else
        {
            ArrayList arrayList = db.searchbyname(nametxt.getText().toString());
            ArrayAdapter arrayAdapter = null;
            if (arrayList.isEmpty())
            {
                listView.setAdapter(arrayAdapter);
                Toast.makeText(this, "Can't find this name", Toast.LENGTH_SHORT).show();
            }
            else
            {
                arrayAdapter = new ArrayAdapter(this,android.R.layout.select_dialog_item , arrayList);
                listView.setAdapter(arrayAdapter);
            }
            nametxt.setText("");
        }
    }
}