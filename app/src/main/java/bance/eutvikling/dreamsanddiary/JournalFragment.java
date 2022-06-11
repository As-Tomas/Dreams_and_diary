package bance.eutvikling.dreamsanddiary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
//        Collections.sort(listOfDreams, new Comparator<Dream>() {
//            public int compare(Dream o1, Dream o2) {
//                return o1.getDate().toString().compareTo(o2.getDate().toString());
//            }
//        });
//        Collections.reverse(listOfDreams);

//        String[] tags = new String[] {"tag1", "tag2"};
//        Dream tmp1 = new Dream("19/05/2022", "10:10", "First record", " Description " +
//                "DescriptionDescriptionDescriptionDescription DescriptionDescription Description " +
//                "Description Description Description Description", "day notice", tags, R.drawable.add_property, R.drawable.add_property,
//                R.drawable.add_property);
//        listOfDreams.add(tmp1);


        adapter = new ArrayListAdapter(listOfDreams, view.getContext());

        list = view.findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Dream chosen=listOfDreams.get(i);

                PreviewDreamFragment prevFrag= new PreviewDreamFragment();
                Bundle bundle = new Bundle();
                bundle.putString(PreviewDreamFragment.ARG_DATE_TIME, chosen.getDateAndTime().toString()); //for display in preview
                bundle.putString(PreviewDreamFragment.ARG_DATE, chosen.getDate().toString());
                bundle.putString(PreviewDreamFragment.ARG_TIME, chosen.getTime().toString());
                bundle.putString(PreviewDreamFragment.ARG_TITLE, chosen.getTitle().toString());
                bundle.putString(PreviewDreamFragment.ARG_DREAM_NOTES, chosen.getDreamsNotice().toString());
                bundle.putString(PreviewDreamFragment.ARG_DAY_NOTES, chosen.getDayNotice().toString());
                bundle.putString(PreviewDreamFragment.ARG_TAGS,  String.join(", ",chosen.getTags()));
                bundle.putInt(PreviewDreamFragment.ARG_MOOD,  chosen.getMoodDream());
                bundle.putInt(PreviewDreamFragment.ARG_QUALITY,  chosen.getSleepQuantity());
                bundle.putInt(PreviewDreamFragment.ARG_CLARITY,  chosen.getClarityDream());
                bundle.putInt(PreviewDreamFragment.ARG_ID, i);
                Log.i("id: ", String.valueOf(i));
                prevFrag.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, prevFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

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