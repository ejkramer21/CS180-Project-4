import java.io.*;
import java.util.ArrayList;

public class Student extends Account {
    public ArrayList<String> quizGrades = new ArrayList<>();
    public ArrayList<String> quizNames = new ArrayList<>();
    public ArrayList<String> quizTotal = new ArrayList<>();

    public Student(String username, String password, String userType) {
        //I added the password and userType to work with the constructor of Account
        //I suggest calling account like Account account = new Account(username, password, userType);
        //this way you can say the usertype within this method is always "student"
        //and you can call account.getUserName() below
        //-Joy
        super(username, password, userType);
    }

    /*
        disclaimer: readGrades is not updated.
     */
    public boolean readGrades() throws NoGradesFoundException {
        try {
            File f1 = new File((getUsername() + ".txt"));
            FileReader fr1 = new FileReader(f1);
            BufferedReader bfr = new BufferedReader(fr1);
            String tempLine;
            while ((tempLine = bfr.readLine()) != null) {
                quizNames.add(tempLine);
                quizGrades.add(bfr.readLine());
                quizTotal.add(bfr.readLine());
            }
            String[] quizDetails;
            for (int i = 0; i < quizGrades.size(); i++) {
                System.out.println(quizNames.get(i));
                quizDetails = quizGrades.get(i).split(",");
                for (int j = 0; j < quizDetails.length / 2; j++) {
                    System.out.println("Question " + quizDetails[j] + " - " + quizDetails[j + 1]);
                }

            }
            bfr.close();
        } catch (IOException e) {
            throw new NoGradesFoundException("This student has no grades!");
        }
        return true;
    }

    /*
        Student.addGrade HOW TO USE
        This function will store the grades for each question for a quiz the student has taken. It'll also calculate
        the total grade as a percentage.
        When you finish grading the quizzes, you need to call this function to store the grade.
        You need to call it with the student's name.addGrade(quizName, questionValue, questionGrade);
        quizName is a String of the quiz's name
        questionValue is an int array of the question point values
        questionGrade is an int array of the points the student earns on each question
        It will throw an exception if it fails.

    */
    public void addGrade(String quizName, int[] questionValue, int[] questionGrade) throws InvalidAccountException {
        int totalPoints = 0;
        int totalGrade = 0;
        double finalGrade;
        try {
            File f1 = new File((getUsername() + ".txt"));
            FileWriter fr1 = new FileWriter(f1, true);
            BufferedWriter bfw = new BufferedWriter(fr1);
            bfw.write((quizName + "\n"));
            for (int i = 0; i < questionGrade.length; i++) {
                bfw.write(questionValue[i] + "," + questionGrade[i]);
                totalPoints += questionValue[i];
                totalGrade += questionGrade[i];
            }
            finalGrade = totalGrade / totalPoints;
            bfw.write("\n" + String.format("%.2f", finalGrade) + "\n");
            bfw.close();
        } catch (IOException e) {
            throw new InvalidAccountException("Something went wrong!");
        }
    }


}
