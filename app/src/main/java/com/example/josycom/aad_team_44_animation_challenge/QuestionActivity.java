package com.example.josycom.aad_team_44_animation_challenge;

import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
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
import android.view.animation.ScaleAnimation;
import android.widget.TextSwitcher;
import android.widget.TextView;

import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    QuizListManager.Quiz quiz;
    DataManager dataManager;
    List<Question> questions;
    Question currentQuestion;
    int currentNumber = 1;
    int score = 0;
    private TextSwitcher questionTextView;
    private ChipGroup choices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = findViewById(R.id.toolbar);

        dataManager = DataManager.getInstance(this);
        quiz = (QuizListManager.Quiz) getIntent().getExtras().getSerializable("quiz");

        toolbar.setTitle(quiz.getName());
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                gotoNextQuestion();
            }
        });


        questionTextView = findViewById(R.id.question_text);
        ScaleAnimation outAnimation = new ScaleAnimation(1.0f, 3.0f, 1.0f, 1.0f);
        outAnimation.setDuration(500);
        ScaleAnimation inAnimation = new ScaleAnimation(3.0f, 1.0f, 1.0f, 1.0f);
        inAnimation.setDuration(500);
        questionTextView.setOutAnimation(outAnimation);
        questionTextView.setInAnimation(inAnimation);


        choices = findViewById(R.id.choice_group);
        choices.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int i) {

                if (i == -1) return;

                final Chip checkedChip = chipGroup.findViewById(chipGroup.getCheckedChipId());
                final ColorStateList defaultColor = checkedChip.getChipBackgroundColor();
                Animation animation;
                if (checkedChip.getText().toString().matches(currentQuestion.answer)) {
                    checkedChip.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#9C27B0")));
                    animation = AnimationUtils.loadAnimation(chipGroup.getContext(), R.anim.correct_anim);
                    ++score;
                } else {
                    checkedChip.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#FFE91E63")));
                    animation = AnimationUtils.loadAnimation(chipGroup.getContext(), R.anim.wrong_anim);
                }

                animation.setDuration(animation.getDuration() + 400);

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        gotoNextQuestion();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        checkedChip.setChipBackgroundColor(defaultColor);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });

                checkedChip.startAnimation(animation);
            }
        });

        dataManager.putQuestion();

    }

    @Override
    protected void onStart() {
        super.onStart();
        dataManager.loadQuestion(quiz, new DataManager.OnQuestionsLoadedListener() {
            @Override
            public void onQuestionsLoaded(List<Question> questions) {
                QuestionActivity.this.questions = questions;
                gotoNextQuestion();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Exit");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle() == "Exit") {
            finish();
        }

        return true;
    }

    private void gotoNextQuestion() {
        if (currentNumber >= questions.size()) {
            new AlertDialog.Builder(this).setMessage("Congratulations your score is: " + score).setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            }).show();
        } else {
            currentQuestion = questions.get(currentNumber++ - 1);
            questionTextView.setText(currentQuestion.Question);

            choices.clearCheck();

            for (int i = 0; i < currentQuestion.options.size(); i++) {
                Chip chip = (Chip) choices.getChildAt(i);
                chip.setText(currentQuestion.options.get(i), TextView.BufferType.NORMAL);
            }
        }


    }
}
