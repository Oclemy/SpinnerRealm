package com.tutorials.hp.spinnerrealm;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tutorials.hp.spinnerrealm.m_Realm.RealmHelper;
import com.tutorials.hp.spinnerrealm.m_Realm.Spacecraft;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    Realm realm;
    ArrayList<String> spacecrafts;
    ArrayAdapter adapter;
    Spinner sp;
    EditText nameEdiText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        sp= (Spinner) findViewById(R.id.sp);

        //REALM CONFIGURATION
        RealmConfiguration config=new RealmConfiguration.Builder(this).build();
        realm=Realm.getInstance(config);

        //retrieve
        RealmHelper helper=new RealmHelper(realm);
        spacecrafts=helper.retrieve();

        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,spacecrafts);
        sp.setAdapter(adapter);

        //ONCLICK
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,spacecrafts.get(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 displayInputDialog();
            }
        });
    }

    private void displayInputDialog()

    {
        Dialog d=new Dialog(this);
        d.setTitle("save To Realm");
        d.setContentView(R.layout.input_dialog);

        nameEdiText= (EditText) d.findViewById(R.id.nameEditText);
        Button saveBtn= (Button) d.findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Spacecraft s=new Spacecraft();
                s.setName(nameEdiText.getText().toString());

                //save
                RealmHelper helper=new RealmHelper(realm);
                helper.save(s);

                nameEdiText.setText("");
                //RETRIEVE
                spacecrafts=helper.retrieve();
                adapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,spacecrafts);

                sp.setAdapter(adapter);

            }
        });


        d.show();

    }

}
















