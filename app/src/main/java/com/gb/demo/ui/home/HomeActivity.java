package com.gb.demo.ui.home;

/*Rishabh*/

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.gb.demo.BaseApp;
import com.gb.demo.MyApplication;
import com.gb.demo.R;
import com.gb.demo.common.ConnectivityReceiver;
import com.gb.demo.common.UnversalMethod;
import com.gb.demo.models.GbResponse;
import com.gb.demo.ui.detail.DetailActivity;

import java.util.ArrayList;


public class HomeActivity extends BaseApp implements HomeView ,ConnectivityReceiver.ConnectivityReceiverListener{

    private ListView listView;
    ProgressBar progressBar;
    private UnversalMethod unversalMethod;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        renderView();
        unversalMethod=new UnversalMethod();
        HomePresenter presenter = new HomePresenter(this);
        if(ConnectivityReceiver.isConnected()) {
            presenter.getCityList();
        }else {
            unversalMethod.buildDialog(this).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);
    }

    public  void renderView(){
        setContentView(R.layout.activity_home);
        listView=findViewById(R.id.list1);
        progressBar = findViewById(R.id.progress);
    }


    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        unversalMethod.buildDialogNoData(this,appErrorMessage).show();
    }

    @Override
    public void getCityListSuccess(ArrayList<GbResponse> gbResponse) {

        HomeListAdapter adapter = new HomeListAdapter(getApplicationContext(), gbResponse,
                new HomeListAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(GbResponse Item) {
                        Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
                        Bundle extras = new Bundle();
                        extras.putString("overview", Item.getOverview());
                        extras.putString("path", Item.getPoster_path());
                        extras.putString("date", Item.getRelease_date());
                        extras.putString("title", Item.getTitle());
                        intent.putExtra("bundle",extras);
                        startActivity(intent);
                    }
                });

        listView.setAdapter(adapter);

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if(!isConnected){
            unversalMethod.buildDialog(this).show();
        }
    }
}
