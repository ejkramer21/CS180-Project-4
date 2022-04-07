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
    private int integerPointValue;


    public Question(String question, String[] option, int answer) {
        this.question = question;
        this.option = option;
        this.answer = answer;
        this.questions = new ArrayList<String>();
    }

    public int getIntegerPointValue(){
        return this.integerPointValue;
    }

    public void setIntegerPointValue(int integerPointValue){
        this.integerPointValue = integerPointValue;
    }

    public Question(String question, String[] option) {
        this.question = question;
        this.option = option;
        this.questions = new ArrayList<String>();
    }

    public String[] numQuestions(String line, String filename) throws IOException {
        int lineCount = 0;
        File f = new File(filename);
        BufferedReader bfr = new BufferedReader(new FileReader(f));
        while ((line = bfr.readLine()) != null) {
            lineCount++;
            questions.add(line);
        }
        String[] questionInfo = new String[questions.size()/5];
        for (int i = 0; i < questions.size(); i++) {
            if (i % 5 != 0) {
                questionInfo[i] = questions.get(i);
            }
        }
        return questionInfo;
    }


}