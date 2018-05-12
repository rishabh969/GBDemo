package com.gb.demo.ui.home;


/*Rishabh*/

import com.gb.demo.models.GbResponse;

import java.util.ArrayList;

public interface HomeView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getCityListSuccess(ArrayList<GbResponse> gbResponse);

}
