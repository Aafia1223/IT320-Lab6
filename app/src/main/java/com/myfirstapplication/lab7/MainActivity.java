package com.myfirstapplication.lab7;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText et_age, et_name;
    Button btn_add, btn_view;
    ListView lv_StudentList;
    ArrayAdapter studentArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // on create, give value
        btn_add = findViewById(R.id.btn_add);
        btn_view = findViewById(R.id.btn_view);
        et_age = findViewById(R.id.et_age);
        et_name = findViewById(R.id.et_name);
        lv_StudentList = findViewById(R.id.Lv_StudentList);

        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        ShowStudentsOnList(dataBaseHelper);

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                ShowStudentsOnList(dataBaseHelper);
                //Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentMod studentMod;
                try{
                    studentMod = new StudentMod(-1, et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()));
                    Toast.makeText(MainActivity.this, studentMod.toString(), Toast.LENGTH_SHORT).show();}
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "Error creating student", Toast.LENGTH_SHORT).show();
                    studentMod = new StudentMod(-1, "error",0);
                }
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                boolean success = dataBaseHelper.addOne(studentMod);
                Toast.makeText(MainActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();
                ShowStudentsOnList(dataBaseHelper);
            }
        });
    }

    private void ShowStudentsOnList(DataBaseHelper dataBaseHelper) {
        studentArrayAdapter = new ArrayAdapter<StudentMod>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
        lv_StudentList.setAdapter(studentArrayAdapter);
    }
}