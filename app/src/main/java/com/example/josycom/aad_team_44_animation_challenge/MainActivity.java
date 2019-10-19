package com.example.josycom.aad_team_44_animation_challenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton = findViewById(R.id.playButton);
        TextView welcomeTextView = findViewById(R.id.welcomeText);


        //Creates the animation from the XML file and applies it to the TextView and Button
        Animation animation3 = AnimationUtils.loadAnimation(this, R.anim.blink_animation);
        Animation animation4 = AnimationUtils.loadAnimation(this, R.anim.move_animation);
        playButton.startAnimation(animation3);
        welcomeTextView.startAnimation(animation4);
    }

    @Override
    protected void onResume() {
        //Resets the colour of the Button if the user returns to the Activity
        playButton.setBackground(getResources().getDrawable(R.drawable.green_oval));
        super.onResume();
    }

    /**
     * Launches the ListOfQuiz Activity.
     */
    public void launchListOfQuizActivity(View view) {
        //Changes the colour of the Button when clicked
        playButton.setBackground(getResources().getDrawable(R.drawable.oval));
        //startActivity(new Intent(this, ListOfQuizActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem sports = menu.add(0, 1, 0, "Sport Quiz");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                startActivity(new Intent(this, SportQuizActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
}
