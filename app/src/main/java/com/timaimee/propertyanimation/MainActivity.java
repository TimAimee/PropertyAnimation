package com.timaimee.propertyanimation;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;

import java.util.Random;

public class MainActivity extends Activity {
    BarView barView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barView = (BarView) findViewById(R.id.fragment_home_sportview);
        setSport();

    }

    private void setSport() {
        int sportBarCount = 48;
        int[] sport = new int[48];
        for (int i = 0; i < sportBarCount; i++) {
            int stepCount = new Random().nextInt(50);
            sport[i] = stepCount;
        }
        barView.setData(sport);
        userObjectAnimator();
    }

    /**
     * use the ObjectAnimator,
     */
    private void userObjectAnimator() {
        ObjectAnimator om = ObjectAnimator.ofFloat(barView, "barProgress", 0f, 1f).setDuration(3000);
        om.setInterpolator(new AccelerateInterpolator());
        om.start();
    }

    /**
     * user the ValueAnimator, user less
     */
    private void userValueAnimator() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(1000);
        valueAnimator.setObjectValues(1f);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.setEvaluator(new TypeEvaluator<Float>() {
            // fraction = t / duration
            @Override
            public Float evaluate(float fraction, Float startValue, Float
                    endValue) {

                return fraction;
            }
        });

        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float progress = (Float) animation.getAnimatedValue();
                System.out.println(progress);
                barView.setBarProgress(progress);
            }
        });
    }

}
