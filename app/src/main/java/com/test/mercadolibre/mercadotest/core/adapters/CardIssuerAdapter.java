package com.test.mercadolibre.mercadotest.core.adapters;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.Glide;
import com.test.mercadolibre.mercadotest.abstraction.adapter.CollectionAdapter;
import com.test.mercadolibre.mercadotest.core.holders.CardIssuerHolder;
import com.test.mercadolibre.mercadotest.core.models.CardIssuerModel;

import java.util.List;

/**
 * Created by macbookpro on 16/6/18.
 */

public class CardIssuerAdapter extends CollectionAdapter<CardIssuerModel, CardIssuerHolder> {

    /**
     * Default constructor
     *
     * @param context    android Context
     * @param dataSource List<T> Collection to render
     * @param layoutId   int layout resource id to inflate
     */
    public CardIssuerAdapter(Context context, List<CardIssuerModel> dataSource, int layoutId) {
        super(context, dataSource, layoutId);
    }

    @Override
    protected CardIssuerHolder newHolderInstance(View rowView) {
        return new CardIssuerHolder(rowView);
    }

    @Override
    protected void bindHolder(CardIssuerHolder holder, CardIssuerModel item, int position) {
        holder.getTitle().setText(item.name);
        Glide.with(getContext())
                .load(item.secureThumbnail)
                .into(holder.getPicture());
    }
}
