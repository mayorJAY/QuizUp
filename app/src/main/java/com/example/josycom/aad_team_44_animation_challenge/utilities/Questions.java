package com.example.josycom.aad_team_44_animation_challenge.utilities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

public class Questions implements Parcelable {

    public static ArrayList<HashMap<String, String>> questionsArray = new ArrayList<>();

    private int questionId;
    private String question;
    private String categories;
    private String optionsA;
    private String optionsB;
    private String optionsC;
    private String optionsD;
    private String correctAnswer;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getOptionsA() {
        return optionsA;
    }

    public void setOptionsA(String optionsA) {
        this.optionsA = optionsA;
    }

    public String getOptionsB() {
        return optionsB;
    }

    public void setOptionsB(String optionsB) {
        this.optionsB = optionsB;
    }

    public String getOptionsC() {
        return optionsC;
    }

    public void setOptionsC(String optionsC) {
        this.optionsC = optionsC;
    }

    public String getOptionsD() {
        return optionsD;
    }

    public void setOptionsD(String optionsD) {
        this.optionsD = optionsD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Questions(int id, String question, String categories, String optionsA, String optionsB,
                     String optionsC, String optionsD, String correctAnswer) {
        this.questionId = id;
        this.question = question;
        this.categories = categories;
        this.optionsA = optionsA;
        this.optionsB = optionsB;
        this.optionsC = optionsC;
        this.optionsD = optionsD;
        this.correctAnswer = correctAnswer;

        HashMap<String, String > questionMap = new HashMap<>();
        questionMap.put("_id", id+"");
        questionMap.put("question", question);
        questionMap.put("category", categories);
        questionMap.put("optiona", optionsA);
        questionMap.put("optionb", optionsB);
        questionMap.put("optionc", optionsC);
        questionMap.put("optiond", optionsD);
        questionMap.put("correctAnswer", correctAnswer);

        questionsArray.add(questionMap);
    }

    public static ArrayList<HashMap<String, String>> getArrayList(){
        return questionsArray;
    }

    public Questions(){}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(optionsA);
        dest.writeString(optionsB);
        dest.writeString(optionsC);
        dest.writeString(optionsD);
        dest.writeString(correctAnswer);
        dest.writeInt(questionId);
    }

    protected Questions(Parcel parcel){
        questionId = parcel.readInt();
        question = parcel.readString();
        optionsA = parcel.readString();
        optionsB = parcel.readString();
        optionsC = parcel.readString();
        optionsD = parcel.readString();
        correctAnswer = parcel.readString();
    }

    public static final Creator<Questions> CREATOR =
            new Creator<Questions>() {
                @Override
                public Questions createFromParcel(Parcel source) {
                    return new Questions(source);
                }

                @Override
                public Questions[] newArray(int size) {
                    return new Questions[size];
                }
            };
}
