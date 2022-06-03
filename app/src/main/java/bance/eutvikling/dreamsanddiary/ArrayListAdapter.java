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
        ImageView iconMood=view.findViewById(R.id.iconMood);
        ImageView iconQuality=view.findViewById(R.id.iconQuality);
        ImageView iconClarity=view.findViewById(R.id.iconClarity);
        TextView title = view.findViewById(R.id.title);
        TextView dreamsNotice = view.findViewById(R.id.dreamsNotice);
        TextView dayNotes = view.findViewById(R.id.dayNotes);
        TextView tags = view.findViewById(R.id.tags);

        Dream dream_record = dreamsRecords.get(position);

        date.setText(dream_record.getDate());
        iconMood.setImageResource(dream_record.getMoodDream());
        iconQuality.setImageResource(dream_record.getSleepQuantity());
        iconClarity.setImageResource(dream_record.getClarityDream());
        title.setText(dream_record.getTitle());
        dreamsNotice.setText(dream_record.getDreamsNotice());
        dayNotes.setText(dream_record.getDayNotice());

        // convert for display
        String allTags = String.join(", ", dream_record.getTags());
        tags.setText(allTags);

        //if want to use context menu
//        view.setId(position);//For an individual record, we assign an id that matches the record number
//        ((MainActivity)context).registerForContextMenu(view);
        //end context meniu

        return view;

    }
}
