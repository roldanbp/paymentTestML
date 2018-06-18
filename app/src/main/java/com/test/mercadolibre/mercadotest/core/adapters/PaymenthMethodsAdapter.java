package com.test.mercadolibre.mercadotest.core.adapters;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.Glide;
import com.test.mercadolibre.mercadotest.abstraction.adapter.CollectionAdapter;
import com.test.mercadolibre.mercadotest.core.holders.PaymenthMethodHolder;
import com.test.mercadolibre.mercadotest.core.models.PaymentMethodModel;

import java.util.List;

/**
 * Created by macbookpro on 16/6/18.
 */

public class PaymenthMethodsAdapter extends CollectionAdapter<PaymentMethodModel, PaymenthMethodHolder> {

    /**
     * Default constructor
     *
     * @param context    android Context
     * @param dataSource List<T> Collection to render
     * @param layoutId   int layout resource id to inflate
     */
    public PaymenthMethodsAdapter(Context context, List<PaymentMethodModel> dataSource, int layoutId) {
        super(context, dataSource, layoutId);
    }

    @Override
    protected PaymenthMethodHolder newHolderInstance(View rowView) {
        return new PaymenthMethodHolder(rowView);
    }

    @Override
    protected void bindHolder(PaymenthMethodHolder holder, PaymentMethodModel item, int position) {
        holder.getTitle().setText(item.name);
        Glide.with(getContext())
                .load(item.secureThumbnail)
                .into(holder.getPicture());
    }
}
