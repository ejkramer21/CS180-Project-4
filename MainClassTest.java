import java.io.*;
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
        int somethingElse;
        String fileLine;
        int counter = 0;

        do {
            //theory testing
            System.out.println("Welcome to the Quiz program!");
            System.out.println(logCreateDeleteMenu);
            accountInput = scan.nextInt();
            scan.nextLine();
            System.out.println(studentTeacherMenu);
            personInput = scan.nextInt();
            scan.nextLine();

            if (accountInput == 1) {
                //code to create account function
                if (personInput == 1) {
                    //student
                    System.out.println("What will be your username?");
                    String username = scan.nextLine();
                    System.out.println("Set your password");
                    String password = scan.nextLine();
                    try {
                        File f = new File(username + ".txt");
                        if (f.exists()) {
                            System.out.println("This account already exists");
                        } else {
                            f.createNewFile();
                            PrintWriter pw = new PrintWriter(f);
                            pw.println(username);
                            pw.println(password);
                            pw.println("Student");
                            Account studentAccount = new Account(username, password, "Student");
                            Student student = new Student(username, password, "Student");
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
                    try {
                        File f = new File(username + ".txt");
                        if (f.exists()) {
                            System.out.println("This account already exists");
                        } else {
                            f.createNewFile();
                            PrintWriter pw = new PrintWriter(f);
                            pw.println(username);
                            pw.println(password);
                            pw.println("Teacher");
                            Account studentAccount = new Account(username, password, "Teacher");
                            Teacher teacher = new Teacher(username, password, "Teacher");
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
            } else if (accountInput == 2) {
                //login
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
                        while ((fileLine = bfr.readLine()) != null) {
                            counter++;
                            if (counter == 2) {
                                filePassword.equals(fileLine);
                            } else if (counter == 3) {
                                userType.equals(fileLine);
                            }
                        }
                        if (!filePassword.equals(password)) {
                            System.out.println("Incorrect Password!");
                        } else {
                            System.out.println("You have successfully logged in");
                            do {
                                System.out.println(studentInputs);
                                studentInput = scan.nextInt();
                                scan.nextLine();
                                if (studentInput!= 1 && studentInput != 2) {
                                    System.out.println("Error. You need to either type 1 or 2.");
                                }
                            } while (studentInput != 1 && studentInput != 2);

                            if (studentInput == 1) {
                                //function to get grade of user selected
                                System.out.println("Which quiz would you like to take?");
                                String filename = scan.nextLine();
                                File f = new File(filename + ".txt");
                                if (!f.exists()) {
                                    System.out.println("It looks like you may have mistyped the quiz name");
                                } else {
                                    //TODO Finish this
                                    Quiz quiz = new Quiz("Student", filename, password, username);
                                   int numQuestions =  quiz.numberQuestions(filename);
                                   String[] grades = new String[numQuestions + 1];
                                   for (int i = 0; i <= numQuestions; i++) {
                                       //FIXME this is not based on the individual question, but on the entire quiz
                                       grades[i] = i + ". " + quiz.getPointsEarned() + "/" + quiz.getPossiblePointValues();
                                       //TODO I want the last line to be the total points earned/total possible points
                                   }
                                }
                            } else {
                                do {
                                    System.out.println(quizNameFileInput);
                                    quizNameFile = scan.nextLine();
                                    File f = new File(quizNameFile + ".txt");
                                    if (!f.exists()) {
                                        System.out.println("This is not in our system!");
                                    } else {
                                        Quiz quiz = new Quiz("Student", quizNameFile, password, username);
                                        BufferedReader bfrQuiz = new BufferedReader(new FileReader(f));
                                        String line = "";
                                        while ((line = bfrQuiz.readLine()) != null) {
                                            for (int i = 0; i < (quiz.numberQuestions(quizNameFile)) * 6; i++) {
                                                if (i % 5 == 0) {
                                                    System.out.println("");//This is the answer line.  Student will only see a space
                                                    int answer = scan.nextInt();
                                                    if (answer == Integer.parseInt(line)) {
                                                        //FIXME I think I need to do something about my getters and setters within quiz
                                                        //FIXME i'm just not sure what yet
                                                        quiz.setPointsEarned(quiz.getPointsEarned().add(question.getPointValue()));
                                                    } else {
                                                        quiz.setPointsEarned(quiz.getPointsEarned().add(question.getPointValue() - question.getPointValue()));
                                                    }
                                                } else {
                                                    System.out.println(line);
                                                }
                                            }
                                        }
                                    }
                                } while (//function that checks if it is false/null);
                                //function to run quiz and prompt user input (somehow)
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
                        while ((fileLine = bfr.readLine()) != null) {
                            counter++;
                            if (counter == 2) {
                                filePassword.equals(fileLine);
                            } else if (counter == 3) {
                                userType.equals(fileLine);
                            }
                        }
                        if (!filePassword.equals(password)) {
                            System.out.println("Incorrect Password!");
                        } else {
                            System.out.println("You have successfully logged in");
                            System.out.println(teacherInputs);
                            teacherInput = scan.nextInt();
                            scan.nextLine();

                            if (teacherInput == 1) {
                                //menu for creating quiz
                            } else if (teacherInput == 2) {
                                //function to edit quiz or inputs needed
                            } else if (teacherInput == 3) {
                                //function to delete quiz, prompt if they want to
                            } else {
                                //function to view student submission (name wanted)
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
            } else if (accountInput == 3) {
                //code for delete account
                //prompt to make sure that they want to delete it!!
                System.out.println("Type your username");
                String username = scan.nextLine();
                File f = new File(username + ".txt");
                System.out.println("Type your password");
                String password = scan.nextLine();
                try {
                    String filePassword = "";
                    String userType = "";
                    BufferedReader bfr = new BufferedReader(new FileReader(f));
                    while ((fileLine = bfr.readLine()) != null) {
                        counter++;
                        if (counter == 2) {
                            filePassword.equals(fileLine);
                        } else if (counter == 3) {
                            userType.equals(fileLine);
                        }
                    }
                    if (!filePassword.equals(password)) {
                        System.out.println("Incorrect Password!");
                    } else {
                        System.out.println("You have successfully logged in");
                        System.out.println("Are you sure you want to delete this account?");
                        System.out.println("1. Yes\n2. No");
                        int yesOrNo = scan.nextInt();
                        scan.nextLine();
                        if (yesOrNo == 1) {

                        } else if (yesOrNo == 2) {

                        } else {
                            System.out.println("You need to type 1 for \"Yes\" or 2 for \"No\"");
                        }
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


    }
}
