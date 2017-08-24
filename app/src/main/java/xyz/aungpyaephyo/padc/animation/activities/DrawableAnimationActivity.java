package xyz.aungpyaephyo.padc.animation.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.aungpyaephyo.padc.animation.R;

/**
 * Created by aung on 8/24/17.
 */

public class DrawableAnimationActivity extends AppCompatActivity {

    @BindView(R.id.iv_gyro)
    ImageView ivGyro;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, DrawableAnimationActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_animation);
        ButterKnife.bind(this, this);

    }

    @OnClick(R.id.btn_animate_drawable)
    public void onTapAnimateDrawable(View view) {
        ivGyro.setBackgroundResource(R.drawable.gyro_anim);
        AnimationDrawable animDrawableGyroImage = (AnimationDrawable) ivGyro.getBackground();
        animDrawableGyroImage.start();
    }
}
