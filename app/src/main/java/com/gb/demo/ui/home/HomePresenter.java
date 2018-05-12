package com.gb.demo.ui.home;

/*Rishabh*/

import com.gb.demo.models.GbResponse;
import com.gb.demo.networking.WebService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Rishabh.
 */
public class HomePresenter {
    private final HomeView view;
    GbResponse gbResponse;
    ArrayList<GbResponse> listItem;

    public HomePresenter(HomeView view) {
        this.view = view;
    }

    public void getCityList() {
        view.showWait();
        listItem = new ArrayList<GbResponse>();
        WebService webService = new WebService(view, new WebService.NetworkApiCallback() {
            @Override
            public void onSuccess(String successResponse) {
                try {
                    JSONObject jsonObject = new JSONObject(successResponse);
                    if (jsonObject.has("results")) {
                        JSONArray listResponse = jsonObject.getJSONArray("results");
                        if (listResponse.length() > 0) {
                            for (int i = 0; i < listResponse.length(); i++) {
                                gbResponse = new GbResponse();
                                JSONObject object = listResponse.getJSONObject(i);
                                gbResponse.setOverview(object.getString("overview"));
                                gbResponse.setPoster_path(object.getString("poster_path"));
                                gbResponse.setRelease_date(object.getString("release_date"));
                                gbResponse.setTitle(object.getString("title"));
                                listItem.add(gbResponse);
                            }
                            view.getCityListSuccess(listItem);
                            view.removeWait();
                        } else {
                            view.removeWait();
                            view.onFailure("No Data Found");

                        }
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onError(String errorResponse) {
                view.removeWait();
                view.onFailure(errorResponse);
            }
        });
        webService.execute();

    }
}
