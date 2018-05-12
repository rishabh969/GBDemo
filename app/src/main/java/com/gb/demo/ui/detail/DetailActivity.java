package com.gb.demo.ui.detail;

/*Rishabh*/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.gb.demo.R;
import com.gb.demo.cache.SmartImageView;
import com.gb.demo.common.UnversalMethod;

public class DetailActivity extends Activity {
    private SmartImageView img_big_poster;
    private TextView release_date;
    private TextView tv_overview;
    private TextView release_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        renderView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(getIntent().getExtras()!=null) {
            Intent intent=getIntent();
            Bundle extras = intent.getExtras().getBundle("bundle");
            String overView = extras.getString("overview");
            String postPath = extras.getString("path");
            String releaseDate = extras.getString("date");
            String title = extras.getString("title");
            release_date.setText(releaseDate);
            tv_overview.setText(overView);
            release_name.setText(title);
            img_big_poster.setImageUrl(UnversalMethod.PREIMAGEPATH+postPath);
          /*  Glide.with(this)
                    .load(UnversalMethod.PREIMAGEPATH+postPath)
                    .into(img_big_poster);*/

        }
    }

    private void renderView(){
        img_big_poster=findViewById(R.id.img_big_poster);
        release_date=findViewById(R.id.release_date);
        tv_overview=findViewById(R.id.tv_overview);
        release_name=findViewById(R.id.release_name);
    }
}
