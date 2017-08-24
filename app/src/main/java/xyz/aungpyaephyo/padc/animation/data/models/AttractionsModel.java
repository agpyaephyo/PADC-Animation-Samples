package xyz.aungpyaephyo.padc.animation.data.models;

import android.text.TextUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import xyz.aungpyaephyo.padc.animation.data.vo.AttractionVO;
import xyz.aungpyaephyo.padc.animation.events.APIEvents;
import xyz.aungpyaephyo.padc.animation.network.AttractionDataAgent;
import xyz.aungpyaephyo.padc.animation.utils.AppConstants;

/**
 * Created by aung on 8/24/17.
 */

public class AttractionsModel {

    private static AttractionsModel sAttractionModel;

    private AttractionDataAgent mDataAgent;
    private List<AttractionVO> mAttractions;

    private AttractionsModel() {
        mDataAgent = AttractionDataAgent.getInstance();

        EventBus.getDefault().register(this);
    }

    public static AttractionsModel getInstance() {
        if (sAttractionModel == null) {
            sAttractionModel = new AttractionsModel();
        }
        return sAttractionModel;
    }

    public void loadAttractions() {
        mDataAgent.loadAttractions(AppConstants.ACCESS_TOKEN);
    }

    public List<AttractionVO> getAttractions() {
        if (mAttractions == null)
            return new ArrayList<>();

        return mAttractions;
    }

    public AttractionVO getAttractionByTitle(String attractionTitle) {
        for(AttractionVO attraction : mAttractions) {
            if(TextUtils.equals(attraction.getTitle(), attractionTitle))
                return attraction;
        }
        return null;
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onAttractionsLoaded(APIEvents.AttractionsLoadedEvent event) {
        mAttractions = event.getAttractionList();
    }
}
