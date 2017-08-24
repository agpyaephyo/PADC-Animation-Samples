package xyz.aungpyaephyo.padc.animation.views.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import xyz.aungpyaephyo.padc.animation.R;
import xyz.aungpyaephyo.padc.animation.data.vo.AttractionVO;
import xyz.aungpyaephyo.padc.animation.utils.AppConstants;

/**
 * Created by aung on 8/24/17.
 */

public class AttractionViewHolder extends BaseViewHolder<AttractionVO> {

    @BindView(R.id.iv_attraction)
    ImageView ivAttraction;

    @BindView(R.id.tv_attraction_title)
    TextView tvAttractionTitle;

    @BindView(R.id.tv_attraction_desc)
    TextView tvAttractionDesc;

    private AttractionItemController mAttractionItemController;

    private AttractionVO mAttraction;

    public AttractionViewHolder(View itemView, AttractionItemController attractionItemController) {
        super(itemView);
        mAttractionItemController = attractionItemController;
    }

    @Override
    public void bind(AttractionVO data) {
        mAttraction = data;

        tvAttractionTitle.setText(data.getTitle());
        tvAttractionDesc.setText(data.getDesc());

        Glide.with(ivAttraction.getContext())
                .load(AppConstants.BASE_IMAGE_PATH + data.getImages()[0])
                .placeholder(R.drawable.placeholder_attraction_image)
                .into(ivAttraction);
    }

    @Override
    public void onClick(View v) {
        mAttractionItemController.onTapAttraction(mAttraction, ivAttraction);
    }

    public interface AttractionItemController {
        void onTapAttraction(AttractionVO attraction, ImageView ivAttraction);
    }
}
