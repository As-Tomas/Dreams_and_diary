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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Dream chosen=listOfDreams.get(i);

                PreviewDreamFragment prevFrag= new PreviewDreamFragment();
                Bundle bundle = new Bundle();
                bundle.putString(PreviewDreamFragment.ARG_TIME, chosen.getDate().toString() + " " + chosen.getTime().toString());
                bundle.putString(PreviewDreamFragment.ARG_TITLE, chosen.getTitle().toString());
                bundle.putString(PreviewDreamFragment.ARG_DREAM_NOTES, chosen.getDreamsNotice().toString());
                bundle.putString(PreviewDreamFragment.ARG_DAY_NOTES, chosen.getDayNotice().toString());
                bundle.putString(PreviewDreamFragment.ARG_TAGS, chosen.getTags().toString());
                prevFrag.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, prevFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

//    Context menu but there is bug with id clean up (postpone for future integrations)

//    @Override
//    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//
//        menu.setHeaderTitle("Chose option"); //menu title
//        //menu commands
//        menu.add(0, v.getId(),0,"Edit");
//        menu.add(0, v.getId(),0,"Delete");
//    }

    // when user choose one option form menu

//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//
//        if(item.getTitle().equals("Edit")) {
//            //TODO temporary use as delete all, later update to edit fragment
//            listOfDreams.clear();
//            adapter.notifyDataSetChanged();
//
//        }else if(item.getTitle().equals("Delete")){
//            int index=item.getItemId();//Meniu ID is same as selected item
//            ////ListView iraso elementui uzdejom id (Adapteryje) kuris sutampa su iraso numeriu ir ji panaudojam trynimui nes ir masyve tie duomenys bus toj paciuj vietoj
//            //We assigned an id (in the Adapter) and it matches to the ListView record element and uses it to delete it because the data will be in the same place in the array.
//            listOfDreams.remove(index);
//            adapter.notifyDataSetChanged();
//        }
//        return super.onContextItemSelected(item);
//    }



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