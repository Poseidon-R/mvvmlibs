package com.robot.baselibs.base;

import android.app.Dialog;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by kang on 2017/6/14.
 */

public abstract class LibBaseDialog extends DialogFragment implements IView {
    public Dialog loadingDialog;

    @Override
    public void show(FragmentManager manager, String tag) {
        if (!LibBaseDialog.this.isVisible() && !LibBaseDialog.this.isAdded()) {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        }
    }

    @Override
    public void dismiss() {
        dismissAllowingStateLoss();
    }

    public abstract Dialog getLoadingDialog();

    @Override
    public void showLoadingView() {
        if (loadingDialog == null && !isDetached())
            loadingDialog = getLoadingDialog();
        loadingDialog.show();
    }

    @Override
    public void hiddenLoadingView() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }
}
