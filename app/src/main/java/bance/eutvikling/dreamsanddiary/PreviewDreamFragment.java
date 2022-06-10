package bance.eutvikling.dreamsanddiary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PreviewDreamFragment extends Fragment {

    private PreviewDreamFragmentListener listener;

    public interface PreviewDreamFragmentListener {
        void editSelectedRecord(int id,
                                String string, String timeArg,
                                String titleArg,
                                String dreamNotesArg,
                                String dayNotesArg,
                                String TagsArg);

        void deleteSelectedRecord(int id);
    }

    public static final String ARG_DATE_TIME="dateAndTime";

    public static final String ARG_DATE="date";
    public static final String ARG_TIME="time" ;
    public static final String ARG_TITLE="title" ;
    public static final String ARG_DREAM_NOTES="dreamNotes" ;
    public static final String ARG_DAY_NOTES="dayNotes" ;
    public static final String ARG_TAGS="tags" ;
    public static final String  ARG_ID= "Id" ;



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
            String timeArg = args.getString(ARG_DATE) + " " +args.getString(ARG_TIME);
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

        //display options menu Edit, Delete
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void  onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.overflow_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Bundle args = getArguments();
        int id=-1;
        if(args != null ) {
            id = args.getInt(ARG_ID);
        }

        switch (item.getItemId()) {
            case R.id.option_edit:
                // Edit item was selected
                listener.editSelectedRecord( id,
                        args.getString(ARG_DATE),
                        args.getString(ARG_TIME),
                        args.getString(ARG_TITLE),
                        args.getString(ARG_DREAM_NOTES),
                        args.getString(ARG_DAY_NOTES),
                        args.getString(ARG_TAGS));

                return true;
            case R.id.option_delete:
                // Delete item was selected
                listener.deleteSelectedRecord(id);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if( context instanceof PreviewDreamFragmentListener){
            listener = (PreviewDreamFragmentListener) context;
        } else {
            throw new RuntimeException( context.toString() +
                    " must implement PreviewDreamFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    //    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//    }
}