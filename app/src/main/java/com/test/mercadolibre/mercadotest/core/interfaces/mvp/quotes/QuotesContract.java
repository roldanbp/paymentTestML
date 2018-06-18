package com.test.mercadolibre.mercadotest.core.interfaces.mvp.quotes;

import android.app.Activity;

import com.test.mercadolibre.mercadotest.core.models.QuotesModel;

import java.util.List;

public interface QuotesContract {

    interface View {

        void onGetQuotesSuccess(List<QuotesModel> quotesModels);

        void onGetQuotesFailure(String message);

        void showProgressDialog();

        void dismissProgressDialog();
    }

    interface Presenter {
        void Quotes(Activity activity, String amount,String paymentId, String issuerId);
    }

    interface Interactor {
        void getQuotes(Activity activity, String payment, String amount, String issuerId);
    }

    interface OnGetQuotesListener {

        void onSuccess(List<QuotesModel> quotesModels);

        void onFailure(String message);

        void showProgress();

        void dismissProgress();

    }
}
