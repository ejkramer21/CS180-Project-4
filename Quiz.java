import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//for Ananya:  I think we should create the points earned array inside of the main method where we are actually scanning in the
//students answers and comparing it to the next line in the file (because remember the 6th line of the questions is going to just
//be the answer as an int, so if the student's answer == the next line, then we can call quiz.setPointsEarned(quiz.getPointsEarned.add(question.getPointValue))
//else we do nothing.  I just think this would be easier than trying to work around the scanner input. Lmk your thoughts -Joy

public class Quiz {
    private String userType;
    private Question question;
    private String filename;
    private String password;
    private String username;
    private ArrayList<Integer> possiblePointValues;
    private ArrayList<Integer> pointsEarned;
    private ArrayList<String> allQuizQuestions;
    private ArrayList<String> randomizedQuizQuestions;

    Scanner in = new Scanner(System.in);

    //constructor for the teacher
    public Quiz(String userType, Question question, String filename, String password, String username) {
        this.question = question;
        this.filename = filename;
        this.password = password;
        this.username = username;
        this.userType = userType;
    }

    //constructor for the student
    public Quiz(String userType, String filename, String password, String username) {
        Account account = new Account(username, password, userType);
        this.userType = account.getUserType();
        this.filename = filename;
    }

    public ArrayList<Integer> getPossiblePointValues() {
        return possiblePointValues;
    }

    public void setPossiblePointValues(ArrayList<Integer> possiblePointValues) {
        this.possiblePointValues = possiblePointValues;
    }

    public ArrayList<Integer> getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(ArrayList<Integer> pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public int numberQuestions(String filename) {
        int numQuestions = 0;
        File f = new File(filename);
        if (f.exists()) {
            try {
                BufferedReader bfr = new BufferedReader(new FileReader(f));
                String quizLine = "";
                int counter = 0;
                numQuestions = 0;
                while ((bfr.readLine()) != null) {
                    counter++;
                }
                if (counter % 6 == 0) {
                    numQuestions++;
                }
                bfr.close();
            } catch (FileNotFoundException e) {
                System.out.println("The file isn't in our system");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return numQuestions;
    }

    public void addQuiz(String filename) throws IncorrectAccountException, FileNotFoundException {
        try {
            if (userType.equalsIgnoreCase("2")) {
                //This is creating the original quiz with just the first question.  The constructor for this one should be Question(String question, String[] option, int answer).
                //Above constructor is the one being printed to file in this method
                //AND Question(String question, int pointValue) so two files are created.  One has the point values and question and other is for student to actually take test
                File studentTestFile = new File(filename);
                if (!studentTestFile.exists()) {
                    studentTestFile.createNewFile();

                    /*String[] options = new String[4];
                    options[0] = "1. " + optionOne;
                    options[1] = "2. " + optionTwo;
                    options[2] = "3. " + optionThree;
                    options[3] = "4. " + optionFour;

                    FileOutputStream fos = new FileOutputStream(studentTestFile, false);
                    PrintWriter pw = new PrintWriter(fos);
                    Question firstQuestion = new Question(question, options, pointValue, answer);
                    pw.println(firstQuestion);
                    pw.close();
                    fos.close();

                     */
                } else {
                    throw new QuizAlreadyExistsException("This quiz already exists!  Are you sure you aren't trying to add a new question or rename the quiz?");
                }

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

    public void randomize() {
        int questionCount = this.allQuizQuestions.size();
        ArrayList<String> tempArray = this.allQuizQuestions;
        while (questionCount != 0) {
            int randomQuestionNumber = (int) Math.random() * (questionCount - 1);
            this.randomizedQuizQuestions.add(tempArray.get(randomQuestionNumber));
            tempArray.remove(randomQuestionNumber);
            questionCount--;
        }
    }

    public void addQuestion(String userType, String actualQuestion, String optionOne, String optionTwo, String optionThree, String optionFour, int pointValue, int answer) throws IncorrectAccountException {
        //we will want to prompt the user to enter their account information before they may add a question.  I recommend a while loop so the teacher inputs their account info
        //then we begin the while loop and ask if they want to add a question and assign "yes" to an int like 1 and the question prompts(and this method) keep getting called
        //while that int still = 1
        //If the quiz doesn't already exist, you must call the addQuiz method first because this method doesn't create a new file

        if (userType.equalsIgnoreCase("2")) {
            //I'm concerned this might be resetting the array list every time.  Gotta figure this out
            ArrayList<Integer> pointsPossible = new ArrayList<Integer>();
            String[] options = new String[4];
            try {
                File studentTestFile = new File(filename);
                FileOutputStream fos = new FileOutputStream(studentTestFile, true);
                PrintWriter pw = new PrintWriter(fos);

                options[0] = "1. " + optionOne;
                options[1] = "2. " + optionTwo;
                options[2] = "3. " + optionThree;
                options[3] = "4. " + optionFour;

                pointsPossible.add(pointValue);
                setPossiblePointValues(pointsPossible);

                Question questionCall = new Question(actualQuestion, options, pointValue, answer);
                pw.println(questionCall);
                pw.close();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            throw new IncorrectAccountException("A student cannot create a question!");
        }
    }

    public void deleteQuestion(String userType, String actualQuestion, String filename, int answer) throws IncorrectAccountException {
        //putting questions into an array list and finding the question from the list and delete that question and the four options
        if (userType.equalsIgnoreCase("2")) {
            String[] option = new String[4];
            int i = 0;
            String line = "";
            ArrayList<String> questionComponents = new ArrayList<>();
            try (BufferedReader bfr = new BufferedReader(new FileReader(filename))) {
                while ((line = (bfr.readLine())) != null) {
                    questionComponents.add(line);
                }

                questionComponents.get(questionComponents.indexOf(actualQuestion));
                //ngl I felt like a genius when I wrote this
                while (i < 4) {
                    option[i] = ++i + ". " + questionComponents.get(questionComponents.indexOf(actualQuestion) + i);
                }

                //Question questionCall = new Question(actualQuestion, option, answer);
                while (i < 7) {
                    //is 6 because we also have the int of answer in the file
                    questionComponents.remove(questionComponents.indexOf(actualQuestion));
                    i++;
                }

                File f = new File(filename);
                //we are rewriting the entire file here
                FileOutputStream fos = new FileOutputStream(f, false);
                PrintWriter pw = new PrintWriter(fos);
                pw.println(questionComponents);
                pw.close();
                fos.close();

                //this is resetting so the questions stored in the quiz no longer includes the removed question
                if (questionComponents.size() % 7 == 0) {
                    String[] newOptions = new String[4];
                    int j = 0;
                    for (i = 0; i < questionComponents.size(); i++) {
                        for (j = 0; j < 4; j++) {
                            newOptions[j] = questionComponents.get(i + 1);
                        }
                        new Question(questionComponents.get(i), newOptions, Integer.parseInt(questionComponents.get(j + 2)), Integer.parseInt(questionComponents.get(j + 1)));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new IncorrectAccountException("A student cannot create a question!");
        }
    }

    public void readQuiz(String filename) throws IOException {
        File f = new File(filename);
        BufferedReader bfr = new BufferedReader(new FileReader(f));
        int counter = 0;
        String line = "";
        String individualQuestion = "";
        while ((line = bfr.readLine()) != null) {
            counter++;
            if (counter % 7 == 0) {
                this.allQuizQuestions.add(individualQuestion);
                individualQuestion = "";
                counter = 0;
                bfr.readLine();
            } else {
                individualQuestion = individualQuestion + line + "\n";
            }
        }
        bfr.close();
    }
}