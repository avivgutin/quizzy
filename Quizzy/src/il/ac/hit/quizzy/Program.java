package il.ac.hit.quizzy;

public class Program {

    public static void main(String[] args) throws QuizException {
        // Creating a quiz
        QuizFactory factory = new QuizFactory();
        IQuiz quiz = factory.createQuiz(QuizType.GUI);
        quiz.setName("Quiz Demo");

        // Creating the 1st question
        IQuizQuestionBuilder builder1 = new QuizQuestion.Builder();
        builder1.setTitle("We Love Canada");
        builder1.setQuestion("Canada starts with…?");
        builder1.addAnswer("Canada starts with the letter 'A'.", false);
        builder1.addAnswer("Canada starts with the letter 'B'.", false);
        builder1.addAnswer("Canada starts with the letter 'C'.", true);
        builder1.addAnswer("Canada starts with the letter 'D'.", false);
        builder1.addAnswer("Canada starts with the letter 'E'.", false);
        IQuizQuestion question1 = builder1.create();

        // Creating the 2nd question
        IQuizQuestionBuilder builder2 = new QuizQuestion.Builder();
        builder2.setTitle("We Love Australia");
        builder2.setQuestion("Australia starts with…?");
        builder2.addAnswer("Australia starts with the letter 'A'.", true);
        builder2.addAnswer("Australia starts with the letter 'B'.", false);
        builder2.addAnswer("Australia starts with the letter 'C'.", false);
        builder2.addAnswer("Australia starts with the letter 'D'.", false);
        builder2.addAnswer("Australia starts with the letter 'E'.", false);
        IQuizQuestion question2 = builder2.create();

        // Adding questions to the quiz
        quiz.addQuestion(question1);
        quiz.addQuestion(question2);

        // Saving the quiz to a file and reading it back
        IQuizFilesDAO dao = SimpleCSVQuizFilesDAO.getInstance();
        dao.saveQuizToFile(quiz, "quiz1.data");
        IQuiz loadedQuiz = dao.loadQuizFromFile("quiz1.data");
        loadedQuiz.start();
    }
}
