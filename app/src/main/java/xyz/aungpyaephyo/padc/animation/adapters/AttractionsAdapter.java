package xyz.aungpyaephyo.padc.animation.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import xyz.aungpyaephyo.padc.animation.R;
import xyz.aungpyaephyo.padc.animation.data.vo.AttractionVO;
import xyz.aungpyaephyo.padc.animation.views.holders.AttractionViewHolder;

/**
 * Created by aung on 8/24/17.
 */

public class AttractionsAdapter extends BaseRecyclerAdapter<AttractionViewHolder, AttractionVO> {

    private AttractionViewHolder.AttractionItemController mAttractionItemController;

    public AttractionsAdapter(Context context, AttractionViewHolder.AttractionItemController attractionItemController) {
        super(context);
        mAttractionItemController = attractionItemController;
    }

    @Override
    public AttractionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_item_attraction, parent, false);
        return new AttractionViewHolder(view, mAttractionItemController);
    }

    @Override
    public void onBindViewHolder(AttractionViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }
}
