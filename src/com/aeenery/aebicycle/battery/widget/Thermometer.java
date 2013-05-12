package com.aeenery.aebicycle.battery.widget;

import com.aeenery.aebicycle.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.BitmapFactory.Options;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Thermometer extends View {

	Bitmap thermometer;

	private float currentValue = 0;

	private static final float MIN_TEMP = -20f;
	private static final float MAX_TEMP = 120f;
	private static final int totalLevel = 75;
	
	private float level = 5;

	public Thermometer(Context context, AttributeSet attrs) {
		super(context, attrs);
		initThermometer();
	}

	public Thermometer(Context context) {
		super(context);
		initThermometer();
	}

	private void initThermometer() {
		Options opt = new Options();
		opt.inScaled = true;
		thermometer = BitmapFactory.decodeResource(getContext().getResources(),
				R.drawable.thermometer_only_empty, opt);
		setStartPosition();
		this.invalidate();
	}

	private void setStartPosition() {
		update(MIN_TEMP);
	}

	public void update(float value) {
		if (value < MIN_TEMP)
			update(MIN_TEMP);
		else if (value > MAX_TEMP)
			update(MAX_TEMP);
		else
			currentValue = value;
		level = 5;
		if(currentValue == 0f){
			level += 10;
		}else if(currentValue < 0){
			level += (20f + value)/2f;
		}else{
			level += currentValue/2f + 10f;
			this.invalidate();
		}
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
//		canvas.drawBitmap(thermometer, 0, 0, null);
		drawRegion(canvas, initRed());
		canvas.drawBitmap(thermometer, 0, 0, null);
	}

	private void drawRegion(Canvas canvas, int color) {
		Paint paint = new Paint();
        RectF rect = new RectF();

        float height = (float) (this.getHeight() * 0.9 - 22.5f);
        Log.e("Thermometer","Height" + height);
        float cylinderHeight = (float) (height - this.getHeight()*0.7*level/totalLevel) ;
        Log.e("Thermometer","cylinderHeight" + cylinderHeight);
        Log.e("Thermometer","level" + level);
        
        float widthleft = (float)(this.getWidth()*0.33);
        float widthright = (float)(this.getWidth()*0.61);
        rect.set(
        		widthleft,
        		cylinderHeight,
                widthright,
                height); 

        paint.setColor(color);
//        paint.setStrokeWidth(getResources().getDimension(R.dimen.stroke_width_thermo));
        paint.setAntiAlias(true);
//        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect, paint);
//        canvas.drawArc(rect, startConer, mainConer, false, paint);
	}

	private int initRed() {
		if (level == 0)
			return getResources().getColor(R.color.transpert);
		else if (level < 10)
			return getResources().getColor(R.color.red_75);
		else if (level < 60)
			return getResources().getColor(R.color.red_50);
		else
			return getResources().getColor(R.color.red);

	}

}
