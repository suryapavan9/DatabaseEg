package com.example.android.databaseeg;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    EditText et1,et2;
    Button b1,b2;
    ListView lv1;
    Cursor c;
    SimpleCursorAdapter s;
    //declare variable for database
    Mydatabase m;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first, container, false);

        et1 = (EditText) v.findViewById(R.id.edit1);
        et2 = (EditText) v.findViewById(R.id.edit2);
        b1 = (Button) v.findViewById(R.id.button1);
        //b2 = (Button) v.findViewById(R.id.button2);
        lv1 = (ListView) v.findViewById(R.id.list1);

        //create database object  - potential crash possibility
        m = new Mydatabase(getActivity());
        //open database
        m.open();
        //write the code here

        c = m.getEmp(); //initialise all employee into cursor
        s = new SimpleCursorAdapter(getActivity(), R.layout.row,c,
                        new String[]{"_id","ename","esal"},
                        new int[] {R.id.text1,R.id.text2,R.id.text3});
        lv1.setAdapter(s);
        /*StringBuilder sb = new StringBuilder();
        while (c.moveToNext() == true){
            //that means next valid row is available
            int id = c.getInt(0);   //Reads ENO_ID
            String name1 = c.getString(1);   //Reads emp name
            int sal1 = c.getInt(2);  //Reads emp salary
            sb.append(id+":"+name1+":"+sal1+"\n");
        }
        //display emp details on textview

        tv1.setText(sb); */

        //for inserting into employee table
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et1.getText().toString();
                int sal = Integer.parseInt(et2.getText().toString());
                m.insertemp(name,sal);
                c.requery();    //this refreshes cursors with latest rows

            }
        });

        return v;
    }

}
