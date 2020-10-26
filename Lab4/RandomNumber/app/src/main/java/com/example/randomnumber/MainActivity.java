package com.example.randomnumber;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.IOException;
import java.net.Socket;
import java.io.*;
import java.net.UnknownHostException;
import java.lang.*;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{
    EditText t1;
    EditText t2;
    EditText t3;
    Button send;
    TextView displayResult;
    Socket socket;
    String response = "";
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (EditText) findViewById(R.id.t1);
        t2 = (EditText) findViewById(R.id.t2);
        t3 = (EditText) findViewById(R.id.t3);
        send = (Button) findViewById(R.id.send);
        displayResult = (TextView) findViewById(R.id.displayResult);
        send.setOnClickListener( this );
        t1.setGravity(Gravity.CENTER);
        t2.setGravity(Gravity.CENTER);
        t3.setGravity(Gravity.CENTER);
        displayResult.setGravity(Gravity.CENTER);
    }
    public void onClick(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    response = "";
                    socket = new Socket("192.168.0.130", 5000);
                    DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
                    DataInputStream dIn = new DataInputStream(socket.getInputStream());
                    dOut.writeUTF (t1.getText() + " "+ t2.getText()+ " "+ t3.getText());
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
