package com.example.josycom.aad_team_44_animation_challenge;

import androidx.annotation.DrawableRes;

class QuizListManager {

    public enum Quiz{
        Android("Android", R.drawable.ic_launcher_background);


        private String name;
        private int image;
        Quiz(String name, @DrawableRes int image){
            this.name = name;
            this.image = image;
        }

        public @DrawableRes int getImage() {
            return image;
        }

        public String getName() {
            return name;
        }
    }


    //private static QuizListManager quizListManager;
    private static Quiz[] quizes = Quiz.values();

    static Quiz getQuizAt(int position){
//        if (quizListManager == null) {
//            quizListManager = new QuizListManager();
        //}


        return quizes[position];
    }

    static int getQuizCount(){
        return quizes.length;
    }
}
