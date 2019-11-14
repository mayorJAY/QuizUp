package com.example.josycom.aad_team_44_animation_challenge;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomDatabase;

import java.util.List;

@Dao
interface QuestionDao{

    @Query("SELECT * from Question WHERE Category LIKE :quizName")
    List<Question> getAll(String quizName);

    @Query("SELECT * from question")
    List<Question> getAll();

    @Query("DELETE FROM question")
    void deleteAll();

    @Insert
    void put(Question... questions);
}

@androidx.room.Database(version = 1, entities = Question.class)
public abstract class Database extends RoomDatabase {
    abstract public QuestionDao getQuestionDao();

}
