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
import android.widget.LinearLayout;


public class ClarityDreamFragment extends Fragment {

    private int clarity;
    private ClarityDreamLitener listener;

    public interface ClarityDreamLitener {
        void saveClarityDream(int i);
        void save();
        void backToJournal();
    }

    public ClarityDreamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if( context instanceof ClarityDreamLitener ) {
            listener = (ClarityDreamLitener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MoodListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clarity_dream, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout first = view.findViewById(R.id.first);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.saveClarityDream( 0);
                listener.save();
                listener.backToJournal();
            }
        });

        LinearLayout second = view.findViewById(R.id.second);
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.saveClarityDream(1);
                listener.save();
                listener.backToJournal();
            }
        });

        LinearLayout third = view.findViewById(R.id.third);
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.saveClarityDream(2);
                listener.save();
                listener.backToJournal();
            }
        });

        LinearLayout forth = view.findViewById(R.id.forth);
        forth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.saveClarityDream(3);
                listener.save();
                listener.backToJournal();
            }
        });

        LinearLayout fifth = view.findViewById(R.id.fifth);
        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.saveClarityDream(4);
                listener.save();
                listener.backToJournal();
            }
        });

        Button save = view.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.save();
                listener.backToJournal();
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}