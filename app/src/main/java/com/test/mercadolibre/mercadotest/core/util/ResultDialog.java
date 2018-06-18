package com.test.mercadolibre.mercadotest.core.util;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.test.mercadolibre.mercadotest.R;

public class ResultDialog extends DialogFragment {

    private static final String TAG = ResultDialog.class.getSimpleName();

    private int show = 0;
    private int dismiss = 0;

    private TextView txtAmount, txtPayment, txtCardIssuer, txtQuotes;
    private String amount, quotes, payment, cardIssuer;

    public ResultDialog() {
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public void setCardIssuer(String cardIssuer) {
        this.cardIssuer = cardIssuer;
    }

    public static ResultDialog newInstance(String amount, String payment, String issuer, String quotes) {
        ResultDialog fragment = new ResultDialog();
        fragment.setAmount(amount);
        fragment.setCardIssuer(issuer);
        fragment.setPayment(payment);
        fragment.setQuotes(quotes);
        return fragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_dialog, container, false);
        txtAmount = view.findViewById(R.id.amount);
        txtPayment = view.findViewById(R.id.payment_method);
        txtCardIssuer = view.findViewById(R.id.card_issuer);
        txtQuotes = view.findViewById(R.id.quotes);

        txtAmount.setText(amount);
        txtQuotes.setText(quotes);
        txtPayment.setText(payment);
        txtCardIssuer.setText(cardIssuer);

        return view;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        this.show++;
        if (show == 1) {
            super.show(manager, tag);
        }
    }

    @Override
    public void dismiss() {
        try {
            if (show != 0 && dismiss == --show) {
                show = 0;
                super.dismiss();
            }
        } catch (RuntimeException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }

    }
}
