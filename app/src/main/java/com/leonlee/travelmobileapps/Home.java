package com.leonlee.travelmobileapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.transition.Slide;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.rxanimation.RxAnimation;
import com.mikhaellopez.rxanimation.RxViewAnimationExtensionKt;

public class Home extends AppCompatActivity {

    private ImageView iv1, iv2, iv3;
    private ConstraintLayout root_home;
    private LinearLayout ll_title;
    private ScrollView sv_content;

    private float mTouchPosition;
    private float mReleasePosition;
    private boolean isMoving = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        root_home = findViewById(R.id.root_home);
        ll_title = findViewById(R.id.ll_title);
        sv_content = findViewById(R.id.sv_content);

        Glide.with(this).load("https://o.aolcdn.com/images/dims?crop=1253%2C834%2C3%2C0&quality=85&format=jpg&resize=640%2C426&image_uri=http%3A%2F%2Fo.aolcdn.com%2Fhss%2Fstorage%2Fmidas%2Fe2673f7a001badd2dac4e874ff4a63ea%2F202427659%2F499003003.jpg&client=a1acac3e1b3290917d92&signature=2a403fcf202531618b5d6e6acbc768ccf0d203b0").into(iv1);
        Glide.with(this).load("https://o.aolcdn.com/images/dims?crop=4965%2C3305%2C206%2C0&quality=85&format=jpg&resize=640%2C426&image_uri=http%3A%2F%2Fo.aolcdn.com%2Fhss%2Fstorage%2Fmidas%2F8efe542a6f2c5921cc2e69e2d3b54880%2F204906839%2F497347635.jpg&client=a1acac3e1b3290917d92&signature=f1e0be04017814d31f7bb7165eadf037e03840b5").into(iv2);
        Glide.with(this).load("https://o.aolcdn.com/images/dims?crop=5616%2C3738%2C0%2C2&quality=85&format=jpg&resize=640%2C426&image_uri=http%3A%2F%2Fo.aolcdn.com%2Fhss%2Fstorage%2Fmidas%2F4a5e2cb4f4288b84b2248103a3a56cb%2F203524460%2Fstock-photo-breathtaking-view-in-the-plitvice-lakes-national-park-croatia-65988727.jpg&client=a1acac3e1b3290917d92&signature=82935e63c9640c54d08c8cf0635e29a0a61d8693").into(iv3);

        sv_content.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            float y = 0;

            @Override
            public void onScrollChanged() {
                if(isMoving){
                    return;
                }
                if (sv_content.getScrollY() > y) {
                    if(ll_title.getVisibility() == View.VISIBLE){
                        isMoving = true;
                        Log.v("Message", "Scrolls Down");
                        slideUp(ll_title);
                    }
                } else {
                    if(ll_title.getVisibility() == View.INVISIBLE){
                        isMoving = true;
                        Log.v("Message", "Scrolls Up");
                        slideDown(ll_title);
                    }
                }
                y = sv_content.getScrollY();
            }
        });
    }

    // slide the view from below itself to the current position
    public void slideUp(View view){
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,  // fromYDelta
                -1000);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        animate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isMoving = false;
                ll_title.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animate);
    }

    // slide the view from its current position to below itself
    public void slideDown(View view){
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                -1000,                 // fromYDelta
                0); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        animate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                ll_title.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isMoving = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animate);
    }


}
