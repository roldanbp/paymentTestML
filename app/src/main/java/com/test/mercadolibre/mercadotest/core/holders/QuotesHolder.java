package com.test.mercadolibre.mercadotest.core.holders;

import android.view.View;
import android.widget.TextView;

import com.test.mercadolibre.mercadotest.R;
import com.test.mercadolibre.mercadotest.abstraction.holder.Holder;

/**
 * Created by macbookpro on 16/6/18.
 */

public class QuotesHolder extends Holder {

    /**
     * Constructor
     *
     * @param itemView View
     */

    private TextView title;

    public QuotesHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
    }

    public TextView getTitle() {
        return title;
    }
}
