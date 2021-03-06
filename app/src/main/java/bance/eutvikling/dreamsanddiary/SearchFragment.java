package bance.eutvikling.dreamsanddiary;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class SearchFragment extends Fragment {

    ArrayList<Dream> listOfDreams;
    ArrayList<Dream> resultListOfDreams;
    ListView list;
    ArrayListAdapter adapter;

    private SearchFragmentListener listener;

    public interface SearchFragmentListener {
        ArrayList<Dream> loadDB();
    }

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        resultListOfDreams = new ArrayList();
        adapter = new ArrayListAdapter(resultListOfDreams, view.getContext());
        list = view.findViewById(R.id.listResults);
        list.setAdapter(adapter);

        listOfDreams = listener.loadDB();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn = view.findViewById(R.id.btnSearch);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                TextInputEditText searchEditText = getActivity().findViewById(R.id.search);
                CharSequence word = searchEditText.getText();
                
                search(word);

                adapter.notifyDataSetChanged();
//                adapter = new ArrayListAdapter(resultListOfDreams, view.getContext());
//                list = view.findViewById(R.id.listResults);
//                list.setAdapter(adapter);

            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof SearchFragmentListener){
            listener = (SearchFragmentListener) context;
        } else {
            throw new RuntimeException(context + " must implement SearchFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    void search( CharSequence searchWord) {

        resultListOfDreams.clear();
        
        for(Dream record : listOfDreams){
            if(record.getTitle().toString().contains(searchWord) ||
                    record.getDreamsNotice().toString().contains(searchWord) ||
                    record.getDayNotice().toString().contains(searchWord)){
                resultListOfDreams.add(record);
            }
            //search in tags
            for(String tag : record.getTags()){
                if(tag.contains(searchWord)){
                    resultListOfDreams.add(record);
                }
            }
        }

        if(resultListOfDreams.size() > 0){
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Dream chosen = listOfDreams.get(i);

                    PreviewDreamFragment prevFrag = new PreviewDreamFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(PreviewDreamFragment.ARG_DATE_TIME, chosen.getDateAndTime().toString()); //for display in preview
                    bundle.putString(PreviewDreamFragment.ARG_DATE, chosen.getDate().toString());
                    bundle.putString(PreviewDreamFragment.ARG_TIME, chosen.getTime().toString());
                    bundle.putString(PreviewDreamFragment.ARG_TITLE, chosen.getTitle().toString());
                    bundle.putString(PreviewDreamFragment.ARG_DREAM_NOTES, chosen.getDreamsNotice().toString());
                    bundle.putString(PreviewDreamFragment.ARG_DAY_NOTES, chosen.getDayNotice().toString());
                    bundle.putString(PreviewDreamFragment.ARG_TAGS, String.join(", ", chosen.getTags()));
                    bundle.putInt(PreviewDreamFragment.ARG_MOOD, chosen.getMoodDream());
                    bundle.putInt(PreviewDreamFragment.ARG_QUALITY, chosen.getSleepQuantity());
                    bundle.putInt(PreviewDreamFragment.ARG_CLARITY, chosen.getClarityDream());
                    bundle.putInt(PreviewDreamFragment.ARG_ID, i);
                    Log.i("id: ", String.valueOf(i));
                    prevFrag.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, prevFrag, "findThisFragment")
                            .addToBackStack(null)
                            .commit();
                }
            });
        }

    }
}