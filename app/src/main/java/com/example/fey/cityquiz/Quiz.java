package com.example.fey.cityquiz;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Fey on 2015/10/11.
 */
public class Quiz implements Serializable{

    private static final long serialVersaionUID = 1L;

    ArrayList<Question> QuestionList = new ArrayList<Question>();   //Question List
    public int CorrectAnswered;        //How many questions are answered correctly for now.
    public int WrongAnswered;
    public int CurrentQuestionIndex;

    //constructor
    public Quiz()
    {
        String test_q = "University of Pittsburgh is ";
        String test_a1 = "Food";
        String test_a2 = "Animal";
        String test_a3 = "Vehicle";
        String test_a4 = "University";
        String text_c = "University";
        Question test_question = new Question(test_q,test_a1,test_a2,test_a3,test_a4,text_c);
        QuestionList.add(test_question);


        CorrectAnswered = 0;
        CurrentQuestionIndex = 1;
        WrongAnswered = 0;
    }

    public void AddQuestion(Question q)
    {
        QuestionList.add(q);

        System.out.println("11111");
    }

    public int getCorrectAnswered(){
        return CorrectAnswered;
    }

    public int getWrongAnswered(){
        return WrongAnswered;
    }

    //return true if no questions left
    public boolean isEmpty()
    {
       if(CurrentQuestionIndex < QuestionList.size())
       {
          // CurrentQuestionIndex++;
           return false;
       }
       else
       {
           return true;
       }
    }

    //send question to users.
    public Question ReturnQuestion()
    {


        return QuestionList.get(CurrentQuestionIndex-1);


    }

    public void CheckAnswer(String answer)
    {
       if(QuestionList.get(CurrentQuestionIndex-1).isAnswer(answer))
       {
           CorrectAnswered++;
       }
       else
       {
           WrongAnswered++;
       }

    }



}
