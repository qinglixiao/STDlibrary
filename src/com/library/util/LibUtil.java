package com.library.util;

import android.content.Context;
import android.graphics.Rect;
import android.os.Debug;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

public class LibUtil {

    /**
     * 描          述 ：获取本机手机号码
     * 创建日期  : 2014-12-9
     * 作           者 ： lx
     * 修改日期  :
     * 修   改   者 ：
     *
     * @param context
     * @return
     * @version : 1.0
     */
    public static String getPhoneNumber(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String simSerialNum = telephonyManager.getLine1Number();
        if (!TextUtils.isEmpty(simSerialNum))
            return simSerialNum.substring(simSerialNum.length() - 11);
        return simSerialNum;
    }

    /**
     * 将软键盘遮盖的部分推至顶部
     *
     * @param root         最外层布局，需要调整的布局
     * @param scrollToView 被键盘遮挡的scrollToView，滚动root,使scrollToView在root可视区域的底部
     */
    private void controlKeyboardLayout(final View root, final View scrollToView) {
        root.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                // 获取root在窗体的可视区域
                root.getWindowVisibleDisplayFrame(rect);
                // 获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
                int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;

                // 如果内容被遮挡则将内容上推至软键盘顶部
                if (rootInvisibleHeight > 100) {
                    int[] location = new int[2];
                    // 获取scrollToView在窗体的坐标
                    scrollToView.getLocationInWindow(location);
                    // 计算root滚动高度，使scrollToView在可见区域
                    int srollHeight = (location[1] + scrollToView.getHeight()) - rect.bottom;
                    root.scrollTo(0, srollHeight);
                } else {
                    // 键盘隐藏
                    root.scrollTo(0, 0);
                }
            }
        });
    }

    /**
     * 获取当前分配的内存堆大小
     *
     * @return
     */
    public static long getHeapSize() {
        Runtime runtime = Runtime.getRuntime();
        long heapMemory = runtime.totalMemory() - runtime.freeMemory();
        return heapMemory;
    }

    /**
     * 获取系统堆大小
     *
     * @return
     */
    public static long getHeapNativeSize() {
        return Debug.getNativeHeapSize();
    }

}