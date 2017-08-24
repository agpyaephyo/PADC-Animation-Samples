package xyz.aungpyaephyo.padc.animation.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.aungpyaephyo.padc.animation.R;
import xyz.aungpyaephyo.padc.animation.data.models.AttractionsModel;
import xyz.aungpyaephyo.padc.animation.data.vo.AttractionVO;
import xyz.aungpyaephyo.padc.animation.utils.AppConstants;

/**
 * Created by aung on 7/29/17.
 */

public class AttractionDetailsActivity extends AppCompatActivity {

    private static final String IE_USER_TAP_ATTRACTION_TITLE = "IE_USER_TAP_ATTRACTION_TITLE";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.iv_attraction)
    ImageView ivAttraction;

    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.tv_attraction_desc)
    TextView tvAttractionDesc;

    public static Intent newIntent(Context context, AttractionVO attraction) {
        Intent intent = new Intent(context, AttractionDetailsActivity.class);
        intent.putExtra(IE_USER_TAP_ATTRACTION_TITLE, attraction.getTitle());
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

        Intent usedIntent = getIntent();
        Bundle bundle = usedIntent.getExtras();
        if (bundle != null) {
            String attractionTitle = bundle.getString(IE_USER_TAP_ATTRACTION_TITLE);
            AttractionVO tappedAttraction = AttractionsModel.getInstance().getAttractionByTitle(attractionTitle);

            bindData(tappedAttraction);
        }
    }

    private void bindData(AttractionVO tappedAttraction) {
        collapsingToolbarLayout.setTitle(tappedAttraction.getTitle());
        tvAttractionDesc.setText(tappedAttraction.getDesc());

        Glide.with(ivAttraction.getContext())
                .load(AppConstants.BASE_IMAGE_PATH + tappedAttraction.getImages()[0])
                .placeholder(R.drawable.placeholder_attraction_image)
                .into(ivAttraction);
    }
}
