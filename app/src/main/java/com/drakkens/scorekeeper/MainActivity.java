package com.drakkens.scorekeeper;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends Activity {

    int currentScoreTeam1 = 0;
    int currentScoreTeam2 = 0;
    TextView team2ScoreTextView;
    TextView team1ScoreTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        team1ScoreTextView = findViewById(R.id.showScoreTeam1);
        team2ScoreTextView = findViewById(R.id.showScoreTeam2);

        if (savedInstanceState != null) {
            currentScoreTeam1 = savedInstanceState.getInt("Score1");
            currentScoreTeam2 = savedInstanceState.getInt("Score2");

            team1ScoreTextView.setText(currentScoreTeam1);
            team2ScoreTextView.setText(currentScoreTeam2);
        }


        ImageButton dec1, dec2, inc1, inc2;

        dec1 = findViewById(R.id.decreaseScoreTeam1);
        dec2 = findViewById(R.id.decreaseScoreTeam2);
        inc1 = findViewById(R.id.increaseScoreTeam1);
        inc2 = findViewById(R.id.increaseScoreTeam2);

        dec1.setOnClickListener(view -> decreaseScore(team1ScoreTextView));
        dec2.setOnClickListener(view -> decreaseScore(team2ScoreTextView));

        inc1.setOnClickListener(view -> increaseScore(team1ScoreTextView));
        inc2.setOnClickListener(view -> increaseScore(team2ScoreTextView));


    }

    public void increaseScore(View view) {
        if (view == team1ScoreTextView) {
            currentScoreTeam1++;
            team1ScoreTextView.setText(String.valueOf(currentScoreTeam1));
        } else {
            currentScoreTeam2++;
            team2ScoreTextView.setText(String.valueOf(currentScoreTeam2));

        }
    }

    public void decreaseScore(View view) {
        if (view == team1ScoreTextView) {
            currentScoreTeam1--;
            team1ScoreTextView.setText(String.valueOf(currentScoreTeam1));
        } else {
            currentScoreTeam2--;
            team2ScoreTextView.setText(String.valueOf(currentScoreTeam2));

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.night_mode) {
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate();
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("Score1", currentScoreTeam1);
        outState.putInt("Score2", currentScoreTeam2);
        super.onSaveInstanceState(outState);
    }
}
