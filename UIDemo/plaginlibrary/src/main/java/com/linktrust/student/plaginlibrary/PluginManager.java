package com.linktrust.student.plaginlibrary;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * 项目名称 :UIDemo
 * 包名:com.linktrust.student.plaginlibrary
 * 类描述:describe
 * 创建人:hh
 * 创建时间:2019/7/12 15:28
 */
public class PluginManager {

    private static final PluginManager instance = new PluginManager();

    public static PluginManager getInstance() {
        return instance;
    }

    private PluginManager() {
    }

    private Context mContext;

    private PlaginApk mPlaginApk;

    public void init(Context context) {

        this.mContext = context.getApplicationContext();

    }

    //加载插件APK
    public void loadApk(String apkPath) {
        PackageInfo packageInfo = mContext.getPackageManager().getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
        if (packageInfo == null) {
            return;
        }

        DexClassLoader classLoader = creatDexClassLoader(apkPath);
        AssetManager am = creatAssetManager(apkPath);
        Resources resources = creatResourse(am);
        mPlaginApk = new PlaginApk(packageInfo, resources, classLoader);

    }

    public PlaginApk getmPlaginApk() {
        return mPlaginApk;
    }

    //创建加载插件apk的DexClassLoader

    private DexClassLoader creatDexClassLoader(String apkPath) {
        File file = mContext.getDir(".dex", Context.MODE_PRIVATE);
        return new DexClassLoader(apkPath, file.getAbsolutePath(), null, mContext.getClassLoader());
    }

    //创建AssetMananger
    private AssetManager creatAssetManager(String apkPath) {
        AssetManager am = null;
        try {
            am = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            method.invoke(am, apkPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return am;
    }

    //创建Resource对象
    private Resources creatResourse(AssetManager am) {
        Resources res = mContext.getResources();
        return new Resources(am,res.getDisplayMetrics(),res.getConfiguration());
    }
}
