package xyz.aungpyaephyo.padc.animation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.aungpyaephyo.padc.animation.R;
import xyz.aungpyaephyo.padc.animation.controllers.UserAccountControlController;

/**
 * Created by aung on 8/24/17.
 */

public class LoginFragment extends Fragment {

    public static final String TAG = "LoginFragment";

    private UserAccountControlController mUserAccountControlController;

    public static LoginFragment newInstance() {
        LoginFragment loginFragment = new LoginFragment();
        return loginFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mUserAccountControlController = (UserAccountControlController) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.lbl_navigate_to_register)
    public void onTapNavigateToRegister(View view) {
        mUserAccountControlController.onTapNavigateToRegister();
    }
}
