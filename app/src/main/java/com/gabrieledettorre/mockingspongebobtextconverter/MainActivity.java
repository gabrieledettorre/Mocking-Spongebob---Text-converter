package com.gabrieledettorre.mockingspongebobtextconverter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etxt;
    TextView  txt;
    Button btn, cpbtn;
    String input, converted;

    private ClipboardManager myClipboard;
    private ClipData myClip;


    public static String toSpongeCase (String input) {
        String result = "";
        for (int i = 0; i < input.length(); i++) {
            result += (i%2 == 0) ? Character.toLowerCase(input.charAt(i)) : Character.toUpperCase(input.charAt(i));
        }
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etxt = (EditText) findViewById(R.id.tRaw);
        txt = (TextView) findViewById(R.id.tConverted);
        btn = (Button) findViewById(R.id.bConvert);
        cpbtn = (Button) findViewById(R.id.bCopy);


        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.v("EditText", etxt.getText().toString());

                input = etxt.getText().toString();
                converted = toSpongeCase(input);

                txt.setText(converted);
            }
        });


        cpbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                String text;
                text = txt.getText().toString();

                myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);

                Toast.makeText(getApplicationContext(), "Text Copied", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


