package pi.pilightswitch;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.SocketException;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private Context context = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // context is passed to ConnectToServerTask to allow update to ui when exception is caught
        context = getApplicationContext();
        final ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.ConstraintLayout);

        // fade in to light color when on button is pressed
        final ObjectAnimator fadeIn = ObjectAnimator.ofObject(constraintLayout,"backgroundColor",
                new ArgbEvaluator(), getColor(R.color.colorPrimary), getColor(R.color.onColor));
        fadeIn.setDuration(1000);

        // fade out to dark color when off button is pressed
        final ObjectAnimator fadeOut = ObjectAnimator.ofObject(constraintLayout,"backgroundColor",
                new ArgbEvaluator(), getColor(R.color.onColor), getColor(R.color.colorPrimary));
        fadeIn.setDuration(1000);

        final Button onButton = (Button) findViewById(R.id.onButton);
        final Button offButton = (Button) findViewById(R.id.offButton);

        // on button
        onButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startConnectToServerTask("ON", fadeIn);
            }
        });
        // off button
        offButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startConnectToServerTask("OFF", fadeOut);
            }
        });
    }
    private void startConnectToServerTask(String inputToServer, ObjectAnimator fade) {
        ConnectToServerTask ct = (ConnectToServerTask) new  ConnectToServerTask(context).execute(inputToServer);
        try {
            String asyncConnectionResult = ct.get();
            // if exception not caught in ConnectToServerTask, allow fade to occur
            if (asyncConnectionResult == "")
                fade.start();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
