package com.johansson.daniel.nflscorer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinnerTeamA, spinnerTeamB;

    String teamA, teamB;

    TextView txtResults;

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

    HashMap<String, String> gameMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapterTeamA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, teamsA);
        ArrayAdapter<String> adapterTeamB = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, teamsB);

        spinnerTeamA = findViewById(R.id.spTeamA);
        spinnerTeamB = findViewById(R.id.spTeamB);

        txtResults = findViewById(R.id.txtResults);

        adapterTeamA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterTeamB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTeamA.setAdapter(adapterTeamA);
        spinnerTeamB.setAdapter(adapterTeamB);

        spinnerTeamA.setOnItemSelectedListener(this);
        spinnerTeamB.setOnItemSelectedListener(this);

        gameMap.put(dc+sf49, "Q1: 14 - 3\nQ2: 6 - 0\nQ3: 13 - 0\nQ4: 7 - 7\nTotal: 40 - 10");
        gameMap.put(dc+gbp, "Q1: 7 - 6\nQ2: 14 - 6\nQ3: 0 - 3\nQ4: 10 - 20\nTotal: 31 - 35");
        gameMap.put(dc+ss, "Q1: 0 - 0\nQ2: 9 - 7\nQ3: 3 - 7\nQ4: 0 - 7\nTotal: 12 - 21");
        gameMap.put(pe+sf49, "Q1: 3 - 0\nQ2: 14 - 0\nQ3: 10 - 7\nQ4: 6 - 3\nTotal: 33 - 10");
        gameMap.put(pe+ss, "Q1: 0 - 10\nQ2: 3 - 0\nQ3: 0 - 7\nQ4: 7 - 7\nTotal: 10 - 24");
        gameMap.put(gbp+mv, "Q1: 0 - 10\nQ2: 0 - 0\nQ3: 0 - 3\nQ4: 0 - 3\nTotal: 0 - 16");
        gameMap.put(mv+ps, "Q1: 0 - 7\nQ2: 3 - 7\nQ3: 6 - 6\nQ4: 0 - 6\nTotal: 9 - 26");
        gameMap.put(nep+ps, "Q1: 7 - 7\nQ2: 3 - 10\nQ3: 6 - 7\nQ4: 11 - 0\nTotal: 27 - 24");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        teamA = spinnerTeamA.getSelectedItem().toString();
        teamB = spinnerTeamB.getSelectedItem().toString();

        if (gameMap.containsKey(teamA+teamB)){
            txtResults.setText(gameMap.get(teamA+teamB));
        }
        else {
            txtResults.setText(teamA + " and " + teamB + " did not play");
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        txtResults.setText("Please select two teams");

    }
}