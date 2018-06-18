package com.test.mercadolibre.mercadotest.core.interfaces.mvp.cardissuer;

import android.app.Activity;

import com.test.mercadolibre.mercadotest.core.interfaces.services.util.IResponseListener;
import com.test.mercadolibre.mercadotest.core.models.CardIssuerModel;
import com.test.mercadolibre.mercadotest.core.services.CardIssuerService;

import java.util.List;


public class CardIssuerInteractor implements CardIssuerContract.Interactor {

    private static final String TAG = CardIssuerInteractor.class.getSimpleName();

    private CardIssuerContract.OnGetCardIssuerListener mOnGetCardIssuerListener;

    public CardIssuerInteractor(CardIssuerContract.OnGetCardIssuerListener onGetPaymentMethodListener) {
        this.mOnGetCardIssuerListener = onGetPaymentMethodListener;
    }

    @Override
    public void getCardIssuer(Activity activity, String id) {

        CardIssuerService.getCardIssuer(id, new IResponseListener<List<CardIssuerModel>, String>() {
            @Override
            public void initRequest() {
                mOnGetCardIssuerListener.showProgress();
            }

            @Override
            public void successful(List<CardIssuerModel> object) {
                mOnGetCardIssuerListener.onSuccess(object);
            }

            @Override
            public void unsuccessful(String object) {
                mOnGetCardIssuerListener.dismissProgress();
                mOnGetCardIssuerListener.onFailure(object);
            }

            @Override
            public void error(Throwable t) {
                mOnGetCardIssuerListener.dismissProgress();
                mOnGetCardIssuerListener.onFailure(t.getMessage());
            }

            @Override
            public void finishRequest() {

            }
        });
    }
}
