package com.example.josycom.aad_team_44_animation_challenge;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    QuizListManager.Quiz quiz;
    DataManager dataManager;
    List<Question> questions;
    Question currentQuestion;
    int currentNumber = 1;
    int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = findViewById(R.id.toolbar);

        dataManager = DataManager.getInstance(this);
        quiz = (QuizListManager.Quiz)getIntent().getExtras().getSerializable("quiz");

        toolbar.setTitle(quiz.getName());
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                gotoNextQuestion();
            }
        });



        ((ChipGroup)findViewById(R.id.choice_group)).setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int i) {

                if (i == -1) return;

                Chip checkedChip = chipGroup.findViewById(chipGroup.getCheckedChipId());
                Animation animation;
                if (checkedChip.getText() == currentQuestion.options.get(currentQuestion.answer)) {
                    animation = AnimationUtils.loadAnimation(chipGroup.getContext(), R.anim.correct_anim);
                    ++score;
                } else {
                    animation = AnimationUtils.loadAnimation(chipGroup.getContext(), R.anim.wrong_anim);
                }

                checkedChip.startAnimation(animation);

                animation.setDuration(animation.getDuration() + 1000);

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) { }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        gotoNextQuestion();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) { }
                });
            }
        });

        dataManager.loadQuestion(quiz, new DataManager.OnQuestionsLoadedListener() {
            @Override
            public void onQuestionsLoaded(List<Question> questions) {
                QuestionActivity.this.questions = questions;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("EXIT");
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle() == "EXIT") {
            finish();
        }

        return true;
    }

    private void gotoNextQuestion(){
        if (currentNumber > questions.size()){
            new AlertDialog.Builder(this).setMessage("Congratulations your score is: " + score).setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            }).show();
        } else {
            currentQuestion = questions.get(currentNumber++ - 1);
            TextView questionTextView = findViewById(R.id.question_text);
            ChipGroup choices = findViewById(R.id.choice_group);

            questionTextView.setText(currentQuestion.Question);

            for (int i = 0; i <  currentQuestion.options.size(); i++){
                Chip chip = (Chip)choices.getChildAt(i);
                chip.setText(currentQuestion.options.get(i), TextView.BufferType.NORMAL);
            }
        }


    }
}
