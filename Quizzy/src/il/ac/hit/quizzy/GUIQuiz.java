package il.ac.hit.quizzy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUIQuiz implements IQuiz {
    private String name;
    private List<IQuizQuestion> questions;
    private JFrame frame;

    public GUIQuiz(String name, List<IQuizQuestion> questions) {
        this.name = name;
        this.questions = questions;
    }

    @Override
    public void start() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Quiz GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Center the JFrame on the screen
        frame.setLocationRelativeTo(null);

        JPanel quizPanel = new JPanel();
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));

        for (IQuizQuestion question : questions) {
            JLabel questionLabel = new JLabel(question.getQuestion());
            quizPanel.add(questionLabel);

            List<IQuizAnswer> answers = question.getAnswers();
            ButtonGroup buttonGroup = new ButtonGroup();
            for (IQuizAnswer answer : answers) {
                JRadioButton radioButton = new JRadioButton(answer.getText());
                buttonGroup.add(radioButton);
                quizPanel.add(radioButton);

                // Add action listener to each radio button
                radioButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        answer.setSelected(radioButton.isSelected());
                    }
                });
            }
        }

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAndDisplayGrade();
            }
        });

        quizPanel.add(submitButton);

        frame.add(new JScrollPane(quizPanel));

        // Center the JFrame on the screen after adding components
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    private void calculateAndDisplayGrade() {
        int totalQuestions = questions.size();
        int correctAnswers = 0;

        for (IQuizQuestion question : questions) {
            List<IQuizAnswer> answers = question.getAnswers();
            for (IQuizAnswer answer : answers) {
                if (answer.isSelected() && answer.isCorrect()) {
                    correctAnswers++;
                }
            }
        }

        double percentage = (double) correctAnswers / totalQuestions * 100;
        String message = "You got " + correctAnswers + " out of " + totalQuestions + " correct.\n";
        message += "Your score: " + percentage + "%";
        JOptionPane.showMessageDialog(frame, message, "Quiz Results", JOptionPane.INFORMATION_MESSAGE);
        frame.dispose();
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
