package com.johansson.daniel.nflscorer;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinnerTeamA, spinnerTeamB;

    String teamA, teamB;

    TextView txtResults, txtTeams;

    //Team list A
    String dc = "Dallas Cowboys";
    String pe = "Philadelphia Eagles";
    String nep = "New England Patriots";
    String mv = "Minnesota Vikings";
    //Team list B
    String gbp = "Green Bay Packers";
    String sf49 = "San Fransisco 49ers";
    String ps = "Pittsburgh Steelers";
    String ss = "Seattle Seahawks";

    String[] teamsA = {dc, pe, nep, mv};
    String[] teamsB = {gbp, sf49, ps, ss};

    String[] quarters = {"Qtr 1", "Qtr 2", "Qtr 3", "Qtr 4", "Total"};

    TableLayout scoreTable;

    HashMap<String, ArrayList<String>> games = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapterTeamA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, teamsA);
        ArrayAdapter<String> adapterTeamB = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, teamsB);

        spinnerTeamA = findViewById(R.id.spTeamA);
        spinnerTeamB = findViewById(R.id.spTeamB);

        txtResults = findViewById(R.id.txtResults);
        txtTeams = findViewById(R.id.txtTeams);
        scoreTable = findViewById(R.id.scoreTable);

        adapterTeamA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterTeamB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTeamA.setAdapter(adapterTeamA);
        spinnerTeamB.setAdapter(adapterTeamB);

        spinnerTeamA.setOnItemSelectedListener(this);
        spinnerTeamB.setOnItemSelectedListener(this);

        games.put(dc+sf49, new ArrayList<>(Arrays.asList("14 - 3", "6 - 0", "13 - 0", "7 - 7", "40 - 10")));
        games.put(dc+gbp, new ArrayList<>(Arrays.asList("7 - 6", "14 - 6", "0 - 3", "10 - 20", "31 - 35")));
        games.put(dc+ss, new ArrayList<>(Arrays.asList("0 - 0", "9 - 7", "3 - 7", "0 - 7", "12 - 21")));
        games.put(pe+sf49, new ArrayList<>(Arrays.asList("3 - 0", "14 - 0", "10 - 7", "6 - 3", "33 - 10")));
        games.put(pe+ss, new ArrayList<>(Arrays.asList("0 - 10", "3 - 0", "0 - 7", "7 - 7", "10 - 24")));
        games.put(gbp+mv, new ArrayList<>(Arrays.asList("0 - 10", "0 - 0", "0 - 3", "0 - 3", "0 - 16")));
        games.put(mv+ps, new ArrayList<>(Arrays.asList("0 - 7", "3 - 7", "6 - 6", "0 - 6", "9 - 26")));
        games.put(nep+ps, new ArrayList<>(Arrays.asList("7 - 7", "3 - 10", "6 - 7", "11 - 0", "27 - 24")));

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        while (scoreTable.getChildCount() > 1)
            scoreTable.removeView(scoreTable.getChildAt(scoreTable.getChildCount() - 1));

        teamA = spinnerTeamA.getSelectedItem().toString();
        teamB = spinnerTeamB.getSelectedItem().toString();

        if (games.containsKey(teamA+teamB)){
            scoreTable.setVisibility(View.VISIBLE);
            txtResults.setText("");
            txtTeams.setText(teamA + "\nvs\n" + teamB + "\n");
            ArrayList<String> value = games.get(teamA+teamB);
            for (int i = 0; i < 5 ; i++){
                TableRow row = new TableRow(this);
                TextView quarter = new TextView(this);
                TextView score = new TextView(this);
                quarter.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                quarter.setTextColor(Color.parseColor("#000000"));
                score.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                score.setTextColor(Color.parseColor("#000000"));
                quarter.setText(quarters[i]);
                score.setText(value.get(i));
                row.addView(quarter);
                row.addView(score);
                scoreTable.addView(row);
            }
        } else {
            scoreTable.setVisibility(View.INVISIBLE);
            txtTeams.setText("");
            txtResults.setText(teamA + " & " + teamB + " did not play");
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        txtResults.setText("Please select two teams");

    }
}