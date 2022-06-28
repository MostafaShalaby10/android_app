package com.example.faculty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class home extends AppCompatActivity {
ImageView settings ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        settings = (ImageView) findViewById(R.id.settings);
    }
    public void insert(View view)
    {
        Intent intent = new Intent(home.this , insert.class);
        startActivity(intent);

    }public void delete(View view)
    {
        Intent intent = new Intent(home.this , delete.class);
        startActivity(intent);
    }public void update(View view)
    {
        Intent intent = new Intent(home.this , update.class);
        startActivity(intent);
    }public void showdata(View view)
    {
        Intent intent = new Intent(home.this , showdata.class);
        startActivity(intent);
    }public void showgrdaes(View view)
    {
        Intent intent = new Intent(home.this , showgrades.class);
        startActivity(intent);
    }
    public void insertgrades(View view)
    {
        Intent intent = new Intent(home.this , insertgrades.class);
        startActivity(intent);
    }public void deletegrades(View view)
    {
        Intent intent = new Intent(home.this , deletegrade.class);
        startActivity(intent);
    }public void updategrade(View view)
    {
        Intent intent = new Intent(home.this , updategrades.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case  R.id.settings:
                Intent intent = new Intent(home.this , SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.searchuser:
                Intent intent1 = new Intent(home.this , search.class);
                startActivity(intent1);
                break;
            case R.id.logout:
                Intent intent2 = new Intent(home.this , signin.class);
                startActivity(intent2);
                break;
            case R.id.searchgrades:
                Intent intent3 = new Intent(home.this , searchgrade.class);
                startActivity(intent3);
                break;
            case R.id.searchbyname:
                Intent intent4 = new Intent(home.this , searchbyname.class);
                startActivity(intent4);
                break;

        }
        return  super.onOptionsItemSelected(item);
    }

}