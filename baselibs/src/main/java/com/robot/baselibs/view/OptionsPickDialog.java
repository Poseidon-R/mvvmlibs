package com.robot.baselibs.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.robot.baselibs.R;
import com.robot.baselibs.base.callback.RobotCallBackObject;
import com.robot.baselibs.view.wheelview.WheelView;
import com.robot.baselibs.view.wheelview.adapters.ArrayWheelAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author leaflc
 */
public class OptionsPickDialog extends DialogFragment {
    private WheelView wheelView;
    private View view;
    private RobotCallBackObject<String> mListener;

    private List<String> datas = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_options, container, false);
        initDateTimePicker();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        WindowManager.LayoutParams params = window.getAttributes();
        //获得window窗口的属性
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.getAttributes().gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
    }

    public void setData(List<String> data){
        datas.clear();
        datas.addAll(data);
    }


    public void initDateTimePicker() {
        String[] monthsBig = {"1", "3", "5", "7", "8", "10", "12"};
        String[] monthsLittle = {"4", "6", "9", "11"};

        final List<String> listBig = Arrays.asList(monthsBig);
        final List<String> listLittle = Arrays.asList(monthsLittle);
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        view.findViewById(R.id.tv_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mListener == null) {
                    return;
                }
                mListener.action(datas.get(wheelView.getCurrentItem()));
            }
        });
        // 年
        wheelView = view.findViewById(R.id.wv_year);
        //设置可见数量
        wheelView.setVisibleItems(7);
        // 设置"年"的显示数据
        String[] arr = new String[datas.size()];
        datas.toArray(arr);
        wheelView.setViewAdapter(new ArrayWheelAdapter<String>(getContext(), arr));
        // 初始化时显示的数据
        wheelView.setCurrentItem(0);
    }


    public void setListener(RobotCallBackObject<String> mListener) {
        this.mListener = mListener;
    }
}
