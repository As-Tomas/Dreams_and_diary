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

public class MainActivity extends AppCompatActivity implements JournalFragment.JournalFragmentListener, AddRecordFragment.AddRecordListener, MoodFragment.MoodListener, SleepQualityFragment.SleepQualityListener, ClarityDreamFragment.ClarityDreamLitener {

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
    private String[] tags;
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
    public void onInputAssent(CharSequence date, CharSequence time, CharSequence title, CharSequence dreamNotes, CharSequence dayNotes, String[] tags) {

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

        switch (i) {
            case 0:
                mood = R.drawable.verynegative;
                break;
            case 1:
                mood = R.drawable.negative;
                break;
            case 2:
                mood = R.drawable.neutral;
                break;
            case 3:
                mood = R.drawable.nice;
                break;
            case 4:
                mood = R.drawable.verynice;
                break;
        }


        sleepQualityFragment = new SleepQualityFragment();
        setCurrentFragment(sleepQualityFragment);

    }

    @Override
    public void saveSleepQuality(int i) {

        switch (i) {
            case 0:
                quality = R.drawable.verybad;
                break;
            case 1:
                quality = R.drawable.notsogood;
                break;
            case 2:
                quality = R.drawable.normalsleep;
                break;
            case 3:
                quality = R.drawable.greatsleep;
                break;
            case 4:
                quality = R.drawable.perfectsleep;
                break;
        }


        clarityDreamFragment = new ClarityDreamFragment();
        setCurrentFragment(clarityDreamFragment);
    }

    @Override
    public void saveClarityDream(int i) {

        switch (i) {
            case 0:
                clarity = R.drawable.cloudy;
                break;
            case 1:
                clarity = R.drawable.semicloudy;
                break;
            case 2:
                clarity = R.drawable.normal;
                break;
            case 3:
                clarity = R.drawable.semiclear;
                break;
            case 4:
                clarity = R.drawable.clear;
                break;
        }


        Log.i(TAG, "here "+ date.toString());
        Log.i(TAG, "here "+ time.toString());
        Log.i(TAG, "here "+ title.toString());
        Log.i(TAG, "here "+ dreamNotes.toString());
        Log.i(TAG, "here "+ dayNotes.toString());
        Log.i(TAG, "here "+ tags.toString());
        Log.i(TAG, "here mood "+ mood);
        Log.i(TAG, "here quality "+ quality);
        Log.i(TAG, "here clarity "+ clarity);

        //todo should save to DB...

    }

    @Override
    public void save() {
        Dream dreamToSave = new Dream(date, time, title, dreamNotes, dayNotes, tags, mood, quality, clarity);

        try {
            updateDB(dreamToSave, true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // reset for new window new instance
        add_record_fragment = new AddRecordFragment();
    }

    @Override
    public void backToJournal() {
        //journal_fragment = new JournalFragment();
        setCurrentFragment(journal_fragment);
    }

    @Override
    public ArrayList<Dream> loadDB() {

        ArrayList<Dream> dreams = new ArrayList();

        try {
            JSONArray jsonArray = readDB();

            for(int i=0; i < jsonArray.length(); i++ ) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Dream dream = extractFromJSONObject(obj);
                dreams.add(dream);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Cant load from DB",Toast.LENGTH_LONG).show();
        }
        return dreams;
    }

    public boolean updateDB(Dream dreamToUpdate, boolean addOrRemove) throws JSONException {

        //read excisting records
        JSONArray records = readDB();
        //convert to JSONObject
        JSONObject obj = convertToJasonObj(dreamToUpdate);

        //add new record
        if(addOrRemove){
            records.put(obj);
            saveDB(records);

        //remove record
        } else {

            //todo on remove
        }
        return true;
    }


    public JSONArray readDB() throws JSONException {

        if(isExternalStorageWritable()==false){
            Toast.makeText(this,"can`t reach external memory",Toast.LENGTH_SHORT).show();
            return null;
        }

        String path = getExternalFilesDir(null) + "/DreamsDiary";
        System.out.println("PATH of file"+path);
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

        JSONArray arrfromFile = null;
        if(content != null){
            arrfromFile = new JSONArray(content);

//            JSONObject temp = new JSONObject(content);
//            arrfromFile.put(temp);

        } else {
            arrfromFile = new JSONArray();
        }

        return arrfromFile;
    }

    public void saveDB(JSONArray records) throws JSONException {

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
                fw.write(String.valueOf(records));
                fw.close();
                Log.i("SaveToFile","Success 2");

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println(" No Permision to write isExternalStorageWritable()");
        }
    }

    public JSONObject convertToJasonObj(Dream record) throws JSONException {

        //JSONArray recordsArray = new JSONArray();

        JSONObject recordObj = new JSONObject();
        JSONObject tagsObj = new JSONObject();
        JSONArray tagsArr = new JSONArray();

        recordObj.put("date",record.getDate());
        recordObj.put("time",record.getDate());
        recordObj.put("title",record.getTitle());
        recordObj.put("dreamsNotice", record.getDreamsNotice());
        recordObj.put("dayNotice", record.getDayNotice());

        String[] tags = record.getTags();
        for (String tag : tags){
            System.out.println("tag: "+ tag);

            tagsArr.put(tag);
        }
        //tagsObj.put("tags", tagsArr);

        recordObj.put("tags",tagsArr);
        recordObj.put("moodDream", record.getMoodDream());
        recordObj.put("sleepQuantity", record.getSleepQuantity());
        recordObj.put("clarityDream", record.getClarityDream());

//        recordsArray.put(recordObj);
//
//        JSONObject recordObjs = new JSONObject(String.valueOf(recordsArray));

        return recordObj;
    }

    public Dream extractFromJSONObject(JSONObject objToExtract){
        Dream dream = null;
        try {

            JSONArray tagsarr = new JSONArray(objToExtract.getString("tags"));
            String[] tags = new String[tagsarr.length()];
            for(int i=0; i<tags.length; i++) {
                tags[i] = tagsarr.optString(i);
            }

            dream = new Dream(objToExtract.getString("date"),
                    objToExtract.getString("time"),
                    objToExtract.getString("title"),
                    objToExtract.getString("dreamsNotice"),
                    objToExtract.getString("dayNotice"),
                    tags,
                    Integer.parseInt(objToExtract.getString("moodDream")),
                    Integer.parseInt(objToExtract.getString("sleepQuantity")),
                    Integer.parseInt(objToExtract.getString("clarityDream")) );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dream;
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