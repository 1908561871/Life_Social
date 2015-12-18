package cn.com.elex.social_life.support.util;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by zhangweibo on 2015/12/3.
 */
public class ScreenUtil {

    public static int getWidth(Context context){

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        return   wm.getDefaultDisplay().getWidth();
    }

    public static int getHeight(Context context){

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        return   wm.getDefaultDisplay().getHeight();

    }
}
