package com.jingfeng.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * Created by santiago on 2016-05-16.
 */
public class CopyJsonContentUtil {
    public static void main(final String[] args) throws Exception {
        final String fileName = "E:/haobo/cms/json/marketentity.json";
        final String outputName = "C:/Users/santiago/Desktop/a.json";

        byte[] bytes = null;

        bytes = org.apache.commons.io.FileUtils
                .readFileToByteArray(new File(fileName));
        final String csv = new String(bytes);
        final File outputFile = new File(outputName);
        FileUtils.writeByteArrayToFile(outputFile, bytes);
    }
}
