package com.test.mercadolibre.mercadotest.core.interfaces.mvp.payment;

import android.app.Activity;

import com.test.mercadolibre.mercadotest.core.models.PaymentMethodModel;

import java.util.List;


public class PaymentMethodPresenter implements PaymentMethodContract.Presenter,
        PaymentMethodContract.OnGetPaymentMethodListener {

    private PaymentMethodContract.View mPaymenthMethodView;
    private PaymentMethodInteractor mPaymenthMethodInteractor;

    public PaymentMethodPresenter(PaymentMethodContract.View articlesView) {
        this.mPaymenthMethodView = articlesView;
        mPaymenthMethodInteractor = new PaymentMethodInteractor(this);
    }

    @Override
    public void onSuccess(List<PaymentMethodModel> paymentMethodModel) {
        mPaymenthMethodView.onGetPaymentMethodSuccess(paymentMethodModel);
    }

    @Override
    public void onFailure(String message) {
        mPaymenthMethodView.onGetPaymentMethodFailure(message);
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
    public void PaymentMethod(Activity activity) {
        mPaymenthMethodInteractor.getPaymenthMethod(activity);

    }
}
