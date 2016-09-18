package com.timaimee.propertyanimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2015/8/7.
 */
public class BarView extends View {
	private final static String TAG = "BarView";
	private Paint mBarPaint, mTextPaint, mNullDataPaint;
	private float mBarWidth = 10f, mBarMaginLeft = 10f, mBarMaxHeight = 150f, mBarMaginTop = 10f;
	private float mWidth, mHeight;
	private float mTextHeight = 15f, mCirlceRaduis = 5f;
	private float roundWidthX = 5f, roundWidthY = roundWidthX;
	private int mBarCount = 24 * 60 / 30;
	private int[] mStepArray;
	private int mStepMaxValue;
	private float mBarProgress;

	public BarView(Context context) {
		this(context, null);

	}

	public BarView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		initPiant();
	}

	private void initPiant() {
		int color = getResources().getColor(R.color.app_color_helper_one);
		mBarPaint = new Paint();
		mBarPaint.setColor(color);
		mBarPaint.setStrokeCap(Cap.ROUND);
		mBarPaint.setStrokeWidth(5);
		mBarPaint.setAntiAlias(true);

		mTextPaint = new Paint();
		mTextPaint.setColor(color);
		mTextPaint.setStyle(Paint.Style.FILL);
		mTextPaint.setStrokeWidth(2);
		mTextPaint.setTextSize(20);
		mTextPaint.setAntiAlias(true);

		mNullDataPaint = new Paint();
		mNullDataPaint.setColor(color);
		mNullDataPaint.setStyle(Paint.Style.FILL);
		mNullDataPaint.setStrokeWidth(15);
		mNullDataPaint.setTextSize(40);
		mNullDataPaint.setAntiAlias(true);
	}

	public void setNullPaintColor(int color) {
		mNullDataPaint.setColor(color);
		mTextPaint.setColor(color);
		mBarPaint.setColor(color);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		mWidth = MeasureSpec.getSize(widthMeasureSpec);
		mHeight = MeasureSpec.getSize(heightMeasureSpec);

		mBarWidth = mWidth / mBarCount / 3 * 2;
		mBarMaginLeft = mWidth / mBarCount / 3;

		mBarMaxHeight = mHeight - mBarMaginTop * 2 - mTextHeight;
	}

	public void setData(int[] step) {
		mStepArray = null;
		mStepMaxValue = 0;
		if (step != null && step.length != 0) {
			this.mStepArray = step;
			for (int i = 0; i < step.length; i++) {
				if (step[i] > mStepMaxValue) {
					mStepMaxValue = step[i];
				}
			}
		}
		invalidate();
	}

	public void setBarProgress(float barProgress) {
		mBarProgress = barProgress;
		invalidate();
	}

	public void onDraw(final Canvas canvas) {

		if (null == mStepArray || mStepArray.length == 0) {
			// drawText(canvas);
			// drawNodata(canvas);
			return;
		}
		int count = mStepArray.length;
		for (int i = 0; i < mBarCount*mBarProgress; i++) {
			if (i < count) {

				float maginTop = getMaginTop(mStepArray[i]);
				RectF recf = new RectF((mBarWidth + mBarMaginLeft) * i, mBarMaxHeight-(mBarMaxHeight-maginTop)*mBarProgress  + mBarMaginTop,
						mBarWidth * (i + 1) + mBarMaginLeft * i, mBarMaxHeight + mBarMaginTop);
				canvas.drawRoundRect(recf, roundWidthX, roundWidthY, mBarPaint);

			}
		}
		drawText(canvas);

	}

	public void drawNodata(Canvas canvas) {
		String str = "ÎÞÊý¾Ý";
		Rect bounds = new Rect();
		mNullDataPaint.getTextBounds(str, 0, str.length(), bounds);
		canvas.drawText(str, (mWidth - bounds.width()) / 2, (mHeight - bounds.height()) / 2, mNullDataPaint);
	}

	private void drawText(Canvas canvas) {
		String str = "00:00";
		Rect bounds = new Rect();
		mTextPaint.getTextBounds(str, 0, str.length(), bounds);
		for (int i = 0; i < 9; i++) {
			if (i == 0) {
				canvas.drawText("00:00", (mWidth - bounds.width()) / 8 * i, mHeight - mTextHeight + bounds.height() / 2,
						mTextPaint);
			} else if (i == 8) {
				canvas.drawText("24:00", (mWidth - bounds.width()) / 8 * i - 10,
						mHeight - mTextHeight + bounds.height() / 2, mTextPaint);
			} else if (i == 2) {
				canvas.drawText("06:00", (mWidth - bounds.width()) / 8 * i, mHeight - mTextHeight + bounds.height() / 2,
						mTextPaint);
			} else if (i == 4) {
				canvas.drawText("12:00", (mWidth - bounds.width()) / 8 * i, mHeight - mTextHeight + bounds.height() / 2,
						mTextPaint);
			} else if (i == 6) {
				canvas.drawText("18:00", (mWidth - bounds.width()) / 8 * i, mHeight - mTextHeight + bounds.height() / 2,
						mTextPaint);
			} else if (i == 1) {
				canvas.drawCircle((mWidth - bounds.width()) / 8 + 5 + bounds.width() / 2, mHeight - mTextHeight,
						mCirlceRaduis, mTextPaint);
			} else {
				canvas.drawCircle((mWidth - bounds.width()) / 8 * i + bounds.width() / 2, mHeight - mTextHeight,
						mCirlceRaduis, mTextPaint);
			}
		}
	}

	private float getMaginTop(float step) {
		float magintop = 0;
		magintop = mBarMaxHeight - step / mStepMaxValue * mBarMaxHeight;
		// Logger.t(TAG).i("step=" + step + ",mStepMaxValue=" + mStepMaxValue +
		// ",mBarMaxHeight=" + mBarMaxHeight + ",magintop=" + magintop);
		return magintop;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}
}