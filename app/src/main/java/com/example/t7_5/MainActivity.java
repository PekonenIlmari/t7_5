package com.example.t7_5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Context context = null;
    TextView info;
    EditText file, text;
    String inputFile, outputFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        file = findViewById(R.id.fileName);
        text = findViewById(R.id.inputText);
        info = findViewById(R.id.textView);
    }

    public void readFile(View v) {
        try {
            inputFile = file.getText().toString();
            InputStream ins = context.openFileInput(inputFile);

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s = "";

            while ((s = br.readLine()) != null) {
                text.setText(s);
            }
            info.setText("Luettu!");
            ins.close();

        } catch(FileNotFoundException e) {
            Log.e("FileNotFoundException", "Lukuvirhe");
            info.setText("VIRHE!");
        } catch (IOException e) {
            Log.e("IOException", "Lukuvirhe");
            info.setText("VIRHE!");
        }
    }

    public void writeFile(View v) {
        try {
            outputFile = file.getText().toString();
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(outputFile, Context.MODE_PRIVATE));

            String s = text.getText().toString();

            osw.write(s);
            info.setText("Kirjoitettu!");
            osw.close();

        } catch(FileNotFoundException e) {
            Log.e("FileNotFoundException", "Lukuvirhe");
            info.setText("VIRHE!");
        } catch (IOException e) {
            Log.e("IOException", "Lukuvirhe");
            info.setText("VIRHE!");
        }
    }
}
