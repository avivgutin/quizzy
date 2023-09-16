package il.ac.hit.quizzy;

import java.util.List;

public interface IQuiz {
    void start();
    void setName(String text);
    String getName();
    void addQuestion(IQuizQuestion question);
    List<IQuizQuestion> getQuestions();
}
