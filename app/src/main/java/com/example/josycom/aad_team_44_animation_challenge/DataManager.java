package com.example.josycom.aad_team_44_animation_challenge;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import androidx.room.Room;

import java.util.List;

import static com.example.josycom.aad_team_44_animation_challenge.QuizListManager.Quiz.AndroidDevelopment;
import static com.example.josycom.aad_team_44_animation_challenge.QuizListManager.Quiz.CurrentAffairs;
import static com.example.josycom.aad_team_44_animation_challenge.QuizListManager.Quiz.GoogleCloudPlatform;
import static com.example.josycom.aad_team_44_animation_challenge.QuizListManager.Quiz.ScienceAndTechnology;
import static com.example.josycom.aad_team_44_animation_challenge.QuizListManager.Quiz.SportsQuestions;
import static com.example.josycom.aad_team_44_animation_challenge.QuizListManager.Quiz.WebDevelopment;

public class DataManager {

    Context context;
    Database database;
    private DataManager(Context context){
        this.context = context;
        database = Room.databaseBuilder(context, Database.class, "Questions.db").createFromAsset("databases/Questions.db").allowMainThreadQueries().build();
    }


    private static DataManager dataManager;
    public static DataManager getInstance(Context context){

        if (dataManager == null) {
            dataManager = new DataManager(context);
        }

        return dataManager;
    }


    interface OnQuestionsLoadedListener{
        void onQuestionsLoaded(List<Question> questions);
    }

    public void putQuestion(){
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

        Question question4 = new Question(
                CurrentAffairs.getName(),"Nigeria is divided into how many geopolitical zones?",
                "Six", "Four", "Six", "Eight", "Twelve");

        Question question5 = new Question(
                CurrentAffairs.getName(),"What was the black shield in the Nigerian coat of arm stand for?",
                "Nigeria’s fertile soil","Nigeria’s Unity", "Nigeria’s Freedom", "Nigeria’s Fertile Soil", "Nigeria's Dignity");

        Question question6 = new Question(SportsQuestions.getName(),
                "Who won the 2018 Russia world cup?",
                "France", "Spain","Belgium",
                "Croatia","France");

        Question question7 = new Question(
                SportsQuestions.getName(),"Who won the 2019 women world cup?",
                "Spain","USA",
                "Croatia","France","Spain");

        Question question8 = new Question(
                SportsQuestions.getName(),"Who won the 2006 world cup?",
                "Italy","Spain","Belgium",
                "Italy","France");

        Question question9 = new Question(
                SportsQuestions.getName(),"The Best player in football after Messi and Ronaldo is",
                "Mbappe young","Mikel Obi","Neymar Junior",
                "Mesut Ozil","Mbappe Young");

        Question question10 = new Question(
                SportsQuestions.getName(),"Which team won the 2018/19 UEFA Champions league final?",
                "Liverpool","Barcelona","Manchester United",
                "Inter milan","Liverpool");

        Question question11 = new Question(
                SportsQuestions.getName(),"Which team won the La Liga title 2018/19 season?",
                "Barcelona", "Chelsea","PSG",
                "Barcelona","Monaco");

        Question question12 = new Question(ScienceAndTechnology.getName(),
                "Medium which sends signals from source to destination is known as:",
                "Transmission channel", "Transmitter", "Transmission channel", "Receiver", "Hardware");
        Question question13 = new Question(
                ScienceAndTechnology.getName(),"Educational institutions, libraries, hospitals and industries store concerned information by:",
                "Data management","Operating system",
                "Word processing", "Data management", "Informing system");

        Question question14 = new Question(
                ScienceAndTechnology.getName(),"In radio, television and cellphones, information is sent through space in form of:",
                "Electromagnetic waves","Cathode rays", "Electric signals", "Light", "Electromagnetic waves");

        Question question15 = new Question(
                ScienceAndTechnology.getName(),"Number of operating computers on the internet is:",
                "More than 50 million","Nearly 10 million", "Nearly 20 million", "More than 50 million", "30 million");

        Question question16 = new Question(
                ScienceAndTechnology.getName(),"What is the capacity of a compact disc?",
                "680 megabytes", "420 megabytes", "380 megabytes", "680 megabytes", "560 megabytes");

        Question question17 = new Question(
                ScienceAndTechnology.getName(),"What is the capacity of a compact disc?",
                "680 megabytes", "420 megabytes", "380 megabytes", "680 megabytes", "560 megabytes");

        Question question18 = new Question(AndroidDevelopment.getName(),
                "What is an ACTIVITY in Android?",
                "Activity performs the actions on the screen", "Activity performs the actions on the screen","Manages the application content",
                "Screen UI","None of the above");

        Question question19 = new Question(
                AndroidDevelopment.getName(),"What is the difference between MARGIN and PADDING in Android layout?",
                "Both A and B are correct","Margin is specifying the extra space left on all four sides the layout",
                "Padding is used to offset the content of a view by specific PX or PD","Both A and B are correct","None of the above");

        Question question20 = new Question(
                AndroidDevelopment.getName(),"What is an APPLICATION CLASS in Android?",
                "Base class for all classes","A class that can create only an object","An Anonymous class",
                "Java class","Base class for all classes");

        Question question21 = new Question(
                AndroidDevelopment.getName(),"What is BREAKPOINT in Android?",
                "Breaks the execution","Breaks the application","Breaks the development code",
                "Breaks the execution","None of the above");

        Question question22 = new Question(
                AndroidDevelopment.getName(),"What is GCM in Android?",
                "Google Cloud Messaging","Google Cloud Messaging","Google Count Messaging",
                "Google Message pack","None of the above");

        Question question23 = new Question(
                AndroidDevelopment.getName(),"What is a GCM in Android?",
                "Google Cloud Messaging","Google Cloud Messaging","Google Count Messaging",
                "Google Message pack","None of the above");

        Question question24 = new Question(WebDevelopment.getName(),
                "Which HTML special character entity name is used to represent a copyright symbol?",
                "&copy", "&copywrite","&copy_right",
                "&copy","&copyright");

        Question question25 = new Question(
                WebDevelopment.getName(),"Which method allows browser access to a device's camera and microphone?",
                "navigator.mediaDevices.getUserMedia()","window.getUserMedia()",
                "navigator.mediaDevices.getUserMedia()","browser.getUserMedia()","window.mediaDevices.getUserMedia()");

        Question question26 = new Question(
                WebDevelopment.getName(),"How is data in a document database (also known as an object database) stored?",
                "As a key/value pair","As a set of serialized objects","As a blob",
                "As a key/value pair","As a row in a table");

        Question question27 = new Question(
                WebDevelopment.getName(),"Which attribute for elements is no longer required for in-line scripts in HTML5?",
                "Type","Rel","Type",
                "Src","Href");

        Question question28 = new Question(
                WebDevelopment.getName(),"Which is NOT a Web Font file format?",
                "SmallVector Type (.svt)","TrueType (.ttf)","Embedded OpenType (.eot)",
                "SmallVector Type (.svt)","OpenType (.otf)");

        Question question29 = new Question(
                WebDevelopment.getName(),"Which is NOT a Web Font file format?",
                "SmallVector Type (.svt)","TrueType (.ttf)","Embedded OpenType (.eot)",
                "SmallVector Type (.svt)","OpenType (.otf)");

        Question question30 = new Question(GoogleCloudPlatform.getName(),
                "Apache Hadoop is written in which programming language?",
                "PHP", "Java","PHP",
                "Python","None of the above");

        Question question31 = new Question(
                GoogleCloudPlatform.getName(),"What is the software framework by Google for distributed computing on large data sets?",
                "Cassandra","Mapreduce",
                "Cassandra","MongoDB","None of the above");

        Question question32 = new Question(
                GoogleCloudPlatform.getName(),"The term VPN expands to what?",
                "Virtual private network","Viral private network","Virtual public network",
                "Virtual private network","None of the above");

        Question question33 = new Question(
                GoogleCloudPlatform.getName(),"What does the term API refer to?",
                "Application programming interface","Application programmable interface","Application programming interface",
                "Application programming interconnect","None of the above");

        Question question34 = new Question(
                GoogleCloudPlatform.getName(),"In cloud service model, what is PaaS is based on?",
                "SaaS","SaaS","IaaS",
                "TaaS","None of the above");

        Question question35 = new Question(
                GoogleCloudPlatform.getName(),"In cloud service model, what is PaaS is based on?",
                "SaaS","SaaS","IaaS",
                "TaaS","None of the above");
        database.getQuestionDao().deleteAll();
        database.getQuestionDao().put(question, question1, question2, question3, question4, question5,
                question6, question7, question8, question9, question10, question11,
                question12, question13, question14, question15, question16, question17,
                question18, question19, question20, question21, question22, question23,
                question24, question25, question26, question27, question28, question29,
                question30, question31, question32, question33, question34, question35);
    }

    public void loadQuestion(final QuizListManager.Quiz quiz, final OnQuestionsLoadedListener listener){

        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                listener.onQuestionsLoaded((List<Question>)msg.obj);
            }
        };
        handler.post(new Runnable() {
            @Override
            public void run() {
                Message message = Message.obtain();

                message.obj = database.getQuestionDao().getAll(quiz.getName());
                handler.sendMessage(message);
            }
        });
    }
}
