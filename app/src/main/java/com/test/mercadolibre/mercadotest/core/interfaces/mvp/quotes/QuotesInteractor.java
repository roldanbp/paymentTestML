package com.test.mercadolibre.mercadotest.core.interfaces.mvp.quotes;

import android.app.Activity;

import com.test.mercadolibre.mercadotest.core.interfaces.services.util.IResponseListener;
import com.test.mercadolibre.mercadotest.core.models.QuotesModel;
import com.test.mercadolibre.mercadotest.core.services.QuotesService;

import java.util.List;


public class QuotesInteractor implements QuotesContract.Interactor {

    private static final String TAG = QuotesInteractor.class.getSimpleName();

    private QuotesContract.OnGetQuotesListener mOngetQuotesListener;

    public QuotesInteractor(QuotesContract.OnGetQuotesListener onGetPaymentMethodListener) {
        this.mOngetQuotesListener = onGetPaymentMethodListener;
    }

    @Override
    public void getQuotes(Activity activity, String payment, String amount, String issuerId) {

        QuotesService.getQuotes(payment, amount, issuerId, new IResponseListener<List<QuotesModel>, String>() {
            @Override
            public void initRequest() {
                mOngetQuotesListener.showProgress();
            }

            @Override
            public void successful(List<QuotesModel> object) {
                mOngetQuotesListener.onSuccess(object);
            }

            @Override
            public void unsuccessful(String object) {
                mOngetQuotesListener.dismissProgress();
                mOngetQuotesListener.onFailure(object);
            }

            @Override
            public void error(Throwable t) {
                mOngetQuotesListener.dismissProgress();
                mOngetQuotesListener.onFailure(t.getMessage());
            }

            @Override
            public void finishRequest() {
                mOngetQuotesListener.dismissProgress();
            }
        });
    }
}
