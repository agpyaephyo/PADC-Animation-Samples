package xyz.aungpyaephyo.padc.animation.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.aungpyaephyo.padc.animation.R;
import xyz.aungpyaephyo.padc.animation.data.vo.AttractionVO;

/**
 * Created by aung on 7/29/17.
 */

public class AttractionDetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.iv_attraction)
    ImageView ivAttraction;

    public static Intent newIntent(Context context, AttractionVO attraction) {
        Intent intent = new Intent(context, AttractionDetailsActivity.class);
        //intent.putExtra("user_tap_attraction_title", attraction.getTitle());
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_details);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ivAttraction.setTransitionName(getString(R.string.detail_transition_name));
        }
    }
}
