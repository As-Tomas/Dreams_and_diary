package bance.eutvikling.dreamsanddiary;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddRecordFragment.AddRecordListener, MoodFragment.MoodListener, SleepQualityFragment.SleepQualityListener, ClarityDreamFragment.ClarityDreamLitener {

    BottomNavigationView bottomNavigationView;

    JournalFragment journal_fragment;
    AddRecordFragment add_record_fragment;
    SearchFragment search_fragment;

    //private AddRecordFragment addRecordFragment;
    private MoodFragment dreamMoodFragment;
    private SleepQualityFragment sleepQualityFragment;
    private ClarityDreamFragment clarityDreamFragment;
    private CharSequence date;
    private CharSequence time;
    private CharSequence title;
    private CharSequence dreamNotes;
    private CharSequence dayNotes;
    private CharSequence tags;
    private int mood;
    private int quality;
    private int clarity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        journal_fragment = new JournalFragment();
        add_record_fragment = new AddRecordFragment();
        search_fragment = new SearchFragment();


        setCurrentFragment(journal_fragment);

        bottomNavigationView=findViewById(R.id.bottomNav);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.journal:
                        setCurrentFragment(journal_fragment);
                        return true;
                    case R.id.addNew:
                        setCurrentFragment(add_record_fragment);
                        return true;
                    case R.id.search:
                        setCurrentFragment(search_fragment);
                        return true;
                }
                return false;
            }
        });
    }


    public void setCurrentFragment(Fragment fragment){

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onInputAssent(CharSequence date, CharSequence time, CharSequence title, CharSequence dreamNotes, CharSequence dayNotes, CharSequence tags) {

        this.date =date;
        this.time = time;
        this.title = title;
        this.dreamNotes = dreamNotes;
        this.dayNotes = dayNotes;
        this.tags = tags;

        dreamMoodFragment = new MoodFragment();
        setCurrentFragment(dreamMoodFragment);

    }

    @Override
    public void saveMood(int i) {

        mood=i;
        sleepQualityFragment = new SleepQualityFragment();
        setCurrentFragment(sleepQualityFragment);

    }

    @Override
    public void saveSleepQuality(int i) {

        quality=i;

        clarityDreamFragment = new ClarityDreamFragment();
        setCurrentFragment(clarityDreamFragment);
    }

    @Override
    public void saveClarityDream(int i) {

        clarity = i;
        Log.i(TAG, "here "+ date.toString());
        Log.i(TAG, "here "+ time.toString());
        Log.i(TAG, "here "+ title.toString());
        Log.i(TAG, "here "+ dreamNotes.toString());
        Log.i(TAG, "here "+ dayNotes.toString());
        Log.i(TAG, "here "+ tags.toString());
        Log.i(TAG, "here mood "+ mood);
        Log.i(TAG, "here clarity "+ clarity);
        Log.i(TAG, "here quality "+ quality);

        //todo should save to DB...

    }


    public JSONArray readDB() throws JSONException {

        if(isExternalStorageWritable()==false){
            Toast.makeText(this,"can`t reach external memory",Toast.LENGTH_SHORT).show();
            return null;
        }

        String path = getExternalFilesDir(null) + "/DreamsDiary";
        File file = new File(path ,"dreams_Records.txt");

        String content=null;
        if(file.exists())
        {
            FileReader reader = null;
            try {
                reader = new FileReader(file);
                char[] chars = new char[(int) file.length()];
                reader.read(chars);
                content = new String(chars);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            System.out.println("CANT READ FILE or no file ");
            Toast.makeText(this, "No dreams registered",Toast.LENGTH_SHORT).show();
        }
        System.out.println("File read success");
        System.out.println(content);

        JSONArray arrfromFile = new JSONArray();

        if(content != null){
            JSONObject temp = new JSONObject(content);
            arrfromFile.put(temp);

        }
        return arrfromFile;
    }

    public void saveDB(ArrayList<Dream> records) throws JSONException {

        JSONObject objToSave = convertToJasonObj(records);

        if(isExternalStorageWritable()){
            try {

                String state = Environment.getExternalStorageState();
                if (Environment.MEDIA_MOUNTED.equals(state)) {
                    System.err.println("Permision is give to read "+state);
                }

                String path = getExternalFilesDir(null) + "/DreamsDiary";
                File myDir = new File(path);
                System.out.println("Creating file here "+myDir);
                if (!myDir.exists()) {
                    myDir.mkdirs();
                    System.err.println("Created new directory "+myDir);
                }

                File files = new File(path,"dreams_Records.txt");
                FileWriter fw;
                fw = new FileWriter(files);
                fw.write(String.valueOf(objToSave));
                fw.close();
                Log.i("SaveToFile","Success 2");

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println(" No Permision to write isExternalStorageWritable()");
        }
    }

    public JSONObject convertToJasonObj(ArrayList<Dream> records) throws JSONException {

        JSONArray recordsArray = new JSONArray();


        JSONObject recordObj = null;
        JSONObject tagsObj = null;
        for (Dream dream : records) {
            recordObj = new JSONObject();
            recordObj.put("date",dream.getDate());
            recordObj.put("title",dream.getTitle());
            recordObj.put("dreamsNotice", dream.getDreamsNotice());
            recordObj.put("dayNotice", dream.getDayNotice());
            String[] tags = dream.getTags();
            tagsObj.put("tags",tags);
            recordObj.put("sleepQuantity", dream.getSleepQuantity());
            recordObj.put("moodDream", dream.getMoodDream());
            recordObj.put("clarityDream", dream.getClarityDream());
        }
        recordsArray.put(recordObj);

        JSONObject recordObjs = new JSONObject(String.valueOf(recordsArray));

        return recordObjs;
    }

    /* check do we have permision to memory */

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}