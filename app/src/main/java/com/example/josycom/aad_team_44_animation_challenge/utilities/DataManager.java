package com.example.josycom.aad_team_44_animation_challenge.utilities;

import java.util.ArrayList;
import java.util.HashMap;

public class DataManager {
    private ArrayList<Questions> questionsList = new ArrayList<>();

    private static DataManager ourInstance = null;
    public static DataManager getInstance(){
        if(ourInstance == null){
            ourInstance = new DataManager();
            ourInstance.initialiseQuiz();
        }
        return ourInstance;
    }

    public static ArrayList<HashMap<String, String>> getQuestions(){
        return Questions.getArrayList();
    }

    private void initialiseQuiz(){
        Questions question = new Questions(1,
                "Who won the 2018 Russia world cup","sports",
                "Spain","Belgium",
                "Croatia","France","France");

        Questions question1 = new Questions(2,
                "Who won the 2019 women world cup","sports",
                "Spain","USA",
                "Croatia","France","USA");

        Questions question2 = new Questions(3,
                "Who won the 2006 world cup","sports",
                "Spain","Belgium",
                "Italy","France","Italy");

        Questions question3 = new Questions(4,
                "The Best player in football after messi and ronaldo is","sports",
                "mikel obi","neymar junior",
                "mesut ozil","mbambe young","mbambe young");

        Questions question4 = new Questions(5,
                "Which team won the European Champions league last season","sports",
                "Barcelona","Manchester United",
                "Inter milan","Liverpool","Liverpool");

        Questions question5 = new Questions(6,
                "Which team won the La Liga title 2018/2019 season","sports",
                "chelsea","PSG",
                "Barcelona","Monaco","Barcelona");
    }

}