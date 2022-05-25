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

    private AddRecordListener listener;
   // private CharSequence date;
//    private CharSequence time;
//    private CharSequence title;
//    private CharSequence dreamNotes;
//    private CharSequence dayNotes;
//    private CharSequence tags;

    public interface AddRecordListener {
        void onInputAssent(CharSequence date, CharSequence time, CharSequence title, CharSequence dreamNotes, CharSequence dayNotes, String[] tags);
    }

    public AddRecordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_record, container, false);

        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime dateNow = LocalDateTime.now();

        TextInputEditText dateFeald = (TextInputEditText)view.findViewById(R.id.date);
        dateFeald.setText(date.format(dateNow));

        TextInputEditText timeTitleText = (TextInputEditText)view.findViewById(R.id.time);
        timeTitleText.setText(time.format(dateNow));

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
                TextInputEditText tagstitleText = (TextInputEditText)getActivity().findViewById(R.id.tags);

                String allTags = tagstitleText.getText().toString();
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