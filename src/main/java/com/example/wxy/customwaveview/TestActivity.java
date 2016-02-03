package com.example.wxy.customwaveview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

/**
 * Created by WangXY on 2016/2/1.
 */
public class TestActivity extends Activity {
    Button bt, bt1;
    CustomWaveView cwv;
    private Handler mHandler;
    int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        bt = (Button) findViewById(R.id.bt);
        bt1 = (Button) findViewById(R.id.bt1);
        cwv = (CustomWaveView) findViewById(R.id.cwv);
        mHandler = new Handler();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cwv.setVisibility(View.VISIBLE);
                cwv.setHeight(0);
                cwv.getLayoutParams().height = 0;
                cwv.showView(true);
                if (height != 0)
                    mHandler.removeCallbacks(stop);
                height = 0;
                mHandler.postDelayed(start, 5);
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.removeCallbacks(start);
                mHandler.postDelayed(stop, 5);
            }
        });
    }

    private Runnable stop = new Runnable() {
        @Override
        public void run() {
            if (height >= 0)
                --height;
            cwv.getLayoutParams().height = height * 2;
            cwv.setHeight(height);
            mHandler.postDelayed(stop, 10);
            if (height == 0) {
                cwv.setHeight(0);
                cwv.getLayoutParams().height = 0;
                cwv.showView(false);
                cwv.setVisibility(View.INVISIBLE);
            }
        }
    };

    private Runnable start = new Runnable() {
        @Override
        public void run() {
            if (height < 24)
                ++height;
            cwv.getLayoutParams().height = height * 2;
            cwv.setHeight(height);
            mHandler.postDelayed(start, 10);
        }
    };

}
