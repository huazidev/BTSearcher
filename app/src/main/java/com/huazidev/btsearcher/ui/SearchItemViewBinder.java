package com.huazidev.btsearcher.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huazidev.btsearcher.R;
import com.huazidev.btsearcher.data.SearchModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * @author hua on 2017/8/21.
 */
public class SearchItemViewBinder extends ItemViewBinder<SearchModel, SearchItemViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_search_view, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, @NonNull SearchModel item) {
        holder.name.setText(item.name);
        holder.size.setText(item.size);
        holder.itemView.setOnClickListener(view -> new ActionMenuDialog(holder.itemView.getContext(), item.magnet).show());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name) TextView name;
        @BindView(R.id.size) TextView size;
        @BindView(R.id.magnet_address) TextView magnetAddress;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

