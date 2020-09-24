package com.robot.baselibs.util;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

import com.robot.baselibs.R;
import com.robot.baselibs.base.callback.RobotCallBack;

/**
 * Date: 2018/7/20.
 * Time: 10:53
 * classDescription:
 *
 * @author fred
 */
public class ViewUtils {
    public static void showTipsDialog(final Activity context, @DrawableRes int res, String title, String content) {
        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle);
        dialog.setContentView(R.layout.dialog_tips);
        ImageView iv_img = dialog.findViewById(R.id.iv_img);
        iv_img.setImageResource(res);

        TextView tvTitle = dialog.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        TextView tvContent = dialog.findViewById(R.id.tv_content);
        if (StringUtil.isValid(content)) {

            tvContent.setVisibility(View.VISIBLE);
            tvContent.setText(content);
        } else {
            tvContent.setVisibility(View.GONE);
        }

        (dialog.findViewById(R.id.tv_send)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                context.finish();
            }
        });

        dialog.show();
    }

    public static void showVertifyDialog(final Activity context, final RobotCallBack callBack) {
        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle);
        dialog.setContentView(R.layout.dialog_vertify);

        (dialog.findViewById(R.id.tv_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                context.finish();
            }
        });
        (dialog.findViewById(R.id.tv_vertify)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.action();
                dialog.dismiss();
                context.finish();
            }
        });

        dialog.show();
    }

}
