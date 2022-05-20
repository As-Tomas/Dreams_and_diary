package bance.eutvikling.dreamsanddiary;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;


public class AddRecordFragment extends Fragment {


    public AddRecordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_record, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button btn = view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Get input text
                TextInputEditText datetitleText = (TextInputEditText)getActivity().findViewById(R.id.date);
                String date = datetitleText.getText().toString();
                TextInputEditText timetitleText = (TextInputEditText)getActivity().findViewById(R.id.title);
                String time = timetitleText.getText().toString();
                TextInputEditText titleText = (TextInputEditText)getActivity().findViewById(R.id.title);
                String title = titleText.getText().toString();
                TextInputEditText dreamNotestitleText = (TextInputEditText)getActivity().findViewById(R.id.title);
                String dreamNotes = dreamNotestitleText.getText().toString();
                TextInputEditText dayNotestitleText = (TextInputEditText)getActivity().findViewById(R.id.title);
                String daynotes = dayNotestitleText.getText().toString();
                TextInputEditText tagstitleText = (TextInputEditText)getActivity().findViewById(R.id.title);
                String tags = tagstitleText.getText().toString();

                Log.i(TAG,title);
            }
        });

    }
}