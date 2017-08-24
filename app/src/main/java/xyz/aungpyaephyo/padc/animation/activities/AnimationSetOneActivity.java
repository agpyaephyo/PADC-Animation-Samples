package xyz.aungpyaephyo.padc.animation.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.aungpyaephyo.padc.animation.R;

/**
 * Created by aung on 8/25/17.
 */

public class AnimationSetOneActivity extends AppCompatActivity {

    @BindView(R.id.iv_sun)
    ImageView ivSun;

    @BindView(R.id.iv_clock)
    ImageView ivClock;

    @BindView(R.id.iv_hourHand)
    ImageView ivHourHand;

    private Animation animSunRise;
    private Animation animClockTurn;
    private Animation animHourHandTurn;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AnimationSetOneActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_set_one);
        ButterKnife.bind(this, this);

        animSunRise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sun_rise);
        animClockTurn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clock_turn);
        animHourHandTurn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.hour_hand_turn);
    }

    @OnClick(R.id.btn_animate_scene_one)
    public void onTapAnimateSceneOne(View view) {
        ivSun.startAnimation(animSunRise);
        ivClock.startAnimation(animClockTurn);
        ivHourHand.startAnimation(animHourHandTurn);
    }
}
