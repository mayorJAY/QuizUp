package com.example.josycom.aad_team_44_animation_challenge;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.example.josycom.aad_team_44_animation_challenge.QuizListManager.Quiz.SportsQuestions;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.josycom.aad_team_44_animation_challenge", appContext.getPackageName());
    }


    @Test
    public void populateDatabase(){
        Question question = new Question(SportsQuestions.getName(),
                "Who won the 2018 Russia world cup",
                "France", "Spain","Belgium",
                "Croatia","France");

        Question question1 = new Question(
                SportsQuestions.getName(),"Who won the 2019 women world cup",
                "Spain","USA",
                "Croatia","France","USA");

        Question question2 = new Question(
                SportsQuestions.getName(),"Who won the 2006 world cup",
                "Italy","Spain","Belgium",
                "Italy","France");

        Question question3 = new Question(
                SportsQuestions.getName(),"The Best player in football after messi and ronaldo is",
                "mbambe young","mikel obi","neymar junior",
                "mesut ozil","mbambe young");

        Question question4 = new Question(
                SportsQuestions.getName(),"Which team won the European Champions league last season",
                "Liverpool","Barcelona","Manchester United",
                "Inter milan","Liverpool");

        Question question5 = new Question(
                SportsQuestions.getName(),"Which team won the La Liga title 2018/2019 season",
                "Barcelona", "chelsea","PSG",
                "Barcelona","Monaco");


        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Database database = Room.databaseBuilder(appContext, Database.class, "databases/Questions.db").allowMainThreadQueries().build();
        QuestionDao dao = database.getQuestionDao();
        List<Question> questions = dao.getAll();
        dao.put(question, question1, question2, question3, question4, question5);
        database.close();

    }
}
