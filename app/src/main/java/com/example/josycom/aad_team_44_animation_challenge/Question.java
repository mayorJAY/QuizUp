package com.example.josycom.aad_team_44_animation_challenge;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;


@Entity
public class Question {

    @Ignore
    private final int NUMBER_OF_ANSWERS = 4;
    @PrimaryKey
    int primaryKey;

    String Question;
    @Ignore
    ArrayList<String> answers = new ArrayList<>(NUMBER_OF_ANSWERS);
    int answer;

}
