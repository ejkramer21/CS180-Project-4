# CS180-Project-4: Learning Management System
Option 2

Team 63

Ananya Seetharaman, Erin Joy Kramer, Alex Lam, James Kor

https://github.com/ejkramer21/CS180-Project-4

Ananya Seetharman- Submitted Report on Brightspace

Erin Joy Kramer - Submitted Vocareum Workspace

InvalidAccountException.java - an exception that is thrown when an invalid account is entered

IncorrectAccountException.java - an exception that is thrown when an incorrect account type is used for something, such as incorrect user type(student vs. teacher)

NoGradesFoundException.java - an exception that is thrown when an attempt is made to retrieve and display grades that don't exist for a certain quiz for a student

QuizAlreadyExistsException.java - an exception that is thrown when an attempt is made to create a quiz with the same name as another quiz

Account.java - a method that handles the storing, retrieval, and editing of account data, as well as implements password checking

Student.java - a method that extends Account, allowing for a student's grades to be added, stored, and viewed

Teacher.java - a method that extends Account, allowing a teacher to look at any student's grades

Quiz.java - a method that handles the creation, deletion, alteration, taking, and grading of quizzes

MainClassTest2.java - the main method that brings all the code together.  Implementation below is used in this class to bring all classes together.  Error's are written to the console if it doesn't compile properly.

To implement:
Create a student and teacher account.
Use teacher account to create a quiz.
Login as a teacher again and click edit quiz, add questions(or another option if preferred).  Repeat until desired number of questions is reached
Login as a student to take the quiz.  Student must have another text file ready with answers if they choose to be submit an automated version
Student can check their overall grade for each question and final grade
Teacher can view above and student response.
To make changes to the accounts, follow the first prompts at the beginning of the program
