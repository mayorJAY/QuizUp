package com.example.josycom.aad_team_44_animation_challenge;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

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


    interface OnQuestionsLoadedListener{
        void onQuestionsLoaded(List<Question> questions);
    }

    public void putQuestion(Question... questions){
        database.getQuestionDao().put(questions);
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
