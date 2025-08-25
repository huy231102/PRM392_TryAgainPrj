package vn.edu.fpt.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import vn.edu.fpt.quizapp.danhSachDeThi.Adapter_De_Thi;
import vn.edu.fpt.quizapp.danhSachDeThi.Item_De_Thi;

import java.util.ArrayList;

public class Danh_Sach_De_Thi extends AppCompatActivity {
    private ArrayList<Item_De_Thi> deThiArrayList;
    private Adapter_De_Thi adapterDeThi;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thitracnghiem);
        TextView ic_exit;
        ic_exit = findViewById(R.id.ic_exit);
        listView = (ListView) findViewById(R.id.thitracnghiem);
        //de thi cung ko luu vao database luu vao ArrayList<> danh sach
        deThiArrayList = new ArrayList<>();
        deThiArrayList.add(new Item_De_Thi(R.drawable.de_thi, "Đề số 1"));
        deThiArrayList.add(new Item_De_Thi(R.drawable.de_thi, "Đề số 2"));
        deThiArrayList.add(new Item_De_Thi(R.drawable.de_thi, "Đề số 3"));
        adapterDeThi = new Adapter_De_Thi(this, deThiArrayList, R.layout.item_thi);
        listView.setAdapter(adapterDeThi);

        ic_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Danh_Sach_De_Thi.this, function.class);
                startActivity(intent);

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Danh_Sach_De_Thi.this, De_So_1.class);
                Bundle bundle = new Bundle();

                //i la so thu tu de thi 0 la de so 1 1 hoac 2
                bundle.putInt("dethi", i);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
