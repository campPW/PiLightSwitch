package pi.pilightswitch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOError;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button onButton = (Button) findViewById(R.id.onButton);
        final Button offButton = (Button) findViewById(R.id.offButton);

        // on button
        onButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConnectToServerTask().execute("ON");
            }
        });

        // off button
        offButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConnectToServerTask().execute("OFF");
            }
        });
    }
}
