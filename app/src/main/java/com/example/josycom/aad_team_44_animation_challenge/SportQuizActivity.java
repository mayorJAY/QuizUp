package com.example.josycom.aad_team_44_animation_challenge;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.josycom.aad_team_44_animation_challenge.utilities.DataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class SportQuizActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mQuestionsText;
    private RadioButton mOptionsA, mOptionsB, mOptionsC, mOptionsD;
    private RadioGroup mOptionsPanel;
    private int mQuestionIndex = 0;
    private ArrayList<HashMap<String, String>> mQuestionsMap;
    private HashMap<String, String> alreadyAnswered;
    private  static int mUsersScore = 0;
    private int mQuestionslistSize;
    ArrayList<HashMap<String, String>> answeredArrayList;
    HashMap<String, String> questionMapped;
    private final static int RESULT_AFTER_QUIZ = 776;
    private static boolean anyOptionChecked;
    Button submitButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_quiz);

        mQuestionsText = findViewById(R.id.sports_custom_questions_text);
        mOptionsPanel = findViewById(R.id.sports_options_panel);

        mOptionsA = findViewById(R.id.sports_option_a);
        mOptionsB = findViewById(R.id.sports_option_b);
        mOptionsC = findViewById(R.id.sports_option_c);
        mOptionsD = findViewById(R.id.sports_option_d);

        submitButton = findViewById(R.id.sports_submit);
        animatebuttons();

        answeredArrayList = new ArrayList<>();
        questionMapped = new HashMap<>();

        mQuestionsMap = DataManager.getInstance().getQuestions();
        mQuestionslistSize = mQuestionsMap.size();

       //set page title
        getSupportActionBar().setTitle("SPORTS.");

        mOptionsPanel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(group.getCheckedRadioButtonId() != 0) {
                    usersChoiceColorChange(checkedId, true);
                    anyOptionChecked = true;
                }
            }
        });
        startQuiz(mQuestionIndex);
        alreadyAnswered = new HashMap<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sports_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sport_close:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //Radio button animation not yet working
    private void animatebuttons(){
        Animation optionaAnime = new TranslateAnimation(0,100,0,0);
        Animation optionbAnime = new TranslateAnimation(0,100,100,0);
        Animation optioncAnime = new TranslateAnimation(0,100,0,100);
        Animation optiondAnime = new TranslateAnimation(100,100,0,0);

        mOptionsA.setAnimation(optionaAnime);
        mOptionsB.setAnimation(optionbAnime);
        mOptionsC.setAnimation(optioncAnime);
        mOptionsD.setAnimation(optiondAnime);
    }

    //show the quiz questions and options from integer array index
    private void startQuiz(int mQuestionIndex) {
        questionMapped = mQuestionsMap.get(mQuestionIndex);

        mQuestionsText.setText(questionMapped.get("question"));
        mOptionsA.setText(questionMapped.get("optiona"));
        mOptionsB.setText(questionMapped.get("optionb"));
        mOptionsC.setText(questionMapped.get("optionc"));
        mOptionsD.setText(questionMapped.get("optiond"));
    }

    //check to see if user selects an options then
    private void nextQuestion(){
        //when no options is selected
        if(!anyOptionChecked){
            if(mQuestionIndex < mQuestionslistSize){
                skipquestion();
            }else if(mQuestionIndex == mQuestionslistSize-1) {
                startQuiz(mQuestionIndex);
            }
        //when an option is selected
        }else {
            RadioButton radioButton = findViewById(mOptionsPanel.getCheckedRadioButtonId());
            String optionSelected = radioButton.getText().toString();
            checkAnswerAndMarkResult(optionSelected);
            radioButton.setChecked(false);
            radioButton.setBackgroundColor(getResources().getColor(android.R.color.white));
            if(mQuestionIndex < mQuestionslistSize - 1){
                mQuestionIndex = mQuestionIndex + 1;
                anyOptionChecked = !anyOptionChecked;
                startQuiz(mQuestionIndex);
            }else {
                showScore();
            }

        }
    }

    //called when no options is selected but user wants to go to next questions
    private void skipquestion(){
        if(mQuestionIndex < mQuestionslistSize - 1 ){
            mQuestionIndex = mQuestionIndex + 1;
            startQuiz(mQuestionIndex);
            submitButtonFades(false);
        }
        else{
            //mQuestionsText.setText("You Reached the End of the Quiz, Submit");
            submitButtonFades(true);

        }
    }

    //animate submit submit Buttons
    private void submitButtonFades(boolean visible){
       if(visible){
           Animation animation = new AlphaAnimation(0, 1);
           animation.setDuration(500);
           animation.setRepeatMode(Animation.REVERSE);
           animation.setRepeatCount(2);
           submitButton.setVisibility(View.VISIBLE);
           submitButton.startAnimation(animation);

       }else{
           Animation animationOut = new AlphaAnimation(1, 0);
           animationOut.setDuration(500);
           animationOut.setRepeatMode(Animation.REVERSE);
           animationOut.setRepeatCount(1);
           submitButton.startAnimation(animationOut);
           submitButton.setVisibility(View.GONE);
       }
    }

    //called when user wants to go to previous questions
    private void previousquestion(){
        if(mQuestionIndex > 0){
            mQuestionIndex = mQuestionIndex - 1;
            submitButtonFades(false);
        }else {
            submitButtonFades(true);
            //mQuestionIndex = mQuestionslistSize - 1;
        }
        startQuiz(mQuestionIndex);
    }

    //called when quiz completes
    private void showScore(){
        showDialog(RESULT_AFTER_QUIZ);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    //animate color transition on radio buttons required min sdk=23
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void usersChoiceColorChange(int radioId, boolean isChecked){
        RadioButton radioButton = findViewById(radioId);
        radioButton.setBackgroundColor(getResources().getColor(isChecked ?
                R.color.colorRadio : R.color.colorSelected));
        resetOtherRadioButtons(radioId);

    }

    //called to remove transition animation from none focused radio buttons
    private void resetOtherRadioButtons(int radioId) {
        View v = new View(this);

        if(mOptionsA.getId() != radioId){
            mOptionsA.setBackgroundColor(getResources().getColor(android.R.color.white));
        }
        if(mOptionsB.getId() != radioId){
            mOptionsB.setBackgroundColor(getResources().getColor(android.R.color.white));
        }
        if(mOptionsC.getId() != radioId){
            mOptionsC.setBackgroundColor(getResources().getColor(android.R.color.white));
        }
        if(mOptionsD.getId() != radioId){
            mOptionsD.setBackgroundColor(getResources().getColor(android.R.color.white));
        }

    }

    //compares users choice from question answer
    private void checkAnswerAndMarkResult(String selected){
        HashMap<String, String> questionMapped;
        questionMapped = mQuestionsMap.get(mQuestionIndex);
        String answerStored = questionMapped.get("correctAnswer");

        if(selected.equalsIgnoreCase(answerStored) && selected!=null){
            mUsersScore = mUsersScore + 1;
            saveAnsweredQuestions(questionMapped.get("question"), selected);
        }
        else{
            mUsersScore = mUsersScore;
        }
    }

    //supposed to holder answered questions in memory to avoid multiple answering not yet working
    private void saveAnsweredQuestions(String question, String usersChoice){
        alreadyAnswered.put("question", question);
        alreadyAnswered.put("answer", usersChoice);

        answeredArrayList.add(alreadyAnswered);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            //dialog to show users score to be transferred into fragment
            case RESULT_AFTER_QUIZ:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View view = View.inflate(this, R.layout.sport_quiz_result_layout, null);
                builder.setView(view);

                ProgressBar result_plot = view.findViewById(R.id.progressBar);
                result_plot.setProgress(mUsersScore / 6);

                TextView result_text = view.findViewById(R.id.sport_result_text);
                result_text.setText(String.format("you scored: %d of 6", mUsersScore));

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismissDialog(RESULT_AFTER_QUIZ);
                    }
                });
                return builder.create();
        }
        return super.onCreateDialog(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sport_previous:
                previousquestion();
                break;

            case R.id.sports_next:
                nextQuestion();
                break;

            case R.id.sports_submit:
                showScore();
                break;
        }
    }
}
