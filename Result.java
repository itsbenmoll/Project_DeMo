/*****************************************************************************************
 * Adapted and tortured by Julian Trinh and Connor Richmond for use in ENG EC 327
 * Source code from https://github.com/academicode/app-simple-tip-calculator/tree/session-7
 * Sweet tutorial at https://www.youtube.com/watch?v=Z3jzIYkxB1s (where the source is from)
 * Boston University: College of Engineering
 * Spring 2015
 *****************************************************************************************/
package org.academicode.tipcalculator;
/*
 * This is the results page that is displayed after you the onClick is called from Main.java
 * */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Result extends Activity
{
    //Initializes TextViews to display total and tip
    private TextView score_1TextView;
    private TextView score_2TextView;

    //Initializes a button
    private Button finished;

    @Override
    //When this activity is called, onCreate is called
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //How this activity actually looks is set inside result.xml
        setContentView(R.layout.result);
		
		/*Set the texts views so they display according to the parameters in result.xml*/
        score_1TextView = (TextView) findViewById(R.id.score1);
        score_2TextView = (TextView) findViewById(R.id.score2);

        //Runs the method to use the values calculated in the Main Activity class
        initializeTextViews();

        //Initializes button to the parameters in result.xml
        finished = (Button) findViewById(R.id.confirm);

        //Sets an onClickListener for the finished button
        finished.setOnClickListener(new OnClickListener()
        {
            @Override
            //If clicked, call the finish method
            public void onClick(View v)
            {
                //Finish ends the current activity and goes back to the activity that called this one (Main in this case)
                finish();
            }
        });
    }

    private void initializeTextViews()
    {
        //Sets doubles according to the values "pushed" from Main.java
        double score_1 = getIntent().getExtras().getDouble(Main.TAG_SCORE1);
        double score_2 = getIntent().getExtras().getDouble(Main.TAG_SCORE2);
        //Sets the strings accordingly
        String currentScore1text = score_1TextView.getText().toString();

        currentScore1text = "Your yes score: " + score_1;

        String currentScore2text = score_2TextView.getText().toString();

        currentScore2text = "Your no score: " + score_2;

        //Sets the texts to display the values
        score_1TextView.setText(currentScore1text);
        score_2TextView.setText(currentScore2text);

    }
}
