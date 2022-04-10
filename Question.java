import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Question {
    private String question;
    private String[] option;
    private int answer;
    private int numQuestions;
    private ArrayList<String> questions;
    private int pointValue;

//One text file will have the question, options, and the answer.  Only the first five lines of the file will be printed to student interface.
    //Answer is just for the reference of comparison
    public Question(String question, String[] option, int pointValue, int answer) {
        //int answer corresponds to first option, second option....
        option = new String[4];
        this.question = question;
        this.option = option;
        this.answer = answer;
        this.pointValue = pointValue;
    }

    public Question(String question, int pointValue) {
        this.question = question;
        this.pointValue = pointValue;
    }

    public int getIntegerPointValue(){
        return pointValue;
    }

    public void setIntegerPointValue(int integerPointValue){
        this.pointValue = pointValue;
    }

    public int getPointValue() {
        return pointValue;
    }

    public int getAnswer() {
        return answer;
    }




}



