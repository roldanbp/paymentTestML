package com.test.mercadolibre.mercadotest.abstraction.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.test.mercadolibre.mercadotest.core.util.DisplayUtil;

public abstract class CommonActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private CharSequence mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar(null);
    }

    public void setToolbar(@Nullable Toolbar toolbar) {
        if (toolbar == null)
            this.toolbar = (Toolbar) findViewById(getToolbarId());
        else
            this.toolbar = toolbar;
        if (this.toolbar != null) {
            setSupportActionBar(this.toolbar);
            if (getSupportActionBar() != null)
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                if (hasToolbarPadding()) {
                    AppBarLayout appBar = (AppBarLayout) findViewById(getAppBarId());
                    if (appBar != null)
                        appBar.setPadding(0, DisplayUtil.getStatusBarHeight(this), 0, 0);
                }
            }

        }
    }




    public Toolbar getToolbar() {
        return this.toolbar;
    }


    /**
     * abstract methods
     */
    /**
     * Get toolbar layout id
     *
     * @return R.id.toolbar id if your layout has declared one, otherwise 0
     */
    protected abstract int getToolbarId();

    /**
     * it is used for activity custom toolbar padding in windows status bar translucent feature
     *
     * @return true if you want to setup padding automatically, otherwise false
     */
    protected boolean hasToolbarPadding() {
        return true;
    }

    protected abstract int getAppBarId();

}
