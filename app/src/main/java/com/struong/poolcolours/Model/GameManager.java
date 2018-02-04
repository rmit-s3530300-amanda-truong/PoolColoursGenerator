package com.struong.poolcolours.Model;

import android.content.res.Resources;
import android.widget.ArrayAdapter;

import com.struong.poolcolours.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Amanda on 3/02/2018.
 */

public class GameManager implements Serializable{
    private PlayerManager playerManager;
    private ArrayList<Player> playerList;
    private ArrayList<Colours> colList;
    private ArrayList<Integer> numList;
    private int gameType;

    public GameManager()
    {
        playerManager = new PlayerManager();
    }

    public void addPlayer(Player player)
    {
        playerManager.addPlayer(player);
    }

    public void removePlayer(Player player)
    {
        playerManager.removePlayer(player);
    }

    public void assignGameType(int type)
    {
        gameType = type;
    }

    public void genColList()
    {
        colList = new ArrayList<>();
        colList.add(new Colours(Colours.Colour.YELLOW));
        colList.add(new Colours(Colours.Colour.BLUE));
        colList.add(new Colours(Colours.Colour.RED));
        colList.add(new Colours(Colours.Colour.PURPLE));
        colList.add(new Colours(Colours.Colour.ORANGE));
        colList.add(new Colours(Colours.Colour.GREEN));
        colList.add(new Colours(Colours.Colour.BROWN));
        colList.add(new Colours(Colours.Colour.BLACK));
    }

    public void genNumList()
    {
        numList = new ArrayList<>();
        numList.add(1);
        numList.add(2);
        numList.add(3);
        numList.add(4);
        numList.add(5);
        numList.add(6);
        numList.add(7);
        numList.add(8);
        numList.add(9);
        numList.add(10);
        numList.add(11);
        numList.add(12);
        numList.add(13);
        numList.add(14);
        numList.add(15);
    }

    public void assignVariable()
    {
        Random random = new Random();
        playerList = playerManager.getList();
        for(Player p: playerList)
        {
            //colours
            if(gameType == 0)
            {
                Colours randCol = colList.get(random.nextInt(colList.size()));
                p.assignColour(randCol);
                colList.remove(randCol);
            }
            //numbers
            else if(gameType == 1)
            {
                //not sure if indexing will be wrong
                int randInt = numList.get(random.nextInt(numList.size()));
                numList.remove(randInt);
                int randInt2 = numList.get(random.nextInt(numList.size()));
                p.assignNums(randInt, randInt2);
                numList.remove(randInt2);
            }
        }
        for(Player p: playerList)
        {
            //colours
            if(gameType == 0)
            {
                System.out.println(p.getName() + p.getColour());
            }
            //numbers
            else if(gameType == 1)
            {
                System.out.println(p.getName() + p.getChosenNumbers());
            }
        }
    }

    public ArrayList<Player> getPlayerList()
    {
        return playerManager.getList();
    }
}
