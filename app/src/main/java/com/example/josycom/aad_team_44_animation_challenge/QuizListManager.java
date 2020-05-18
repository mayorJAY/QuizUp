package com.example.josycom.aad_team_44_animation_challenge;

import androidx.annotation.DrawableRes;

class QuizListManager {

    public enum Quiz{
        CurrentAffairs("Current Affairs", R.drawable.ic_current_affairs_black_24dp),
        ScienceAndTechnology("Science And Technology", R.drawable.ic_science_black_24dp),
        AndroidDevelopment("Android Development", R.drawable.ic_android_black_24dp),
        WebDevelopment("Web Development", R.drawable.ic_web_black_24dp),
        GoogleCloudPlatform("Google Cloud Platform", R.drawable.ic_cloud_black_24dp),
        SportsQuestions("Sports", R.drawable.ic_sports_black_24dp);

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
