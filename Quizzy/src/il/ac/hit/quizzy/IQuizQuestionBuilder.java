package il.ac.hit.quizzy;

public interface IQuizQuestionBuilder {
    IQuizQuestionBuilder setTitle(String text);
    IQuizQuestionBuilder setQuestion(String text);
    IQuizQuestionBuilder addAnswer(String text, boolean correct);
    IQuizQuestion create();
}
