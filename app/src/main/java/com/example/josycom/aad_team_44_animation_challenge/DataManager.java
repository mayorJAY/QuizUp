package com.example.josycom.aad_team_44_animation_challenge;

import android.content.Context;
import android.provider.ContactsContract;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public class DataManager {

    Context context;
    Database database;
    private DataManager(Context context){
        this.context = context;
        database = Room.databaseBuilder(context, Database.class, "Questions").allowMainThreadQueries().build();
    }


    private static DataManager dataManager;
    public static DataManager getInstance(Context context){

        if (dataManager == null) {
            dataManager = new DataManager(context);
        }

        return dataManager;
    }

    public void loadQuestion(QuizListManager.Quiz quiz){
        database.getQuestionDao().getAll("");
    }
}
