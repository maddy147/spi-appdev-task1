package com.example.lorentzfactorcalculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.DecimalFormat;

public class LorentzFactor extends AppCompatActivity {
    private static final String TAG ="LorentzFactor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lorentz_factor);
        Log.d(TAG,"onCreate:Lorentz Factor");

        Button btn4= findViewById(R.id.button4);
        btn4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick:to home screen");
                Intent intent = new Intent(LorentzFactor.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Button btn3 = findViewById(R.id.button3);
        EditText getv = findViewById(R.id.editTextNumberDecimal);
        btn3.setOnClickListener(new OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick:value entered");
                String txt=getv.getText().toString();
                if(txt.isEmpty()){
                    toastmsg("please enter the value");
                }

                double vel = Double.parseDouble(txt);
                if((vel >= 300000000))
                {
                    toastmsg("Invalid Input");
                }
                double lf;
                double c= 300000000;
                lf=c/(Math.sqrt((c*c) - (vel*vel)));
                TextView txtview = findViewById(R.id.textView6);
                txtview.setText(lf+"");
            }
        });
        Button btn6=findViewById(R.id.button6);
        EditText getvel = findViewById(R.id.editTextNumberDecimal2);
        EditText getlf =findViewById(R.id.editTextNumberDecimal3);
        ConstraintLayout rl = findViewById(R.id.lorentz_factor);
        Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        btn6.setOnClickListener(new OnClickListener() {
                                    @SuppressLint("SetTextI18n")
                                    @Override
                                    public void onClick(View v) {
                                        double velo =Double.parseDouble(getvel.getText().toString());
                                        double lf=Double.parseDouble(getlf.getText().toString());
                                        if(velo >= 300000000){
                                            toastmsg("Invalid Input");
                                        }
                                        double c=300000000;
                                        double actlf=c/(Math.sqrt((c*c) - (velo*velo)));
                                        DecimalFormat dec = new DecimalFormat("#0.00");
                                        if( Double.parseDouble(dec.format(actlf)) == lf){
                                            System.out.println("Green");
                                            rl.setBackgroundColor(Color.GREEN);
                                        }
                                        else{
                                            rl.setBackgroundColor(Color.RED);
                                            vib.vibrate(500);
                                            TextView txtview = findViewById(R.id.textView6);
                                            toastmsg("Wrong Answer");
                                            txtview.setText("Correct Value:"+actlf);
                                        }
                                    }
                                }
        );
    }
    private void toastmsg(String msg){
        Toast.makeText(LorentzFactor.this, msg, Toast.LENGTH_SHORT).show();
    }
}
