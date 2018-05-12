package com.gb.demo.ui.home;

/*Rishabh*/

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.gb.demo.R;
import com.gb.demo.cache.SmartImageView;
import com.gb.demo.common.UnversalMethod;
import com.gb.demo.models.GbResponse;

import java.util.ArrayList;

public class HomeListAdapter extends ArrayAdapter<GbResponse> {
    private final HomeListAdapter.OnItemClickListener listener;
    private ArrayList<GbResponse> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView nameMovie;
        SmartImageView background;
        FrameLayout row;

        public void click(final GbResponse listData, final HomeListAdapter.OnItemClickListener listener) {
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(listData);
                }
            });
        }
    }

    public HomeListAdapter( Context context,ArrayList<GbResponse> data,HomeListAdapter.OnItemClickListener listener) {
        super(context, R.layout.item_home, data);
        this.dataSet = data;
        this.mContext=context;
        this.listener = listener;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GbResponse dataModel = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_home, parent, false);
            viewHolder.nameMovie = (TextView) convertView.findViewById(R.id.name);
            viewHolder.background = (SmartImageView) convertView.findViewById(R.id.img);
            viewHolder.row=(FrameLayout) convertView.findViewById(R.id.row);
            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }


        viewHolder.nameMovie.setText(dataModel.getTitle());
        String images = UnversalMethod.PREIMAGEPATH+ dataModel.getPoster_path();
        viewHolder.background.setImageUrl(images);
        viewHolder.click(dataModel, listener);

        return convertView;
    }


    public interface OnItemClickListener {
        void onClick(GbResponse Item);
    }

}
