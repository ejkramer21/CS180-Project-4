import java.io.*;
import java.util.ArrayList;
/* Student class extends account
* you can add a grade or get grades for a particular student
*/

public class Student extends account {
    public static ArrayList<String> quizGrades = new ArrayList<>();

    public Student(String userName) {
        super(userName);
    }


    public boolean readGrades() {
        try {
            File f1 = new File((getUserName() + ".txt"));
            FileReader fr1 = new FileReader(f1);
            BufferedReader bfr = new BufferedReader(fr1);
            String tempLine;
            while ((tempLine = bfr.readLine()) != null) {
                    quizGrades.add(tempLine);
            }
            String[] quizDetails = new String[2];
            for(int i = 0; i < quizGrades.size(); i++) {
                quizDetails = quizGrades.get(i).split(",");
                System.out.println(quizDetails[0] + " - " + quizDetails[1]);
            }
        } catch (IOException e) {
            //NoGradesFoundException();
            return false;
        }
        return true;
    }


    public boolean addGrade(String quizName, String quizGrade) {
        try {
            File f1 = new File((getUserName() + ".txt"));
            FileWriter fr1 = new FileWriter(f1, true);
            BufferedWriter bfw = new BufferedWriter(fr1);
            bfw.write((quizName + "," + quizGrade + "\n"));
            bfw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }









}
