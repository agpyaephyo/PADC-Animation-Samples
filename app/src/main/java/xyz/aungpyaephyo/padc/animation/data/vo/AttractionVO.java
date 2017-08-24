package xyz.aungpyaephyo.padc.animation.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aung on 8/24/17.
 */

public class AttractionVO {

    @SerializedName("title")
    private String title;

    @SerializedName("desc")
    private String desc;

    @SerializedName("images")
    private String[] images;

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String[] getImages() {
        return images;
    }
}
