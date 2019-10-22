package com.example.josycom.aad_team_44_animation_challenge;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AnswerConverter {

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

@Entity(indices = {@Index(unique = false, value = "Category")})
public class Question {

    public Question(){}

    public Question(String category, String question, String answer, String... options){
        this.Question = question;
        this.Category = category;
        this.answer = answer;
        this.options.addAll(Arrays.asList(options));
    }

    @PrimaryKey(autoGenerate = true)
    int primaryKey;

    String Category;

    String Question;

    String answer;

    @TypeConverters(AnswerConverter.class)
    List<String> options = new ArrayList<>();
}
