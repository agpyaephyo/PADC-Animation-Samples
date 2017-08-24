package xyz.aungpyaephyo.padc.animation.views.pods;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.aungpyaephyo.padc.animation.R;

/**
 * Created by aung on 2/25/16.
 */
public class FabsViewPod extends FrameLayout {

    @BindView(R.id.fab_plus)
    FloatingActionButton fabPlus;

    @BindView(R.id.fab_sms)
    FloatingActionButton fabSms;

    @BindView(R.id.fab_fb_messenger)
    FloatingActionButton fabFbMessenger;

    @BindView(R.id.fab_viber)
    FloatingActionButton fabViber;

    private FabsController controller;

    private boolean isOpen = false;

    public FabsViewPod(Context context) {
        super(context);
    }

    public FabsViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FabsViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void openAnim() {
        ObjectAnimator objAnimRotation = ObjectAnimator.ofFloat(fabPlus, "rotation", 540f, 0f);
        objAnimRotation.setDuration(600);
        objAnimRotation.setInterpolator(new AccelerateInterpolator());
        objAnimRotation.start();

        ObjectAnimator objAnimCallFW = ObjectAnimator.ofFloat(fabSms, "y", fabSms.getY(), fabSms.getY() - 310f);
        objAnimCallFW.setDuration(500);
        objAnimCallFW.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator objAnimCallBW = ObjectAnimator.ofFloat(fabSms, "y", fabSms.getY() - 310, fabSms.getY() - 280f);
        objAnimCallBW.setDuration(100);
        objAnimCallBW.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet asCall = new AnimatorSet();
        asCall.play(objAnimCallBW).after(objAnimCallFW);
        asCall.start();

        ObjectAnimator objAnimFacebookFW = ObjectAnimator.ofFloat(fabViber, "x", fabViber.getX(), fabViber.getX() - 310f);
        objAnimFacebookFW.setDuration(500);
        objAnimFacebookFW.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator objAnimFacebookBW = ObjectAnimator.ofFloat(fabViber, "x", fabViber.getX() - 310, fabViber.getX() - 280f);
        objAnimFacebookBW.setDuration(100);
        objAnimFacebookBW.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet asFacebook = new AnimatorSet();
        asFacebook.play(objAnimFacebookBW).after(objAnimFacebookFW);
        asFacebook.start();

        ObjectAnimator objAnimMapXFW = ObjectAnimator.ofFloat(fabFbMessenger, "x", fabFbMessenger.getX(), fabFbMessenger.getX() - 180f);
        objAnimMapXFW.setDuration(500);
        objAnimMapXFW.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator objAnimMapYFW = ObjectAnimator.ofFloat(fabFbMessenger, "y", fabFbMessenger.getY(), fabFbMessenger.getY() - 180f);
        objAnimMapYFW.setDuration(500);
        objAnimMapYFW.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet asMapFW = new AnimatorSet();
        asMapFW.play(objAnimMapYFW).with(objAnimMapXFW);
        asMapFW.start();

        ObjectAnimator objAnimMapXBW = ObjectAnimator.ofFloat(fabFbMessenger, "x", fabFbMessenger.getX() - 180f, fabFbMessenger.getX() - 160f);
        objAnimMapXBW.setDuration(100);
        objAnimMapXBW.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator objAnimMapYBW = ObjectAnimator.ofFloat(fabFbMessenger, "y", fabFbMessenger.getY() - 180f, fabFbMessenger.getY() - 160f);
        objAnimMapYBW.setDuration(100);
        objAnimMapYBW.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet asMapBW = new AnimatorSet();
        asMapBW.play(objAnimMapYBW).with(objAnimMapXBW);
        asMapBW.setStartDelay(500);
        asMapBW.start();
    }

    private void closeAnim() {
        ObjectAnimator objAnimRotation = ObjectAnimator.ofFloat(fabPlus, "rotation", 0, 540f);
        objAnimRotation.setDuration(600);
        objAnimRotation.setInterpolator(new AccelerateInterpolator());
        objAnimRotation.start();

        ObjectAnimator objAnimCallFW = ObjectAnimator.ofFloat(fabSms, "y", fabSms.getY(), fabSms.getY() - 30f);
        objAnimCallFW.setDuration(100);
        objAnimCallFW.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator objAnimCallBW = ObjectAnimator.ofFloat(fabSms, "y", fabSms.getY() - 30, fabSms.getY() + 280f);
        objAnimCallBW.setDuration(500);
        objAnimCallBW.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet asCall = new AnimatorSet();
        asCall.play(objAnimCallBW).after(objAnimCallFW);
        asCall.start();

        ObjectAnimator objAnimFacebookFW = ObjectAnimator.ofFloat(fabViber, "x", fabViber.getX(), fabViber.getX() - 30f);
        objAnimFacebookFW.setDuration(100);
        objAnimFacebookFW.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator objAnimFacebookBW = ObjectAnimator.ofFloat(fabViber, "x", fabViber.getX() - 30, fabViber.getX() + 280f);
        objAnimFacebookBW.setDuration(500);
        objAnimFacebookBW.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet asFacebook = new AnimatorSet();
        asFacebook.play(objAnimFacebookBW).after(objAnimFacebookFW);
        asFacebook.start();

        ObjectAnimator objAnimMapXFW = ObjectAnimator.ofFloat(fabFbMessenger, "x", fabFbMessenger.getX(), fabFbMessenger.getX() - 20);
        objAnimMapXFW.setDuration(100);
        objAnimMapXFW.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator objAnimMapYFW = ObjectAnimator.ofFloat(fabFbMessenger, "y", fabFbMessenger.getY(), fabFbMessenger.getY() - 20);
        objAnimMapYFW.setDuration(100);
        objAnimMapYFW.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet asMapFW = new AnimatorSet();
        asMapFW.play(objAnimMapYFW).with(objAnimMapXFW);
        asMapFW.start();

        ObjectAnimator objAnimMapXBW = ObjectAnimator.ofFloat(fabFbMessenger, "x", fabFbMessenger.getX() - 20f, fabFbMessenger.getX() + 160f);
        objAnimMapXBW.setDuration(500);
        objAnimMapXBW.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator objAnimMapYBW = ObjectAnimator.ofFloat(fabFbMessenger, "y", fabFbMessenger.getY() - 20f, fabFbMessenger.getY() + 160f);
        objAnimMapYBW.setDuration(500);
        objAnimMapYBW.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet asMapBW = new AnimatorSet();
        asMapBW.play(objAnimMapYBW).with(objAnimMapXBW);
        asMapBW.setStartDelay(100);
        asMapBW.start();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    @OnClick(R.id.fab_plus)
    public void onTapFabPlus(View view) {
        if (!isOpen) {
            openAnim();
            isOpen = true;
        } else {
            closeAnim();
            isOpen = false;
        }
    }

    @OnClick(R.id.fab_sms)
    public void onTapFabSms(View view) {
        if (controller != null) {
            controller.onTapSms();
        }

    }

    @OnClick(R.id.fab_fb_messenger)
    public void onTapFabFbMessenger(View view) {
        if (controller != null) {
            controller.onTapFbMessenger();
        }
    }

    @OnClick(R.id.fab_viber)
    public void onTapFabViber(View view) {
        if (controller != null) {
            controller.onTapViber();
        }
    }

    public interface FabsController {
        void onTapSms();

        void onTapFbMessenger();

        void onTapViber();
    }
}
