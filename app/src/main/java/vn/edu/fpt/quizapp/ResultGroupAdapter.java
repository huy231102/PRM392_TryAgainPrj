package vn.edu.fpt.quizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ResultGroupAdapter extends ArrayAdapter<ResultGroup> {
    private Context context;

    public ResultGroupAdapter(Context context, List<ResultGroup> resultGroupList) {
        super(context, 0, resultGroupList);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item_result_group, parent, false);
        }

        ResultGroup resultGroup = getItem(position);
        TextView lanthiTextView = itemView.findViewById(R.id.lan_thi_text_view);
        ListView resultCheckedListView = itemView.findViewById(R.id.result_checked_list_view);

        lanthiTextView.setText("Lần thi " + resultGroup.getLanthi());
        ArrayAdapter<String> resultCheckedAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, resultGroup.getResultCheckedList());
        resultCheckedListView.setAdapter(resultCheckedAdapter);

        return itemView;
    }
}
