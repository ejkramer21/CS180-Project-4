import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Teacher extends Account {

        public Teacher(String username, String password, String userType) {
            super(username, password, userType);
        }

        public void seeStudentGrades(String studentUsername) throws NoGradesFoundException {
            try {
                File f1 = new File((studentUsername + ".txt"));
                FileReader fr1 = new FileReader(f1);
                BufferedReader bfr = new BufferedReader(fr1);
                String tempLine;
                ArrayList<String> quizGrades = new ArrayList<>();
                ArrayList<String> quizNames = new ArrayList<>();
                ArrayList<String> quizTotal = new ArrayList<>();
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
                quizGrades.clear();
                quizNames.clear();
                quizTotal.clear();
            } catch (IOException e) {
                throw new NoGradesFoundException("This student has no grades!");
            }
        }
}