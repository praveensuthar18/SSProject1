package com.example.ssproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContentProviderActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

//       resultView = (ListView) findViewById(R.id.res_view);
        fetchData();
    }

    public void onClickAddDetails(View view) {

        // class to add values in the database
        ContentValues values = new ContentValues();

        // fetching text from user
        values.put(ContactsContentProvider.name, ((EditText) findViewById(R.id.name)).getText().toString());
        values.put(ContactsContentProvider.phone, ((EditText) findViewById(R.id.phone)).getText().toString());
        // inserting into database through content URI
        getContentResolver().insert(ContactsContentProvider.CONTENT_URI, values);

        fetchData();
        // displaying a toast message
        Toast.makeText(getBaseContext(), "New Record Inserted", Toast.LENGTH_LONG).show();
    }




//    @SuppressLint("Range")
//    public void onClickShowDetails(View view) {
//        // inserting complete table details in this text field
//
//
//        // creating a cursor object of the
//        // content URI
//        Cursor cursor = getContentResolver().query(Uri.parse("content://com.example.contacts.provider/contacts"), null, null, null, null);
//
//        // iteration of the cursor
//        // to print whole table
//        if(cursor.moveToFirst()) {
//            StringBuilder strBuild=new StringBuilder();
//            while (!cursor.isAfterLast()) {
//                strBuild.append("\n"+cursor.getString(cursor.getColumnIndex("id"))+ "-"+ cursor.getString(cursor.getColumnIndex("name"))+"-"+ cursor.getString(cursor.getColumnIndex("phone")));
//                cursor.moveToNext();
//            }
//            resultView.setText(strBuild);
//        }
//        else {
//            resultView.setText("No Records Found");
//        }
//    }

    public void onClickDelete(View view){
        String id = ((EditText)findViewById(R.id.delete_id)).getText().toString();

        deleteData(id);

    }

    public void deleteData(String id){
        getContentResolver().delete(ContactsContentProvider.CONTENT_URI, id,null);
        fetchData();
    }


    @SuppressLint("Range")
    public void fetchData(){
        // creating a cursor object of the
        // content URI
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.example.contacts.provider/contacts"), null, null, null, null);

        // iteration of the cursor
        // to print whole table

        List<String> items = new ArrayList<String>();

        if(cursor.moveToFirst()) {
            StringBuilder strBuild=new StringBuilder();
            while (!cursor.isAfterLast()) {
                strBuild.append("\n"+cursor.getString(cursor.getColumnIndex("id"))+ "-"+ cursor.getString(cursor.getColumnIndex("name"))+"-"+ cursor.getString(cursor.getColumnIndex("phone")));
                cursor.moveToNext();
            }
            items.add(strBuild.toString());
            //resultView.setText(strBuild);
        }
//        else {
//            resultView.setText("No Records Found");
//        }

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        ListView listView = (ListView) findViewById(R.id.res_view);
        listView.setAdapter(itemsAdapter);

    }

}