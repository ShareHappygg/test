package com.example.smartcity_app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.smartcity_app.R;
import com.example.smartcity_app.bean.BusBean;

import org.w3c.dom.Text;

import java.util.List;

public class BusListAdapter extends ArrayAdapter<BusBean> {

    public BusListAdapter(@NonNull Context context, int resource, @NonNull List<BusBean> objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BusBean busBean = getItem(position);
        ViewHolder viewHolder =  new ViewHolder();
        if (convertView == null)
        {
            convertView = View.inflate(getContext(), R.layout.item_bus,null);
            viewHolder = new ViewHolder();
            viewHolder.mTvStartTime = convertView.findViewById(R.id.text_start);
            viewHolder.mTvEndTime = convertView.findViewById(R.id.text_end);
            viewHolder.mTvName = convertView.findViewById(R.id.text_name);
            viewHolder.mTvPrice = convertView.findViewById(R.id.text_price);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTvName.setText(busBean.getName()+" " +busBean.getNumber());
        viewHolder.mTvPrice.setText(String.format(getContext().getString(R.string.price),busBean.getLowestprice()));
        viewHolder.mTvEndTime.setText(busBean.getEndtime());
        viewHolder.mTvStartTime.setText(busBean.getStarttime());
        return  convertView;
    }
    class  ViewHolder{
        private TextView mTvStartTime,mTvEndTime,mTvName,mTvPrice;
    }
}
