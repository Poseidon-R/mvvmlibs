package com.robot.baselibs.view.titlebar;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.robot.baselibs.R;
import com.robot.baselibs.view.pop.CommonToolMorePop;

/**
 * 创建日期：2020/9/14  14:35
 * 类说明:
 *
 * @author：robot
 */
public class NormalTitleBar  implements ITitleBar{

    private ImageView leftImage ;

    private ImageView ivRight ;

    private TextView titleTextView;

    private TextView leftText;

    private RelativeLayout rightImageOneView;

    private CommonToolMorePop mCommonToolMorePop;

    public static final int LIGHT_MODE = 1;
    public static final int DARK_MODE = 2;

    private int mode = LIGHT_MODE;


    @Override
    public int getViewResId() {
        return R.layout.layout_normal_title;
    }

    @Override
    public void onBindTitleBar(View titleView) {
        leftImage = titleView.findViewById(R.id.image_left);
        leftText = titleView.findViewById(R.id.tv_left);
        titleTextView = titleView.findViewById(R.id.tv_title);
        ivRight = titleView.findViewById(R.id.image_right);
        rightImageOneView = titleView.findViewById(R.id.layout_right_image_one);
    }
    public void setTitleText(String string) {
        if(titleTextView!=null){
            titleTextView.setText(string);
        }
    }
    /**
     * 左图标
     *
     * @param id
     */
    public void setLeftImageSrc(int id) {
        leftImage.setVisibility(View.VISIBLE);
        leftImage.setImageResource(id);
    }

    /*
     * 点击事件
     */
    public void setOnLeftImageListener(View.OnClickListener listener) {
        leftImage.setOnClickListener(listener);
    }

    public void setRightCommonTool(Activity activity, int mode) {
        ivRight.setVisibility(View.VISIBLE);
        if (mode == DARK_MODE) {
            ivRight.setImageResource(R.drawable.ic_white_more);
        } else {
            ivRight.setImageResource(R.drawable.iv_menu);
        }

        rightImageOneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPop(activity);
            }
        });
        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPop(activity);
            }
        });
    }
    private void showPop(Activity activity) {
        if (mCommonToolMorePop == null) {
            mCommonToolMorePop = new CommonToolMorePop(activity);
        }
        mCommonToolMorePop.setOnMoreOptionClickListener(new CommonToolMorePop.OnMoreOptionClickListener() {
            @Override
            public void onMoreOptionClick(int pos) {
//                switch (pos) {
//                    case 0://消息
//                        if (BizUtils.checkLoginStatus(activity)) {
//                            MessageActivity.start(activity);
//                        }
//                        break;
//                    case 1://首页
//                        MainActivity.launch(activity, null);
//                        EventBus.getDefault().postSticky(new GoToMainEvent());
//                        break;
//                    case 2://搜索
//                        SearchGoodsActivity.start(activity);
//                        break;
//                    case 3://购物车
//                        if (BizUtils.checkLoginStatus(activity)) {
//                            MainActivity.launch(activity, null);
//                            EventBus.getDefault().postSticky(new GoToCartEvent());
//                        }
//                        break;
//                    case 4://我的
//                        if (BizUtils.checkLoginStatus(activity)) {
//                            MainActivity.launch(activity, null);
//                            EventBus.getDefault().postSticky(new GoToMineEvent());
//                        }
//                        break;
//                }
//                mCommonToolMorePop.dismiss();
            }
        });
        mCommonToolMorePop.showAsDropDown(ivRight);
    }
}
