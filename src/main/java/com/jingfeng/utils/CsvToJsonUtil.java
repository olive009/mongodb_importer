package com.jingfeng.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by santiago on 2016-05-15.
 */
public class CsvToJsonUtil {

    private static final String separator = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";//ignore text contains comma also.

    public static void main(final String[] args) throws Exception {

        final String fileName = "E:\\study\\vaadin\\workspace\\mongodb_importer\\data\\marketentity.csv";
        final String outputName = "C:/Users/santiago/Desktop/a.json";

        convert(fileName, outputName);
        /*final String inputFolderName = "E:\\haobo\\cms\\csv";
        final String outputFolderName = "E:/haobo/cms/json";
        convertByFolder(inputFolderName, outputFolderName);*/
    }

    public static void convertByFolder(final String inputFolderName, final String outputFolderName) {
        final File folder = new File(inputFolderName);
        final File[] files = folder.listFiles();
        for (final File file : files) {
            final String fileName = FilenameUtils.removeExtension(file.getName());
            final String outputFileName = outputFolderName + File.separator + fileName + ".json";
            final String fullFileName = file.getAbsolutePath();
            convert(fullFileName, outputFileName);
        }
    }

    public static void convert(final String intputFileName, final String outputFileName) {
        try {
            final String fileString = getJSONFromFile(intputFileName, separator);
            System.out.println(fileString);
            final File outputFile = new File(outputFileName);
            FileUtils.writeStringToFile(outputFile, fileString);
        } catch (final Exception e) {
            e.printStackTrace();
        }

    }

    /*public static String getJSONFromURL(final URL url, final String separator) throws IOException {

        final String csv = IOUtils.toString(url);
        return getJSON(csv, separator);
    }*/

    public static String getJSONFromFile(final String fileName, final String separator)
            throws IOException {

        byte[] bytes = null;

        bytes = org.apache.commons.io.FileUtils
                .readFileToByteArray(new File(fileName));

        final String csv = new String(bytes);
        return getJSON(csv, separator);


    }

    public static String getJSON(final String content, final String separator) {

        final StringBuilder sb = new StringBuilder("[\n");

        final String csv = content;
        final String[] csvValues = csv.split("\n");
        final String[] header = csvValues[0].split(separator, -1);

        for (int i = 1; i < csvValues.length; i++) {
            sb.append("\t").append("{").append("\n");
            final String[] tmp = csvValues[i].split(separator, -1);
            if (header.length != tmp.length) {
                System.out.println("shit");
            }
            for (int j = 0; j < tmp.length; j++) {
                try {
                    final String headerText = header[j].trim();
                    final String contentText = tmp[j].trim();
                    sb.append("\t").append("\t\"").append(headerText).append("\":").append(contentText).append("");
                    if (j < tmp.length - 1) {
                        sb.append(",\n");
                    } else {
                        sb.append("\n");
                    }
                } catch (final ArrayIndexOutOfBoundsException e) {
                    System.out.println("header[j-1}: " + header[j - 1]);
                    System.out.println(tmp[j]);
                }
            }
            if (i < csvValues.length - 1) {
                sb.append("\t},\n");
            } else {
                sb.append("\t}\n");
            }
        }

        sb.append("]");

        return sb.toString();
    }

}