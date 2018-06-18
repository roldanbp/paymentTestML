package com.test.mercadolibre.mercadotest.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.test.mercadolibre.mercadotest.R;
import com.test.mercadolibre.mercadotest.abstraction.fragment.CommonFragment;
import com.test.mercadolibre.mercadotest.core.util.ResultDialog;
import com.test.mercadolibre.mercadotest.core.util.SharedPreferencesUtil;
import com.test.mercadolibre.mercadotest.ui.activitys.MainActivity;

public class AmountFragment extends CommonFragment {

    private String TAG = AmountFragment.class.getName();
    private TextInputLayout txtInputAmount;
    private EditText edtAmount;
    private boolean isFromQuotes;


    public static AmountFragment newInstance(boolean isFromQuotes) {
        AmountFragment fragment = new AmountFragment();
        fragment.setFromQuotes(isFromQuotes);
        return fragment;
    }

    public void setFromQuotes(boolean fromQuotes) {
        isFromQuotes = fromQuotes;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.amount_fragment;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        init();
        if (isFromQuotes) {
            initDialogResult();
        }
    }

    private void initDialogResult() {
        SharedPreferencesUtil instance = SharedPreferencesUtil.getInstance(getContext());
        String amount = instance.getPreferencesString(SharedPreferencesUtil.KEY_AMOUNT_PREFERENCES);
        String payment = instance.getPreferencesString(SharedPreferencesUtil.KEY_PAYMENT_METHOD_PREFERENCES);
        String quotes = instance.getPreferencesString(SharedPreferencesUtil.KEY_QUOTES_PREFERENCES);
        String cardIssuer = instance.getPreferencesString(SharedPreferencesUtil.KEY_CARD_ISSUER_PREFERENCES);

        ResultDialog.newInstance(amount, payment, cardIssuer, quotes).show(getFragmentManager(), TAG);

    }

    private void init() {
        ((MainActivity) getParent()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getParent()).getSupportActionBar().setDisplayShowHomeEnabled(false);

        txtInputAmount = (TextInputLayout) findViewById(R.id.text_input_layout);
        edtAmount = (EditText) findViewById(R.id.amount);
        edtAmount.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() < 6)
                    txtInputAmount.setError(null);
            }
        });
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.amount, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_next:
                String amount = edtAmount.getText().toString();
                if (!isEmpty(edtAmount)
                        && Double.parseDouble(amount) <= 250000
                        && Double.parseDouble(amount) > 0) {

                    SharedPreferencesUtil.getInstance(getContext())
                            .setPreferences(SharedPreferencesUtil.KEY_AMOUNT_PREFERENCES, amount);

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .addToBackStack(PaymenthMethodFragment.class.getName())
                            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                            .replace(R.id.fragments, PaymenthMethodFragment.newInstance(), PaymenthMethodFragment.class.getName())
                            .commit();


                } else {
                    txtInputAmount.setError(getResources().getString(R.string.please_enter_a_amount));
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
