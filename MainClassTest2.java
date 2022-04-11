import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClassTest2 {

    private static final String studentTeacherMenu = "Student : 1, Teacher : 2";
    private static final String logCreateDeleteMenu = "Create Account: 1, Login : 2, Delete Account: 3, Edit Account: 4";
    private static final String studentInputs = "View grades : 1, Take quiz : 2";
    private static final String teacherInputs = "Create quiz : 1, edit quiz : 2, delete quiz : 3, view student submissions : 4";
    private static final String quizNameFileInput = "Enter the name of the quiz that you would like to take (exactly as listed!!)";
    private static final String takeQuizPrompt = "How would you like to take the quiz?";
    private static final String takeQuizMenu = "Manually : 1, Upload : 2";

    public static void main(String[] args) {

        //make the whole thing as a do while, keep running till user(s) are done

        Scanner scan = new Scanner(System.in);

        int accountInput;
        int personInput;
        int teacherInput;
        int studentInput;
        String quizNameFile;
        int somethingElse;
        String fileLine;
        int counter = 0;
        int correctAnswerCount = 0;


        do {

            //Initial setup
            System.out.println("Welcome to the Quiz program!");
            System.out.println(logCreateDeleteMenu);
            accountInput = scan.nextInt();
            scan.nextLine();

            //DO NOT CHANGE ANYTHING UNDER ACCOUNTINPUT == 1 !!!!!
            //IT WORKS PERFECT
            if (accountInput == 1) {
                //code to create account function

                System.out.println(studentTeacherMenu);
                personInput = scan.nextInt();
                scan.nextLine();

                do {

                    if (personInput == 1) {

                        //student

                        System.out.println("What will be your username?");
                        String username = scan.nextLine();
                        System.out.println("Set your password");
                        String password = scan.nextLine();

                        //Checking for account and then creating if doesn't exist
                        /* Example LittleSuzy.txt
                                    LittleSuzy
                                    pinkPrincess47
                                    1 (or 2)
                        */
                        try {
                            //if(isAlphanumeric(username))
                            File f = new File(username + ".txt");
                            if (f.exists()) {
                                System.out.println("This account already exists");
                            } else {
                                f.createNewFile();
                                //FileOutputStream fos = new FileOutputStream(f, false);
                                PrintWriter pw = new PrintWriter(f);
                                pw.println(username);
                                pw.println(password);
                                pw.println("1");
                                pw.close();

                                Account studentAccount = new Account(username, password, "1");
                                studentAccount.accountsArrayListAfterReset();
                                studentAccount.createAccount(username, password, "1");
                                studentAccount.sortIdentity();
                                Student student = new Student(username, password, "Student");
                                //call student function, has not been done
                                System.out.println("Account Created!");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (personInput == 2) {

                        //teacher

                        System.out.println("What will be your username?");
                        String username = scan.nextLine();
                        System.out.println("Set your password");
                        String password = scan.nextLine();

                        //Checking if account already exists
                        try {
                            File f = new File(username + ".txt");
                            if (f.exists()) {
                                System.out.println("This account already exists");
                            } else {
                                f.createNewFile();
                                PrintWriter pw = new PrintWriter(f);
                                pw.println(username);
                                pw.println(password);
                                pw.println("2");
                                pw.close();
                                Account studentAccount = new Account(username, password, "2");
                                studentAccount.accountsArrayListAfterReset();
                                studentAccount.createAccount(username, password, "2");
                                studentAccount.sortIdentity();

                                Teacher teacher = new Teacher(username, password, "2");
                                //call teacher function, has not been done
                                System.out.println("Account Created!");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                        System.out.println("You need to type either 1 for student or 2 for teacher!");
                    }

                    //do/while protects against invalid input
                } while (personInput != 1 && personInput != 2);

            } else if (accountInput == 2) {

                //login (code for this is different)

                System.out.println(studentTeacherMenu);
                personInput = scan.nextInt();
                scan.nextLine();

                if (personInput == 1) {

                    //student

                    System.out.println("Type your username");
                    String username = scan.nextLine();
                    File f = new File(username + ".txt");
                    System.out.println("Type your password");
                    String password = scan.nextLine();

                    try {
                        String filePassword = "";
                        String userType = "";
                        BufferedReader bfr = new BufferedReader(new FileReader(f));
                        counter = 0;
                        while ((fileLine = bfr.readLine()) != null) {
                            counter++;
                            if (counter == 2) {
                                filePassword = fileLine;
                            } else if (counter == 3) {
                                userType = fileLine;
                            }
                        }

                        //checking if the passwords match
                        if (!password.equals(filePassword)) {
                            System.out.println("Incorrect Password!");

                        } else {
                            if (!userType.equals("1")) {
                                System.out.println("Incorrect user type");
                            } else {
                                System.out.println("You have successfully logged in");

                                do {
                                    System.out.println(studentInputs);
                                    studentInput = scan.nextInt();
                                    scan.nextLine();
                                    if (studentInput != 1 && studentInput != 2) {
                                        System.out.println("Error. You need to either type 1 or 2.");
                                    }

                                } while (studentInput != 1 && studentInput != 2);

                                //get grade
                                if (studentInput == 1) {
                                    System.out.println("Which quiz would you like to view the grades of?");
                                    String filename = scan.nextLine();
                                    try {
                                        File f1 = new File(filename + ".txt");
                                        if (!f1.exists()) {
                                            System.out.println("It looks like you may have mistyped the quiz name");
                                        } else {
                                            //TODO Finish this
                                            int pointsEarned = 0;
                                            int pointsPossible = 0;
                                            Quiz quiz = new Quiz("Student", filename, password, username);
                                            int numQuestions = quiz.numberQuestions(filename);
                                            String[] grades = new String[numQuestions + 1];
                                            for (int i = 0; i < numQuestions; i++) {
                                                //FIXME this is not based on the individual question, but on the entire quiz

                                                grades[i] = i + ". " + quiz.getPointsEarned().get(i) + "/" + quiz.getPossiblePointValues().get(i);
                                                //TODO I want the last line to be the total points earned/total possible points
                                                pointsEarned += quiz.getPointsEarned().get(i);
                                                pointsPossible += quiz.getPossiblePointValues().get(i);
                                            }
                                            grades[numQuestions] = pointsEarned + "/" + pointsPossible;
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                } else {
                                    System.out.println(quizNameFileInput);
                                    quizNameFile = scan.nextLine();
                                    String line = "";
                                    File f3 = new File(quizNameFile + ".txt");
                                    if (!f3.exists()) {
                                        System.out.println("This is not in our system!");
                                    } else {
                                        Quiz quiz = new Quiz("1", quizNameFile, password, username);
                                        BufferedReader bfrQuiz = new BufferedReader(new FileReader(f3));
                                        System.out.println(takeQuizPrompt);
                                        System.out.println(takeQuizMenu);
                                        int decision = scan.nextInt();
                                        scan.nextLine();
                                        ArrayList<Integer> correctAnswers = new ArrayList<>();
                                        ArrayList<Integer> possiblePoints = new ArrayList<>();
                                        ArrayList<Integer> pointsAccumulated = new ArrayList<>();
                                        if (decision == 1) {
                                            while ((line = bfrQuiz.readLine()) != null) {
                                                counter = 0;
                                                int j = 0;
                                                int answer;
                                                String[] option = new String[4];
                                                String question = "";
                                                int pointValue = 0;
                                                for (int i = 0; i < 7; i++) {
                                                    if (i == 0) {
                                                        question = line;
                                                        System.out.println(line);
                                                        line = bfrQuiz.readLine();
                                                    } else if (i > 0 && i < 5) {
                                                        option[j] = line;
                                                        j++;
                                                        System.out.println(line);
                                                        line = bfrQuiz.readLine();
                                                    } else if (i == 5) {
                                                        //point value line
                                                        pointValue = Integer.parseInt(line);
                                                        possiblePoints.add(pointValue);
                                                        line = bfrQuiz.readLine();
                                                    } else if (i == 6) {
                                                        //This is the answer line
                                                        answer = scan.nextInt();
                                                        scan.nextLine();
                                                        if (answer == Integer.parseInt(line)) {
                                                            pointsAccumulated.add(pointValue);
                                                        } else {
                                                            pointsAccumulated.add(0);
                                                        }
                                                    }
                                                }
                                                int[] pointsPossible = new int[possiblePoints.size()];
                                                int[] earnedPoints = new int[pointsAccumulated.size()];
                                                for (int i = 0; i < possiblePoints.size(); i++) {
                                                    pointsPossible[i] = possiblePoints.get(i);
                                                }
                                                for (int i = 0; i < pointsAccumulated.size(); i++) {
                                                    earnedPoints[i] = pointsAccumulated.get(i);
                                                }
                                                Student student = new Student(username, password, userType);
                                                student.addGrade(quizNameFile + ".txt", pointsPossible, earnedPoints);
                                            }
                                        } else if (decision == 2) {
                                            String studentAnswer = "";
                                            while ((line = bfr.readLine()) != null) {
                                                counter = 0;
                                                for (int i = 0; i < 7; i++) {
                                                    counter++;
                                                    if (counter != 6 && counter != 7) {
                                                        System.out.println(line);
                                                    }
                                                    if (counter == 7) {
                                                        correctAnswers.add(Integer.parseInt(line));
                                                    }
                                                    if (counter == 6) {
                                                        possiblePoints.add(Integer.parseInt(line));
                                                    }
                                                }
                                            }
                                            System.out.println("Please type the name of the file holding your answers");
                                            String filename = scan.nextLine();
                                            try {
                                                File answerFile = new File(filename);
                                                BufferedReader answerFileReader = new BufferedReader(new FileReader(answerFile));
                                                for (int i = 0; i < correctAnswers.size(); i++) {
                                                    if ((studentAnswer = answerFileReader.readLine()) != null) {
                                                        if (Integer.parseInt(studentAnswer) == correctAnswers.get(i)) {
                                                            pointsAccumulated.add(possiblePoints.get(i));
                                                        } else {
                                                            pointsAccumulated.add(0);
                                                        }
                                                    }
                                                }

                                            } catch (FileNotFoundException e) {
                                                e.printStackTrace();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            int[] pointsPossible = new int[possiblePoints.size()];
                                            int[] pointEarned = new int[pointsAccumulated.size()];
                                            for (int i =0; i < possiblePoints.size(); i++) {
                                                pointsPossible[i] = possiblePoints.get(i);
                                            }
                                            for (int i = 0; i < pointsAccumulated.size(); i++) {
                                                pointEarned[i] = pointsAccumulated.get(i);
                                            }
                                            Student student = new Student(username,password,userType);
                                            student.addGrade(quizNameFile,pointsPossible, pointEarned);
                                        } else {
                                            System.out.println("That wasn't an option!");
                                        }
                                    }
                                }
                            }
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("This account is not in our system");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (personInput == 2) {
                    //function call for teacher
                    //make exams, delete, edit, view student submissions
                    System.out.println("Type your username");
                    String username = scan.nextLine();
                    File f = new File(username + ".txt");
                    System.out.println("Type your password");
                    String password = scan.nextLine();
                    try {
                        String filePassword = "";
                        String userType = "";
                        BufferedReader bfr = new BufferedReader(new FileReader(f));
                        counter = 0;
                        while ((fileLine = bfr.readLine()) != null) {
                            counter++;
                            if (counter == 2) {
                                filePassword = fileLine;
                            } else if (counter == 3) {
                                userType = fileLine;
                            }
                        }
                        if (!filePassword.equals(password)) {
                            System.out.println("Incorrect Password!");
                        } else {
                            if (!userType.equals("2")) {
                                System.out.println("Incorrect userType!");
                            } else {
                                System.out.println("You have successfully logged in");
                                System.out.println(teacherInputs);
                                teacherInput = scan.nextInt();
                                scan.nextLine();

                                //DO NOT TOUCH THIS WORKS
                                if (teacherInput == 1) {
                                    //menu for creating quiz
                                    System.out.println("Write the quiz name");
                                    String quizName = scan.nextLine();
                                    Quiz studentQuiz = new Quiz(userType, quizName, password, username);
                                    studentQuiz.addQuiz(quizName + ".txt");
                                    System.out.println("Quiz successfully added");

                                    //DO NOT TOUCH THIS WORKS
                                } else if (teacherInput == 2) {
                                    //function to edit quiz or inputs needed
                                    System.out.println("Which quiz is it?");
                                    String quizName = scan.nextLine();

                                    System.out.println("Would you like to\n1. Add a Question\n2. Delete a Question\n3. Randomize the questions");
                                    int addOrDelete = scan.nextInt();
                                    scan.nextLine();
                                    if (addOrDelete == 1) {
                                        String question = "";
                                        String optionOne = "";
                                        String optionTwo = "";
                                        String optionThree = "";
                                        String optionFour = "";

                                        System.out.println("Please enter your question");
                                        question = scan.nextLine();

                                        for (int i = 1; i < 5; i++) {
                                            System.out.println("Input option " + i);
                                            if (i == 1) {
                                                optionOne = scan.nextLine();
                                            } else if (i == 2) {
                                                optionTwo = scan.nextLine();
                                            } else if (i == 3) {
                                                optionThree = scan.nextLine();
                                            } else {
                                                optionFour = scan.nextLine();
                                            }
                                        }
                                        System.out.println("Which option (1,2,3,or 4) is correct?");
                                        int answer = Integer.parseInt(scan.nextLine());
                                        System.out.println("How many points is the question worth?");
                                        int pointValue = Integer.parseInt(scan.nextLine());
                                        //define method and call method
                                        Quiz quiz = new Quiz(userType, quizName + ".txt", password, username);
                                        quiz.addQuestion(userType, question, optionOne, optionTwo, optionThree, optionFour, pointValue, answer);

                                        //FIXME I need help with this one!!!!
                                    } else if (addOrDelete == 2) {
                                        System.out.println("What is the question? (type out question, not number)");
                                        String question = scan.nextLine();
                                        Quiz quiz = new Quiz(userType, quizName + ".txt", password, username);
                                        quiz.deleteQuestion(question, quizName + ".txt");

                                        //FIXME This doesn't work
                                    } else if (addOrDelete == 3) {
                                        Quiz quiz = new Quiz(userType, quizName + ".txt", password, username);
                                        quiz.readQuiz(quizName + ".txt");
                                        quiz.randomize(quizName + ".txt");
                                    } else {
                                        System.out.println("That wasn't an option");
                                    }

                                } else if (teacherInput == 3) {
                                    //function to delete quiz, prompt if they want to
                                    System.out.println("Which quiz do you wish to delete?");
                                    String filename = scan.nextLine() + ".txt";
                                    System.out.println("Are you sure?\n1.Yes\n2.No");
                                    int yesOrNo = scan.nextInt();
                                    scan.nextLine();
                                    if (yesOrNo == 1) {
                                        Quiz quiz = new Quiz(userType, filename, password, username);
                                        quiz.deleteQuiz(filename);
                                    } else if (yesOrNo == 2) {
                                        System.out.println("No worries");
                                    } else {
                                        System.out.println("That wasn't an option");
                                    }
                                } else {
                                    //function to view student submission (name wanted)
                                }
                            }
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("This account is not in our system");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("You need to type 1 for student or 2 for teacher!");
                }

                //DO NOT CHANGE
            } else if (accountInput == 3) {

                //code for delete account
                //prompt to make sure that they want to delete it!!

                System.out.println("Type your username");
                String username = scan.nextLine();
                File f = new File(username + ".txt");
                System.out.println("Type your password");
                String password = scan.nextLine();
                ArrayList<String> list = new ArrayList<>();

                try {
                    String filePassword = "";
                    String userType = "";
                    BufferedReader bfr = new BufferedReader(new FileReader(f));
                    counter = 0;
                    while ((fileLine = bfr.readLine()) != null) {
                        counter++;
                        if (counter == 2) {
                            filePassword = fileLine;
                        } else if (counter == 3) {
                            userType = fileLine;
                        }
                    }
                    bfr.close();
                    if (!filePassword.equals(password)) {
                        System.out.println("Incorrect Password!");

                    } else {
                        System.out.println("You have successfully logged in");
                        //do while loop here (do here)
                        System.out.println("Are you sure you want to delete this account?");
                        System.out.println("1. Yes\n2. No");
                        int yesOrNo = scan.nextInt();
                        scan.nextLine();
                        Account account = new Account(username, password, userType);

                        if (yesOrNo == 1) {
                            f.delete();
                            System.out.println("Account deleted.");

                        } else if (yesOrNo == 2) {
                            System.out.println("Glad to have you stay!");

                        } else {
                            System.out.println("You need to type 1 for \"Yes\" or 2 for \"No\"");
                        }
                        //while should go here
                    }

                } catch (FileNotFoundException e) {
                    System.out.println("This file wasn't found in our system!");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //DO NOT CHANGE ACCOUNT INPUT 4
                //IT WORKS PERFECTLY
            } else if (accountInput == 4) {

                //code for edit account
                //prompt to make sure that the password they want is correct

                System.out.println("Type your username");
                String username = scan.nextLine();
                File f = new File(username + ".txt");
                System.out.println("Type your password");
                String password = scan.nextLine();

                try {
                    String filePassword = "";
                    String userType = "";
                    BufferedReader bfr = new BufferedReader(new FileReader(f));
                    ArrayList<String> list = new ArrayList<>();
                    counter = 0;
                    while ((fileLine = bfr.readLine()) != null) {
                        //read whole file
                        counter++;
                        list.add(fileLine);
                        if (counter == 2) {
                            filePassword = fileLine;
                        } else if (counter == 3) {
                            userType = fileLine;
                        }
                    }

                    boolean passwordCheck = false;
                    if (!filePassword.equals(password)) {
                        System.out.println("Incorrect Password!");
                        passwordCheck = false;

                    } else {
                        passwordCheck = true;

                        do {
                            passwordCheck = true;
                            System.out.println("You have successfully logged in");

                            System.out.println("Please enter your new password");
                            String newPassword = scan.nextLine();

                            System.out.println("Please enter your new password again to confirm");
                            String checkNewPassword = scan.nextLine();

                            if (newPassword.equals(checkNewPassword)) {
                                File f4 = new File(username + ".txt");
                                FileOutputStream fos = new FileOutputStream(f4, false);
                                PrintWriter pw = new PrintWriter(fos);
                                pw.println(username);
                                pw.println(newPassword);
                                pw.println(userType);
                                //for loop for the reading portion
                                for (int i = 3; i < list.size(); i++) {
                                    pw.println(list.get(i));
                                }
                                pw.close();
                                System.out.println("Successfully changed password!");

                            } else {
                                System.out.println("Error, password does not match");
                                passwordCheck = false;
                            }

                        } while (passwordCheck == false);
                    }

                } catch (FileNotFoundException e) {
                    System.out.println("This file wasn't found in our system!");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("Input Error!");
            }

            do {
                System.out.println("Would you like to do something else?");
                System.out.println("1. Yes\n2. No");
                somethingElse = scan.nextInt();
                scan.nextLine();
                if (somethingElse != 1 && somethingElse != 2) {
                    System.out.println("Oops! That wasn't an option");
                }
            } while (somethingElse != 1 && somethingElse != 2);


        } while (somethingElse == 1);

        System.out.println("Thank you for using Quiz Maker!");

    }

    public static boolean isAlphaNumeric (String input){
        return input != null && input.matches("^[a-zA-Z0-9]*$");
    }

    /*int counter = 0;
        String fileLine;
        private static final String studentInputs = "View grades : 1, Take quiz : 2";
        private static final String teacherInputs = "Create quiz : 1, edit quiz : 2, delete quiz : 3, view student submissions : 4";
        private static final String takeQuizPrompt = "How would you like to take the quiz?";
        private static final String takeQuizMenu = "Manually : 1, Upload : 2";
        System.out.println("Type your username");
        String username = scan.nextLine();
        File f = new File(username + ".txt");
        System.out.println("Type your password");
        String password = scan.nextLine();
        try {
            String userType = "";
            boolean passwordCorrect = true;
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            int teacherOption;
            int studentOption;
            int quizOption;
            //while loop to check if password is correct and the same (might need to check this when testing)
            while ((fileLine = bfr.readLine()) != null) {
                counter++;
                if (counter == 2) {
                    if (!password.equals(fileLine)) {
                        passwordCorrect = false;
                    }
                } else if (counter == 3) {
                    userType = fileLine;
                    //1 is student, 2 teacher
                }
            }
            //if password is not correct, then statement prints and leaves loop
            if (passwordCorrect == false) {
                System.out.println("Error, password incorrect!");
            }
            //if statements for student or teacher
            //if it is a student (1), code below runs
            if (Integer.parseInt(userType) == 1) {
                //do this when input is not 1 and 2
                do {
                    //get input of the student
                    System.out.println(studentInputs);
                    studentOption = scan.nextInt();
                    scan.nextLine();
                    //if input is 1, will show grades (function told me by Ananya)
                    //else if input is 2, ask if user
                    if (studentOption == 1) {
                        Student.readGrades();
                    } else if (studentOption == 2) {
                        System.out.println(takeQuizPrompt);
                        do {
                            System.out.println(takeQuizMenu);
                            quizOption = scan.nextInt();
                            scan.nextLine();
                            if (quizOption == 1) {
                                //code to take quiz manually
                            } else if (quizOption == 2) {
                                //code to upload quiz
                            } else {
                                System.out.println("Error, option does not work, reselect");
                            }
                        } while (quizOption != 1 && quizOption != 2);
                        //run quiz that they desire
                    } else {
                        System.out.println("Error, option does not work, reselect");
                    }
                } while (studentOption != 1 && studentOption != 2);
            } else {
                do {
                    System.out.println(teacherInputs);
                    teacherOption = scan.nextInt();
                    scan.nextLine();
                    if (teacherOption == 1) {
                        //create quiz call function
                    } else if (teacherOption == 2) {
                        //edit quiz call function
                    } else if (teacherOption == 3) {
                        //delete quiz call function
                    } else if (teacherOption == 4) {
                        view student submission call function;
                    } else {
                        System.out.println("Error, option does not work, reselect");
                    }
                } while (teacherOption < 1 || teacherOption > 4);
            }
        } catch (FileNotFoundException e) {
            System.out.println("This account is not in our system");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

    /*
        Code to input question and options
        String question = "";
        String optionOne = "";
        String optionTwo = "";
        String optionThree = "";
        String optionFour = "";
        System.out.println("Please enter your question");
        question = scan.nextLine();
        for (int i = 1; i < 5; i++) {
            System.out.printf("Please enter option %f: \n", i);
            if (i == 1) {
                optionOne == scan.nextLine();
            } else if (i == 2) {
                optionTwo == scan.nextLine();
            } else if (i == 3) {
                optionThree == scan.nextLine();
            } else {
                optionFour == scan.nextLine();
            }
          }
          //define method and call method
        }
     */
}
