package bance.eutvikling.dreamsanddiary;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PreviewDreamFragment extends Fragment {

    public static final String ARG_TIME="Time" ;
    public static final String ARG_TITLE="title" ;
    public static final String ARG_DREAM_NOTES="dreamNotes" ;
    public static final String ARG_DAY_NOTES="dayNotes" ;
    public static final String ARG_TAGS="tags" ;



    public PreviewDreamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_preview_dream, container, false);

        TextView time = view.findViewById(R.id.dateAndTime);
        TextView title = view.findViewById(R.id.title);
        TextView dreamNotes = view.findViewById(R.id.dreamNotes);
        TextView dayNotes = view.findViewById(R.id.dayNotes);
        TextView tags = view.findViewById(R.id.tags);



        Bundle args = getArguments();
        if(args != null) {
            String timeArg = args.getString(ARG_TIME);
            String titleArg = args.getString(ARG_TITLE);
            String dreamNotesArg =  args.getString(ARG_DREAM_NOTES);
            String dayNotesArg = args.getString(ARG_DAY_NOTES);
            String TagsArg = args.getString(ARG_TAGS);

            time.setText(timeArg);
            title.setText(titleArg);
            dreamNotes.setText(dreamNotesArg);
            dayNotes.setText(dayNotesArg);
            tags.setText(TagsArg);

        } else {
            time.setText("No time arg");
        }


        return view;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//    }
}