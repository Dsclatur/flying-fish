package com.example.flyingfish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameoverActivity extends AppCompatActivity {
    private Button button;
    private TextView textView;
    private String score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        button=(Button)findViewById(R.id.button);
        textView=(TextView)findViewById(R.id.displayscore);

        score=getIntent().getExtras().get("Score:").toString() ;

        //clicking on button "play again" leads to the main activity.
        button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainIntent=new Intent(GameoverActivity.this,MainActivity.class);
            startActivity(mainIntent);
        }

    });

    textView.setText("Score :"+score);
    }

}
