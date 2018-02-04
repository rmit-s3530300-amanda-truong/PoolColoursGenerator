package com.struong.poolcolours.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Amanda on 3/02/2018.
 */

public class Player implements Serializable{

    private String name;
    private int winCount;
    private Colours chosenColour;
    private ArrayList<Integer> chosenNumbers;

    public Player(String name)
    {
        this.name = name;
        winCount = 0;
        chosenNumbers = new ArrayList<>();
    }

    public void assignColour(Colours attr)
    {
        chosenColour = attr;
    }

    public void assignNums(int num1, int num2)
    {
        chosenNumbers.add(num1);
        chosenNumbers.add(num2);
    }

    public void clearNums()
    {
        chosenNumbers.clear();
    }

    public void updateWinCount()
    {
        winCount++;
    }

    public String getName()
    {
        return name;
    }

    public Colours getColour()
    {
        return chosenColour;
    }

    public ArrayList<Integer> getChosenNumbers()
    {
        return chosenNumbers;
    }

    public int getWinCount()
    {
        return winCount;
    }
}
