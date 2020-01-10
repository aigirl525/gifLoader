package com.example.gifloaderapplication.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by luo on 20/01/10.
 * 加载gif
 */
public class GifDecoder {
    public static Context context;

    public static class Gif {
        public static GifDecoder instance = new GifDecoder();
    }

    public static GifDecoder with(Context c) {
        context = c;
        return Gif.instance;
    }


    /**
     * load gif file form inputstream
     */
    public GifDrawer load(InputStream is) {
        GifDrawer drawer = new GifDrawer();
        drawer.setIs(is);
        return drawer;
    }
    /**
     * load png file form inputstream
     */
    public GifDrawer loadPng(InputStream is) {
        GifDrawer drawer = new GifDrawer();
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        drawer.setBitmap(bitmap);
        return drawer;
    }
    /**
     * load gif file form uri
     */
    public GifDrawer load(Uri uri) {
        InputStream is = null;
        try {
            is = context.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return load(is);
    }

    /**
     * load gif file form sdcard
     */
    public GifDrawer load(File file) {
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return load(is);
    }

    /**
     * load gif file form url
     */
    public GifDrawer load(String url) {
        String name = Md5Utils.getMD5(url);
        System.out.println(name);

        FileInputStream is = null;
        String path = context.getExternalCacheDir() + File.separator + name;

        File file = new File(path);
        if (file.exists()) {
            try {
                is = new FileInputStream(file);
                //加载不是gif的图片
                if (!"gif".equals(url.substring(url.lastIndexOf(".") + 1))){
                    return loadPng(is);
                }
                return load(is);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
//           不存在 先下载下来
            GifDrawer gifDrawer = new GifDrawer();
            GifLoaderTask loadGifTask = new GifLoaderTask(gifDrawer, context);
            loadGifTask.execute(url);
            return gifDrawer;
        }
        //加载不是gif的图片
        if (!"gif".equals(url.substring(url.lastIndexOf(".") + 1))){
            return loadPng(is);
        }
        return load(is);
    }


}
