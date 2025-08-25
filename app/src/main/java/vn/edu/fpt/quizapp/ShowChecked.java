package vn.edu.fpt.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShowChecked extends AppCompatActivity {
    private ListView listView;
    private ResultCheckedAdapter adapter;
    private List<String> resultCheckedList;
//    private ResultGroupAdapter adapter;
//    private List<ResultGroup> resultGroupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_checked);
        TextView ic_exit;
        listView = findViewById(R.id.list_view);
        resultCheckedList = new ArrayList<>();
        ic_exit = findViewById(R.id.ic_exit);
        adapter = new ResultCheckedAdapter(this, resultCheckedList);
        listView.setAdapter(adapter);
ic_exit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ShowChecked.this, function.class);
        startActivity(intent);

    }
});
        getResultCheckedData();
    }

    private void getResultCheckedData() {
        // Assuming you have a SQLite database helper class named "MyDatabaseHelper"
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                "id",
                "dapanchon"
        };

        Cursor cursor = db.query(
                "resultchecked",
                projection,
                null,
                null,
                null,
                null,
                null
        );

        resultCheckedList.clear();

        int totalRowCount = cursor.getCount();
        int startIndex = Math.max(0, totalRowCount - 10); // Start index of the last 10 elements

        if (cursor.moveToPosition(startIndex)) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String dapanchon = cursor.getString(cursor.getColumnIndexOrThrow("dapanchon"));
                int id_div = id % 10;
                String resultCheckedData = "Câu: " + id_div + ", Dap an chon: " + dapanchon;

                resultCheckedList.add(resultCheckedData);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        adapter.notifyDataSetChanged();
    }
}