package com.dmaker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Daduck on 2/29/16.
 */
public class DMakerAdapter extends RecyclerView.Adapter<DMakerHolder> {

    private final DMakerFragment dFragment;
    private boolean isMultiple;
    private String[] content;
    private String[] images;
    private int[] imagesRes;
    private DMakerFragment.OnItemListClickListener itemListener;

    public DMakerAdapter(String[] content, boolean isMultiple, DMakerFragment dFragment){
        this(content, (String[]) null, isMultiple, dFragment);
    }

    public DMakerAdapter(String[] content, String[] images, boolean isMultiple, DMakerFragment dFragment) {
        this(content, images, null, isMultiple, dFragment);
    }

    public DMakerAdapter(String[] content, int[] images, boolean isMultiple, DMakerFragment dFragment) {
        this(content, null, images, isMultiple, dFragment);
    }

    public DMakerAdapter(String[] content, String[] images, int[] imagesRes, boolean isMultiple, DMakerFragment dFragment) {
        this.content = content;
        this.images = images;
        this.imagesRes = imagesRes;
        this.isMultiple = isMultiple;
        this.
                dFragment = dFragment;
    }

    @Override
    public DMakerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_maker_item, parent, false);
        DMakerHolder viewHolder = new DMakerHolder(itemLayoutView, isMultiple);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final DMakerHolder holder, final int position) {
        boolean isSelected = isSelected(position);
        if(images != null){
            holder.bind(images[position], content[position], isSelected );
        }else if (imagesRes != null){
            holder.bind(imagesRes[position], content[position], isSelected );
        }else {
            holder.bind(content[position], isSelected );
        }
        View itemView = (View) holder.iVItem.getParent();
        if(isMultiple){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(holder.iVItem.getContext(), "multiple choice ", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemListener != null){
                        itemListener.onItemClicked(position);
                    } else {
                        Toast.makeText(holder.iVItem.getContext(), "Single Choice", Toast.LENGTH_SHORT).show();
                    }
                    dFragment.dismissAllowingStateLoss();
                }
            });
        }
    }

    private boolean isSelected(int position) {
        return false;
    }

    @Override
    public int getItemCount() {
        return content.length;
    }

    public void setItemListener(DMakerFragment.OnItemListClickListener itemListener) {
        this.itemListener = itemListener;
    }
}
