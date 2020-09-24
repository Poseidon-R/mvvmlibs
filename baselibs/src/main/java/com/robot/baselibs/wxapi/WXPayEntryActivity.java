package com.robot.baselibs.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.alibaba.fastjson.JSON;
import com.robot.baselibs.R;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import static com.robot.baselibs.configs.WxConstants.PAY_FAILED;
import static com.robot.baselibs.configs.WxConstants.PAY_SUCCESS;


/**
 * 作者：robot
 * 创建日期：2020/9/11  11:37
 * 类说明:微信支付回调类
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "WXPayEntryActivity";

    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wx_pay);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        String appId = "";
        api = WXAPIFactory.createWXAPI(this,appId);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.d("PayResult", JSON.toJSONString(resp));
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            //如果支付成功
            if (resp.errCode == 0) {
                LocalBroadcastManager.getInstance(this).
                        sendBroadcast(new Intent(PAY_SUCCESS));
            }
            //取消或者失败
            else {
                LocalBroadcastManager.getInstance(this).
                        sendBroadcast(new Intent(PAY_FAILED));
            }
            finish();

        }
    }

}