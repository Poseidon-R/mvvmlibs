package com.robot.baselibs.utils.pic;

import android.net.Uri;
import android.os.Environment;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.compress.CompressConfig;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.LubanOptions;
import org.devio.takephoto.model.TakePhotoOptions;

import java.io.File;

/**
 * @program: fcj
 * @description:
 * @author: zk
 * @create: 2019-09-12 09:36
 **/
public class TakePhotoConfig {
    public static final String TYPE_PHOTO = "TYPE_PHOTO";
    public static final String TYPE_ALBUM = "TYPE_ALBUM";

    private static volatile TakePhotoConfig instance = null;
    private int photoNum;
    private TakePhoto takePhoto;

    public static final TakePhotoConfig getInstance() {
        if (instance == null) {
            synchronized (TakePhotoConfig.class) {
                if (instance == null) {
                    instance = new TakePhotoConfig();
                }
            }
        }

        return instance;
    }


    private void configTakePhotoOption() {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(true);
        takePhoto.setTakePhotoOptions(builder.create());
    }

    private void configCompress() {
        LubanOptions option = new LubanOptions.Builder()
                .setMaxHeight(480)
                .setMaxWidth(480)
                .setMaxSize(102400)
                .create();
        CompressConfig config = CompressConfig.ofLuban(option);
        config.enableReserveRaw(true);
        takePhoto.onEnableCompress(config, true);
    }

    private CropOptions getCropOptions() {
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setOutputX(400).setOutputY(400);
        builder.setWithOwnCrop(false);
        return builder.create();
    }

    public void setPhotoNum(int num) {
        photoNum = num;
    }

    public void setTakePhoto(TakePhoto takePhoto) {
        this.takePhoto = takePhoto;
    }

    public void choseImage(String type) {
        configTakePhotoOption();
        configCompress();
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri imageUri = Uri.fromFile(file);
        switch (type) {
            case TYPE_ALBUM:
                if (photoNum > 1) {
                    takePhoto.onPickMultipleWithCrop(photoNum, getCropOptions());
                    return;
                }
                takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions());
                break;
            case TYPE_PHOTO:
                takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
                break;
            default:
                break;
        }
    }
}
