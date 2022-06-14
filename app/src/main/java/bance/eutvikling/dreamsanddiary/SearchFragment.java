package bance.eutvikling.dreamsanddiary;

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
    ListView list;
    ArrayListAdapter adapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        listOfDreams = new ArrayList();
        adapter = new ArrayListAdapter(listOfDreams, view.getContext());
        list = view.findViewById(R.id.listResults);
        list.setAdapter(adapter);

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
}