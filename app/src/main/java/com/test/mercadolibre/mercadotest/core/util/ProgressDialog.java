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
import android.widget.ProgressBar;

import com.test.mercadolibre.mercadotest.R;

public class ProgressDialog extends DialogFragment {

    private static final String TAG = ProgressDialog.class.getSimpleName();
    private static ProgressDialog instance = new ProgressDialog();

    private ProgressBar progressBar;
    private int show = 0;
    private int dismiss = 0;


    public static ProgressDialog getInstance() {
        return instance;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.progress_dialog,container, false);
        progressBar = view.findViewById(R.id.progress_dialog_wehpah);
        progressBar.setIndeterminate(true);
        return view;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        this.show++;
        if(show==1) {
            super.show(manager, tag);
        }
    }

    @Override
    public void dismiss() {
        try {
            if(show!=0 && dismiss == --show) {
                show=0;
                super.dismiss();
            }
        }catch (RuntimeException e){
            Log.e(TAG,e.getMessage());
            e.printStackTrace();
        }

    }
}
