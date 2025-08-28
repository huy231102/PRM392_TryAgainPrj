package vn.edu.fpt.quizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import vn.edu.fpt.quizapp.ResultCheckedItem;
public class ResultCheckedAdapter extends ArrayAdapter<ResultCheckedItem> {
    private Context context;
    private List<ResultCheckedItem> resultCheckedList;

    public ResultCheckedAdapter(Context context, List<ResultCheckedItem> resultCheckedList) {
        super(context, 0, resultCheckedList);
        this.context = context;
        this.resultCheckedList = resultCheckedList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            // Sử dụng item_result_checked layout
            view = LayoutInflater.from(context).inflate(R.layout.item_result_checked, parent, false);
        }
        
        TextView tvQuestion = view.findViewById(R.id.tv_question);
        TextView tvSelected = view.findViewById(R.id.tv_selected_answer);
        TextView tvCorrect = view.findViewById(R.id.tv_correct_answer);

        // Kiểm tra null để tránh crash
        if (tvQuestion == null || tvSelected == null || tvCorrect == null) {
            System.out.println("One or more TextViews are null in adapter");
            return view;
        }

        if (position < resultCheckedList.size()) {
            ResultCheckedItem item = resultCheckedList.get(position);
            if (item != null) {
                tvQuestion.setText("Câu " + item.getQuestionNum());
                tvSelected.setText("Đáp án chọn: " + item.getSelectedAnswer());
                tvCorrect.setText("Đáp án đúng: " + item.getCorrectAnswer());
            }
        }

        return view;
    }
}
