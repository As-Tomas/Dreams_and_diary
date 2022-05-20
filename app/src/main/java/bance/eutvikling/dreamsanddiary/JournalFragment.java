package bance.eutvikling.dreamsanddiary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class JournalFragment extends Fragment {

    ArrayList<Dream> listOfDreams;
    ListView list;
    ArrayListAdapter adapter;

    public JournalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_journal, container, false);

        //loading listView
        listOfDreams = new ArrayList<>();

        String[] tags = new String[] {"tag1", "tag2"};
        Dream tmp1 = new Dream("19/05/2022", "First record", " Description " +
                "DescriptionDescriptionDescriptionDescription DescriptionDescription Description " +
                "Description Description Description Description", "day notice", tags, R.drawable.add_property, R.drawable.add_property,
                R.drawable.add_property);
        listOfDreams.add(tmp1);
        listOfDreams.add(new Dream("19/05/2022", "First record", " Description " +
                "DescriptionDescriptionDescriptionDescription DescriptionDescription Description " +
                "Description Description Description Description", "day notice", tags, R.drawable.add_property, R.drawable.add_property,
                R.drawable.add_property));
        listOfDreams.add(new Dream("19/05/2022", "First record", " Description " +
                "DescriptionDescriptionDescriptionDescription DescriptionDescription Description " +
                "Description Description Description Description", "day notice", tags, R.drawable.add_property, R.drawable.add_property,
                R.drawable.add_property));
        listOfDreams.add(new Dream("19/05/2022", "First record", " Description " +
                "DescriptionDescriptionDescriptionDescription DescriptionDescription Description " +
                "Description Description Description Description", "day notice", tags, R.drawable.add_property, R.drawable.add_property,
                R.drawable.add_property));
        listOfDreams.add(new Dream("19/05/2022", "First record", " Description " +
                "DescriptionDescriptionDescriptionDescription DescriptionDescription Description " +
                "Description Description Description Description", "day notice", tags, R.drawable.add_property, R.drawable.add_property,
                R.drawable.add_property));
        listOfDreams.add(new Dream("19/05/2022", "First record", " Description " +
                "DescriptionDescriptionDescriptionDescription DescriptionDescription Description " +
                "Description Description Description Description", "day notice", tags, R.drawable.add_property, R.drawable.add_property,
                R.drawable.add_property));
        listOfDreams.add(new Dream("19/05/2022", "First record", " Description " +
                "DescriptionDescriptionDescriptionDescription DescriptionDescription Description " +
                "Description Description Description Description", "day notice", tags, R.drawable.add_property, R.drawable.add_property,
                R.drawable.add_property));

        adapter = new ArrayListAdapter(listOfDreams, view.getContext());

        list = view.findViewById(R.id.list);
        list.setAdapter(adapter);

        //todo add context menu: delete, edit

        // Inflate the layout for this fragment
        return view;
    }
}