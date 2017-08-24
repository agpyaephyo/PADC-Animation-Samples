package xyz.aungpyaephyo.padc.animation.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import xyz.aungpyaephyo.padc.animation.network.responses.AttractionListResponse;
import xyz.aungpyaephyo.padc.animation.utils.AppConstants;

/**
 * Created by aung on 8/24/17.
 */

public interface AttractionsApi {

    @FormUrlEncoded
    @POST(AppConstants.API_GET_ATTRACTION_LIST)
    Call<AttractionListResponse> loadAttractions(
            @Field(AppConstants.PARAM_ACCESS_TOKEN) String param);
}
