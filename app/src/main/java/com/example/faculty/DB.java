package com.example.faculty;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {
    private final static String dbname = "faculty";
    private final static String students = "students";
    private final static String grades = "grades";
    private static int version_code = 1;

    Context context;

    public DB(Context context) {
        super(context, dbname, null, version_code);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create table " + students + "(id integer primary key  , name text , password text , email text , phone text )");
            db.execSQL("create table " + grades + "(id integer  , grade1 text, grade2 text, grade3 text, grade4 text, grade5 text, grade6 text , foreign key(id) REFERENCES students(id))");
        } catch (Exception e) {
            Toast.makeText(context, "Error in oncreate", Toast.LENGTH_SHORT).show();
        
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("drop table " + students);
            Toast.makeText(context, "Upgrade successful", Toast.LENGTH_SHORT).show();
            onCreate(db);

        } catch (SQLException e) {
            Toast.makeText(context, "Error in upgrade", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean insert(String id, String name, String password, String email, String phone) {
        SQLiteDatabase db1 = getReadableDatabase();
        Cursor cursor = db1.rawQuery("select * from " + students + " where id=?", new String[]{id});
        boolean flag = false;
        if (cursor.moveToNext())
            flag = true;
        if (flag == false) {

            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", id);
            contentValues.put("name", name);
            contentValues.put("password", password);
            contentValues.put("email", email);
            contentValues.put("phone", phone);

            long result = db.insert(students, null, contentValues);

            if (result == -1)
                return false;
            else
                return true;
        } else {
            Toast.makeText(context, "There is ID like this", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean insertgrades(String id, String grade1, String grade2, String grade3, String grade4, String grade5, String grade6) {
        SQLiteDatabase db1 = getReadableDatabase();
        Cursor cursor = db1.rawQuery("select * from " + students + " where id=?", new String[]{id});
        boolean flag = false;
        if (cursor.moveToNext())
            flag = true;
        if (flag == true) {

            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", id);
            contentValues.put("grade1", grade1);
            contentValues.put("grade2", grade2);
            contentValues.put("grade3", grade3);
            contentValues.put("grade4", grade4);
            contentValues.put("grade5", grade5);
            contentValues.put("grade6", grade6);

            long result = db.insert(grades, null, contentValues);
            if (result == -1)
                return false;
            else
                return true;
        } else {
            Toast.makeText(context, "There is no id like this", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean update(String id, String name, String password, String email, String phone) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("phone", phone);

        long result = db.update(students, contentValues, "id=?", new String[]{id});

        if (result > 0)
            return true;
        else
            return false;
    }

    public boolean updategrades(String id, String grade1, String grade2, String grade3, String grade4, String grade5, String grade6) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("grade1", grade1);
        contentValues.put("grade2", grade2);
        contentValues.put("grade3", grade3);
        contentValues.put("grade4", grade4);
        contentValues.put("grade5", grade5);
        contentValues.put("grade6", grade6);


        long result = db.update(grades, contentValues, "id=?", new String[]{id});

        if (result > 0)
            return true;
        else
            return false;
    }

    public boolean delete(String id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + students + " where id=?", new String[]{id});
        boolean flag = false;
        if (cursor.moveToNext())
            flag = true;

        if (flag == true) {
            cursor = db.rawQuery("select * from " + grades + " where id=?", new String[]{id});
            if (cursor.moveToNext()) {
                Toast.makeText(context, "This id used in another table", Toast.LENGTH_SHORT).show();
                return false ;
            } else
            {
                SQLiteDatabase db1 = getWritableDatabase();
                long result = db1.delete(students, "id=?", new String[]{id});
                return true;
            }
        } else
            return false;

    }


    public boolean deletegrades(String id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + grades + " where id=?", new String[]{id});
        boolean flag = false;
        if (cursor.moveToNext())
            flag = true;

        if (flag == true) {
            SQLiteDatabase db1 = getWritableDatabase();
            long result = db1.delete(grades, "id=?", new String[]{id});
            return true;
        } else
            return false;
    }

    public ArrayList search(String id) {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select *  from " + students + " where id=?", new String[]{id});
        if (cursor.moveToNext()) {
            String idd = cursor.getString(0);
            String name = cursor.getString(1);
            String password = cursor.getString(2);
            String email = cursor.getString(3);
            String phone = cursor.getString(4);
            arrayList.add("ID : " + idd + "\nname : " + name + "\npass : " + password + "\nE-mail : " + email + "\nphone : " + phone);
        }
        return arrayList;
    }

    public ArrayList searchbyname(String name) {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select *  from " + students + " where name=?", new String[]{name});
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String namee = cursor.getString(1);
            String password = cursor.getString(2);
            String email = cursor.getString(3);
            String phone = cursor.getString(4);
            arrayList.add("ID : " + id + "\nname : " + namee + "\npass : " + password + "\nE-mail : " + email + "\nphone : " + phone);
        }
        return arrayList;
    }

    public ArrayList searchgrades(String id) {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select *  from " + grades + " where id=?", new String[]{id});
        if (cursor.moveToNext()) {
            String idd = cursor.getString(0);
            String grade1 = cursor.getString(1);
            String grade2 = cursor.getString(2);
            String grade3 = cursor.getString(3);
            String grade4 = cursor.getString(4);
            String grade5 = cursor.getString(5);
            String grade6 = cursor.getString(6);
            arrayList.add("ID : " + idd + "\ngrade1 : " + grade1 + "\ngrade2 : " + grade2 + "\ngrade3 : " + grade3 + "\ngrade4 : " + grade4 + "\ngrade5 : " + grade5 + "\ngrade6 : " + grade6);

        }
        return arrayList;
    }

    public boolean login(String name, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + students + " where name=? and password=? ", new String[]{name, password});
        if (cursor.moveToNext())
            return true;
        return false;
    }

    public ArrayList getall() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + students, null);

        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String namee = cursor.getString(1);
            String password = cursor.getString(2);
            String email = cursor.getString(3);
            String phone = cursor.getString(4);

            arrayList.add("ID : " + id + "\nname : " + namee + "\npass : " + password + "\nE-mail : " + email + "\nphone : " + phone);
        }
        return arrayList;

    }

    public ArrayList getgrades() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select s.id , s.name , g.grade1 , g.grade2 , g.grade3, g.grade4, g.grade5, g.grade6 from " + students + " s join " + grades + " g on g.id = s.id", null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String grade1 = cursor.getString(2);
            String grade2 = cursor.getString(3);
            String grade3 = cursor.getString(4);
            String grade4 = cursor.getString(5);
            String grade5 = cursor.getString(6);
            String grade6 = cursor.getString(7);
            arrayList.add(id + "     " + name + " " + "\n" + "grade1 : " + grade1 + "\n" + "grade2 : " + grade2 + "\n" + "grade3 : " + grade3 + "\n" + "grade4 : " + grade4 + "\n" + "grade5 : " + grade5 + "\n" + "grade6 : " + grade6 + "\n");
        }
        return arrayList;
    }
}