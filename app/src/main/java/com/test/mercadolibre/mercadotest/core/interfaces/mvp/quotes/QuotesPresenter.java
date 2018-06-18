package com.test.mercadolibre.mercadotest.core.interfaces.mvp.quotes;

import android.app.Activity;

import com.test.mercadolibre.mercadotest.core.models.QuotesModel;

import java.util.List;


public class QuotesPresenter implements QuotesContract.Presenter,
        QuotesContract.OnGetQuotesListener {

    private QuotesContract.View mPaymenthMethodView;
    private QuotesInteractor mPaymenthMethodInteractor;

    public QuotesPresenter(QuotesContract.View articlesView) {
        this.mPaymenthMethodView = articlesView;
        mPaymenthMethodInteractor = new QuotesInteractor(this);
    }

    @Override
    public void onSuccess(List<QuotesModel> quotesModels) {
        mPaymenthMethodView.onGetQuotesSuccess(quotesModels);
    }

    @Override
    public void onFailure(String message) {
        mPaymenthMethodView.onGetQuotesFailure(message);
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
    public void Quotes(Activity activity, String amount, String paymentId, String issuerId) {
        mPaymenthMethodInteractor.getQuotes(activity, paymentId,amount,issuerId);
    }
}
