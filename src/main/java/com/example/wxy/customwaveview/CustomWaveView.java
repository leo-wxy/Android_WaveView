package com.example.wxy.customwaveview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;


/**
 * Created by John on 2014/10/15.
 */
public class CustomWaveView extends LinearLayout {

    private int mAboveWaveColor;
    private int mBlowWaveColor;
    private int mWaveHeight;
    private int mWaveLength;
    private int mWaveHz;

    private Wave mWave;
    private Context context;

    private final int DEFAULT_ABOVE_WAVE_COLOR = Color.parseColor("#f5f5f5");
    private final int DEFAULT_BLOW_WAVE_COLOR = Color.parseColor("#0086d0");
    private int waveHeight = 0;
    private CustomWaveView waveView;


    public CustomWaveView(Context context) {
        super(context);
    }

    public CustomWaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        this.context = context;
        waveView = this;
        final TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomWaveView, 0, 0);
        mAboveWaveColor = attributes.getColor(R.styleable.CustomWaveView_above_wave_color, DEFAULT_ABOVE_WAVE_COLOR);
        mBlowWaveColor = attributes.getColor(R.styleable.CustomWaveView_blow_wave_color, DEFAULT_BLOW_WAVE_COLOR);
        mWaveHeight = attributes.getInt(R.styleable.CustomWaveView_wave_height, 25);
        mWaveLength = attributes.getInt(R.styleable.CustomWaveView_wave_length, 2);
        mWaveHz = attributes.getInt(R.styleable.CustomWaveView_wave_hz, 10);
        attributes.recycle();

        mWave = new Wave(context, null);
        mWave.initializeWaveSize(mWaveLength, mWaveHeight, mWaveHz);
        mWave.setAboveWaveColor(mAboveWaveColor);
        mWave.setBlowWaveColor(mBlowWaveColor);
        mWave.initPaint();

        addView(mWave);
        waveHeight = mWave.getHeight();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
    }

    public void showView(boolean show) {
        mWave.showView(show);
    }

    public void setHeight(int height) {
        mWave.initializeWaveSize(mWaveLength, height, mWaveHz);
        mWave.initPaint();
        invalidate();
    }


}
