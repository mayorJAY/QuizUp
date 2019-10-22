package com.example.josycom.aad_team_44_animation_challenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView quizList = findViewById(R.id.quiz_list);
        quizList.setLayoutManager(new LinearLayoutManager(this));
        quizList.setAdapter(new QuizListAdapter());
    }


    private class QuizListAdapter extends RecyclerView.Adapter<QuizListViewHolder>{
        @NonNull
        @Override
        public QuizListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new QuizListViewHolder(LayoutInflater.from(parent.getContext()).inflate( R.layout.quiz_list_item_view, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull QuizListViewHolder holder, int position) {
            holder.bindView(position);
        }

        @Override
        public int getItemCount() {
            return QuizListManager.getQuizCount();
        }
    }

    private class QuizListViewHolder extends RecyclerView.ViewHolder {
        TextView quizName;
        ImageView quizImage;
        QuizListManager.Quiz quiz;
        QuizListViewHolder(View view){
            super(view);

            quizImage = view.findViewById(R.id.quiz_image);
            quizName = view.findViewById(R.id.quiz_name);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent questionActivityIntent = new Intent(v.getContext(), QuestionActivity.class);
                    questionActivityIntent.putExtra("quiz", quiz);
                    startActivity(questionActivityIntent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                }
            });
        }

        void bindView(int position){
            this.quiz = QuizListManager.getQuizAt(position);

            quizName.setText(quiz.getName());
            quizImage.setImageResource(quiz.getImage());
        }
    }

}
