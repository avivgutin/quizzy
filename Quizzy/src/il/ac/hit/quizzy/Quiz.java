package il.ac.hit.quizzy;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Quiz implements IQuiz {
    private String name;
    private List<IQuizQuestion> questions = new ArrayList<>();

    @Override
    public void start() {
        // Create and show the quiz selection screen using SwingUtilities.invokeLater()
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                showPlatformSelection();
            }
        });
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

    // Method to show the platform selection screen
    private void showPlatformSelection() {
        JFrame frame = new JFrame("Quiz Platform Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new FlowLayout());

        JButton terminalButton = new JButton("Terminal Quiz");
        JButton guiButton = new JButton("GUI Quiz");

        frame.add(terminalButton);
        frame.add(guiButton);

        terminalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Start the terminal-based quiz
                IQuiz terminalQuiz = createTerminalQuiz();
                terminalQuiz.start();
                frame.dispose();
            }
        });

        guiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Start the GUI-based quiz
                IQuiz guiQuiz = createGUIQuiz();
                guiQuiz.start();
                frame.dispose();
            }
        });

        // Center the JFrame on the screen
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    // Method to create a terminal-based quiz
    private IQuiz createTerminalQuiz() {
        // Implement logic to create a terminal-based quiz with questions loaded from a file
        IQuizFilesDAO dao = SimpleCSVQuizFilesDAO.getInstance();
        try {
            IQuiz loadedQuiz = dao.loadQuizFromFile("quiz1.data");

            // Create a TerminalQuiz instance and set the questions
            return new TerminalQuiz("Quiz Demo", loadedQuiz.getQuestions());
        } catch (QuizException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
        return null;
    }

    // Method to create a GUI-based quiz
    private IQuiz createGUIQuiz() {
        // Implement logic to create a GUI-based quiz with questions loaded from a file
        IQuizFilesDAO dao = SimpleCSVQuizFilesDAO.getInstance();
        try {
            IQuiz loadedQuiz = dao.loadQuizFromFile("quiz1.data");

            // Create a GUIQuiz instance and set the questions
            return new GUIQuiz("Quiz Demo", loadedQuiz.getQuestions());
        } catch (QuizException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
        return null;
    }
}
