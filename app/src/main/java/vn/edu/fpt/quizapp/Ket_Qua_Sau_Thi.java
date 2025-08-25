package vn.edu.fpt.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import vn.edu.fpt.quizapp.lichSu.LichSu;

public class Ket_Qua_Sau_Thi extends AppCompatActivity {
    TextView caudung, causai, tongcau;

    Button btnMain, btnSave, btnShowChecked;

    String a;
    DBHelper db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ket_qua_sau_thi);
        db = new DBHelper(this);
        caudung = (TextView) findViewById(R.id.socaudung);
        causai = (TextView) findViewById(R.id.socausai);
        tongcau = (TextView) findViewById(R.id.tongsocau);
        btnMain = (Button) findViewById(R.id.main);
        btnSave = (Button) findViewById(R.id.save);
        btnShowChecked = (Button) findViewById(R.id.btn_show_checked);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int correct =bundle.getInt("correct");
        int wrong = bundle.getInt("wrong");
        int marks = bundle.getInt("marks");
        caudung.setText("Số Câu Đúng : " + correct);
        causai.setText("Số Câu Sai : " + wrong);
        tongcau.setText("Tổng Điểm : " + correct+"/"+marks);

        a = bundle.getString("de");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ket_Qua_Sau_Thi.this, LichSu.class);
                Bundle bundle = new Bundle();
                bundle.putString("de",a);
                bundle.putInt("correct",correct);
                bundle.putInt("wrong",wrong);
                bundle.putInt("marks",marks);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        btnShowChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ket_Qua_Sau_Thi.this, ShowChecked.class);
                // Pass necessary data to the ShowChecked activity
                intent.putExtra("de", a);
                startActivity(intent);
            }
        });
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ket_Qua_Sau_Thi.this,function.class);
                startActivity(intent);
            }
        });
    }
}
