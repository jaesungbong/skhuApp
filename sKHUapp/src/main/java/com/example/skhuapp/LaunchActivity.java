package com.example.skhuapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class LaunchActivity extends Activity {
	
	private int i=0;
	private TextView myi;
	private ImageView imageView;
	
    Handler handler = new Handler() {
		 public void handleMessage(android.os.Message msg) {
			 updateThread();
		 };
    };

	public static final int SPLASH_TIME_CHOICE = 1500;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);
		imageView = (ImageView)findViewById(R.id.imageView1);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent i = new Intent(LaunchActivity.this, MainActivity.class);
				startActivity(i);
				finish();
			}

		}, SPLASH_TIME_CHOICE);
	}
    @Override
    protected void onStart() {
        super.onStart();
 
        Thread myThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        handler.sendMessage(handler.obtainMessage());
                        Thread.sleep(300);
                    } catch (Throwable t) {
                    }
                }
            }
        });
 
        myThread.start();
    }
 
    private void updateThread() {
 	
        	
        int mod = i % 25;
         
        switch (mod) {
        case 0:
            i++;
            imageView.setImageResource(R.drawable.skhu_0);
            break;
        case 1:
            i++;
            imageView.setImageResource(R.drawable.skhu_1);
            break;
        case 2:
            i++;
            imageView.setImageResource(R.drawable.skhu_2);
            break;
        case 3:
        	i++;
            imageView.setImageResource(R.drawable.skhu_3);
            break;
        case 4:
        	i++;
            imageView.setImageResource(R.drawable.skhu_4);
            break;
        case 5:
        	i++;
            imageView.setImageResource(R.drawable.skhu_5);
            break;
        case 6:
        	i++;
            imageView.setImageResource(R.drawable.skhu_6);
            break;
        case 7:
        	i++;
            imageView.setImageResource(R.drawable.skhu_7);
            break;
        case 8:
        	i++;
            imageView.setImageResource(R.drawable.skhu_8);
            break;
        case 9:
        	i++;
            imageView.setImageResource(R.drawable.skhu_9);
            break;
        case 10:
        	i++;
            imageView.setImageResource(R.drawable.skhu_10);
            break;
        case 11:
        	i++;
            imageView.setImageResource(R.drawable.skhu_11);
            break;
        case 12:
        	i++;
            imageView.setImageResource(R.drawable.skhu_12);
            break;
        case 13:
        	i++;
            imageView.setImageResource(R.drawable.skhu_13);
            break;
        case 14:
        	i++;
            imageView.setImageResource(R.drawable.skhu_14);
            break;
        case 15:
        	i++;
            imageView.setImageResource(R.drawable.skhu_15);
            break;
        case 16:
        	i++;
            imageView.setImageResource(R.drawable.skhu_16);
            break;
        case 17:
        	i++;
            imageView.setImageResource(R.drawable.skhu_17);
            break;
        case 18:
        	i++;
            imageView.setImageResource(R.drawable.skhu_18);
            break;
        case 19:
        	i++;
            imageView.setImageResource(R.drawable.skhu_19);
            break;
        case 20:
        	i++;
            imageView.setImageResource(R.drawable.skhu_20);
            break;
        case 21:
        	i++;
            imageView.setImageResource(R.drawable.skhu_21);
            break;
        case 22:
        	i++;
            imageView.setImageResource(R.drawable.skhu_22);
            break;
        case 23:
        	i++;
            imageView.setImageResource(R.drawable.skhu_23);
            break;
        case 24:
        	i++;
            imageView.setImageResource(R.drawable.skhu_24);
            break;
        case 25:
        	i++;
            imageView.setImageResource(R.drawable.skhu_25);
            break;
       /* case 26:
        	i++;
            imageView.setImageResource(R.drawable.skhu_26);
            break;
        case 27:
        	i++;
            imageView.setImageResource(R.drawable.skhu_27);
            break;
        case 28:
        	i++;
            imageView.setImageResource(R.drawable.skhu_28);
            break;
        case 29:
        	i++;
            imageView.setImageResource(R.drawable.skhu_29);
            break;*/
       /* case 30:
        	i++;
            imageView.setImageResource(R.drawable.skhu_30);
            break;*/
        /*case 31:
        	i++;
            imageView.setImageResource(R.drawable.skhu_31);
            break;
        case 32:
        	i++;
            imageView.setImageResource(R.drawable.skhu_32);
            break;
        case 33:
        	i++;
            imageView.setImageResource(R.drawable.skhu_33);
            break;
        case 34:
        	i++;
            imageView.setImageResource(R.drawable.skhu_34);
            break;
        case 35:
        	i++;
            imageView.setImageResource(R.drawable.skhu_35);
            break;
        case 36:
        	i++;
            imageView.setImageResource(R.drawable.skhu_36);
            break;
        case 37:
        	i++;
            imageView.setImageResource(R.drawable.skhu_37);
            break;
        case 38:
        	i++;
            imageView.setImageResource(R.drawable.skhu_38);
            break;
        case 39:
        	i++;
            imageView.setImageResource(R.drawable.skhu_39);
            break;
      
     */
        
            
            
        }
//        myi.setText(String.valueOf(i));
    }

}
