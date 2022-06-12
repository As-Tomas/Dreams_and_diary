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
import android.widget.TextView;


public class SleepQualityFragment extends Fragment {

    private static final String ARG_QUALITY = "quality";
  private int sleepQuality;

  private SleepQualityListener listener;

  public interface SleepQualityListener {
      void saveSleepQuality(int i);
  }

    public SleepQualityFragment() {
        // Required empty public constructor
    }

    public static SleepQualityFragment newInstance(int param1){
        SleepQualityFragment fragment = new SleepQualityFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_QUALITY, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            sleepQuality = getArguments().getInt(ARG_QUALITY);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if( context instanceof SleepQualityListener) {
            listener = (SleepQualityListener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sleep_quality, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout first = view.findViewById(R.id.first);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.saveSleepQuality(0);
            }
        });

        LinearLayout second = view.findViewById(R.id.second);
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.saveSleepQuality(1);
            }
        });

        LinearLayout third = view.findViewById(R.id.third);
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.saveSleepQuality(2);
            }
        });

        LinearLayout forth = view.findViewById(R.id.forth);
        forth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.saveSleepQuality(3);
            }
        });

        LinearLayout fifth = view.findViewById(R.id.fifth);
        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.saveSleepQuality(4);
            }
        });


        Button button = (Button) view.findViewById(R.id.nextButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.saveSleepQuality(sleepQuality); // no selection -> edit mode
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}