package vn.edu.fpt.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class function extends AppCompatActivity {
    TextView thi,xemlichsu,thoat;
    DBHelper db;
    String a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        db = new DBHelper(this);
        xemlichsu = (TextView) findViewById(R.id.xemlichsu);
        thi = (TextView)findViewById(R.id.thi);
        DBHelper db = new DBHelper(this);

        thi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Danh_Sach_De_Thi.class));
            }
        });

        xemlichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ShowChecked.class));

            }
        });


    }
}