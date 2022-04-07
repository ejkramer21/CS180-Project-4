import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Quiz extends Question {
    private Account accountType;
    private Question question;
    private String filename;

    Scanner in = new Scanner(System.in);

    public Quiz(Account accountType, Question question, String filename) {
        //should i extend account and use getTeacher/Student?
        this.accountType = accountType;
        this.question = question;
        this.filename = filename;
    }

    public Question addQuiz(String filename, Question question) throws IncorrectAccountException, FileNotFoundException {
        try {
            if (accountType instanceof Teacher) {
                File f = new File(filename);
                if (!f.exists()) {
                    f.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(f);
                PrintWriter pw = new PrintWriter(fos);
                pw.println(question);
                pw.close();
                fos.close();
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

    /*
    public void editQuiz(Account accountType, String filename, String questionNumber, String firstChoice, String secondChoice, String thirdChoice, String fourthChoice) throws IncorrectAccountException {
        if (accountType instanceof Teacher) {
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

    public void addQuestion(Account accountType, String actualQuestion, String optionOne, String optionTwo, String optionThree, String optionFour) throws IncorrectAccountException{
        //TODO do we want to return question also

        if (accountType instanceof Teacher) {

            questionLines = new String[4];

            File f = new File(filename);
            if (!f.exists()) {
                f.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(f, true);
            PrintWriter pw = new PrintWriter(fos);

            /*System.out.println("What question do you want to add?");
            questionNumber = in.nextLine();
            actualQuestion = questionNumber;
            System.out.println("Type in your answer first answer choice.");
            firstChoice = in.nextLine();
            question[0] = firstChoice;
            System.out.println("Type your second choice");
            secondChoice = in.nextLine();
            question[1] = secondChoice;
            System.out.println("Type your third choice");
            thirdChoice = in.nextLine();
            question[2] = thirdChoice;
            System.out.println("Type your fourth choice");
            fourthChoice = in.nextLine();
            question[3] = fourthChoice;
             */

            question[0] = optionOne;
            question[1] = optionTwo;
            question[2] = optionThree;
            question[3] = optionFour;

            Question questionCall = new Question(actualQuestion, question);
            pw.println(questionCall);

            pw.close();
            fos.close();

        } else {
            throw new IncorrectAccountException("A student cannot create a question!");
        }
    }

    public void deleteQuestion(Account accountType, String actualQuestion, String filename) {
        //putting questions into an array list and finding the question from the list and delete that question and the four options
        if (accountType instanceof Teacher) {
            String[] option = new String[4];
            int i = 0;
            String line = "";
            ArrayList<String> list = new ArrayList<>();
            try (BufferedReader bfr = new BufferedReader(new FileReader(filename))) {
                while ((line = (bfr.readLine())) != null) {
                    list.add(line);
                }

                list.get(list.indexOf(actualQuestion));
                //FIXME add into a for loop

                option[0] = list.get(list.indexOf(actualQuestion)++);
                option[1] = list.get(list.indexOf(actualQuestion) + 2);
                option[2] = list.get(list.indexOf(actualQuestion) + 3);
                option[3] = list.get(list.indexOf(actualQuestion) + 4);

                Question questionCall = new Question(actualQuestion, option);
                //TODO possibly change actualQuestion into an integer?
                while (i < 5) {
                    list.remove(list.indexOf(actualQuestion));
                    i++;
                }

                /*
                list.remove(list.indexOf(actualQuestion)++);
                list.remove(list.indexOf(actualQuestion) + 2);
                list.remove(list.indexOf(actualQuestion) + 3);
                list.remove(list.indexOf(actualQuestion) + 4);
                 */

                File f = new File(filename);
                //noQuestionsFoundException?
                FileOutputStream fos = new FileOutputStream(f, false);
                PrintWriter pw = new PrintWriter(fos);
                pw.println(list);

                questionCall.numQuestions(list, filename);
                pw.close();
                fos.close();
                bfr.close();
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
