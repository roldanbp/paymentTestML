package com.test.mercadolibre.mercadotest.core.interfaces.mvp.cardissuer;

import android.app.Activity;

import com.test.mercadolibre.mercadotest.core.models.CardIssuerModel;

import java.util.List;


public class CardIssuerPresenter implements CardIssuerContract.Presenter,
        CardIssuerContract.OnGetCardIssuerListener {

    private CardIssuerContract.View mPaymenthMethodView;
    private CardIssuerInteractor mPaymenthMethodInteractor;

    public CardIssuerPresenter(CardIssuerContract.View articlesView) {
        this.mPaymenthMethodView = articlesView;
        mPaymenthMethodInteractor = new CardIssuerInteractor(this);
    }

    @Override
    public void onSuccess(List<CardIssuerModel> cardIssuerModels) {
        mPaymenthMethodView.onGetCardIssuerSuccess(cardIssuerModels);
    }

    @Override
    public void onFailure(String message) {
        mPaymenthMethodView.onGetCardIssuerFailure(message);
    }

    @Override
    public void showProgress() {
        mPaymenthMethodView.showProgressDialog();
    }

    @Override
    public void dismissProgress() {
        mPaymenthMethodView.dismissProgressDialog();
    }

    @Override
    public void CardIssuer(Activity activity, String id) {
        mPaymenthMethodInteractor.getCardIssuer(activity, id);

    }
}
