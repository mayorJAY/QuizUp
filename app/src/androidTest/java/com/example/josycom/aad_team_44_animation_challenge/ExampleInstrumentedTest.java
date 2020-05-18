package com.example.josycom.aad_team_44_animation_challenge;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import static com.example.josycom.aad_team_44_animation_challenge.QuizListManager.Quiz.CurrentAffairs;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private Database mDatabase;
    private QuestionDao mDao;
    private Context appContext;

    @Before
    public void createDB(){
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        mDatabase = Room.inMemoryDatabaseBuilder(appContext, Database.class).build();
        mDao = mDatabase.getQuestionDao();
    }

    @Test
    public void useAppContext() {
        assertEquals("com.example.josycom.aad_team_44_animation_challenge", appContext.getPackageName());
    }

    @After
    public void closeDB(){
        mDatabase.close();
    }


    @Test
    public void populateDatabaseAndCheckForContent(){
        Question question = new Question(CurrentAffairs.getName(),
                "Who formed the first political party in Nigeria?",
                "Herbert Macaulay", "Herbert Macaulay", "Nnamdi Azikwe", "Abubakar Tafawa Balewa", "Ahmadu Bello");
        Question question1 = new Question(
                CurrentAffairs.getName(),"What was the first political party in Nigeria?",
                "Nigerian National Democratic Party (NNDP)","Advances People’s Democratic Alliance (APDA)",
                "Nigerian National Democratic Party (NNDP)",
                "Social Democratic Mega Party (SDMP)", "Labour Party");

        Question question2 = new Question(
                CurrentAffairs.getName(),"What does the Eagle in the Nigerian coat of arm represent?",
                "Strength","Patience", "Dignity", "Strength", "Peace");

        Question question3 = new Question(
                CurrentAffairs.getName(),"What do the two horses on the Nigerian coat of arm represent?",
                "Dignity","Strength", "Love", "Peace", "Dignity");
        mDao.deleteAll();
        mDao.put(question, question1, question2, question3);

        List<Question> questions = mDao.getAll();
        assertThat(questions.size(), equalTo(4));
    }
}