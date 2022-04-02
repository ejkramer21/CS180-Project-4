import java.util.Scanner;

public class MainClassTest {

    private static final String studentTeacherMenu = "Student : 1, Teacher : 2";
    private static final String logCreateDeleteMenu = "Create : 1, Login : 2, Delete : 3";
    private static final String studentInputs = "View grades : 1, Take quiz : 2";
    private static final String teacherInputs = "Create quiz : 1, edit quiz : 2, delete quiz : 3, view student submissions : 4";
    private static final String quizNameFileInput = "Enter the name of the quiz that you would like to take (exactly as listed!!)";


    public static void main(String[] args) {
        //make the whole thing as a do while, keep running till user(s) are done
        Scanner scan = new Scanner(System.in);

        int accountInput;
        int personInput;
        int teacherInput;
        int studentInput;
        String quizNameFile;

        //theory testing
        System.out.println(logCreateDeleteMenu);
        accountInput = scan.nextInt();
        scan.nextLine();
        System.out.println(studentTeacherMenu);
        personInput = scan.nextInt();
        scan.nextLine();

        if(accountInput == 1) {
            //code to create account function
        } else if (accountInput == 2) {
            if (personInput == 1) {
                do {
                    System.out.println(studentInputs);
                    studentInput = scan.nextInt();
                    scan.nextLine();
                } while (studentInput > 2 || studentInput < 1);

                if (studentInput == 1) {
                    //function to get grade of user selected
                } else {
                    do {
                        System.out.println(quizNameFileInput);
                        quizNameFile = scan.nextLine();
                    } while (//function that checks if it is false/null);
                    //function to run quiz and prompt user input (somehow)
                }
            } else {
                //function call for teacher
                //make exams, delete, edit, view student submissions
                System.out.println(teacherInputs);
                teacherInput = scan.nextInt();
                scan.nextLine();

                if (teacherInput == 1) {
                    //menu for creating quiz
                } else if (teacherInput == 2) {
                    //function to edit quiz or inputs needed
                } else if (teacherInput == 3){
                    //function to delete quiz, prompt if they want to
                } else {
                    //function to view student submission (name wanted)
                }
            }
        } else if (accountInput == 3) {
            //code for delete account
            //prompt to make sure that they want to delete it!!
        }


    }
}
