package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.List;

public class QuizQuestion implements IQuizQuestion {
    private String title;
    private String question;
    private List<IQuizAnswer> answers = new ArrayList<>();

    private QuizQuestion() {
        // Private constructor to prevent direct instantiation
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public List<IQuizAnswer> getAnswers() {
        return answers;
    }

    // Implement other methods

    // Implement the Builder class
    public static class Builder implements IQuizQuestionBuilder {
        private String title;
        private String question;
        private List<IQuizAnswer> answers = new ArrayList<>();

        @Override
        public IQuizQuestionBuilder setTitle(String text) {
            this.title = text;
            return this;
        }

        @Override
        public IQuizQuestionBuilder setQuestion(String text) {
            this.question = text;
            return this;
        }

        @Override
        public IQuizQuestionBuilder addAnswer(String text, boolean correct) {
            IQuizAnswer answer = new QuizAnswer(text, correct);
            answers.add(answer);
            return this;
        }

        @Override
        public IQuizQuestion create() {
            QuizQuestion question = new QuizQuestion();
            question.title = this.title;
            question.question = this.question;
            question.answers = this.answers;
            return question;
        }
    }
}
