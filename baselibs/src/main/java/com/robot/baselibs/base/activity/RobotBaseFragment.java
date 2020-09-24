package com.robot.baselibs.base.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.robot.baselibs.base.callback.RobotCallBackBoolean;
import com.robot.baselibs.util.TitleBarUtils;
import com.robot.baselibs.util.ToastUtils;
import com.robot.baselibs.view.CommonDialog;
import com.robot.baselibs.view.TipDialog;
import com.robot.baselibs.view.statubar.QMUIStatusBarHelper;
import com.robot.baselibs.view.titlebar.ITitleBar;
import com.robot.baselibs.view.titlebar.SingleTextTitle;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.goldze.mvvmhabit.base.BaseFragment;

public abstract class RobotBaseFragment extends BaseFragment {

    protected View mContentView;
    protected TipDialog loadingDialog;
    public boolean isCreate = false;
    public boolean isHasLoadOnce = false;
    private int containerId;


    public int getContainerId() {
        return containerId;
    }

    public void setContainerId(int containerId) {
        this.containerId = containerId;
    }

    private CompositeDisposable mDisposables = new CompositeDisposable();
    protected boolean isVisibleToUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreate = true;

    }

    private void load() {
      /*  if (isCreate && getUserVisibleHint() && !isHasLoadOnce) {
            initComponents();
            obtainData();
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
        load();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(getLayoutId(), container, false);
        return mContentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initComponents();
        obtainData();
    }

    public void setTranslucent(Activity context, View view) {
        if (view != null) {
            QMUIStatusBarHelper.translucent(context);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
                view.setPadding(0, QMUIStatusBarHelper.getStatusbarHeight(context), 0, 0);
            }
        }
    }


    /**
     * 添加标题栏
     */
    public void addTitleBar(ITitleBar iTitleBar) {
        if (iTitleBar == null) return;
        int titleBarResId = iTitleBar.getViewResId();
        View toolbarContainer = TitleBarUtils.init(getActivity(), titleBarResId);
        iTitleBar.onBindTitleBar(toolbarContainer);

    }

    public void addSindleTitleBar(String title) {
        addTitleBar(new SingleTextTitle(title));
    }

    /**
     * 一般用于加载网络请求
     * 此方法不是抽象方法，通过覆盖实现，可调用多次
     */
    public void obtainData() {
    }

    /**
     * onCreateView layout id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化组件
     */
    protected abstract void initComponents();


    public void showLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
        loadingDialog = new TipDialog.Builder(getActivity())
                .setIconType(TipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();
        loadingDialog.show();
    }

    public void dismissLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    public void showToast(String message) {
        ToastUtils.showShort(message);
    }

    public void addNetworkRequest(Disposable d) {
        mDisposables.add(d);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mDisposables != null) mDisposables.clear();
    }

    public void tokenInvalid() {
      /*  DKUserManager.resetToken();
        DKUserManager.resetUserInfo();
//        EMClient.getInstance().logout(true);
        ActivityUtils.finishAllActivities();
        try {
            startActivity(new Intent(getContext(),
                    Class.forName(getString(R.string.login_activity_path))));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        CommonDialog.Builder builder = new CommonDialog.Builder(getContext());
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        load();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        this.isVisibleToUser = !hidden;
    }

    public void finishActivity() {

    }
}
