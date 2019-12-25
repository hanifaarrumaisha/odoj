package id.ac.ui.cs.mobileprogramming.hanifa.odoj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import id.ac.ui.cs.mobileprogramming.hanifa.odoj.openGL.OpenGLView;

public class SplashScreenActivity extends AppCompatActivity {
    private static boolean splashLoaded = false;
    private static final int LOADING_TIME = 3000;
    private OpenGLView openGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (!splashLoaded) {
            setContentView(R.layout.activity_splash_screen);

            openGLView = (OpenGLView) findViewById(R.id.openGLView);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    //setelah loading maka akan langsung berpindah ke home activity
                    Intent splashScreen = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(splashScreen);
                    finish();

                }
            }, LOADING_TIME);
            splashLoaded = true;
        }
        else {
            Intent goToMainActivity = new Intent(SplashScreenActivity.this, MainActivity.class);
            goToMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(goToMainActivity);
            finish();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        openGLView.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        openGLView.onResume();
    }


}
