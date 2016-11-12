package com.example.android.databaseeg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//step 3 create a separate java file for database logic
public class Mydatabase {

    //dtep 5 declare all varaibles
    MyHelper m;
    SQLiteDatabase s;

    //step 6 create helper object in constructor
    public Mydatabase(Context c){
        m = new MyHelper(c, "techpalle.db",null,1); //version no is important for application.here is(1)
    }

    //step 7 open database in open method
    public void open(){
        s = m.getWritableDatabase(); //this will load database into memory
    }

    //step 8 dml operations is insert,update,delete,query
    //this method will be called from fragment
    public void insertemp(String name, int sal){
        ContentValues c = new ContentValues();
        c.put("ename", name);   //1st parameter column name , 2nd parameter value
        c.put("esal", sal);
        s.insert("employee",null,c);    //1st parameter table name , 2nd parameter null, 3rd parameter content values
    }

    public Cursor getEmp(){
        Cursor c = null;

        //query 1 : read all employee rows from employee table
        c = s.query("employee",null,null,null,null,null,null);
      /*  //query 2 : read employee whose no is 2     SELECT * FROM EMPLOYEE WHERE _ID = 2;
        c = s.query("employee",null,"_id = ?", new String[]{"2"},null,null,null);
        //query 3 : read employee details whose name is steve
        c = s.query("employee",null,"ename = ?", new String[]{"steve"},null,null,null);
        //quey 4 : read all employees whose salary is >15000
        c = s.query("employee",null,"esal > ?", new String[]{"15000"},null,null,null);
        //query 5 : read all employee whose id > 2 and salary is < 40000
        c = s.query("employee",null,"_id > ? AND esal < ?", new String[]{"2","40000"},null,null,null);
        //query 6 : read all employee whose names starts with "s"
        c = s.query("employee",null,"ename LIKE ?", new String[]{"s%"},null,null,null); */
        return c;
    }
    //step 9 close database  //this cleans memory,(so that there is no memory leaks) it's an interview question
    public void close(){
        s.close();
    }
    //step 4  create inner helper class for DDL commands
    public class MyHelper extends SQLiteOpenHelper{

        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //here we have to create all tables
            db.execSQL("create table employee(_id integer primary key,ename text, esal integer);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
//in android monitor through before select file explorer and select the emulator in that select "data" folder,again select
//package name "(databaseeg)" again open "databases" folder here the files will be stored