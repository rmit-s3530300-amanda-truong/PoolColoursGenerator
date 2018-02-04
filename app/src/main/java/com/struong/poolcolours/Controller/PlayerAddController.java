package com.struong.poolcolours.Controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.struong.poolcolours.Model.GameManager;
import com.struong.poolcolours.Model.Player;
import com.struong.poolcolours.R;
import com.struong.poolcolours.View.PlayerListActivity;
import com.struong.poolcolours.ViewModel.PlayerAdapter;

/**
 * Created by Amanda on 4/02/2018.
 */

public class PlayerAddController implements View.OnClickListener {

    private PlayerListActivity activity;
    private GameManager gm;
    private AlertDialog.Builder alertBuilder;

    public PlayerAddController(PlayerListActivity activity, GameManager gm)
    {
        this.activity = activity;
        this.gm = gm;
    }

    @Override
    public void onClick(View view) {

    }

    public void showAdd()
    {
        LayoutInflater li = LayoutInflater.from(activity);
        final View dialogView = li.inflate(R.layout.addplayer_dialog, null);
        alertBuilder = new AlertDialog.Builder(activity);
        alertBuilder.setView(dialogView);
        alertBuilder.setTitle(activity.getResources().getString(R.string.addP));
        final EditText et = (EditText) dialogView.findViewById(R.id.etAddPlayer);
        alertBuilder
                .setNegativeButton(activity.getResources().getString(R.string.cancel), null)
                .setPositiveButton(activity.getResources().getString(R.string.addP), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(et.getText().toString().length() != 0)
                        {
                            gm.addPlayer(new Player(et.getText().toString()));
                            Log.i("asdlkfjsdlkf", gm.getPlayerList().toString());
                            Toast.makeText(activity, et.getText().toString() + " " + activity.getResources().getString(R.string.player_added), Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(activity, activity.getResources().getString(R.string.invalidEmpty), Toast.LENGTH_SHORT).show();
                            //make it loop if name is empty
                        }
                    }
                });
        alertBuilder.show();
    }
}
