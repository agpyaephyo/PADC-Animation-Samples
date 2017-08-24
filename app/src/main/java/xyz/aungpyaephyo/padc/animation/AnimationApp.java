package xyz.aungpyaephyo.padc.animation;

import android.app.Application;

import xyz.aungpyaephyo.padc.animation.data.models.AttractionsModel;

/**
 * Created by aung on 8/24/17.
 */

public class AnimationApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AttractionsModel.getInstance().loadAttractions();
    }
}
