package com.example.shahbazahmed.favouritesapp.adapters;

/**
 * Created by shahbazahmed on 19/07/17.
 */

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.example.shahbazahmed.favouritesapp.ItemsBus;
import com.example.shahbazahmed.favouritesapp.R;
import com.example.shahbazahmed.favouritesapp.entities.Item;

/**
 * Created by shahbazahmed on 19/07/17.
 */
public class ItemsRecyclerAdapter extends RecyclerView.Adapter<ItemsRecyclerAdapter.Holder> {

    private final Context mContext;
    private List<Item> items;

    public ItemsRecyclerAdapter(Context context) {
        mContext = context;
        items = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(mContext).inflate(
                R.layout.item_row_selection, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Item item = items.get(position);
        holder.mTextViewTitle.setText(item.getTitle());
        holder.mTextViewDesc.setText(item.getDesc());
        holder.mTextViewType.setText(item.getType());
        holder.mTextViewCount.setText(item.getViewCount() + "");
        holder.mBtnFavourite.setChecked(item.isFavourite());
        Glide.with(mContext).load(item.getImageUrl()).into(holder.mItemImageView);
        int bgColor;
        if ("internship".equals(item.getType().toLowerCase())) {
            bgColor = R.color.deepRed;
        } else {
            bgColor = R.color.deepPurple;
        }
        holder.mTextViewType.setBackgroundColor(ContextCompat.getColor(mContext, bgColor));
        holder.mTextViewType.setTextColor(ContextCompat.getColor(mContext, R.color.colorWhite));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTextViewTitle;
        TextView mTextViewDesc;
        TextView mTextViewType;
        TextView mTextViewCount;
        ImageView mItemImageView;
        CheckBox mBtnFavourite;

        Holder(View v) {
            super(v);
            mTextViewTitle = v.findViewById(R.id.title_tv);
            mTextViewDesc = v.findViewById(R.id.desc_tv);
            mTextViewType = v.findViewById(R.id.type_tv);
            mTextViewCount = v.findViewById(R.id.count_tv);
            mItemImageView = v.findViewById(R.id.item_imageview);
            mBtnFavourite = v.findViewById(R.id.mark_favourite);
            mBtnFavourite.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            CheckBox cb = (CheckBox) view;
            Item item = items.get(getAdapterPosition());
            Item modifiedItem = new Item(
                    item.getTitle(),
                    item.getDesc(),
                    item.getType(),
                    item.getImageUrl(),
                    item.getViewCount(),
                    cb.isChecked()
            );
            items.set(getAdapterPosition(), modifiedItem);
            notifyItemChanged(getAdapterPosition());
            ItemsBus.publish(items);
        }
    }
}