package il.ac.hit.quizzy;

import java.util.List;
import java.util.Scanner;

public class TerminalQuiz implements IQuiz {
    private String name;
    private List<IQuizQuestion> questions;

    public TerminalQuiz(String name, List<IQuizQuestion> questions) {
        this.name = name;
        this.questions = questions;
    }

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        System.out.println("Welcome to " + name);

        // Iterate through questions and display them in the terminal
        for (IQuizQuestion question : questions) {
            System.out.println(question.getQuestion());

            // Display answer choices
            List<IQuizAnswer> answers = question.getAnswers();
            for (int i = 0; i < answers.size(); i++) {
                System.out.println((i + 1) + ". " + answers.get(i).getText());
            }

            // Get user's answer
            System.out.print("Enter the number of your answer: ");
            int userAnswer = scanner.nextInt();

            // Check if the user's answer is correct
            if (userAnswer >= 1 && userAnswer <= answers.size() && answers.get(userAnswer - 1).isCorrect()) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect.");
            }
        }

        // Display the final score
        System.out.println("Quiz completed! Your score: " + score + "/" + questions.size());
    }

    @Override
    public void setName(String text) {
        this.name = text;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addQuestion(IQuizQuestion question) {
        questions.add(question);
    }

    @Override
    public List<IQuizQuestion> getQuestions() {
        return questions;
    }
}
