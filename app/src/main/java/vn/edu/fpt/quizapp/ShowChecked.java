package vn.edu.fpt.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import vn.edu.fpt.quizapp.ResultCheckedItem;

public class ShowChecked extends AppCompatActivity {
    private ListView listView;
    private ResultCheckedAdapter adapter;
    private List<ResultCheckedItem> resultCheckedList;
    private TextView deso;
    private String tenDe = "";
//    private ResultGroupAdapter adapter;
//    private List<ResultGroup> resultGroupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_checked);
        
        try {
            deso = findViewById(R.id.deso);
            TextView timeCompleted = findViewById(R.id.time_completed);

            tenDe = getIntent().getStringExtra("de");
            long timestamp = getIntent().getLongExtra("timestamp", 0);

            if (tenDe != null) {
                deso.setText(tenDe);
            }

            // Nếu chưa có timestamp, cố gắng truy vấn timestamp của lần thi mới nhất
            if (timestamp == 0) {
                try {
                    DBHelper helper = new DBHelper(this);
                    int lan = helper.getMaxLanThi(tenDe);
                    android.database.Cursor cur = helper.getData("SELECT timestamp FROM resultchecked WHERE tenDe='"+tenDe+"' AND lanthi="+lan+" LIMIT 1");
                    if (cur.moveToFirst()) {
                        timestamp = cur.getLong(0);
                    }
                    cur.close();
                } catch (Exception ignored) {}
            }

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.getDefault());
            String timeText = timestamp != 0 ? sdf.format(new java.util.Date(timestamp * 1000)) : "Không rõ thời gian";
            timeCompleted.setText(timeText);
            
            listView = findViewById(R.id.list_view);
            if (listView == null) {
                System.out.println("ListView is null!");
                return;
            }
            
            resultCheckedList = new ArrayList<>(); // ArrayList<ResultCheckedItem>()
            adapter = new ResultCheckedAdapter(this, resultCheckedList);
            listView.setAdapter(adapter);
            
            getResultCheckedData();
        } catch (Exception e) {
            System.out.println("Error in onCreate: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void getResultCheckedData() {
        try {
            // Assuming you have a SQLite database helper class named "MyDatabaseHelper"
            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String[] projection = {
                    "cauhoi",   // số thứ tự câu hỏi
                    "dapanchon",
                    "dapandung"
            };

            // Lấy lần thi mới nhất
            int maxLanThi = dbHelper.getMaxLanThi(tenDe);

            // Lấy các bản ghi của lần thi đó và sắp xếp theo số câu hỏi
            Cursor cursor = db.query(
                    "resultchecked",
                    projection,
                    "tenDe=? AND lanthi=?",
                    new String[]{tenDe, String.valueOf(maxLanThi)},
                    null,
                    null,
                    "cauhoi ASC"
            );

            resultCheckedList.clear();

            int totalRowCount = cursor.getCount();
            System.out.println("Total rows in resultchecked: " + totalRowCount);
            
            if (totalRowCount != 0) {
                if (cursor.moveToFirst()) {
                    do {
                        String dapanchon = cursor.getString(cursor.getColumnIndexOrThrow("dapanchon"));
                        // Bỏ qua bản ghi chưa chọn đáp án
                        if(dapanchon==null || dapanchon.trim().isEmpty()) continue;

                        int questionIndex = cursor.getInt(cursor.getColumnIndexOrThrow("cauhoi"));
                        String dapandung = cursor.getString(cursor.getColumnIndexOrThrow("dapandung"));
                        resultCheckedList.add(new ResultCheckedItem(questionIndex, dapanchon, dapandung));
                    } while (cursor.moveToNext());
                }
            }

            cursor.close();
            db.close();

            // Danh sách đã sắp xếp theo cauhoi ASC nên không cần sort lại
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            System.out.println("Error in getResultCheckedData: " + e.getMessage());
            e.printStackTrace();
            // Thêm dữ liệu mẫu nếu có lỗi
            resultCheckedList.clear();
            adapter.notifyDataSetChanged();
        }
    }
}