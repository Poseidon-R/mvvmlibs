package com.robot.baselibs.ui.web;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebViewClient;
import com.robot.baselibs.R;
import com.robot.baselibs.base.activity.RobotBaseActivity;
import com.robot.baselibs.base.vm.RobotBaseViewModel;
import com.robot.baselibs.configs.constants.IntentConstants;
import com.robot.baselibs.databinding.ActivityWebBinding;
import com.robot.baselibs.ui.web.client.CommonWebChromeClient;
import com.robot.baselibs.view.titlebar.NormalTitleBar;
import com.robot.baselibs.view.webview.WebLayout;

import me.goldze.mvvmhabit.BR;

/**
 * 创建日期：2020/9/14  15:39
 * 类说明:
 *
 * @author：robot
 */
public class PureWebActivity extends RobotBaseActivity<ActivityWebBinding, RobotBaseViewModel> {

    private NormalTitleBar titleBar;

    public String webUrl;
    public String mTitle;
    private AgentWeb mAgentWeb;

    public static void launch(Activity baseActivity, String webUrl, String title) {
        Intent intent = new Intent(baseActivity, PureWebActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(IntentConstants.WEB_URL, webUrl);
        bundle.putString(IntentConstants.WEBURLTITLEKEY, title);
        intent.putExtras(bundle);
        baseActivity.startActivity(intent);
    }

    @Override
    protected void initComponents() {
        webUrl = getIntent().getExtras().getString(IntentConstants.WEB_URL);
        mTitle = getIntent().getExtras().getString(IntentConstants.WEBURLTITLEKEY);
        titleBar = new NormalTitleBar();
        addTitleBar(titleBar);
        titleBar.setTitleText(mTitle);
        titleBar.setLeftImageSrc(R.drawable.ic_black_back);
        titleBar.setOnLeftImageListener(v -> finish());
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(binding.container, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebViewClient(mWebViewClient)//WebViewClient ， 与 WebView 使用一致 ，但是请勿获取WebView调用setWebViewClient(xx)方法了,会覆盖AgentWeb DefaultWebClient,同时相应的中间件也会失效。
                .setWebChromeClient(new CommonWebChromeClient()) //WebChromeClient
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setWebLayout(new WebLayout(this))
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go(getUrl());
    }
    private com.just.agentweb.WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.i("Info", "PureWebActivity onPageStarted");
        }
    };

    public String getUrl() {
        return webUrl;
    }
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_web;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mAgentWeb.destroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }
    @Override
    public boolean onKeyDown(int kCode, KeyEvent kEvent) {
        if (kCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return false;
        } else {
            return super.onKeyDown(kCode, kEvent);
        }

    }

}
