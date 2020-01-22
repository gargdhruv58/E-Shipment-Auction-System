package com.example.e_shipmentauctionsystem1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductList extends ArrayAdapter<Product> {
    private Activity context;
    private List<Product> productList;

    public ProductList(Activity context, List<Product> productList) {
        super(context, R.layout.list_layout_2, productList);
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= context.getLayoutInflater();

        View listViewItem= inflater.inflate(R.layout.list_layout_2, null, true);

//        TextView tvWeight=(TextView) listViewItem.findViewById(R.id.tvWeight);
//        TextView tvLength=(TextView) listViewItem.findViewById(R.id.tvLength);
//        TextView tvWidth=(TextView) listViewItem.findViewById(R.id.tvWidth);
//        TextView tvHeight=(TextView) listViewItem.findViewById(R.id.tvHeight);

        ImageView imageView=(ImageView) listViewItem.findViewById(R.id.imageView100);
        TextView tvDesc=(TextView) listViewItem.findViewById(R.id.tvDesc100);
        TextView tvWeight=(TextView) listViewItem.findViewById(R.id.tvWeight100);
        TextView tvLength=(TextView) listViewItem.findViewById(R.id.tvLength100);
        TextView tvWidth=(TextView) listViewItem.findViewById(R.id.tvWidth100);
        TextView tvHeight=(TextView) listViewItem.findViewById(R.id.tvHeight100);
        TextView  tvSbid=(TextView) listViewItem.findViewById(R.id.tvSbid100);
        TextView tvPick=(TextView) listViewItem.findViewById(R.id.tvPick100);

        Product pr2= productList.get(position);

//        tvWeight.setText(pr2.getPrWeight());
//        tvLength.setText(pr2.getPrLength());
//        tvWidth.setText(pr2.getPrWidth());
//        tvHeight.setText(pr2.getPrHeight());

        Picasso.get()
                .load(pr2.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(imageView);

        tvDesc.setText(pr2.getPrDesc());
        tvWeight.setText(pr2.getPrWeight());
        tvLength.setText(pr2.getPrLength());
        tvWidth.setText(pr2.getPrWidth());
        tvHeight.setText(pr2.getPrHeight());
        tvSbid.setText(pr2.getStartBid());
        tvPick.setText(pr2.getPrPickup());

        return listViewItem;
    }
}

