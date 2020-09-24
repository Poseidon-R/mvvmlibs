package com.robot.baselibs.pay;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;

import java.util.Map;

//import com.alipay.sdk.app.PayTask;

/**
 * @author vane
 * @since 2018/8/27
 */
public class PayUtils {

    /*
        支付宝支付流程

        Setp1. compile files('libs/alipaySdk-20170725.jar')

        Setp2. 在商户应用工程的AndroidManifest.xml文件里面添加声明：
        <activity
            android:name="com.alipay.sdk.app.H5PayActivity"
            android:configChanges="orientation|keyboardHidden|navigation|screenSize"
            android:exported="false"
            android:screenOrientation="behind"
            android:windowSoftInputMode="adjustResize|stateHidden" >
        </activity>
         <activity
            android:name="com.alipay.sdk.app.H5AuthActivity"
            android:configChanges="orientation|keyboardHidden|navigation"
            android:exported="false"
            android:screenOrientation="behind"
            android:windowSoftInputMode="adjustResize|stateHidden" >
        </activity>

        Setp3. 需要申请的权限
        <uses-permission android:name="android.permission.INTERNET" />
        <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
        <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
        <uses-permission android:name="android.permission.READ_PHONE_STATE" />
        <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

        Setp4. 混淆
        -keep class com.alipay.android.app.IAlixPay{*;}
        -keep class com.alipay.android.app.IAlixPay$Stub{*;}
        -keep class com.alipay.android.app.IRemoteServiceCallback{*;}
        -keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
        -keep class com.alipay.sdk.app.PayTask{ public *;}
        -keep class com.alipay.sdk.app.AuthTask{ public *;}
        -keep class com.alipay.sdk.app.H5PayCallback {
            <fields>;
            <methods>;
        }
        -keep class com.alipay.android.phone.mrpc.core.** { *; }
        -keep class com.alipay.apmobilesecuritysdk.** { *; }
        -keep class com.alipay.mobile.framework.service.annotation.** { *; }
        -keep class com.alipay.mobilesecuritysdk.face.** { *; }
        -keep class com.alipay.tscenter.biz.rpc.** { *; }
        -keep class org.json.alipay.** { *; }
        -keep class com.alipay.tscenter.** { *; }
        -keep class com.ta.utdid2.** { *;}
        -keep class com.ut.device.** { *;}
     */

    /**
     * 支付宝 支付
     * <p>
     * 异步请求该接口
     *
     * @param payResult 又臭又长的orderInfo
     * @return 返回支付结果
     */
    public static boolean aliPay(Activity activity, String payResult) {
        PayTask alipay = new PayTask(activity);
        Map<String, String> result = alipay.payV2(payResult, true);
        Log.d("OkHttp", "result = " + result.get("result"));
        if (TextUtils.equals(result.get("resultStatus"), "9000")) {
            JSONObject resultJson = JSON.parseObject(result.get("result"));
            JSONObject payResponse = resultJson.getJSONObject("alipay_trade_app_pay_response");
            if (TextUtils.equals(payResponse.getString("code"), "10000")) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    /*
        微信支付流程

        Step1. 商户APP工程中引入微信JAR包，调用API前，需要先向微信注册您的APPID，代码如下：
               final IWXAPI msgApi = WXAPIFactory.createWXAPI(context, null);
               msgApi.registerApp(WX_APP_ID);

        Step2. 商户服务器生成支付订单，先调用统一下单API(详见第7节)生成预付单，获取到prepay_id后将参数再次签名传输给APP发起支付。

        Step3. 调用wechatPay();


        Step4. 在.wxapi包路径中实现WXPayEntryActivity类(包名或类名不一致会造成无法回调)，在WXPayEntryActivity类中实现onResp函数，支付完成后，
        微信APP会返回到商户APP并回调onResp函数，开发者需要在该函数中接收通知，判断返回错误码，如果支付成功则去后台查询支付结果再展示用户实际支付结果。
        注意一定不能以客户端返回作为用户支付的结果，应以服务器端的接收的支付通知或查询API返回的结果为准。


        Step5. 在AndroidManifest.xml 中注册 WXPayEntryActivity
        注销注册 if (msgApi != null) msgApi.unregisterApp();

        public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

            private static final String TAG = "WXPayEntryActivity";

            private IWXAPI api;

            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

                api = WXAPIFactory.createWXAPI(this, WXEntryActivity.WX_APP_ID);
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
                Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);

                if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("支付结果");
                    builder.setMessage("：" + resp.errCode);
                    builder.show();
                }
            }
        }

     */



    /**
     * * 微信支付
     * <p>
     * 异步请求该接口
     *
     * @param payResult 预支付订单 Json
     * @param extData   附带数据
     */
    public static PayReq wechatPay(String payResult) {
        JSONObject json = JSON.parseObject(payResult);
        PayReq req = new PayReq();
        req.appId = json.getString("appid");
        req.partnerId = json.getString("partnerid");
        req.prepayId = json.getString("prepayid");
        req.nonceStr = json.getString("noncestr");
        req.timeStamp = json.getString("timestamp");
        req.packageValue = json.getString("package");
        req.sign = json.getString("sign");
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        return req;
    }

}
