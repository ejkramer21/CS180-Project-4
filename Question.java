import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Question {
    private String question;
    private String option;
    private int answer;
    private int numQuestions;
    private ArrayList<String> questions;
    private Stringp[] question;


    public Question(String question, String[4] option, int answer) {
        this.question = question;
        this.option = option;
        this.answer = answer;
    }

    public Question(String question, String[4] option) {
        this.question = question;
        this.option = option;
    }

    public String[] numQuestions(String line, String filename) {
        int lineCount = 0;
        File f = new File(filename);
        BufferedReader bfr = new BufferedReader(new FileReader(f));
        while ((line = bfr.readLine()) != null) {
            lineCount++;
            questions.add(line);
        }
        question = new String[questions.size()/5];
        for (int i = 0; i < questions.size(); i++) {
            if (i % 5 != 0) {
                question[i] = questions.get(i);
            }
        }
        return question;
    }


}
