package com.test.mercadolibre.mercadotest.core.interfaces.mvp.payment;

import android.app.Activity;

import com.test.mercadolibre.mercadotest.core.interfaces.services.util.IResponseListener;
import com.test.mercadolibre.mercadotest.core.models.PaymentMethodModel;
import com.test.mercadolibre.mercadotest.core.services.PaymentService;

import java.util.List;


public class PaymentMethodInteractor implements PaymentMethodContract.Interactor {

    private static final String TAG = PaymentMethodInteractor.class.getSimpleName();

    private PaymentMethodContract.OnGetPaymentMethodListener mOnGetPaymentMethodListener;

    public PaymentMethodInteractor(PaymentMethodContract.OnGetPaymentMethodListener onGetPaymentMethodListener) {
        this.mOnGetPaymentMethodListener = onGetPaymentMethodListener;
    }

    @Override
    public void getPaymenthMethod(Activity activity) {

        PaymentService.getPaymentMethod(new IResponseListener<List<PaymentMethodModel>, String>() {
            @Override
            public void initRequest() {
                mOnGetPaymentMethodListener.showProgress();
            }

            @Override
            public void successful(List<PaymentMethodModel> object) {
                mOnGetPaymentMethodListener.onSuccess(object);
            }

            @Override
            public void unsuccessful(String object) {
                mOnGetPaymentMethodListener.dismissProgress();
                mOnGetPaymentMethodListener.onFailure(object);
            }

            @Override
            public void error(Throwable t) {
                mOnGetPaymentMethodListener.dismissProgress();
                mOnGetPaymentMethodListener.onFailure(t.getMessage());
            }

            @Override
            public void finishRequest() {

            }
        });
    }
}
