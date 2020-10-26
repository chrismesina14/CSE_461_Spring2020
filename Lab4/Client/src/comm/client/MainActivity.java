package comm.client;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import android.app.Activity;
import android.widget.EditText;

public class MainActivity extends Activity {
    private Socket socket;
            private static final int SERVERPORT = 5000;
           // private static final int SERVERPORT = 6000;
           private static final String SERVER_IP = "10.0.2.2";
         //   private static final String SERVER_IP = "139.182.148.49";

            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                new Thread(new ClientThread()).start();
            }
            public void onClick(View view) {
                try {
                    EditText et = (EditText) findViewById(R.id.EditText01);
                    String str = et.getText().toString();
                    PrintWriter out = new PrintWriter(new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream())),
                            true);
                    out.println(str);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            class ClientThread implements Runnable {

                @Override
                public void run() {

                    try {
                        InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                        socket = new Socket(serverAddr, SERVERPORT);
                    } catch (UnknownHostException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }

            }
}
 