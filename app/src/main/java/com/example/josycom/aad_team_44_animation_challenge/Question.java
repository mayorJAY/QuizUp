package com.example.josycom.aad_team_44_animation_challenge;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Entity
public class Question {

    @Ignore
    private final int NUMBER_OF_ANSWERS = 4;
    @PrimaryKey
    int primaryKey;

    String Question;

    String category;

    @TypeConverters(AnswerConverter.class)
    List<String> answers = new ArrayList<>();
    int answer;

    static class AnswerConverter {

        @TypeConverter
        public String answersFromArray(List<String> answers) {
            String a = answers.get(0);


            for (int i = 1; i < answers.size(); i++) {
                a = a.concat(":").concat(answers.get(i));
            }

            return a;
        }

        @TypeConverter
        public List<String> answersToArray(String answers) {
            return Arrays.asList(answers.split(":"));
        }

    }
}
