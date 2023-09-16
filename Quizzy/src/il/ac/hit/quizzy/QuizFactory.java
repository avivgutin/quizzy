package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.List;

public class QuizFactory {
    public IQuiz createQuiz(QuizType type) {
        if (type == QuizType.GUI) {
            return new GUIQuiz("Default GUI Quiz", new ArrayList<>()); // Provide default values
        } else {
            return new TerminalQuiz("Default Terminal Quiz", new ArrayList<>()); // Provide default values
        }
    }
}
