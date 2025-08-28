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
        listView = (ListView) findViewById(R.id.thitracnghiem);
        //de thi cung ko luu vao database luu vao ArrayList<> danh sach
        deThiArrayList = new ArrayList<>();
        // Thiết lập danh sách bộ đề với tên chính xác
        String[] setNames = {"Lập trình Mobile App", "Lập trình OOP", "Toán xác suất"};
        for (int idx = 0; idx < setNames.length; idx++) {
            deThiArrayList.add(new Item_De_Thi(R.drawable.de_thi, setNames[idx]));
        }
        adapterDeThi = new Adapter_De_Thi(this, deThiArrayList, R.layout.item_thi);
        listView.setAdapter(adapterDeThi);

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
