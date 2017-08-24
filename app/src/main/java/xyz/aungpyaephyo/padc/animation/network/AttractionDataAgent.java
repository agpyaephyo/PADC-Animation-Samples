package xyz.aungpyaephyo.padc.animation.network;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.aungpyaephyo.padc.animation.events.APIEvents;
import xyz.aungpyaephyo.padc.animation.network.responses.AttractionListResponse;
import xyz.aungpyaephyo.padc.animation.utils.AppConstants;

/**
 * Created by aung on 8/24/17.
 */

public class AttractionDataAgent {

    private static AttractionDataAgent sDataAgent;

    private AttractionsApi mAttractionApi;

    private AttractionDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.ATTRACTION_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .build();

        mAttractionApi = retrofit.create(AttractionsApi.class);
    }

    public static AttractionDataAgent getInstance() {
        if (sDataAgent == null) {
            sDataAgent = new AttractionDataAgent();
        }
        return sDataAgent;
    }

    public void loadAttractions(String accessToken) {
        Call<AttractionListResponse> attractionListResponseCall = mAttractionApi.loadAttractions(accessToken);
        attractionListResponseCall.enqueue(new Callback<AttractionListResponse>() {
            @Override
            public void onResponse(Call<AttractionListResponse> call, Response<AttractionListResponse> response) {
                AttractionListResponse attractionListResponse = response.body();
                if (attractionListResponse == null) {
                    EventBus.getDefault().post(new APIEvents.ErrorLoadingAttractionsEvent(response.message()));
                } else {
                    EventBus.getDefault().post(new APIEvents.AttractionsLoadedEvent(attractionListResponse.getAttractionList()));
                }
            }

            @Override
            public void onFailure(Call<AttractionListResponse> call, Throwable t) {
                EventBus.getDefault().post(new APIEvents.ErrorLoadingAttractionsEvent(t.getMessage()));
            }
        });
    }
}
