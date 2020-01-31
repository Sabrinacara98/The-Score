package com.example.basket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements ChangeDialog.ChangeDialogListener{
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;
    private int counter = 0;
    private Button button;
    private static final String FILE_NAME = "sub.srt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = findViewById(R.id.chronometer);
        button  = findViewById(R.id.processid);
    }

    public void screenTapped(View view) {
        openDialog();
    }

    public void openDialog(){
        Bundle args = new Bundle();
        int elapsedMillis = (int)(SystemClock.elapsedRealtime() - chronometer.getBase());
        DateFormat time = new SimpleDateFormat("HH:mm:ss,SSS");
        time.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date result = new Date(elapsedMillis);
        Date end = new Date (elapsedMillis+3000);
        args.putString("time", time.format(result));
        args.putString("endtime", time.format(end));
        Toast.makeText(getApplicationContext(), "Value: " +time.format(result), Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "END Value: " +time.format(end), Toast.LENGTH_SHORT).show();
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.setArguments(args);
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }
    public void startChronometer(View view){
        if(!running){
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
        else{
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    public void processVideo(View view){
        startActivity(new Intent(MainActivity.this, VideoProcess.class));
    }


    @Override
    public void applyTexts(String time,String end, String person, String action,String selection_team, String playerIn, String playerOut) {
        Toast.makeText(getApplicationContext(), "Value: " +time.format(time) +" "+ person+action+selection_team+playerIn+playerOut, Toast.LENGTH_SHORT).show();
        counter++;
        Toast.makeText(getApplicationContext(), "Counter: " + counter, Toast.LENGTH_SHORT).show();
        if(playerOut.equals("null")){
            playerOut = " ";
        }
        String text = counter +"\n"+ time +" --> "+ end + "\n" + person + " " + action + " " + selection_team + " " + playerIn + " " + playerOut + "\n\n";
        write(text, counter);
    }

    public void write(String text, int counter){
            FileOutputStream fos = null;

        try{
            if(counter == 1){
                fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            }
            fos = openFileOutput(FILE_NAME, getApplicationContext().MODE_APPEND);
            fos.write(text.getBytes());
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
