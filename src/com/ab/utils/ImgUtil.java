package com.ab.utils;

/**
 * Created by guocui on 2017/10/18.
 */
import java.io.*;

public class ImgUtil {
    public static byte[] getImageByte(String imagePath) throws IOException {
        File file = new File(imagePath);
        byte[] b = new byte[(int) file.length()];
        InputStream inputStream = new FileInputStream(file);
        inputStream.read(b);
        inputStream.close();
        return b;
    }
    public static void saveImg(File file, byte[] b) throws Exception{
        OutputStream out = new FileOutputStream(file);
        out.write(b);
        out.close();
    }
}
