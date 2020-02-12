package com.leonlee.travelmobileapps;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.transition.AutoTransition;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

public class Home extends AppCompatActivity {

    private ImageView iv1, iv2, iv3, iv4, iv5, iv_menu;
    private ConstraintLayout root_home;
    private LinearLayout ll_title, ll_content;
    private ScrollView sv_content;

    private boolean isMoving = false;
    private boolean isAtOrigin = true;
    private boolean isScrolling = false;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        iv4 = findViewById(R.id.iv4);
        iv5 = findViewById(R.id.iv5);
        iv_menu = findViewById(R.id.iv_menu);
        root_home = findViewById(R.id.root_home);
        ll_title = findViewById(R.id.ll_title);
        ll_content = findViewById(R.id.ll_content);
        sv_content = findViewById(R.id.sv_content);

        Glide.with(this).load("https://o.aolcdn.com/images/dims?crop=1253%2C834%2C3%2C0&quality=85&format=jpg&resize=640%2C426&image_uri=http%3A%2F%2Fo.aolcdn.com%2Fhss%2Fstorage%2Fmidas%2Fe2673f7a001badd2dac4e874ff4a63ea%2F202427659%2F499003003.jpg&client=a1acac3e1b3290917d92&signature=2a403fcf202531618b5d6e6acbc768ccf0d203b0").into(iv1);
        Glide.with(this).load("https://o.aolcdn.com/images/dims?crop=4965%2C3305%2C206%2C0&quality=85&format=jpg&resize=640%2C426&image_uri=http%3A%2F%2Fo.aolcdn.com%2Fhss%2Fstorage%2Fmidas%2F8efe542a6f2c5921cc2e69e2d3b54880%2F204906839%2F497347635.jpg&client=a1acac3e1b3290917d92&signature=f1e0be04017814d31f7bb7165eadf037e03840b5").into(iv2);
        Glide.with(this).load("https://o.aolcdn.com/images/dims?crop=5616%2C3738%2C0%2C2&quality=85&format=jpg&resize=640%2C426&image_uri=http%3A%2F%2Fo.aolcdn.com%2Fhss%2Fstorage%2Fmidas%2F4a5e2cb4f4288b84b2248103a3a56cb%2F203524460%2Fstock-photo-breathtaking-view-in-the-plitvice-lakes-national-park-croatia-65988727.jpg&client=a1acac3e1b3290917d92&signature=82935e63c9640c54d08c8cf0635e29a0a61d8693").into(iv3);
        Glide.with(this).load("https://o.aolcdn.com/images/dims?crop=663%2C441%2C0%2C16&quality=85&format=jpg&resize=640%2C426&image_uri=http%3A%2F%2Fo.aolcdn.com%2Fhss%2Fstorage%2Fmidas%2Ffd26cfa960b2d129569319d8cec51215%2F205803182%2FScreen%2BShot%2B2017-10-26%2Bat%2B2.11.06%2BPM.png&client=a1acac3e1b3290917d92&signature=109988e25f1126a1652eaa5de903ca1dca66ec81").into(iv4);
        Glide.with(this).load("https://o.aolcdn.com/images/dims?crop=5758%2C3833%2C0%2C3&quality=85&format=jpg&resize=640%2C426&image_uri=http%3A%2F%2Fo.aolcdn.com%2Fhss%2Fstorage%2Fmidas%2F46681e7a97ccd1a38c038741485f9140%2F205803190%2Fmagnificent-cliffs-of-moher-ireland-in-winter-picture-id486727437&client=a1acac3e1b3290917d92&signature=26f42795f7f8345a0e5be5c0465f224c82e0916e").into(iv5);

        sv_content.setOnTouchListener(new View.OnTouchListener() {
            float y0 = 0;
            float y1 = 0;

            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP ||
                        motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
                    Log.d("MotionEvent", "ACTION_UP");
                    isScrolling = false;
                }

                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    y0 = motionEvent.getY();

                    if (y1 - y0 > 0 && ll_title.getVisibility() == View.INVISIBLE && !isScrolling) {
                        Log.v("Message", "Scrolls Up");

                        ConstraintSet constraintSet = new ConstraintSet();
                        constraintSet.clone(root_home);
                        constraintSet.constrainPercentHeight(R.id.ll_title, (float) 0.1);
                        constraintSet.connect(R.id.ll_content, ConstraintSet.TOP, R.id.ll_title, ConstraintSet.BOTTOM);
                        constraintSet.connect(R.id.ll_content, ConstraintSet.START, R.id.iv_menu, ConstraintSet.START);
                        constraintSet.connect(R.id.ll_content, ConstraintSet.END, R.id.iv_search, ConstraintSet.END);
                        constraintSet.connect(R.id.ll_content, ConstraintSet.BOTTOM, R.id.root_home, ConstraintSet.BOTTOM);

                        AutoTransition autoTransition = new AutoTransition();
                        autoTransition.setDuration(500);
                        autoTransition.setInterpolator(new AccelerateDecelerateInterpolator());
                        autoTransition.addListener(new Transition.TransitionListener() {
                            @Override
                            public void onTransitionStart(@NonNull Transition transition) {
                            }

                            @Override
                            public void onTransitionEnd(@NonNull Transition transition) {
                                ll_title.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onTransitionCancel(@NonNull Transition transition) {

                            }

                            @Override
                            public void onTransitionPause(@NonNull Transition transition) {

                            }

                            @Override
                            public void onTransitionResume(@NonNull Transition transition) {

                            }
                        });

                        TransitionManager.beginDelayedTransition(root_home, autoTransition);
                        constraintSet.applyTo(root_home);
                    } else if (y1 - y0 < 0 && ll_title.getVisibility() == View.VISIBLE && !isScrolling) {
                        Log.v("Message", "Scrolls Down");

                        ConstraintSet constraintSet = new ConstraintSet();
                        constraintSet.clone(root_home);
                        constraintSet.constrainPercentHeight(R.id.ll_title, 0);
                        constraintSet.connect(R.id.ll_content, ConstraintSet.TOP, R.id.iv_menu, ConstraintSet.BOTTOM);
                        constraintSet.connect(R.id.ll_content, ConstraintSet.START, R.id.iv_menu, ConstraintSet.START);
                        constraintSet.connect(R.id.ll_content, ConstraintSet.END, R.id.iv_search, ConstraintSet.END);
                        constraintSet.connect(R.id.ll_content, ConstraintSet.BOTTOM, R.id.root_home, ConstraintSet.BOTTOM);

                        AutoTransition autoTransition = new AutoTransition();
                        autoTransition.setDuration(500);
                        autoTransition.setInterpolator(new AccelerateDecelerateInterpolator());
                        autoTransition.addListener(new Transition.TransitionListener() {
                            @Override
                            public void onTransitionStart(@NonNull Transition transition) {
                            }

                            @Override
                            public void onTransitionEnd(@NonNull Transition transition) {
                                ll_title.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onTransitionCancel(@NonNull Transition transition) {

                            }

                            @Override
                            public void onTransitionPause(@NonNull Transition transition) {

                            }

                            @Override
                            public void onTransitionResume(@NonNull Transition transition) {

                            }
                        });
                        TransitionManager.beginDelayedTransition(root_home, autoTransition);
                        constraintSet.applyTo(root_home);
                    }

                    y1 = motionEvent.getY();
                    isScrolling = true;
                }
                return false;
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
