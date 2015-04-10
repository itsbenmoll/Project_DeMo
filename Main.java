/*****************************************************************************************
 * Adapted and tortured by Julian Trinh and Connor Richmond for use in ENG EC 327
 * Source code from https://github.com/academicode/app-simple-tip-calculator/tree/session-7
 * Sweet tutorial at https://www.youtube.com/watch?v=Z3jzIYkxB1s (where the source is from)
 * Boston University: College of Engineering
 * Spring 2015
 *****************************************************************************************/
package org.academicode.tipcalculator;

//This is the class that will first be run when the app is first opened

//import is #include from C++
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//Extends = inherits, implements means it interfaces with another class
public class Main extends Activity implements OnClickListener
{
    //Declaring and setting string variables
    private static final String TAG_DEBUG = Main.class.getName();
    public static final String TAG_SCORE1 = "tag";
    public static final String TAG_SCORE2 = "total";

    //Creating the EditText object
    //private EditText et;

    //Creating button objects, which extend (inherit) the View class
    private Button option1;
    private Button option2;

    @Override
	/*An onCreate method/function is what is called when a screen is first showed.
	  In this case, this is the main menu screen.
	*/
    protected void onCreate(Bundle savedInstanceState)
    {
        //The super keyword is used to refer to the parent class in java
        super.onCreate(savedInstanceState);

        //How the activity actually looks is inside main.xml, inside the layout folder
        setContentView(R.layout.main);

        // The edit text has a string value set by the editText1 ID in Main.xml
        //et = (EditText) findViewById(R.id.editText1);

        //The buttons have parameters corresponding to the IDs in Main.xml
        option1 = (Button) findViewById(R.id.answer1);
        option2 = (Button) findViewById(R.id.answer2);
		
		/*The buttons now have onClickListeners set, a method/function of the button class
		 * to start a new activity/intent when pressed. In this case, pressing a button
		 * will go to the results page.
		 * */
        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
    }

    @Override
	/*onClick is what is called when the buttons are pressed and they take in Views as arguments
	 * as buttons are children of the view class, buttons can polymorphically be passed in. The button
	 * that called the onClick is automatically fed in*/
    public void onClick(View v)
    {
        double myScore1 = 0.0d;
        double myScore2 = 0.0d;

        //The switch statements grab the id values of the button pressed and calculates the tip accordingly
        switch(v.getId()){
            case R.id.answer1 :
            {
                myScore1 += 1;

                break;
            }

            case R.id.answer2 :
            {
                myScore2 += 1;

                break;
            }

            default :
            {
                break;
            }
        }

        //Pulls the input from the EditText
        //String text = et.getText().toString();

        //If the user tried to proceed without entering a value
        //if(text.equals(""))
        //{
            //Show a toast telling them they need to enter a value. Text is pulled from strings.xml
          //  Toast.makeText(Main.this, getResources().getString(R.string.error_et), Toast.LENGTH_LONG).show();
            //Breaks out of the onClick, as we do not want to launch a new Activity without entered values
            //return;
        //}
		
		/*If all is good, launch a new activity that passes in the amount entered and tip percentage
		NOTE: Double.parseDouble(string) turns a string into a double. It is used to cast the user's input
		to a double and feed it into the new activity
		*/
        launchResultActivity(myScore1, myScore2);
    }

    /* The launchResultActivity method is used to start a new activity from within an onClickListener
     * */

     private void launchResultActivity(double score1, double score2)
    {
        double score_1 = score1;
        double score_2 = score2;
		
		/*The intent class represents an action is used to "load" activities into a variable so they can be passed in and launched from
		 * the startActivity method. Basic intents take two arguments, the current class(.java) and the class(.java) that the app will move to
		 *  The line below initializes an Intent named resultActivity and passes in (Main.this,Result.class) much like the this-> pointer in C++,
		 *  the this keyword in java is used by classes to reference themselves*/
        Intent resultActivity = new Intent(Main.this, Result.class);
		
		/*Since this method is private, if we want the Result Activity/class to access it's members (the strings TAG_SCORE and TAG_GRAND_TOTAL),
		 *we can "push" members from the Main Acivity/class to Result, much like how a friend function can "pull" private members from objects
		*/
        resultActivity.putExtra(TAG_SCORE1, score_1);
        resultActivity.putExtra(TAG_SCORE2, score_2);

        Log.d(TAG_DEBUG, "Yes Score: " + score_1);
        Log.d(TAG_DEBUG, "No Score: " + score_2);

        //Launches the new activity
        startActivity(resultActivity);
    }
}
