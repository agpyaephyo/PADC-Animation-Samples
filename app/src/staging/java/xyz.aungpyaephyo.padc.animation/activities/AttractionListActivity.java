package xyz.aungpyaephyo.padc.animation.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.aungpyaephyo.padc.animation.BuildConfig;
import xyz.aungpyaephyo.padc.animation.R;
import xyz.aungpyaephyo.padc.animation.adapters.AttractionsAdapter;
import xyz.aungpyaephyo.padc.animation.components.rvset.SmartRecyclerView;
import xyz.aungpyaephyo.padc.animation.data.models.AttractionsModel;
import xyz.aungpyaephyo.padc.animation.data.vo.AttractionVO;
import xyz.aungpyaephyo.padc.animation.events.APIEvents;
import xyz.aungpyaephyo.padc.animation.views.holders.AttractionViewHolder;
import xyz.aungpyaephyo.padc.animation.views.pods.EmptyViewPod;

public class AttractionListActivity extends AppCompatActivity
        implements AttractionViewHolder.AttractionItemController {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_attractions)
    SmartRecyclerView rvAttractions;

    @BindView(R.id.vp_empty_attractions)
    EmptyViewPod vpEmptyAttractions;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.vp_user_data)
    FrameLayout vpUserData;

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.tv_attractions)
    TextView tvAttractions;

    @BindView(R.id.tv_drawable_animation)
    TextView tvDrawableAnimation;

    @BindView(R.id.tv_sensitive_data)
    TextView tvSensitiveData;

    @BindView(R.id.tv_app_version)
    TextView tvAppVersion;

    private AttractionsAdapter mAttractionsAdapter;

    private float mWidthPx, mHeightPx;
    private float mDensity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_list);
        ButterKnife.bind(this, this);
        initScreenDimension();

        swipeRefreshLayout.setEnabled(false);

        setSupportActionBar(toolbar);

        mAttractionsAdapter = new AttractionsAdapter(getApplicationContext(), this);
        rvAttractions.setAdapter(mAttractionsAdapter);
        rvAttractions.setEmptyView(vpEmptyAttractions);

        mAttractionsAdapter.setNewData(AttractionsModel.getInstance().getAttractions());

        initUserDataPosition();

        tvAttractions.setSelected(true);

        tvSensitiveData.setText(BuildConfig.VERY_SENSITIVE_DATA);

        String appName = getResources().getString(R.string.app_name_short);
        tvAppVersion.setText(getResources().getString(R.string.format_app_version,
                appName,
                BuildConfig.APP_BUILD_TYPE,
                BuildConfig.VERSION_NAME));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_user_account) {
            showUserData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (vpUserData.getVisibility() == View.VISIBLE) {
            hideUserData();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onTapAttraction(AttractionVO attraction, ImageView ivAttraction) {
        Intent intent = AttractionDetailsActivity.newIntent(getApplicationContext(), attraction);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                    new Pair(ivAttraction, getString(R.string.detail_transition_name)));
            ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
        } else {
            startActivity(intent);
        }
    }

    @OnClick(R.id.btn_login)
    public void onTapLogin(View view) {
        hideUserData(new UserDataAnimListener() {
            @Override
            public void onFinishHideUserData() {
                Intent intent = UserAccountControlActivity.newIntent(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.tv_attractions)
    public void onTapMenuAttractions(View view) {
        resetMenu();
        tvAttractions.setSelected(true);
        hideUserData();
    }

    @OnClick(R.id.tv_drawable_animation)
    public void onTapMenuDrawableAnimation(View view) {
        resetMenu();
        tvDrawableAnimation.setSelected(true);
        hideUserData(new UserDataAnimListener() {
            @Override
            public void onFinishHideUserData() {
                Intent intent = DrawableAnimationActivity.newIntent(getApplicationContext());
                startActivity(intent);

                resetMenu();
                tvAttractions.setSelected(true);
            }
        });
    }

    @OnClick(R.id.vp_user_data)
    public void onTapUserData(View view) {
        hideUserData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAttractionsLoaded(APIEvents.AttractionsLoadedEvent event) {
        mAttractionsAdapter.setNewData(event.getAttractionList());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorLoadingAttractions(APIEvents.ErrorLoadingAttractionsEvent event) {
        Snackbar.make(rvAttractions, "Error on loading attractions." + event.getErrorMsg(), Snackbar.LENGTH_INDEFINITE).show();
    }

    private void showUserData() {
        vpUserData.setVisibility(View.VISIBLE);

        final ObjectAnimator animMoveX = ObjectAnimator.ofFloat(coordinatorLayout, "x", (mWidthPx / 2) * -1);
        animMoveX.setInterpolator(new AccelerateInterpolator());

        final ObjectAnimator animScaleDownY = ObjectAnimator.ofFloat(coordinatorLayout, "scaleY", 0.5f);
        animScaleDownY.setInterpolator(new AccelerateInterpolator());

        final ObjectAnimator animScaleDownX = ObjectAnimator.ofFloat(coordinatorLayout, "scaleX", 0.5f);
        animScaleDownX.setInterpolator(new AccelerateInterpolator());

        AnimatorSet asScaleDown = new AnimatorSet();
        asScaleDown.playTogether(animMoveX, animScaleDownY, animScaleDownX);
        asScaleDown.start();

        final ObjectAnimator animMoveXNav = ObjectAnimator.ofFloat(vpUserData, "x", mWidthPx, 0);
        animMoveXNav.setStartDelay(100);
        animMoveXNav.start();
    }

    private void hideUserData() {
        hideUserData(null);
    }

    private void hideUserData(final UserDataAnimListener listener) {
        final ObjectAnimator animMoveX = ObjectAnimator.ofFloat(coordinatorLayout, "x", 0);
        animMoveX.setInterpolator(new AccelerateInterpolator());

        final ObjectAnimator animScaleUpY = ObjectAnimator.ofFloat(coordinatorLayout, "scaleY", 1.0f);
        animScaleUpY.setInterpolator(new AccelerateInterpolator());

        final ObjectAnimator animScaleUpX = ObjectAnimator.ofFloat(coordinatorLayout, "scaleX", 1.0f);
        animScaleUpX.setInterpolator(new AccelerateInterpolator());

        AnimatorSet asScaleUp = new AnimatorSet();
        asScaleUp.playTogether(animMoveX, animScaleUpX, animScaleUpY);
        asScaleUp.start();

        final ObjectAnimator animMoveXNav = ObjectAnimator.ofFloat(vpUserData, "x", mWidthPx * 2);
        animMoveXNav.setInterpolator(new AccelerateInterpolator());

        AnimatorSet asScaleUpNav = new AnimatorSet();
        asScaleUpNav.playTogether(animMoveXNav);
        asScaleUpNav.start();
        asScaleUpNav.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                vpUserData.setVisibility(View.GONE);
                if (listener != null) {
                    listener.onFinishHideUserData();
                }
            }
        });
    }

    private void initUserDataPosition() {
        final ObjectAnimator animMoveXNav = ObjectAnimator.ofFloat(vpUserData, "x", mWidthPx * 2);
        animMoveXNav.setInterpolator(new AccelerateInterpolator());
        animMoveXNav.start();
        animMoveXNav.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                vpUserData.setVisibility(View.GONE);
            }
        });
    }

    private void initScreenDimension() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        mDensity = getResources().getDisplayMetrics().density;
        mWidthPx = outMetrics.widthPixels;
        mHeightPx = outMetrics.heightPixels;
    }

    private void resetMenu() {
        tvAttractions.setSelected(false);
        tvDrawableAnimation.setSelected(false);
    }

    public interface UserDataAnimListener {
        void onFinishHideUserData();
    }
}
