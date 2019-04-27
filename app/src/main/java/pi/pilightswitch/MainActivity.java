package pi.pilightswitch;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                new ConnectToServerTask().execute("ON");
                fadeIn.start();
            }
        });

        // off button
        offButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConnectToServerTask().execute("OFF");
                fadeOut.start();
            }
        });
    }
}
