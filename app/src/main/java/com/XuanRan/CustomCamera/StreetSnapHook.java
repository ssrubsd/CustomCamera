package com.XuanRan.CustomCamera;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * @author GSWXXN 2021/6/20
 **/
public class StreetSnapHook {
    public final static String targetClassName = "com.android.camera.CameraSettings";

    public static void streetSnapHook(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        Class<?> clazz = XposedHelpers.findClass(targetClassName, lpparam.classLoader);
        XposedHelpers.findAndHookMethod(clazz, "getMiuiSettingsKeyForStreetSnap", String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);

                XposedBridge.log(
                        "Hooked method: com.android.camera.CameraSettings.getMiuiSettingsKeyForStreetSnap()");
                param.setResult("Street-snap-movie");
            }
        });

    }
}
