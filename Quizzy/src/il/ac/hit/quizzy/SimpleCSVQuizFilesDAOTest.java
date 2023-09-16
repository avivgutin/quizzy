package il.ac.hit.quizzy;

import org.junit.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.Assert.*;

public class SimpleCSVQuizFilesDAOTest {

    @Test
    public void testSaveAndLoadQuizToFile() {
        // Create a mock quiz with some questions
        IQuiz quiz = new Quiz();
        IQuizQuestion question1 = new QuizQuestion.Builder()
                .setQuestion("Question 1")
                .addAnswer("Answer 1", true)
                .addAnswer("Answer 2", false)
                .create();
        // Add more questions as needed
        quiz.addQuestion(question1);
        // Add more questions as needed

        // Define a temporary file name for testing
        String fileName = "testQuiz.data";

        // Use the SimpleCSVQuizFilesDAO to save the quiz to a file
        try {
            SimpleCSVQuizFilesDAO dao = (SimpleCSVQuizFilesDAO) SimpleCSVQuizFilesDAO.getInstance();
            dao.saveQuizToFile(quiz, fileName);

            // Check if the file was created
            File file = new File(fileName);
            assertTrue(file.exists());

            // Use FileReader and BufferedReader to read the saved file
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder fileContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
            reader.close();

            // Define the expected content based on your quiz format
            String expectedContent = "Question 1;Answer 1*,Answer 2\n"; // Adjust this based on your format

            // Use assertEquals to verify the expected and actual content
            assertEquals(expectedContent, fileContent.toString());

            // Clean up: delete the test file
            file.delete();
            assertFalse(file.exists());
        } catch (QuizException | IOException e) {
            // Handle exceptions if necessary
            fail("Exception thrown: " + e.getMessage());
        }
    }
}
