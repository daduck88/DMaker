package com.dmaker;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Daduck on 2/29/16.
 */
public class DMakerHolder extends RecyclerView.ViewHolder {
    public ImageView iVItem;
    public TextView tVItem;
    public AppCompatCheckBox cBItem;
    public DMakerHolder(View view, boolean isMultiple) {
        super(view);
        iVItem = (ImageView) view.findViewById(R.id.iVItem);
        tVItem = (TextView) view.findViewById(R.id.tVItem);
        cBItem = (AppCompatCheckBox) view.findViewById(R.id.cBItem);
        cBItem.setVisibility(isMultiple ? View.VISIBLE : View.GONE);
    }

    public void bind(String content, boolean isSelected){
        iVItem.setVisibility(View.GONE);
        tVItem.setText(content);
        cBItem.setChecked(isSelected);
    }

    public void bind(String image, String content, boolean isSelected){
        iVItem.setVisibility(View.VISIBLE);
        Picasso.with(iVItem.getContext())
                .load(image)
                .resize(iVItem.getContext().getResources().getDimensionPixelSize(R.dimen.dialog_maker_item_image_size),
                        iVItem.getContext().getResources().getDimensionPixelSize(R.dimen.dialog_maker_item_image_size))
                .centerCrop()
                .into(iVItem);
        tVItem.setText(content);
        cBItem.setChecked(isSelected);
    }

    public void bind(int image, String content, boolean isSelected){
        iVItem.setVisibility(View.VISIBLE);
        iVItem.setImageResource(image);
        tVItem.setText(content);
        cBItem.setChecked(isSelected);
    }
}
