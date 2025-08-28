package vn.edu.fpt.quizapp.lichSu;


import android.content.Context;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.fpt.quizapp.DBHelper;

import vn.edu.fpt.quizapp.R;
import vn.edu.fpt.quizapp.ShowChecked;
import vn.edu.fpt.quizapp.function;

import java.util.ArrayList;

public class LichSuMau extends AppCompatActivity {
    ListView listView;
    Context context;
    ArrayList<ItemLichSu> arrayList;
    LichSuAdapter lichSuAdapter;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lich_su_thi);
        db = new DBHelper(this);
        context = this;
        listView = (ListView) findViewById(R.id.lv_lich_su);
        arrayList = new ArrayList<>();
        lichSuAdapter = new LichSuAdapter(this,arrayList,R.layout.item_lich_su);
        listView.setAdapter(lichSuAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(this, ShowChecked.class);
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            intent.putExtras(bundle);
            startActivity(intent);
        });
        Cursor cursor = db.getData("select * from story ORDER BY timestamp DESC");
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);

            String tende = cursor.getString(1);
            Log.d("Tag", "tag");
            String soCauDung =cursor.getString(2);
            String soCauSai = cursor.getString(3);
            String tongCau = cursor.getString(4);
            long timestamp = 0;
            try {
                timestamp = cursor.getLong(5);
            } catch (Exception e) {
                timestamp = 0;
            }
            arrayList.add(new ItemLichSu(id,tende,soCauDung,soCauSai,tongCau,timestamp));
        }
    }
}
