package vn.edu.fpt.quizapp.lichSu;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import vn.edu.fpt.quizapp.R;

import java.util.List;

public class LichSuAdapter extends BaseAdapter {
    Context context;
    List<ItemLichSu> lichSuList;
    int layout;

    public LichSuAdapter(Context context, List<ItemLichSu> lichSuList, int layout) {
        this.context = context;
        this.lichSuList = lichSuList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return lichSuList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(layout,null);
        TextView deThi = (TextView) view.findViewById(R.id.de_thi);
        TextView cauDung = (TextView) view.findViewById(R.id.cau_dung);
        TextView cauSai = (TextView) view.findViewById(R.id.cau_sai);
        TextView tongCau = (TextView) view.findViewById(R.id.tong_cau);
        TextView time = (TextView) view.findViewById(R.id.time_saved);

        // Debug log để kiểm tra dữ liệu
        String tenDe = lichSuList.get(i).deThi;
        Log.d("LichSuAdapter", "Vị trí " + i + " - Tên đề: " + tenDe);
        
        // Kiểm tra nếu tên đề là null thì gán giá trị mặc định
        if (tenDe == null || tenDe.isEmpty()) {
            tenDe = "Đề thi không xác định";
            Log.d("LichSuAdapter", "Tên đề bị null ở vị trí " + i + ", gán giá trị mặc định: " + tenDe);
        }

        final String deVal = tenDe;
        final long tsVal = lichSuList.get(i).timestamp;
        // Hiển thị tiêu đề bài kiểm tra đã lưu
        deThi.setText("Bộ đề: " + tenDe);
        cauDung.setText(lichSuList.get(i).cauDung);
        cauSai.setText(lichSuList.get(i).cauSai);
        tongCau.setText(lichSuList.get(i).tongCau);
        time.setText(lichSuList.get(i).getFormattedTime());
        view.setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(context, vn.edu.fpt.quizapp.ShowChecked.class);
            intent.putExtra("de", deVal);
            intent.putExtra("timestamp", tsVal);
            context.startActivity(intent);
        });
        return view;
    }
}
