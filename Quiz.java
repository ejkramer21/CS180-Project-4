import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Quiz {
    private boolean teacher;
    private boolean student;
    private String[] question;
    private String filename;

    Scanner in = new Scanner(System.in);

    public Quiz(boolean teacher, boolean student, Question question, String filename) {
        this.teacher = teacher;
        this.student = student;
        this.question = new String[5];
        this.filename = filename;
    }

    public void addQuiz(String filename, Question question) throws IncorrectAccountException, FileNotFoundException {
        try {
            if (teacher) {
                File f = new File(filename);
                if (!f.exists()) {
                    f.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(f);
                PrintWriter pw = new PrintWriter(fos);
                pw.println(question);
            } else {
                throw new IncorrectAccountException("A student cannot create a new quiz!");
            }
        } catch (FileNotFoundException e) {
            System.out.println("We could not find the file!");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Something went wrong!");
            e.printStackTrace();
        }
    }

    public void editQuiz(String filename, String questionNumber, String firstChoice, String secondChoice, String thirdChoice, String fourthChoice) throws IncorrectAccountException {
        if (teacher) {
            System.out.println("Do you want to add a question? \n1.Yes\n2.No");
            int addQuestion = in.nextInt();
            in.nextLine();
            System.out.println("Do you want to delete a question? \n1. Yes\n2.No");
            int subtractQuestion = in.nextInt();
            in.nextLine();
            System.out.println("What is the file name of the quiz you want to edit?");
            filename = in.nextLine();
            try (BufferedReader bfr = new BufferedReader(new FileReader(filename))) {
                ArrayList<String> questions = new ArrayList<>();
                String line = "";
                while ((line = bfr.readLine()) != null) {
                    questions.add(line);
                }
                if (addQuestion == 1) {
                    System.out.println("What question do you want to add?");
                    questionNumber = in.nextLine();
                    question[0] = questionNumber;
                    System.out.println("Type in your answer first answer choice.");
                    firstChoice = in.nextLine();
                    question[1] = firstChoice;
                    System.out.println("Type your second choice");
                    secondChoice = in.nextLine();
                    question[2] = secondChoice;
                    System.out.println("Type your third choice");
                    thirdChoice = in.nextLine();
                    question[3] = thirdChoice;
                    System.out.println("Type your fourth choice");
                    fourthChoice = in.nextLine();
                    question[4] = fourthChoice;
                    addQuestion(question, filename);
                } else {
                    System.out.println("What question do you want to delete?");
                    question[0] = in.nextLine();
                    for (int i = 1; i < 5; i++) {
                        question[i] = questions.get(questions.indexOf(question[0]) + i);
                    }
                    deleteQuestion(question, filename);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            throw new IncorrectAccountException("A student cannot edit a quiz!");
        }

    }

    public void randomize() {

    }

    /*
    public void takeQuiz() throws IncorrectAccountException {
        if (student) {

        } else {
            throw new IncorrectAccountException("A teacher cannot take the quiz!");
        }

    }



    public void submitQuiz() {

    }

    public void linkFile() {

    }

    public void viewQuizGrade() {

    }

     */

    public void addQuestion(String[] question, String filename) throws IncorrectAccountException, IOException {
        if (teacher) {
            File f = new File(filename);
            if (!f.exists()) {
                f.createNewFile();
            }
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            FileOutputStream fos = new FileOutputStream(f);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(question);
        } else {
            throw new IncorrectAccountException("A student cannot create a question!");
        }

    }

    public void deleteQuestion(String[] question, String filename) {
        //putting questions into an array list and finding the question from the list and delete that question and the four options
        if (teacher) {
            String line = "";
            ArrayList<String> list = new ArrayList<>();
            ArrayList<line> questionList = new ArrayList<>();
            try (BufferedReader bfr = new BufferedReader(new FileReader(filename))) {
                while ((line = (bfr.readLine())) != null) {
                    list.add(line);
                    for (int i = 0; i < list.size(); i+=5) {
                        questionList.add(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new IncorrectAccountException("A student cannot create a question!")
        }
    }

}
