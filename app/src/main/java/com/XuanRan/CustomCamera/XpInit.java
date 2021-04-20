package com.XuanRan.CustomCamera;

import android.content.res.AssetManager;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created By XuanRan on 2021/4/14
 */
public class XpInit implements IXposedHookLoadPackage {

    public static final String MYPPACKAGE_NAME = "com.XuanRan.CustomCamera";
    public static final String HOOK_NAME = "com.android.camera";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        if (lpparam.packageName.equals(HOOK_NAME) || lpparam.packageName.equals(MYPPACKAGE_NAME)) {

            Method open = AssetManager.class.getDeclaredMethod("openFd", String.class);

            XposedBridge.hookMethod(open, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);

                    String filename = (String) param.args[0];

                    String[] end = filename.split("\\.");

                    String find = end[end.length-1];
                    if (find.equals("mp3") || find.equals("ogg")){
                        param.setResult(null);
                   }

                }
            });
        }
    }
}
