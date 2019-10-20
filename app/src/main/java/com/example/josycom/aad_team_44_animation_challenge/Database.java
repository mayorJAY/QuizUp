package com.example.josycom.aad_team_44_animation_challenge;


import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomDatabase;

@Dao
interface QuestionDao{

    @Query("SELECT :quizName from question")
    void getAll(String quizName);

}

@androidx.room.Database(version = 1, entities = Question.class)
public abstract class Database extends RoomDatabase {
    abstract public QuestionDao getQuestionDao();

}
