package il.ac.hit.quizzy;

public class QuizAnswer implements IQuizAnswer {
    private String text;
    private boolean correct;
    private boolean selected; // Add selected property

    public QuizAnswer(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public boolean isCorrect() {
        return correct;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
