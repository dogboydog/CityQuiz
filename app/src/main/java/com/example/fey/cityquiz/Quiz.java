package com.example.fey.cityquiz;

import java.util.ArrayList;

/**
 * Created by Fey on 2015/10/11.
 */
public class Quiz {

    ArrayList<Question> QuestionList = new ArrayList<Question>();   //Question List
    int CorrectAnswered;        //How many questions are answered correctly for now.


    //constructor
    public Quiz()
    {

    }

    //return true if no questions left
    public boolean isEmpty()
    {
        return true;
    }

    public void GenerateQuiz()
    {
        if(!isEmpty())
        {
            //send question to activity
        }
        else
        {
            //result page
        }


    }



}
