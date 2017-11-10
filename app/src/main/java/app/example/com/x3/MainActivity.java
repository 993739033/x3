package app.example.com.x3;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.PointF;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView huaji;
    ax3_bx3_cx_d customView;
    PointF pointF;
    ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        huaji = (ImageView) findViewById(R.id.iv_huaji);
        customView = (ax3_bx3_cx_d) findViewById(R.id.customView);
        pointF=new PointF(customView.getX(),customView.getY());
    }

    public void beClick(View view) {
        if (objectAnimator!=null&&objectAnimator.isRunning())return;
         objectAnimator= ObjectAnimator.ofFloat(huaji, "translationX", 0, 420);
        objectAnimator.setDuration(2000);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        CustomInterpolator interpolator = new CustomInterpolator();
        interpolator.setPoints(customView.getPoints());
        objectAnimator.setInterpolator(interpolator);
        objectAnimator.setRepeatCount(1);
        objectAnimator.start();
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
}
