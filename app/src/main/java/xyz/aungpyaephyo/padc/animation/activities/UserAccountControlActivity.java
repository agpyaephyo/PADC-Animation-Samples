package xyz.aungpyaephyo.padc.animation.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.aungpyaephyo.padc.animation.R;
import xyz.aungpyaephyo.padc.animation.controllers.UserAccountControlController;
import xyz.aungpyaephyo.padc.animation.fragments.LoginFragment;
import xyz.aungpyaephyo.padc.animation.fragments.RegisterFragment;

/**
 * Created by aung on 8/24/17.
 */

public class UserAccountControlActivity extends AppCompatActivity
        implements UserAccountControlController {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account_control);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, LoginFragment.newInstance(), LoginFragment.TAG)
                    .commit();
        }
    }

    @Override
    public void onTapNavigateToRegister() {
        Fragment registerFragment = getSupportFragmentManager().findFragmentByTag(RegisterFragment.TAG);
        if (registerFragment != null) { //exit in the backstack
            getSupportFragmentManager().popBackStack(LoginFragment.TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            getSupportFragmentManager().beginTransaction()
                    //.setCustomAnimations(R.anim.enter, R.anim.slide_to_up, R.anim.slide_to_down, R.anim.pop_exit)
                    .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                    .replace(R.id.fl_container, RegisterFragment.newInstance(), RegisterFragment.TAG)
                    .addToBackStack(RegisterFragment.TAG)
                    .commit();
        }

    }

    @Override
    public void onTapNavigateToLogin() {
        Fragment loginFragment = getSupportFragmentManager().findFragmentByTag(LoginFragment.TAG);
        if (loginFragment != null) { //exit in the backstack
            getSupportFragmentManager().popBackStack(RegisterFragment.TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            getSupportFragmentManager().beginTransaction()
                    //.setCustomAnimations(R.anim.slide_to_down, R.anim.pop_exit, R.anim.enter, R.anim.slide_to_up)
                    .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit, R.anim.enter, R.anim.exit)
                    .replace(R.id.fl_container, LoginFragment.newInstance(), LoginFragment.TAG)
                    .addToBackStack(LoginFragment.TAG)
                    .commit();
        }
    }
}
