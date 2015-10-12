package test;

import android.test.InstrumentationTestCase;
import com.example.fey.cityquiz.Quiz;
import com.example.fey.cityquiz.Question;

/**
 * Created by Ryan on 10/12/15.
 */
public class QuizTest extends InstrumentationTestCase {

    Question question1;
    Quiz quiz1;

    @Override
    protected void setUp() throws Exception{
        super.setUp();
        Quiz testQuiz = new Quiz();
        testQuestion();
        testQuiz();
    }

    private void testQuiz() {
        quiz1 = new Quiz();


        assertTrue(quiz1.isEmpty());
        assertTrue(quiz1.isEmpty());
        quiz1.AddQuestion(question1);
        assertFalse(quiz1.isEmpty());


        quiz1.AddQuestion(question1);
        quiz1.CurrentQuestionIndex++;
        assertEquals(question1, quiz1.ReturnQuestion());

        assertEquals(0, quiz1.CorrectAnswered);
        quiz1.CheckAnswer(question1.correctAnswer);
        assertEquals(1, quiz1.CorrectAnswered);
        assertEquals(0, quiz1.WrongAnswered);
        quiz1.CheckAnswer("wrong answer");
        assertEquals(1, quiz1.WrongAnswered);

    }

    public void testQuestion() {
        String question = "This is the question";
        String answer1 = "answer1";
        String answer2 = "answer2";
        String answer3 = "answer3";
        String answer4 = "answer4";
        String correctAnswer = "answer1";

        question1 = new Question(question, answer1, answer2, answer3, answer4, correctAnswer);

        //System.out.println(question1.button1());
        assertEquals("This is the question", question1.return_question());
        assertEquals("answer1", question1.button1());
        assertEquals("answer2", question1.button2());
        assertEquals("answer3", question1.button3());
        assertEquals("answer4", question1.button4());
        assertEquals("answer1", question1.correctAnswer);

        assertTrue(question1.isAnswer("answer1"));
        assertFalse(question1.isAnswer("answer2"));
    }


    @Override
    protected void tearDown() throws Exception{
        super.tearDown();
    }



}
