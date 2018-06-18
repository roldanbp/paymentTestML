package com.test.mercadolibre.mercadotest.core.services;

import android.os.AsyncTask;

import com.test.mercadolibre.mercadotest.MercadoTestApp;
import com.test.mercadolibre.mercadotest.R;
import com.test.mercadolibre.mercadotest.abstraction.enums.StatusError;
import com.test.mercadolibre.mercadotest.core.interfaces.services.ICardIssuer;
import com.test.mercadolibre.mercadotest.core.interfaces.services.util.IResponseListener;
import com.test.mercadolibre.mercadotest.core.models.CardIssuerModel;
import com.test.mercadolibre.mercadotest.core.util.connection.BaseService;

import java.io.IOException;
import java.net.ConnectException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class CardIssuerService {


    public static void getCardIssuer(String id,final IResponseListener responseListener) {

        class EventAsyncTask extends AsyncTask<Call, Integer, Void> {
            private Call<List<CardIssuerModel>> pageEventCall;

            @Override
            protected Void doInBackground(Call... calls) {
                pageEventCall = calls[0];

                try {
                    Response<List<CardIssuerModel>> pageEventResponse = pageEventCall.execute();

                    if (pageEventResponse.isSuccessful()) {
                        responseListener.successful(pageEventResponse.body());
                    } else {
                        if (pageEventResponse.code() == StatusError.ERROR_401.getValue()) {
                            responseListener.unsuccessful(MercadoTestApp.getContext()
                                    .getResources()
                                    .getString(R.string.error_response));

                        } else if (pageEventResponse.code() == StatusError.ERROR_404.getValue())
                            responseListener.unsuccessful(MercadoTestApp.getContext()
                                    .getResources()
                                    .getString(R.string.error_response));

                        else if (pageEventResponse.code() == StatusError.ERROR_403.getValue())
                            responseListener.unsuccessful(MercadoTestApp.getContext()
                                    .getResources()
                                    .getString(R.string.error_response));
                        else
                            responseListener.unsuccessful(MercadoTestApp.getContext()
                                    .getResources()
                                    .getString(R.string.error_response));
                    }
                } catch (ConnectException e) {
                    responseListener.unsuccessful(e.getMessage());
                } catch (IOException e) {
                    responseListener.error(e);
                }
                return null;


            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                responseListener.initRequest();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                responseListener.finishRequest();
            }
        }
        Call<List<CardIssuerModel>> call = BaseService.getInstance()
                .auth(ICardIssuer.class, "")
                .getCardIssuer(id);

        new EventAsyncTask().execute(call);
    }


}