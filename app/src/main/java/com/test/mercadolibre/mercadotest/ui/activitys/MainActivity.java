package com.test.mercadolibre.mercadotest.ui.activitys;

import android.os.Bundle;
import android.view.KeyEvent;

import com.test.mercadolibre.mercadotest.R;
import com.test.mercadolibre.mercadotest.abstraction.activity.CommonActivity;
import com.test.mercadolibre.mercadotest.abstraction.fragment.CommonFragment;
import com.test.mercadolibre.mercadotest.ui.fragments.AmountFragment;

public class MainActivity extends CommonActivity {


    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected int getAppBarId() {
        return R.id.appbar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar(null);
        initFragment();
    }


    public void initFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(AmountFragment.class.getName())
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.fragments, AmountFragment.newInstance(false), AmountFragment.class.getName())
                .commit();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            CommonFragment f = (CommonFragment) getSupportFragmentManager().findFragmentById(R.id.fragments);
            if (f instanceof AmountFragment) {
                finish();
            } else if (f != null && !f.onBackPressed()) {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    new android.os.Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            getSupportFragmentManager().popBackStack();
                        }
                    });
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
