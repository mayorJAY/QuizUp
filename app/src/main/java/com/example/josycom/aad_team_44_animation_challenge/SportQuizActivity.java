package com.example.josycom.aad_team_44_animation_challenge;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
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
        animatebuttons();

        answeredArrayList = new ArrayList<>();
        questionMapped = new HashMap<>();

        mQuestionsMap = DataManager.getInstance().getQuestions();
        mQuestionslistSize = mQuestionsMap.size();

        getSupportActionBar().setTitle("SPORTS.");

        mOptionsPanel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(group.getCheckedRadioButtonId() != 0) {
                    usersChoiceColorChange(checkedId, true);
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
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

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

    private void startQuiz(int mQuestionIndex) {
        questionMapped = mQuestionsMap.get(mQuestionIndex);

        mQuestionsText.setText(questionMapped.get("question"));
        mOptionsA.setText(questionMapped.get("optiona"));
        mOptionsB.setText(questionMapped.get("optionb"));
        mOptionsC.setText(questionMapped.get("optionc"));
        mOptionsD.setText(questionMapped.get("optiond"));
    }

    private void nextQuestion(){
        RadioButton radioButton = findViewById(mOptionsPanel.getCheckedRadioButtonId());

        if(mQuestionIndex < mQuestionslistSize){
            if(mOptionsPanel.getCheckedRadioButtonId()==0){
                skipquestion();
            }else {
                checkAnswerAndMarkResult(radioButton.getText().toString());
                mQuestionIndex = mQuestionIndex + 1;
                startQuiz(mQuestionIndex);
            }
        }

    }

    private void skipquestion(){
        mQuestionIndex = mQuestionIndex + 1;

        if(mQuestionIndex < mQuestionslistSize){
            startQuiz(mQuestionIndex);
        }
        else{
            mQuestionsText.setText("You Reached the End of the Quiz, Submit");
        }
    }

    private void previousquestion(){
        mQuestionIndex = mQuestionIndex - 1;

        if(mQuestionIndex < 0){
            mQuestionIndex = mQuestionslistSize - 1;
        }
        startQuiz(mQuestionIndex);
    }

    private void showScore(){
        showDialog(RESULT_AFTER_QUIZ);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void usersChoiceColorChange(int radioId, boolean isChecked){
        RadioButton radioButton = findViewById(radioId);
        radioButton.setBackgroundColor(getResources().getColor(isChecked ?
                R.color.colorRadio : R.color.colorSelected));
        resetOtherRadioButtons(radioId);

        checkAnswerAndMarkResult(radioButton.getText().toString());

    }

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

    private void checkAnswerAndMarkResult(String selected){
        Toast.makeText(this, selected, Toast.LENGTH_LONG).show();

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

    private void saveAnsweredQuestions(String question, String usersChoice){
        alreadyAnswered.put("question", question);
        alreadyAnswered.put("answer", usersChoice);

        answeredArrayList.add(alreadyAnswered);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
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
