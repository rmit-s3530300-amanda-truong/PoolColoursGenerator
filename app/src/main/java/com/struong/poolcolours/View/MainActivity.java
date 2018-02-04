package com.struong.poolcolours.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.struong.poolcolours.Controller.GameTypeController;
import com.struong.poolcolours.Model.GameManager;
import com.struong.poolcolours.R;

public class MainActivity extends AppCompatActivity {

    private Button btColours;
    private Button btNumbers;
    private GameTypeController controller;
    private GameManager gm;
    private String LOG_TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btColours = (Button) findViewById(R.id.btColours);
        btNumbers = (Button) findViewById(R.id.btNumbers);

        Bundle extras = getIntent().getExtras();
        if(extras!= null)
        {
            gm = (GameManager) extras.get(getResources().getString(R.string.gm));
            if(gm == null)
            {
                gm = new GameManager();
            }
        }
        else
        {
            gm = new GameManager();
        }

        controller = new GameTypeController(this, gm);
        btColours.setOnClickListener(controller);
        btNumbers.setOnClickListener(controller);
    }
}
