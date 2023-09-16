package il.ac.hit.quizzy;

import java.io.*;
import java.util.List;

public class SimpleCSVQuizFilesDAO implements IQuizFilesDAO {
    private static SimpleCSVQuizFilesDAO instance;

    // Implement the Singleton pattern
    public static synchronized IQuizFilesDAO getInstance() {
        if (instance == null) {
            instance = new SimpleCSVQuizFilesDAO();
        }
        return instance;
    }

    @Override
    public void saveQuizToFile(IQuiz quiz, String fileName) throws QuizException {
        try (FileWriter writer = new FileWriter(fileName)) {
            List<IQuizQuestion> questions = quiz.getQuestions();
            for (IQuizQuestion question : questions) {
                // Write question and answers to the file in your custom format
                writer.write(questionToString(question) + "\n");
            }
        } catch (IOException e) {
            throw new QuizException("Error saving quiz to file: " + e.getMessage());
        }
    }

    @Override
    public IQuiz loadQuizFromFile(String fileName) throws QuizException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            IQuiz quiz = new Quiz();
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse the line to create questions and answers
                IQuizQuestion question = stringToQuestion(line);
                if (question != null) {
                    quiz.addQuestion(question);
                }
            }
            return quiz;
        } catch (IOException e) {
            throw new QuizException("Error loading quiz from file: " + e.getMessage());
        }
    }

    // Helper method to convert a question to a string
    private String questionToString(IQuizQuestion question) {
        StringBuilder builder = new StringBuilder();
        builder.append(question.getQuestion()).append(";");
        List<IQuizAnswer> answers = question.getAnswers();
        for (int i = 0; i < answers.size(); i++) {
            IQuizAnswer answer = answers.get(i);
            builder.append(answer.getText());
            if (answer.isCorrect()) {
                builder.append("*"); // Use '*' to mark correct answers
            }
            if (i < answers.size() - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }

    // Helper method to convert a string to a question
    private IQuizQuestion stringToQuestion(String line) {
        String[] parts = line.split(";");
        if (parts.length >= 2) {
            String questionText = parts[0];
            String[] answerStrings = parts[1].split(",");
            IQuizQuestionBuilder builder = new QuizQuestion.Builder()
                    .setQuestion(questionText);
            for (String answerString : answerStrings) {
                boolean isCorrect = answerString.endsWith("*");
                if (isCorrect) {
                    answerString = answerString.substring(0, answerString.length() - 1);
                }
                builder.addAnswer(answerString, isCorrect);
            }
            return builder.create();
        }
        return null;
    }
}
