package il.ac.hit.quizzy;

import java.util.List;

public interface IQuizQuestion {
    String getTitle();

    String getQuestion();

    List<IQuizAnswer> getAnswers();

}
