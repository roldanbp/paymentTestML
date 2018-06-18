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
import com.test.mercadolibre.mercadotest.core.adapters.CardIssuerAdapter;
import com.test.mercadolibre.mercadotest.core.holders.CardIssuerHolder;
import com.test.mercadolibre.mercadotest.core.interfaces.mvp.cardissuer.CardIssuerContract;
import com.test.mercadolibre.mercadotest.core.interfaces.mvp.cardissuer.CardIssuerPresenter;
import com.test.mercadolibre.mercadotest.core.models.CardIssuerModel;
import com.test.mercadolibre.mercadotest.core.util.ProgressDialog;
import com.test.mercadolibre.mercadotest.core.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbookpro on 17/6/18.
 */

public class CardIssuerFragment extends CommonFragment implements CardIssuerContract.View {

    private String TAG = PaymenthMethodFragment.class.getName();
    private CardIssuerContract.Presenter mCardIssuerPresenter;
    private RecyclerView mRecycler;
    private CardIssuerAdapter mAdapter;
    private String paymentId;


    public static CardIssuerFragment newInstance(String id) {
        CardIssuerFragment fragment = new CardIssuerFragment();
        fragment.setPaymentId(id);
        return fragment;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.card_issuer_fragment;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        init();
        mCardIssuerPresenter = new CardIssuerPresenter(this);
        mCardIssuerPresenter.CardIssuer(getActivity(), paymentId);
    }

    public void init() {
        this.setRecycler();
    }


    public void setRecycler() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CardIssuerAdapter(getContext(), new ArrayList<CardIssuerModel>(), R.layout.item_list_cardissuer);
        mAdapter.setOnItemClickListener(new CollectionAdapter.OnItemClickListener<CardIssuerModel, CardIssuerHolder>() {
            @Override
            public void onItemClick(CardIssuerModel item, CardIssuerHolder holder, int position) {

                String amount = SharedPreferencesUtil.getInstance(getContext())
                        .getPreferencesString(SharedPreferencesUtil.KEY_AMOUNT_PREFERENCES);


                SharedPreferencesUtil.getInstance(getContext())
                        .setPreferences(SharedPreferencesUtil.KEY_CARD_ISSUER_PREFERENCES, item.name);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(QuotesFragment.class.getName())
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .replace(R.id.fragments, QuotesFragment.newInstance(amount, paymentId, item.id), QuotesFragment.class.getName())
                        .commit();

            }
        });
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onGetCardIssuerSuccess(final List<CardIssuerModel> cardIssuerModels) {
        Log.d(TAG, "onGetCardIssuerSuccess: " + cardIssuerModels.toString());
        ProgressDialog.getInstance().dismiss();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (cardIssuerModels.size() > 0) {
                    mAdapter.insertAll(cardIssuerModels);
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), R.string.no_data_available, Toast.LENGTH_SHORT).show();
                    dispatchKeyBack();
                }
            }
        });
    }

    @Override
    public void onGetCardIssuerFailure(final String message) {
        Log.d(TAG, "onGetCardIssuerFailure: " + message);
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
