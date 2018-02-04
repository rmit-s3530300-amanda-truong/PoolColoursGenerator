package com.struong.poolcolours.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.struong.poolcolours.Controller.PlayerAddController;
import com.struong.poolcolours.Model.GameManager;
import com.struong.poolcolours.Model.Player;
import com.struong.poolcolours.R;
import com.struong.poolcolours.ViewModel.PlayerAdapter;

import java.util.ArrayList;

/**
 * Created by Amanda on 4/02/2018.
 */

public class PlayerListActivity extends AppCompatActivity{

    private GameManager gm;
    private PlayerAddController controller;
    private String[] menuItems;
    private PlayerAdapter adapter;
    private AlertDialog.Builder alertBuilder;
    private Button btStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            gm = (GameManager) extras.get(getResources().getString(R.string.gm));
        }

        btStart = (Button) findViewById(R.id.btDone);
        if(gm.getGameType() == 0)
        {
            btStart.setText(getResources().getString(R.string.genCol));
        }
        else
        {
            btStart.setText(getResources().getString(R.string.genNum));
        }

        controller = new PlayerAddController(this, gm);
        adapter = new PlayerAdapter(this, R.layout.list_view, gm.getPlayerList());
        ListView lvPlayerList = (ListView) findViewById(R.id.list_view_add);
        lvPlayerList.setAdapter(adapter);
        registerForContextMenu(lvPlayerList);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(getResources().getString(R.string.options));
        menuItems = getResources().getStringArray(R.array.menu);
        for(int i=0; i < menuItems.length; i++)
        {
            menu.add(Menu.NONE, i, i, menuItems[i]);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String menuItemName = menuItems[menuItemIndex];
        int listPos = info.position;
        if(menuItemName.equals(menuItems[0]))
        {
            //edit player name
            LayoutInflater li = LayoutInflater.from(this);
            final View dialogView = li.inflate(R.layout.addplayer_dialog, null);
            alertBuilder = new AlertDialog.Builder(this);
            alertBuilder.setView(dialogView);
            alertBuilder.setTitle(getResources().getString(R.string.addP));
            final EditText et = (EditText) dialogView.findViewById(R.id.etAddPlayer);
            final Player p = gm.findPlayer(listPos);
            et.setText(p.getName());
            alertBuilder
                    .setNegativeButton(getResources().getString(R.string.cancel), null)
                    .setPositiveButton(getResources().getString(R.string.save), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if(et.getText().toString().length() != 0)
                            {
                                p.editName(et.getText().toString());
                                adapter.notifyDataSetChanged();
                                Log.i("asdlkfjsdlkf", gm.getPlayerList().toString());
                                Toast.makeText(getApplicationContext(), et.getText().toString() + " " + getResources().getString(R.string.player_saved), Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.invalidEmpty), Toast.LENGTH_SHORT).show();
                                //make it loop if name is empty
                            }
                        }
                    });
            alertBuilder.show();
        }
        else if(menuItemName.equals(menuItems[1]))
        {
            //remove player
            Player p = gm.findPlayer(listPos);
            gm.removePlayer(p);
            adapter.notifyDataSetChanged();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.addplayer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.action_add) 
        {
            if (gm.getGameType() == 0) 
            {
                if (gm.getPlayerList().size() == 8)
                {
                    Toast.makeText(this, getResources().getString(R.string.max8), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    showAdd();
                }
            } 
            else if (gm.getGameType() == 1) 
            {
                if (gm.getPlayerList().size() == 7)
                {
                    Toast.makeText(this, getResources().getString(R.string.max7), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    showAdd();
                }
            }
        }
        return true;
    }
    
    public void showAdd()
    {
        LayoutInflater li = LayoutInflater.from(this);
        final View dialogView = li.inflate(R.layout.addplayer_dialog, null);
        alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setView(dialogView);
        alertBuilder.setTitle(getResources().getString(R.string.addP));
        final EditText et = (EditText) dialogView.findViewById(R.id.etAddPlayer);
        alertBuilder
                .setNegativeButton(getResources().getString(R.string.cancel), null)
                .setPositiveButton(getResources().getString(R.string.addP), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(et.getText().toString().length() != 0)
                        {
                            gm.addPlayer(new Player(et.getText().toString()));
                            adapter.notifyDataSetChanged();
                            Log.i("asdlkfjsdlkf", gm.getPlayerList().toString());
                            Toast.makeText(getApplicationContext(), et.getText().toString() + " " + getResources().getString(R.string.player_added), Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.invalidEmpty), Toast.LENGTH_SHORT).show();
                            //make it loop if name is empty
                        }
                    }
                });
        alertBuilder.show();
    }

    @Override
    protected void onRestart() {
        Log.i("resume", "onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i("resume", "onResume()");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.i("start", "onStart()");
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.i("pause", "onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("stop", "onStop()");
        super.onStop();
    }
}
