package com.test.mercadolibre.mercadotest.core.interfaces.mvp.payment;

import android.app.Activity;

import com.test.mercadolibre.mercadotest.core.models.PaymentMethodModel;

import java.util.List;

public interface PaymentMethodContract {

    interface View {

        void onGetPaymentMethodSuccess(List<PaymentMethodModel> paymentMethodModel);

        void onGetPaymentMethodFailure(String message);

        void showProgressDialog();

        void dismissProgressDialog();
    }

    interface Presenter {
        void PaymentMethod(Activity activity);
    }

    interface Interactor {
        void getPaymenthMethod(Activity activity);
    }

    interface OnGetPaymentMethodListener {

        void onSuccess(List<PaymentMethodModel> articlesModel);

        void onFailure(String message);

        void showProgress();

        void dismissProgress();

    }
}
