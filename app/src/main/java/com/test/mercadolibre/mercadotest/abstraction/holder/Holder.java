package com.test.mercadolibre.mercadotest.abstraction.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class Holder extends RecyclerView.ViewHolder {

    /**
     * Constructor
     *
     * @param itemView View
     */
    public Holder(View itemView) {
        super(itemView);
    }
}
