package bance.eutvikling.dreamsanddiary;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
                CharSequence searchFor = searchEditText.getText();



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
}