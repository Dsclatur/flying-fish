package com.example.flyingfish;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Flyingfishview extends View
{
   //declaration of various variables
    private Bitmap fish[]=new Bitmap[2];
    private int fishX=15;
    private int fishY;
    private int fishspeed;
    private int canvaswidth,canvasheight;
    private boolean touch=false;

    //declaration for yellow ball.
    private  int yellowx,yellowy,yellowspeed=25;
    private Paint yellowpaint=new Paint();

    //declaration for green ball.
    private int greenx,greeny,greenspeed=22;
    private Paint greenpaint=new Paint();

    //declaration of  blue ball.
    private int bluex,bluey,bluespeed=20;
    private Paint bluepaint=new Paint();

    //declaration for  red ball
    private int redx,redy,redspeed=35;
    private Paint redpaint=new Paint();

    private int score,lifecounteroffish;

    private Bitmap backgroundimage;
    private Paint scorepaint=new Paint();
    private Bitmap  life[]=new Bitmap[2];

//constructor
    public Flyingfishview(Context context) {
        super(context);

        backgroundimage=BitmapFactory.decodeResource(getResources(),R.drawable.background);
        fish[0]= BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        fish[1]= BitmapFactory.decodeResource(getResources(),R.drawable.fish2);

        //color setting to  yellow ball
       yellowpaint.setColor(Color.YELLOW);
       yellowpaint.setAntiAlias(false);
        //color setting to  green ball
       greenpaint.setColor(Color.GREEN);
        greenpaint.setAntiAlias(false);
        //color setting to  red ball
        redpaint.setColor(Color.RED);
        redpaint.setAntiAlias(false);
        //color setting to  blue ball
        bluepaint.setColor(Color.BLUE);
        bluepaint.setAntiAlias(false);

        //color setting to score while playing game.
        scorepaint.setColor(Color.WHITE);
       scorepaint.setTextSize(70);
       scorepaint.setTypeface(Typeface.DEFAULT_BOLD);
      scorepaint.setAntiAlias(true);

       life[0]= BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1]= BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);

        //initial position of fish in main activity,initial score and 3 lives
       fishY=500;
       score=0;
       lifecounteroffish=3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //code for the fish.
        canvaswidth=canvas.getWidth();
        canvasheight=canvas.getHeight()+250;
       int minfishY=fish[0].getWidth();
       int maxfishY=canvasheight-fish[0].getHeight()*3;

       fishY=fishY+fishspeed;

       if(fishY<minfishY)
       {
           fishY=minfishY;
       }
        if(fishY>maxfishY)
        {
            fishY=maxfishY;
        }
        fishspeed=fishspeed+4;
        canvas.drawBitmap(backgroundimage,0,0,null);
        if(touch)
        {
            canvas.drawBitmap(fish[1],fishX,fishY,null);
            touch=false;
        }
        else
        {
            canvas.drawBitmap(fish[0],fishX,fishY,null);
        }




     //code for the yellow ball.
      yellowx=yellowx-yellowspeed;
        if(hitballchecker(yellowx,yellowy))
        {
            score=score+10;
            yellowx=-100;
        }
        if(yellowx<0)
        {
            yellowx=canvaswidth+21;
            yellowy=(int)Math.floor(Math.random()*(maxfishY-minfishY))+minfishY;

        }
        canvas.drawCircle(yellowx,yellowy,25,yellowpaint);

        //code for the blue ball.
        bluex=bluex-bluespeed;
        if(hitballchecker(bluex,bluey))
        {
            score=score+10;
            bluex=-100;
        }
        if(bluex<0)
        {
            bluex=canvaswidth+21;
            bluey=(int)Math.floor(Math.random()*(maxfishY-minfishY))+minfishY;

        }
        canvas.drawCircle(bluex,bluey,25,bluepaint);

       //code for the green ball.
        greenx=greenx-greenspeed;
        if(hitballchecker(greenx,greeny))
        {
            score=score+20;
            greenx=-100;
        }
        if(greenx<0)
        {
            greenx=canvaswidth+21;
            greeny=(int)Math.floor(Math.random()*(maxfishY-minfishY))+minfishY;

        }
        canvas.drawCircle(greenx,greeny,25,greenpaint);

        // code for red ball
        redx=redx-redspeed;
        if(hitballchecker(redx,redy))
        {

            redx=-100;
            lifecounteroffish--;
            if(lifecounteroffish==0)
            {

                Toast.makeText(getContext(),"Game over",Toast.LENGTH_SHORT).show();
                Intent gameoverintent=new Intent(getContext(),GameoverActivity.class);
                gameoverintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameoverintent.putExtra("Score:",score);
                getContext().startActivity(gameoverintent);
            }
        }
        if(redx<0)
        {
            redx=canvaswidth+21;
            redy=(int)Math.floor(Math.random()*(maxfishY-minfishY))+minfishY;

        }
        canvas.drawCircle(redx,redy,35,redpaint);
        canvas.drawText("score :"+score,20,60,scorepaint);



        //code for 3 lives
        for (int i = 0; i <3 ; i++)
        {
         int x=(int)  (450 + life[0].getWidth()*1.5*i);
         int y=25;
         if(i<lifecounteroffish)
         {
             canvas.drawBitmap(life[0],x,y,null);
         }
         else
         {
             canvas.drawBitmap(life[1],x,y,null);
         }
        }




    }
    //following method checks whether the fish gets hit by the ball or not.
public  boolean hitballchecker(int x,int y)
{
    if(fishX<x && x<fishX+fish[0].getWidth() && fishY<y && y<fishY+fish[0].getHeight())
    {
       return true;
    }
    return false;
}


//the following  method executes when we touch on the screen.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()== MotionEvent.ACTION_DOWN)
        {
            touch=true;
            fishspeed=-25;

        }
        return true;
    }
}
