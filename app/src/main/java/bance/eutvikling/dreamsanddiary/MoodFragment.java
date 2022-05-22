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
import android.widget.TextView;


public class MoodFragment extends Fragment {

    private MoodListener listener;

    public MoodFragment() {
        // Required empty public constructor
    }

    public interface MoodListener {
        void saveMood(int i);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MoodListener){
            listener = (MoodListener) context;
        }else {
            throw new RuntimeException(context.toString()
                    + " must implement MoodListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mood, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button next = view.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView sad = view.findViewById(R.id.sad);
                TextView kappy = view.findViewById(R.id.happy);

                listener.saveMood( 1);
            }
        });


    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}