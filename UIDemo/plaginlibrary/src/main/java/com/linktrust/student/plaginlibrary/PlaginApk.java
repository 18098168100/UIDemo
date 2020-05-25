package com.linktrust.student.plaginlibrary;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

/**
 * 插件化步骤：
 * 1、创建 DexClassLoader加载插件代码
 * 2、创建 Resource 加载资源文件
 * 3、管理插件Activity的生命周期
 * 创建人:hh
 * 创建时间:2019/7/12 15:22
 */
public class PlaginApk {
    //插件Apk的实体对象
    public PackageInfo mPackageInfo;
    public Resources mResources;
    public AssetManager mAssetManager;
    public DexClassLoader mDexClassLoader;

    public PlaginApk(PackageInfo mPackageInfo, Resources mResources, DexClassLoader mDexClassLoader) {
        this.mPackageInfo = mPackageInfo;
        this.mResources = mResources;
        this.mAssetManager = mResources.getAssets();
        this.mDexClassLoader = mDexClassLoader;
    }
}
