package com.test.mercadolibre.mercadotest.core.adapters;

import android.content.Context;
import android.view.View;

import com.test.mercadolibre.mercadotest.abstraction.adapter.CollectionAdapter;
import com.test.mercadolibre.mercadotest.core.holders.QuotesHolder;
import com.test.mercadolibre.mercadotest.core.models.PayerCost;

import java.util.List;

/**
 * Created by macbookpro on 16/6/18.
 */

public class QuotesAdapter extends CollectionAdapter<PayerCost, QuotesHolder> {

    /**
     * Default constructor
     *
     * @param context    android Context
     * @param dataSource List<T> Collection to render
     * @param layoutId   int layout resource id to inflate
     */
    public QuotesAdapter(Context context, List<PayerCost> dataSource, int layoutId) {
        super(context, dataSource, layoutId);
    }

    @Override
    protected QuotesHolder newHolderInstance(View rowView) {
        return new QuotesHolder(rowView);
    }

    @Override
    protected void bindHolder(QuotesHolder holder, PayerCost item, int position) {
        holder.getTitle().setText(item.recommendedMessage);
    }
}
