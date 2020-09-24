package com.robot.baselibs.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;


import com.robot.baselibs.RobotApplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * @author vane
 * @since 2018/2/6
 */

public class FileUtils {

    /**
     * 时间格式
     */
    private static final String TIME_FORMAT = "yyyyMMddHHmmss";

    /**
     * SD 路径
     */
    public static final String SDCARD_DIR = Environment.getExternalStorageDirectory().getPath();


    public static final String FILES_DIR = SDCARD_DIR + "/" + "Android/data/" +
            RobotApplication.getContext().getPackageName() + "/files/";

    /**
     * 默认本地上传图片目录
     */
    public static final String UPLOAD_PHOTO_DIR = SDCARD_DIR + "/upload_photos/";

    /**
     * 系统相机目录
     */
    public static final String CAMERA_PHOTO_DIR =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath() + "/Camera/";

    /**
     * 将Assets文件转化为字符串
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static String readAssetsFile(Context context, String path) {
        String str = null;
        try {
            InputStream is = context.getAssets().open(path);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            str = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * @param timeFormatHeader 格式化的头(除去时间部分)
     * @param extension        后缀名
     * @return 返回时间格式化后的文件名
     */
    public static String getFileNameByTime(String timeFormatHeader, String extension) {
        return getTimeFormatName(timeFormatHeader) + "." + extension;
    }

    private static String getTimeFormatName(String timeFormatHeader) {
        final Date date = new Date(System.currentTimeMillis());
        //必须要加上单引号
        final SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'" + timeFormatHeader + "'" + TIME_FORMAT, Locale.getDefault());
        return dateFormat.format(date);
    }

    /**
     * 创建文件夹
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static File createDir(String sdcardDirName) {
        //拼接成SD卡中完整的dir
        final String dir = SDCARD_DIR + "/" + sdcardDirName + "/";
        final File fileDir = new File(dir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        return fileDir;
    }

    /**
     * 在某个文件夹下创建文件
     *
     * @param sdcardDirName 文件夹名称
     * @param fileName      文件名称
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static File createFile(String sdcardDirName, String fileName) {
        return new File(createDir(sdcardDirName), fileName);
    }

    /**
     * 在某个文件夹下创建带时间格式的文件
     *
     * @param sdcardDirName    文件夹名称
     * @param timeFormatHeader 文件前缀
     * @param extension        文件后缀
     * @return
     */
    private static File createFileByTime(String sdcardDirName, String timeFormatHeader, String extension) {
        final String fileName = getFileNameByTime(timeFormatHeader, extension);
        return createFile(sdcardDirName, fileName);
    }

    /**
     * 获取文件的后缀
     */
    public static String getExtension(String filePath) {
        String suffix = "";
        final File file = new File(filePath);
        final String name = file.getName();
        final int idx = name.lastIndexOf('.');
        if (idx > 0) {
            suffix = name.substring(idx + 1);
        }
        return suffix;
    }

    /**
     * 通知系统刷新系统相册，使照片展现出来
     */
    public static void refreshDCIM(Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            //兼容android4.4版本，只扫描存放照片的目录
            MediaScannerConnection.scanFile(context,
                    new String[]{Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath()},
                    null, null);
        } else {
            //扫描整个SD卡来更新系统图库，当文件很多时用户体验不佳，且不适合4.4以上版本
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" +
                    Environment.getExternalStorageDirectory())));
        }
    }

    /**
     * 获取图片真实路径
     */
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) {
            return null;
        }
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            final Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    final int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }


    public static File compressImageUriToFile(Context context, Uri imageUri) throws Exception {
        String photoName = FileUtils.getFileNameByTime("IMG", "jpg");
        String path = FileUtils.CAMERA_PHOTO_DIR;
        final File tempFile = new File(path, photoName);
        try {
            if (!tempFile.exists()) tempFile.createNewFile();
            Bitmap bitmap = getThumbnail(context, imageUri, 400);
            compressImageToFile(bitmap, tempFile);
        } catch (Exception e) {
            if (!tempFile.exists()) tempFile.createNewFile();
            String imgPath = imageUri.getPath();
            Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
            compressImageToFile(bitmap, tempFile);
        }
        return tempFile;
    }


    /**
     * 图片压缩并输出到文件
     */
    public static void compressImageToFile(Bitmap bmp, File file) throws Exception {
        // 0-100 100为不压缩
        int options = 50;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(baos.toByteArray());
        fos.flush();
        fos.close();
    }


    /**
     * 删除文件
     *
     * @param srcFilePath 文件路径
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteFile(final String srcFilePath) {
        return deleteFile(getFileByPath(srcFilePath));
    }

    /**
     * 根据文件路径获取文件
     *
     * @param filePath 文件路径
     * @return 文件
     */
    public static File getFileByPath(final String filePath) {
        return isSpace(filePath) ? null : new File(filePath);
    }

    private static boolean isSpace(final String s) {
        if (s == null) {
            return true;
        }
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 删除文件
     *
     * @param file 文件
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteFile(final File file) {
        return file != null && (!file.exists() || file.isFile() && file.delete());
    }

    /**
     * 获取图片缩略图
     */
    public static Bitmap getThumbnail(Context context, Uri uri, int size) throws IOException {
        InputStream input = context.getContentResolver().openInputStream(uri);
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1))
            return null;
        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;
//        double ratio = (originalSize > size) ? (originalSize / size) : 1.0;
        double ratio = 1.0;
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither = true;
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
        input = context.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();
        return bitmap;
    }

    private static int getPowerOfTwoForSampleRatio(double ratio) {
        int k = Integer.highestOneBit((int) Math.floor(ratio));
        if (k == 0) return 1;
        else return k;
    }

    public static void readFileToList(Context context, String fileName, List<String> list) {

        FileInputStream fileOutputStream = null;
        BufferedReader reader = null;
        List<String> searchList = new ArrayList<>(1);
        try {
            fileOutputStream = context.openFileInput(fileName);
            reader = new BufferedReader(new InputStreamReader(fileOutputStream));
            String linedata = null;
            while ((linedata = reader.readLine()) != null) {
                searchList.add(linedata);

            }
            list.addAll(searchList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void saveContent(Context context, String fileName, String content) {

        FileOutputStream fileOutputStream = null;
        BufferedWriter writer = null;
        try {
            fileOutputStream = context.openFileOutput(fileName, Context.MODE_APPEND);
            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            writer.write(content + "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static boolean isContain(Context context, String fileName, String content) {

        FileInputStream fileOutputStream = null;
        BufferedReader reader = null;
        boolean isContain = false;


        File file = new File(context.getFilesDir(), fileName);
        if (!file.exists())
            return isContain;

        List<String> searchList = new ArrayList<>(1);
        try {
            fileOutputStream = context.openFileInput(fileName);
            reader = new BufferedReader(new InputStreamReader(fileOutputStream));
            String linedata = null;
            while ((linedata = reader.readLine()) != null) {
                searchList.add(linedata);
            }
            if (searchList.contains(content))
                isContain = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return isContain;

    }

    public static void clearFile(Context context, String fileName) {

        File file = new File(context.getFilesDir(), fileName);
        if (!file.exists())
            return;
        file.delete();
    }

}
