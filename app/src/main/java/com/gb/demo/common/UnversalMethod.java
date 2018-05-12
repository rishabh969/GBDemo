package com.gb.demo.common;

/*Rishabh*/

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.gb.demo.R;


public class UnversalMethod {

    public static final String PREIMAGEPATH="http://image.tmdb.org/t/p/w780";

    public AlertDialog.Builder buildDialog(final Activity c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(R.string.no_net);
        builder.setMessage(R.string.no_net_detail);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                c.finish();
            }
        });

        return builder;
    }


    public AlertDialog.Builder buildDialogNoData(final Activity c,String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("GB");
        builder.setMessage(message);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                c.finish();
            }
        });

        return builder;
    }
}
