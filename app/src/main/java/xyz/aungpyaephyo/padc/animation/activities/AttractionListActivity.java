package xyz.aungpyaephyo.padc.animation.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.aungpyaephyo.padc.animation.R;
import xyz.aungpyaephyo.padc.animation.adapters.AttractionsAdapter;
import xyz.aungpyaephyo.padc.animation.components.rvset.SmartRecyclerView;
import xyz.aungpyaephyo.padc.animation.data.vo.AttractionVO;
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

    private AttractionsAdapter mAttractionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_list);
        ButterKnife.bind(this, this);

        swipeRefreshLayout.setEnabled(false);

        setSupportActionBar(toolbar);

        mAttractionsAdapter = new AttractionsAdapter(getApplicationContext(), this);
        rvAttractions.setAdapter(mAttractionsAdapter);
        rvAttractions.setEmptyView(vpEmptyAttractions);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    public void onTapFab(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
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
}
