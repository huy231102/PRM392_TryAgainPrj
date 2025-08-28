package vn.edu.fpt.quizapp.lichSu;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
        listView = (ListView) findViewById(R.id.lv_lich_su);
        arrayList = new ArrayList<>();
        lichSuAdapter = new LichSuAdapter(this,arrayList,R.layout.item_lich_su);
        listView.setAdapter(lichSuAdapter);
        
        // Thêm nút xóa dữ liệu cũ để test
        Button btnClearData = findViewById(R.id.btn_clear_data);
        if (btnClearData != null) {
            btnClearData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Xóa tất cả dữ liệu lịch sử
                    db.queryData("DELETE FROM story");
                    db.queryData("DELETE FROM resultchecked");
                    Toast.makeText(LichSu.this, "Đã xóa dữ liệu cũ", Toast.LENGTH_SHORT).show();
                    // Reload lại danh sách
                    loadDataFromDatabase();
                }
            });
        }
        
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        // Chỉ insert lịch sử khi được đánh dấu
        if (bundle != null && bundle.getBoolean("saveHistory", false)) {
            String de = bundle.getString("de");
            int a = bundle.getInt("correct");
            int b = bundle.getInt("wrong");
            int c = bundle.getInt("marks");
            
            // Debug log để kiểm tra dữ liệu nhận được
            Log.d("LichSu", "Nhận được tên đề: " + de);
            Log.d("LichSu", "Số câu đúng: " + a);
            Log.d("LichSu", "Số câu sai: " + b);
            Log.d("LichSu", "Tổng số câu: " + c);
            
            String correct = "Số câu đúng : " + a;
            String wrong = "Số câu sai : " + b;
            String marks = "Tổng Số Câu : " + c;
            
            // Kiểm tra nếu tên đề là null thì gán giá trị mặc định
            if (de == null || de.isEmpty()) {
                de = "Đề thi không xác định";
                Log.d("LichSu", "Tên đề bị null, gán giá trị mặc định: " + de);
            }
            
            Boolean insert = db.insertDataStory(de, correct, wrong, marks);
            Log.d("LichSu", "Kết quả insert: " + insert);
            if (insert)
                Toast.makeText(this, "Bạn đã lưu thành công", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
        }

        loadDataFromDatabase();
    }
    
    private void loadDataFromDatabase() {
        arrayList.clear();
        Cursor cursor = db.getData("select * from story ORDER BY timestamp DESC");
        Log.d("LichSu", "Số bản ghi trong database: " + cursor.getCount());
        while (cursor.moveToNext()){
            id = cursor.getInt(0);
            String tende = cursor.getString(1);
            String soCauDung =cursor.getString(2);
            String soCauSai = cursor.getString(3);
            String tongCau = cursor.getString(4);
            long timestamp = 0;
            try {
                timestamp = cursor.getLong(5);
            } catch (Exception e) {
                timestamp = 0;
            }
            
            // Debug log để kiểm tra dữ liệu từ database
            Log.d("LichSu", "Từ DB - ID: " + id + ", Tên đề: " + tende + ", Đúng: " + soCauDung + ", Sai: " + soCauSai + ", Tổng: " + tongCau);
            
            arrayList.add(new ItemLichSu(id,tende,soCauDung,soCauSai,tongCau,timestamp));
        }
        lichSuAdapter.notifyDataSetChanged();
    }
}
