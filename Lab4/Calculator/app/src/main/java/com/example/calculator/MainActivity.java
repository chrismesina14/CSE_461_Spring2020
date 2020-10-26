package com.example.calculator;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText t1;
    EditText t2;

    Button plus;
    Button minus;
    Button multiply;
    Button divide;

    TextView displayResult;

    String oper = "";

    Socket socket;
    String response = "";

    /** Called when the activity is first created. */
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find the EditText elements (defined in res/layout/activity_main.xml
        t1 = (EditText) findViewById(R.id.t1);
        t2 = (EditText) findViewById(R.id.t2);

        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        multiply = (Button) findViewById(R.id.multiply);
        divide = (Button) findViewById(R.id.divide);

        displayResult = (TextView) findViewById(R.id.displayResult);

        // set  listeners
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
    }

    // @Override
    public void onClick(View view) {
        if (TextUtils.isEmpty(t1.getText().toString())) {
            return;
        }
        switch (view.getId()) {
            case R.id.plus:
                oper = "+";
                break;
            case R.id.minus:
                oper = "-";
                break;
            case R.id.multiply:
                oper = "*";
                break;
            case R.id.divide:
                oper = "/";
                break;
            default:
                break;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    response = "";
                    socket = new Socket("192.168.0.130", 6000);
                    DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
                    DataInputStream dIn = new DataInputStream(socket.getInputStream());
                    dOut.writeUTF(t1.getText() + " " + t2.getText() + " " + oper);
                    dOut.flush();
                    response = dIn.readUTF();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            displayResult.setText(response);
                        }
                    });
                    dIn.close();
                    dOut.close();
                    socket.close();
                }
                catch (UnknownHostException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}