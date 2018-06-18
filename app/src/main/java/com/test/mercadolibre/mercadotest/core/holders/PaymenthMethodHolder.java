package com.test.mercadolibre.mercadotest.core.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.mercadolibre.mercadotest.R;
import com.test.mercadolibre.mercadotest.abstraction.holder.Holder;

/**
 * Created by macbookpro on 16/6/18.
 */

public class PaymenthMethodHolder extends Holder {

    /**
     * Constructor
     *
     * @param itemView View
     */

    private ImageView picture;
    private TextView title;

    public PaymenthMethodHolder(View itemView) {
        super(itemView);
        picture = (ImageView) itemView.findViewById(R.id.picture);
        title = (TextView) itemView.findViewById(R.id.title);
    }

    public ImageView getPicture() {
        return picture;
    }

    public TextView getTitle() {
        return title;
    }
}
