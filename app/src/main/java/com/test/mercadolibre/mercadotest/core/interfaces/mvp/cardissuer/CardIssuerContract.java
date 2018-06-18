package com.test.mercadolibre.mercadotest.core.interfaces.mvp.cardissuer;

import android.app.Activity;

import com.test.mercadolibre.mercadotest.core.models.CardIssuerModel;

import java.util.List;

public interface CardIssuerContract {

    interface View {

        void onGetCardIssuerSuccess(List<CardIssuerModel> paymentMethodModel);

        void onGetCardIssuerFailure(String message);

        void showProgressDialog();

        void dismissProgressDialog();
    }

    interface Presenter {
        void CardIssuer(Activity activity,String id);
    }

    interface Interactor {
        void getCardIssuer(Activity activity, String id);
    }

    interface OnGetCardIssuerListener {

        void onSuccess(List<CardIssuerModel> articlesModel);

        void onFailure(String message);

        void showProgress();

        void dismissProgress();

    }
}
