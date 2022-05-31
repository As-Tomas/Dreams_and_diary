package bance.eutvikling.dreamsanddiary;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class JournalFragment extends Fragment {

    private JournalFragmentListener listener;

    public interface JournalFragmentListener {
        ArrayList<Dream> loadDB();
    }

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


        listOfDreams= listener.loadDB();

        //sorting
        Collections.sort(listOfDreams, new Comparator<Dream>() {
            public int compare(Dream o1, Dream o2) {
                return o1.getDate().toString().compareTo(o2.getDate().toString());
            }
        });
        Collections.reverse(listOfDreams);

//        String[] tags = new String[] {"tag1", "tag2"};
//        Dream tmp1 = new Dream("19/05/2022", "10:10", "First record", " Description " +
//                "DescriptionDescriptionDescriptionDescription DescriptionDescription Description " +
//                "Description Description Description Description", "day notice", tags, R.drawable.add_property, R.drawable.add_property,
//                R.drawable.add_property);
//        listOfDreams.add(tmp1);


        adapter = new ArrayListAdapter(listOfDreams, view.getContext());

        list = view.findViewById(R.id.list);
        list.setAdapter(adapter);

        //todo add context menu: delete, edit

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof JournalFragmentListener){
            listener = (JournalFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement JournalFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}