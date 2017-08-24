package xyz.aungpyaephyo.padc.animation.views.holders;

import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import xyz.aungpyaephyo.padc.animation.R;
import xyz.aungpyaephyo.padc.animation.data.vo.AttractionVO;

/**
 * Created by aung on 8/24/17.
 */

public class AttractionViewHolder extends BaseViewHolder<AttractionVO> {

    @BindView(R.id.iv_attraction)
    ImageView ivAttraction;

    private AttractionItemController mAttractionItemController;

    public AttractionViewHolder(View itemView, AttractionItemController attractionItemController) {
        super(itemView);
        mAttractionItemController = attractionItemController;
    }

    @Override
    public void bind(AttractionVO data) {

    }

    @Override
    public void onClick(View v) {
        mAttractionItemController.onTapAttraction(null, ivAttraction);
    }

    public interface AttractionItemController {
        void onTapAttraction(AttractionVO attraction, ImageView ivAttraction);
    }
}
