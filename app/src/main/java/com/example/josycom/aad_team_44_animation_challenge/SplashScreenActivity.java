package com.example.josycom.aad_team_44_animation_challenge;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ImageView splashImage = findViewById(R.id.splashImage);
        TextView splashText = findViewById(R.id.splashText);

        //Creates the animation from the XML file and applies it to the ImageView and TextView
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.fade_animation);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.slide_animation);
        splashImage.startAnimation(animation1);
        splashText.startAnimation(animation2);

        final Intent intent = new Intent(this, MainActivity.class);

        //Creates a new Thread to make the splash screen work perfectly
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //Launches the Main Activity
                    startActivity(intent);
                    //Creates an Activity transition animation
                    Animatoo.animateZoom(SplashScreenActivity.this);
                    finish();
                }
            }
        };
        timer.start();
    }
}