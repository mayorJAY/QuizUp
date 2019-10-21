package com.example.josycom.aad_team_44_animation_challenge;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Animatable2;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
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
        splashImage.setAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_animation));
        splashText.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_animation));

        AnimationSet animationSet = new AnimationSet(false);

        animationSet.addAnimation(splashImage.getAnimation());
        animationSet.addAnimation(splashText.getAnimation());

        splashImage.getAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animation.getDuration();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                //Creates an Activity transition animation
                Animatoo.animateZoom(SplashScreenActivity.this);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animationSet.start();
    }
}