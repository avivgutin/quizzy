package il.ac.hit.quizzy;

public interface IQuizAnswer {
    String getText();
    boolean isCorrect();
    boolean isSelected();
    void setSelected(boolean selected);
}
