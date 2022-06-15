package bance.eutvikling.dreamsanddiary;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;


public class AddRecordFragment extends Fragment {

    private static final String ARG_DATE ="date";
    private static final String ARG_TIME = "timeArg";
    private static final String ARG_TITLE="title" ;
    private static final String ARG_DREAM_NOTES="dreamNotes" ;
    private static final String ARG_DAY_NOTES="dayNotes" ;
    private static final String ARG_TAGS="tags" ;

    private String date;
    private String timeArg;
    private String title;
    private String dreamNotes;
    private String dayNotes;
    private String tags;

    private AddRecordListener listener;


    public interface AddRecordListener {
        void onInputAssent(CharSequence date, CharSequence time, CharSequence title, CharSequence dreamNotes, CharSequence dayNotes, String[] tags);
    }

    public AddRecordFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment AddRecordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddRecordFragment newInstance( String param2, String param3, String param4, String param5, String param6, String param7) {
        AddRecordFragment fragment = new AddRecordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DATE, param2);
        args.putString(ARG_TIME, param3);
        args.putString(ARG_TITLE, param4);
        args.putString(ARG_DREAM_NOTES, param5);
        args.putString(ARG_DAY_NOTES, param6);
        args.putString(ARG_TAGS, param7);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            date = getArguments().getString(ARG_DATE);
            timeArg = getArguments().getString(ARG_TIME);
            title = getArguments().getString(ARG_TITLE);
            dreamNotes = getArguments().getString(ARG_DREAM_NOTES);
            dayNotes = getArguments().getString(ARG_DAY_NOTES);
            tags = getArguments().getString(ARG_TAGS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_record, container, false);

        if (getArguments() != null){

            TextInputEditText dateField = (TextInputEditText)view.findViewById(R.id.date);
            dateField.setText(date);

            TextInputEditText timeTitleText = (TextInputEditText)view.findViewById(R.id.time);
            timeTitleText.setText(timeArg);


            TextInputEditText titleText = (TextInputEditText)view.findViewById(R.id.title);
            titleText.setText(title);
            TextInputEditText dreamNotesTitleText = (TextInputEditText)view.findViewById(R.id.dreamNotes);
            dreamNotesTitleText.setText(dreamNotes);
            TextInputEditText dayNotesTitleText = (TextInputEditText)view.findViewById(R.id.dayNotes);
            dayNotesTitleText.setText(dayNotes);
            TextInputEditText tagsTitleText = (TextInputEditText)view.findViewById(R.id.tags);
            tagsTitleText.setText(tags);


        } else {
            DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime dateNow = LocalDateTime.now();

            TextInputEditText dateField = (TextInputEditText)view.findViewById(R.id.date);
            dateField.setText(date.format(dateNow));

            TextInputEditText timeTitleText = (TextInputEditText)view.findViewById(R.id.time);
            timeTitleText.setText(time.format(dateNow));
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button btn = view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Get input text
                TextInputEditText dateTitleText = (TextInputEditText)getActivity().findViewById(R.id.date);
                CharSequence date = dateTitleText.getText();
                TextInputEditText timeTitleText = (TextInputEditText)getActivity().findViewById(R.id.time);
                CharSequence time = timeTitleText.getText();
                TextInputEditText titleText = (TextInputEditText)getActivity().findViewById(R.id.title);
                CharSequence title = titleText.getText();
                TextInputEditText dreamNotesTitleText = (TextInputEditText)getActivity().findViewById(R.id.dreamNotes);
                CharSequence dreamNotes = dreamNotesTitleText.getText();
                TextInputEditText dayNotesTitleText = (TextInputEditText)getActivity().findViewById(R.id.dayNotes);
                CharSequence dayNotes = dayNotesTitleText.getText();
                TextInputEditText tagsTitleText = (TextInputEditText)getActivity().findViewById(R.id.tags);

                String allTags = tagsTitleText.getText().toString();
                String[] tags = allTags.split(",");

                listener.onInputAssent(date, time, title, dreamNotes, dayNotes, tags);

            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddRecordListener ) {
            listener = (AddRecordListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement AddRecordListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}