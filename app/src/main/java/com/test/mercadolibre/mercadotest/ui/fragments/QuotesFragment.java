package com.test.mercadolibre.mercadotest.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.test.mercadolibre.mercadotest.R;
import com.test.mercadolibre.mercadotest.abstraction.adapter.CollectionAdapter;
import com.test.mercadolibre.mercadotest.abstraction.fragment.CommonFragment;
import com.test.mercadolibre.mercadotest.core.adapters.QuotesAdapter;
import com.test.mercadolibre.mercadotest.core.holders.QuotesHolder;
import com.test.mercadolibre.mercadotest.core.interfaces.mvp.quotes.QuotesContract;
import com.test.mercadolibre.mercadotest.core.interfaces.mvp.quotes.QuotesPresenter;
import com.test.mercadolibre.mercadotest.core.models.PayerCost;
import com.test.mercadolibre.mercadotest.core.models.QuotesModel;
import com.test.mercadolibre.mercadotest.core.util.ProgressDialog;
import com.test.mercadolibre.mercadotest.core.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;


public class QuotesFragment extends CommonFragment implements QuotesContract.View {

    private String TAG = QuotesFragment.class.getName();
    private QuotesContract.Presenter mQuotesPresenter;
    private RecyclerView mRecycler;
    private QuotesAdapter mAdapter;
    private String amount, paymentId, issuerId;

    public static QuotesFragment newInstance(String amount, String paymentId, String issuerId) {
        QuotesFragment fragment = new QuotesFragment();
        fragment.setAmount(amount);
        fragment.setIssuerId(issuerId);
        fragment.setPaymentId(paymentId);
        return fragment;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.quotes_fragment;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        init();
        mQuotesPresenter = new QuotesPresenter(this);
        mQuotesPresenter.Quotes(getActivity(), amount, paymentId, issuerId);
    }

    private void init() {
        this.setRecycler();
    }

    private void setRecycler() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new QuotesAdapter(getContext(), new ArrayList<PayerCost>(), R.layout.item_list_quotes);
        mAdapter.setOnItemClickListener(new CollectionAdapter.OnItemClickListener<PayerCost, QuotesHolder>() {
            @Override
            public void onItemClick(PayerCost item, QuotesHolder holder, int position) {
                SharedPreferencesUtil.getInstance(getContext())
                        .setPreferences(SharedPreferencesUtil.KEY_QUOTES_PREFERENCES, item.recommendedMessage);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(AmountFragment.class.getName())
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .replace(R.id.fragments, AmountFragment.newInstance(true), AmountFragment.class.getName())
                        .commit();
            }
        });
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onGetQuotesSuccess(final List<QuotesModel> quotesModels) {
        Log.d(TAG, "onGetQuotesSuccess: " + quotesModels.toString());
        ProgressDialog.getInstance().dismiss();

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (quotesModels.size() > 0) {
                    mAdapter.insertAll(quotesModels.get(0).payerCosts);
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), R.string.no_data_available, Toast.LENGTH_SHORT).show();
                    dispatchKeyBack();
                }
            }
        });
    }

    @Override
    public void onGetQuotesFailure(final String message) {
        Log.d(TAG, "onGetQuotesFailure: " + message);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                dispatchKeyBack();
            }
        });

    }

    @Override
    public void showProgressDialog() {
        ProgressDialog.getInstance().show(getFragmentManager(), TAG);
    }

    @Override
    public void dismissProgressDialog() {
        ProgressDialog.getInstance().dismiss();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                dispatchKeyBack();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
