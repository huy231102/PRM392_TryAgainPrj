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

public class ResultCheckedAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> resultCheckedList;

    public ResultCheckedAdapter(Context context, List<String> resultCheckedList) {
        super(context, 0, resultCheckedList);
        this.context = context;
        this.resultCheckedList = resultCheckedList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        String resultCheckedItem = resultCheckedList.get(position);

        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(resultCheckedItem);

        return view;
    }
}
