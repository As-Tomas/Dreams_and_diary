package bance.eutvikling.dreamsanddiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public JSONArray readDB() throws JSONException {


        if(isExternalStorageWritable()==false){
            Toast.makeText(this,"Negalima pasiekti isorines atminties",Toast.LENGTH_SHORT).show();
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

    /* tikrinam, ar galime skaityti ir rasyti */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}