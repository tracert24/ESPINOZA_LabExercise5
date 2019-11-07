package com.espinoza.espinoza_labexercise5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.espinoza.espinoza_labexercise5.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView list;
    String[] api;
    String[] rDate;
    String[] info;
    String[] names;
    String[] version;

    int[] cLogo = {R.drawable.v1, R.drawable.cupcake, R.drawable.donut, R.drawable.eclair, R.drawable.froyo, R.drawable.gingerbread, R.drawable.honeycomb, R.drawable.icsandwich, R.drawable.jellybean, R.drawable.kitkat,
            R.drawable.lollipop, R.drawable.marshmallow, R.drawable.nougat, R.drawable.oreo, R.drawable.pie, R.drawable.ten};

    ArrayList<Android> androidList =  new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names = getResources().getStringArray(R.array.androidNames);
        version = getResources().getStringArray(R.array.androidVersions);
        api = getResources().getStringArray(R.array.androidAPI);
        rDate = getResources().getStringArray(R.array.androidRdate);
        info = getResources().getStringArray(R.array.info);
        list = findViewById(R.id.lvAndroids);

        for(int array = 0; array < names.length; array++){
            androidList.add(new Android(cLogo[array], names[array], version[array], api[array], rDate[array], info[array]));
        }

        list = findViewById(R.id.lvAndroids);
        AndroidAdapter adapter = new AndroidAdapter(this, R.layout.item, androidList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        final File folder = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder, "android.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            String choiceName = androidList.get(i).getName();
            String choiceVersion = androidList.get(i).getVersion();
            String choiceApi = androidList.get(i).getApi();
            String choiceRdate = androidList.get(i).getRdate();
            String choiceInfo = androidList.get(i).getInfo();

            fos.write(("Version " + choiceName + " (" + choiceVersion + ")\n").getBytes());
            fos.write((choiceApi + "\n").getBytes());
            fos.write((choiceRdate + "\n").getBytes());
            fos.write((choiceInfo + "\n").getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(androidList.get(i).getName());
        dialog.setIcon(androidList.get(i).getLogo());
        dialog.setMessage(androidList.get(i).getInfo());
        dialog.setNeutralButton("OKAY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick (DialogInterface dialog,int which) {
                File file = new File(folder, "android.txt");
                StringBuilder sb = new StringBuilder();
                // dialog.dismiss();

                String toastName = null;
                String toastRdate = null;

                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    for (int counter = 0; counter < 1; counter++) {
                        toastName = br.readLine();
                    }
                    for (int counter = 0; counter < 2; counter++) {
                        toastRdate = br.readLine();
                    }
                } catch (FileNotFoundException e)
                {
                    Log.d("error", "Sorry, file not found.");
                } catch (IOException e)
                {
                    Log.d("error", "IO error!");
                }

                Toast.makeText(MainActivity.this, toastName+ "\n" + toastRdate, Toast.LENGTH_LONG).show();
            }
        });
        dialog.create().show();

    }
}
