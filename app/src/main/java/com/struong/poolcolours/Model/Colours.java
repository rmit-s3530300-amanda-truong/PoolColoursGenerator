package com.struong.poolcolours.Model;

/**
 * Created by Amanda on 3/02/2018.
 */

public class Colours {

    public enum Colour
    {
        YELLOW,
        BLUE,
        RED,
        PURPLE,
        ORANGE,
        GREEN,
        BROWN,
        BLACK
    }

    private Colour col;

    public Colours(Colour col)
    {
        this.col = col;
    }

    public Colour getCol()
    {
        return col;
    }
}
