package com.example.josycom.aad_team_44_animation_challenge;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class QuestionActivity extends AppCompatActivity {

    QuizListManager.Quiz quiz;
    DataManager dataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataManager = DataManager.getInstance(this);
        quiz = (QuizListManager.Quiz)getIntent().getExtras().getSerializable("quiz");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new AlertDialog.Builder(view.getContext()).setMessage("Do you want to score and exit?").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("Sure!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Snackbar.make(view, "Congratulations your score is:", Snackbar.LENGTH_LONG).show();
                    }
                }).show();

            }
        });

        ((ChipGroup)findViewById(R.id.choice_group)).setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int i) {

                if (i == -1) return;

                Chip checkedChip = chipGroup.findViewById(chipGroup.getCheckedChipId());
                Animation animation = AnimationUtils.loadAnimation(chipGroup.getContext(), R.anim.correct_anim);
                checkedChip.startAnimation(animation);
            }
        });

    }
}
