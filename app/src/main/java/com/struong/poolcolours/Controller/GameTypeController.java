package com.struong.poolcolours.Controller;

import android.content.Intent;
import android.view.View;

import com.struong.poolcolours.View.MainActivity;
import com.struong.poolcolours.Model.GameManager;
import com.struong.poolcolours.R;
import com.struong.poolcolours.View.PlayerListActivity;

/**
 * Created by Amanda on 3/02/2018.
 */

public class GameTypeController implements View.OnClickListener{

    private MainActivity activity;
    private GameManager gm;
    private String LOG_TAG = this.getClass().getName();

    public GameTypeController(MainActivity main, GameManager gm)
    {
        this.activity = main;
        this.gm = gm;
    }

    @Override
    public void onClick(View view) {
        //colours = 8 max players
        //numbers = 7 max players
        int clicked = view.getId();
        if(clicked == R.id.btColours)
        {
            gm.assignGameType(0);
        }
        else if(clicked == R.id.btNumbers)
        {
            gm.assignGameType(1);
        }
        Intent intent = new Intent(activity, PlayerListActivity.class);
        intent.putExtra(activity.getResources().getString(R.string.gm), gm);
        activity.startActivity(intent);
    }
}
