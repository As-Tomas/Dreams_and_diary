package bance.eutvikling.dreamsanddiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ArrayListAdapter extends ArrayAdapter<Dream> {

    private ArrayList<Dream> dreamsRecords;
    Context context;


    public ArrayListAdapter(ArrayList<Dream> data, @NonNull Context context) {
        super(context, R.layout.list_item_view, data);
        dreamsRecords= data;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_view, parent, false);

        TextView date=view.findViewById(R.id.date);
        ImageView icon=view.findViewById(R.id.icon);
        TextView title = view.findViewById(R.id.title);
        TextView description = view.findViewById(R.id.description);
        TextView tags = view.findViewById(R.id.tags);

        Dream dream_record = dreamsRecords.get(position);

        date.setText(dream_record.getDate());
        icon.setImageResource(dream_record.getMoodDream());
        title.setText(dream_record.getTitle());
        description.setText(dream_record.getDreamsNotice());
        tags.setText(dream_record.getTags().toString());

        return view;

    }
}
