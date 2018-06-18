package com.test.mercadolibre.mercadotest.abstraction.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.test.mercadolibre.mercadotest.abstraction.activity.CommonActivity;

public abstract class CommonFragment extends Fragment {

    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }
        try {
            rootView = inflater.inflate(getLayoutResourceId(), container, false);
        } catch (InflateException e) {
        /* map is already there, just return view as it is */
        }
        onCreateView(savedInstanceState);
        return rootView;
    }


    /**
     * Find any view inside of this fragment view
     *
     * @param id int resource id to find
     * @return View instance object
     */
    protected View findViewById(int id) {
        return rootView != null ? rootView.findViewById(id) : null;
    }

    /*
     * Event handler to go back
     */

    public void dispatchKeyBack() {
        if (getActivity() == null)
            return;
        getActivity().dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
        hideKeyword();
    }

    /*
     * Event handler onBackPressed
     *
     */

    public boolean onBackPressed() {
        return false;
    }

    /*
     * Hide android keyword when needed by getting the active current focus
     */

    protected void hideKeyword() {
        try {
            View view = getActivity().getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive())
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Get layout resource to attach to this fragment
     *
     * @return integer view resource R.layout.layout_example
     */
    protected abstract int getLayoutResourceId();

    /**
     * Implement this method to be able to initialize this fragment
     * Use findViewById(R.id.view_id) to find any view attached to this fragment
     *
     * @param savedInstanceState Bundle
     */
    protected abstract void onCreateView(Bundle savedInstanceState);

    /**
     * Get Keylimetie parent activity instance object
     *
     * @return CommonActivity parent
     */
    public CommonActivity getParent() {
        if (getActivity() instanceof CommonActivity)
            return (CommonActivity) getActivity();
        return null;
    }

}