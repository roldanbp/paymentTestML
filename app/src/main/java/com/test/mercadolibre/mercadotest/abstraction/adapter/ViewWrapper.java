package com.test.mercadolibre.mercadotest.abstraction.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ViewWrapper<V extends View> extends RecyclerView.ViewHolder {

    private final V view;

    ViewWrapper(V itemView) {
        super(itemView);
        view = itemView;
    }

    public V getView() {
        return view;
    }
}
