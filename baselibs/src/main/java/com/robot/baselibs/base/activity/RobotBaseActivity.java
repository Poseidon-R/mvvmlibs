package com.robot.baselibs.base.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.robot.baselibs.R;
import com.robot.baselibs.base.BaseView;
import com.robot.baselibs.base.callback.RobotCallBackBoolean;
import com.robot.baselibs.util.DKHandler;
import com.robot.baselibs.util.TitleBarUtils;
import com.robot.baselibs.util.ToastUtils;
import com.robot.baselibs.view.CommonDialog;
import com.robot.baselibs.view.DebugWatermarkText;
import com.robot.baselibs.view.TipDialog;
import com.robot.baselibs.view.statubar.QMUIStatusBarHelper;
import com.robot.baselibs.view.titlebar.ITitleBar;
import com.robot.baselibs.view.titlebar.SingleTextTitle;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

public abstract class RobotBaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseActivity<V, VM> implements BaseView {
    protected TipDialog loadingDialog;
    private CompositeDisposable mDisposables = new CompositeDisposable();
    private NetworkReceiver mReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initStatusBar();
        //初始化组件
        initComponents();

        obtainData();
        initReceiver();

    }

    private void initReceiver() {
        mReceiver = new NetworkReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(mReceiver, filter);
    }

    //监听网络状态的广播
    private class NetworkReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeInfo = manager.getActiveNetworkInfo();
                if (null == activeInfo) {
                    notNetWork();
                } else {
                    hasNetWork();
                }
            }
        }
    }

    public void notNetWork() {

    }

    public void hasNetWork() {

    }

    protected void initStatusBar() {
        QMUIStatusBarHelper.setStatusBarLightMode(this);
    }

    public void setTranslucent(Activity context, View view) {
        if (view != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
                view.setPadding(0, QMUIStatusBarHelper.getStatusbarHeight(context), 0, 0);
            }
        }
    }


    /**
     * 初始化组件
     */
    protected abstract void initComponents();

    /**
     * 添加标题栏
     */
    public void addTitleBar(ITitleBar iTitleBar) {
        if (iTitleBar == null) {
            return;
        }

        int titleBarResId = iTitleBar.getViewResId();

        View toolbarContainer = TitleBarUtils.init(this, titleBarResId);

        View viewById = toolbarContainer.findViewById(R.id.image_left);
        if (viewById != null) {
            viewById.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

        iTitleBar.onBindTitleBar(toolbarContainer);
    }

    public void addSingleTitleBar(String title) {
        addTitleBar(new SingleTextTitle(title));
    }

    public void obtainData() {
    }

    @Override
    public void showLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
        loadingDialog = new TipDialog.Builder(this)
                .setIconType(TipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();
        loadingDialog.show();
    }

    @Override
    public void dismissLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void showToast(String message) {
        try {
            ToastUtils.showShort(message);
        } catch (Exception ignored) {
        }
    }

    @Override
    public void tokenInvalid() {
//      EMClient.getInstance().logout(true);
        CommonDialog.Builder builder = new CommonDialog.Builder(this);
        builder.setTitle("您的飞歌账号在其他设备上通过飞歌密码登录，如果这不是你的操作，你的飞歌密码已经泄露，请修改飞歌密码")
                .setPositiveButton("确定", Color.parseColor("#141414"), new RobotCallBackBoolean() {
                    @Override
                    public void action(int type) {
//                        DKUserManager.resetToken();
//                        DKUserManager.resetUserInfo();
//                        ActivityUtils.finishAllActivities();
//                        try {
//                            startActivity(new Intent(this,
//                                    Class.forName(getString(R.string.login_activity_path))));
//                        } catch (ClassNotFoundException e) {
//                            e.printStackTrace();
//                        }
//                        builder.getDialog().dismiss();
                    }
                })
                .create()
                .show();
    }

    @Override
    public void addNetworkRequest(Disposable d) {
        mDisposables.add(d);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposables != null) {
            mDisposables.clear();
        }
        unregisterReceiver(mReceiver);
    }

    @Override
    protected void onPause() {
        ToastUtils.cancel();
        super.onPause();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            DebugWatermarkText.addDebugTextView(this);
        }
    }

    protected void postDelayedToFinish() {
        new DKHandler(this).postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1000);
    }

    protected void destoryWebview(WebView dkWebView) {
        if (dkWebView != null) {
            ViewGroup parent = (ViewGroup) dkWebView.getParent();
            if (parent != null) {
                parent.removeView(dkWebView);
            }
            dkWebView.removeAllViews();
            dkWebView.destroy();
        }
    }

    @Override
    public void finishActivity() {
        finish();
    }

}
