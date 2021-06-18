package com.example.lorentzfactorcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate:Home Screen");

        TextView textView2 = findViewById(R.id.textView2);
        TextView textView =findViewById(R.id.textView);
        TextView textView10 = findViewById(R.id.textView10);
        TextView textView9 = findViewById(R.id.textView9);
        textView9.setVisibility(View.INVISIBLE);
        textView10.setVisibility(View.INVISIBLE);
        Button btn7=findViewById(R.id.button7);
        btn7.setVisibility(View.INVISIBLE);
        Button btn1 = findViewById(R.id.button);
        btn1.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick:to lorentz factor");
                Intent intent = new Intent(MainActivity.this,LorentzFactor.class);
                startActivity(intent);
            }
        });

        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick:to spi factor");
                textView.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                btn1.setVisibility(View.INVISIBLE);
                btn2.setVisibility(View.INVISIBLE);
                textView9.setVisibility(View.VISIBLE);
                textView10.setVisibility(View.VISIBLE);
                btn7.setVisibility(View.VISIBLE);
                Thread thread = new Thread()
                {
                    @Override
                    public void run() {
                        try {
                            while (!isInterrupted()) {
                                Thread.sleep(1000);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Date date = Calendar.getInstance().getTime();
                                        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
                                        String strDate = dateFormat.format(date);
                                        String[] hourMin = strDate.split(":");
                                        double h= Double.parseDouble(hourMin[0]);
                                        double m = Double.parseDouble(hourMin[1]);
                                        double s = Double.parseDouble(hourMin[2]);
                                        double spi;
                                        spi = fact(h)/((m*m*m)+s);
                                        System.out.println(spi);
                                        String x = ""+spi;
                                        textView10.setText(x);
                                    }
                                });
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                };

                thread.start();
            }
        });
        btn7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                btn1.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.VISIBLE);
                textView9.setVisibility(View.INVISIBLE);
                textView10.setVisibility(View.INVISIBLE);
                btn7.setVisibility(View.INVISIBLE);
            }
        });
    }
    public double fact(double n) {
        if (n == 0) {
            return 1;
        } else {
            return n * fact(n - 1);
        }
    }
}