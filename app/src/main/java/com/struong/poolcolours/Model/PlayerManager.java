package com.struong.poolcolours.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Amanda on 3/02/2018.
 */

public class PlayerManager implements Serializable{

    public ArrayList<Player> playerList;

    public PlayerManager()
    {
        if(playerList == null)
        {
            playerList = new ArrayList<>();
        }
    }

    public void addPlayer(Player player)
    {
        playerList.add(player);
    }

    public void removePlayer(Player player)
    {
        playerList.remove(player);
    }

    public ArrayList<Player> getList()
    {
        return playerList;
    }

    public Player findPlayer(int index)
    {
        return playerList.get(index);
    }
}
