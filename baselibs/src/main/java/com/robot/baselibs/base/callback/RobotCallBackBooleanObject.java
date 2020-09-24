package com.robot.baselibs.base.callback;

/**
 * 具备判断条件的回调
 */
public interface RobotCallBackBooleanObject<T> {
    int YES=1;
    int NO=0;
    void action(int type, T t);
}