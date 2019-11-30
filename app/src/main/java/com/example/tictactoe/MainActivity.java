package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private int mBoard[] = new int[9];
    private static final Random random = new Random();
    public int Pmove,Cmove;
    public String WinTxt;
    public char CurrentPlayer = 'X',Gstat = 'M',Winner;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn0 = findViewById(R.id.btn0),
                btn1 = findViewById(R.id.btn1),
                btn2 = findViewById(R.id.btn2),
                btn3 = findViewById(R.id.btn3),
                btn4 = findViewById(R.id.btn4),
                btn5 = findViewById(R.id.btn5),
                btn6 = findViewById(R.id.btn6),
                btn7 = findViewById(R.id.btn7),
                btn8 = findViewById(R.id.btn8);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);

        Button NewGame = findViewById(R.id.btnNGame);
        NewGame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View V)
            {
                Button btn0 = findViewById(R.id.btn0),
                        btn1 = findViewById(R.id.btn1),
                        btn2 = findViewById(R.id.btn2),
                        btn3 = findViewById(R.id.btn3),
                        btn4 = findViewById(R.id.btn4),
                        btn5 = findViewById(R.id.btn5),
                        btn6 = findViewById(R.id.btn6),
                        btn7 = findViewById(R.id.btn7),
                        btn8 = findViewById(R.id.btn8);

                btn0.setText("");
                btn1.setText("");
                btn2.setText("");
                btn3.setText("");
                btn4.setText("");
                btn5.setText("");
                btn6.setText("");
                btn7.setText("");
                btn8.setText("");
                TextView T = findViewById(R.id.txtViewStatus);
                T.setText("");
                CurrentPlayer = 'X';
                Gstat = 'M';
                WinTxt = "";
                Arrays.fill(mBoard, 0 );
            }
        });
    }

    @Override
    public void onClick(View V)
    {
        if (Gstat == 'M' && CurrentPlayer == 'X')
        {
            switch (V.getId())
            {
                case R.id.btn0:
                    Pmove = 0;
                    break;
                case R.id.btn1:
                    Pmove = 1;
                    break;
                case R.id.btn2:
                    Pmove = 2;
                    break;
                case R.id.btn3:
                    Pmove = 3;
                    break;
                case R.id.btn4:
                    Pmove = 4;
                    break;
                case R.id.btn5:
                    Pmove = 5;
                    break;
                case R.id.btn6:
                    Pmove = 6;
                    break;
                case R.id.btn7:
                    Pmove = 7;
                    break;
                case R.id.btn8:
                    Pmove = 8;
                    break;
            }
            PlayButtonClick(V);
        }
    }
    public void getComputerMove(View v)
    {
        CurrentPlayer = 'X';
        int position ;
        do
        {
            position = random.nextInt(9);
        }while (mBoard[position] != 0);
        mBoard[position] = 2;

        if (Gstat == 'T')
        {
            WinTxt = "It's a tie";
            setStatus();
        }
        else if(Gstat == 'M')
        {
            setPlayerMove("O",v,position);
            checkForWinner();
        }
    }
    public void setStatus( )
    {
        TextView txtView = (TextView)findViewById(R.id.txtViewStatus);
        txtView.setText(WinTxt);
        /*GridLayout Glayout = (GridLayout)findViewById(R.id.GrdLayout);
        Glayout.setEnabled(false)*/;
        return;
    }
    public void setPlayerMove(CharSequence move, View v, int po)
    {
        if ( move == "X")
        {
            Button button = (Button)findViewById(v.getId());
            button.setText(move);
            button.setTextColor(getResources().getColor(R.color.Xcolor));
        }
        else if(move == "O")
        {
            Button btn;
            switch (po)
            {
                case 0:
                    btn = (Button)findViewById(R.id.btn0);
                    break;
                case 1:
                    btn = (Button)findViewById(R.id.btn1);
                    break;
                case 2:
                    btn = (Button)findViewById(R.id.btn2);
                    break;
                case 3:
                    btn = (Button)findViewById(R.id.btn3);
                    break;
                case 4:
                    btn = (Button)findViewById(R.id.btn4);
                    break;
                case 5:
                    btn = (Button)findViewById(R.id.btn5);
                    break;
                case 6:
                    btn = (Button)findViewById(R.id.btn6);
                    break;
                case 7:
                    btn = (Button)findViewById(R.id.btn7);
                    break;
                case 8:
                    btn = (Button)findViewById(R.id.btn8);
                    break;
                default:
                    throw new IllegalStateException("Wrong Choice" + po);
            }
            btn.setText(move);
            btn.setTextColor(getResources().getColor(R.color.Ocolor));
        }


    }
    public void PlayButtonClick(View V)
    {
         if (mBoard[Pmove] == 0)
         {
             mBoard[Pmove] = 1;
             setPlayerMove("X", V, 0);
             checkForWinner();
             if (Gstat == 'T')
             {
                 setStatus();
             }
             if (Gstat == 'M')
             {
                 CurrentPlayer = 'C';
                 getComputerMove(V);
             }
         }
    }
    public void checkForWinner()
    {
        int i;
        if ( (mBoard[0] == 1 && mBoard[1] == 1 && mBoard[2] == 1) ||
             (mBoard[3] == 1 && mBoard[4] == 1 && mBoard[5] == 1) ||
             (mBoard[6] == 1 && mBoard[7] == 1 && mBoard[8] == 1) ||
             (mBoard[0] == 1 && mBoard[3] == 1 && mBoard[6] == 1) ||
             (mBoard[1] == 1 && mBoard[4] == 1 && mBoard[7] == 1) ||
             (mBoard[2] == 1 && mBoard[5] == 1 && mBoard[8] == 1) ||
             (mBoard[2] == 1 && mBoard[4] == 1 && mBoard[6] == 1) ||
             (mBoard[0] == 1 && mBoard[4] == 1 && mBoard[8] == 1) )
        {
            Gstat = 'W';
            Winner = 'P';
            WinTxt = "You Won!";
            setStatus();
        }
        else if( (mBoard[0] == 2 && mBoard[1] == 2 && mBoard[2] == 2) ||
                 (mBoard[3] == 2 && mBoard[4] == 2 && mBoard[5] == 2) ||
                 (mBoard[6] == 2 && mBoard[7] == 2 && mBoard[8] == 2) ||
                 (mBoard[0] == 2 && mBoard[3] == 2 && mBoard[6] == 2) ||
                 (mBoard[1] == 2 && mBoard[4] == 2 && mBoard[7] == 2) ||
                 (mBoard[2] == 2 && mBoard[5] == 2 && mBoard[8] == 2) ||
                 (mBoard[2] == 2 && mBoard[4] == 2 && mBoard[6] == 2) ||
                 (mBoard[0] == 2 && mBoard[4] == 2 && mBoard[8] == 2) )
        {
            Gstat = 'W';
            Winner = 'C';
            WinTxt = "Computer Won!";

            setStatus();
        }
        else
        {

            for( i = 0 ; i < mBoard.length; i++)
            {
                if (mBoard[i] == 0)
                {
                    Gstat = 'M';
                    break;
                }
            }
            if (i == mBoard.length)
            {
                Gstat = 'T';
                WinTxt = "It's a Tie";
            }
        }

    }
}
