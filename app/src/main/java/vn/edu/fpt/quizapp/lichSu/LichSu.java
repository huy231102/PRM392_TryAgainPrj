package vn.edu.fpt.quizapp.lichSu;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.fpt.quizapp.DBHelper;

import vn.edu.fpt.quizapp.R;
import vn.edu.fpt.quizapp.function;

import java.util.ArrayList;

public class LichSu extends AppCompatActivity {

    ListView listView;
    Context context;
    ArrayList<ItemLichSu> arrayList;
    LichSuAdapter lichSuAdapter;

    DBHelper db;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DBHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lich_su_thi);
        context = this;
        TextView icon_exit;
        listView = (ListView) findViewById(R.id.lv_lich_su);
        icon_exit=(TextView) findViewById(R.id.ic_exit);
        arrayList = new ArrayList<>();
        lichSuAdapter = new LichSuAdapter(this,arrayList,R.layout.item_lich_su);
        listView.setAdapter(lichSuAdapter);
        icon_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LichSu.this, function.class);
                startActivity(intent);

            }
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String de = bundle.getString("de");
        int a = bundle.getInt("correct");
        int b= bundle.getInt("wrong");
        int c = bundle.getInt("marks");

        String correct = "Số câu đúng : " + a;
        String wrong = "Số câu sai : " + b;
        String marks = "Tổng Số Câu : " + c;

        Boolean insert = db.insertDataStory(de,correct,wrong,marks);
        if(insert == true)
            Toast.makeText(this, "Ban Da Luu Thanh Cong", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Luu De Thi That Bai", Toast.LENGTH_SHORT).show();

        Cursor cursor = db.getData("select * from story ");
        while (cursor.moveToNext()){
            id = cursor.getInt(0);
            String tende = cursor.getString(1);
            String soCauDung =cursor.getString(2);
            String soCauSai = cursor.getString(3);
            String tongCau = cursor.getString(4);
            arrayList.add(new ItemLichSu(id,tende,soCauDung,soCauSai,tongCau));
        }
    }
}
