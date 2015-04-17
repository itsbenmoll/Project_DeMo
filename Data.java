package org.academicode.tipcalculator;

/**
 * Created by jennma on 4/15/15.
 */
public class Data {

    private String[] questionsArray = new String[5];
    private int[] yayscoreArray = new int[5];
    private int[] nayscoreArray = new int[5];


    void populateStringArray() {
        questionsArray[0] = "Do you like dogs?";
        questionsArray[1] = "Do you like babies?";
        questionsArray[2] = "LOL";
        questionsArray[3] = "LOL";
        questionsArray[4] = "LOL";

        yayscoreArray[0] = 0;
        yayscoreArray[1] = 0;
        yayscoreArray[2] = 0;
        yayscoreArray[3] = 0;
        yayscoreArray[4] = 0;

        nayscoreArray[0] = 0;
        nayscoreArray[1] = 0;
        nayscoreArray[2] = 0;
        nayscoreArray[3] = 0;
        nayscoreArray[4] = 0;


    }
}
