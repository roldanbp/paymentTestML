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
import com.test.mercadolibre.mercadotest.core.adapters.PaymenthMethodsAdapter;
import com.test.mercadolibre.mercadotest.core.holders.PaymenthMethodHolder;
import com.test.mercadolibre.mercadotest.core.interfaces.mvp.payment.PaymentMethodContract;
import com.test.mercadolibre.mercadotest.core.interfaces.mvp.payment.PaymentMethodPresenter;
import com.test.mercadolibre.mercadotest.core.models.PaymentMethodModel;
import com.test.mercadolibre.mercadotest.core.util.ProgressDialog;
import com.test.mercadolibre.mercadotest.core.util.SharedPreferencesUtil;
import com.test.mercadolibre.mercadotest.ui.activitys.MainActivity;

import java.util.ArrayList;
import java.util.List;


public class PaymenthMethodFragment extends CommonFragment implements PaymentMethodContract.View {

    private String TAG = PaymenthMethodFragment.class.getName();
    private PaymentMethodContract.Presenter mPaymentMethodPresenter;
    private RecyclerView mRecycler;
    private PaymenthMethodsAdapter mAdapter;


    public static PaymenthMethodFragment newInstance() {
        PaymenthMethodFragment fragment = new PaymenthMethodFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.payment_method_fragment;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        init();
        mPaymentMethodPresenter = new PaymentMethodPresenter(this);
        mPaymentMethodPresenter.PaymentMethod(getParent());
    }

    public void init() {
        ((MainActivity) getParent()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getParent()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.setRecycler();
    }

    public void setRecycler() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new PaymenthMethodsAdapter(getContext(), new ArrayList<PaymentMethodModel>(), R.layout.item_list_paymentmethod);
        mAdapter.setOnItemClickListener(new CollectionAdapter.OnItemClickListener<PaymentMethodModel, PaymenthMethodHolder>() {
            @Override
            public void onItemClick(PaymentMethodModel item, PaymenthMethodHolder holder, int position) {

                SharedPreferencesUtil.getInstance(getContext())
                        .setPreferences(SharedPreferencesUtil.KEY_PAYMENT_METHOD_PREFERENCES, item.name);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CardIssuerFragment.class.getName())
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .replace(R.id.fragments, CardIssuerFragment.newInstance(item.id), CardIssuerFragment.class.getName())
                        .commit();
            }
        });
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onGetPaymentMethodSuccess(final List<PaymentMethodModel> paymentMethodModel) {
        Log.d(TAG, "onGetPaymentMethodSuccess: " + paymentMethodModel.toString());
        ProgressDialog.getInstance().dismiss();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (paymentMethodModel.size() > 0) {
                    mAdapter.insertAll(paymentMethodModel);
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), R.string.no_data_available, Toast.LENGTH_SHORT).show();
                    dispatchKeyBack();
                }
            }
        });

    }

    @Override
    public void onGetPaymentMethodFailure(final String message) {
        Log.d(TAG, "onGetPaymentMethodFailure: " + message);
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
