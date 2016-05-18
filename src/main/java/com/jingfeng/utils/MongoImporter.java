package com.jingfeng.utils;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

/**
 * Created by santiago on 2016-05-14.
 */
public class MongoImporter {

    public static String csvImportCommand = "mongoimport -u %userName% -p %password% --db %dbName% --collection %collectionName% --type csv --headerline --ignoreBlanks --file %fileName%";
    public static String jsonImportCommand = "mongoimport -u %userName% -p %password% --db %dbName% --collection %collectionName% --jsonArray --file %fileName%";

    public static String dbName = "jingfeng_db";
    public static String userName = "jingfeng";
    public static String password = "password";//change accordingly
    public static String collection_prefix = "offLS_";//you can add a collection prefix. the first letter should be lower case, eg: not "OffLS".

    public static void main(final String[] args) {

//        final String inputFolderName = "E:/haobo/cms/csv";
//        generateCMD("csv", inputFolderName);
        final String inputFolderName = "E:/haobo/cms/json";
        generateCMD("json", inputFolderName);
    }

    public static void generateCMD(final String extension, final String inputFolderName) {
        String cmd = "";
        if (extension.equals("json")) {
            cmd = jsonImportCommand;
        } else {
            cmd = csvImportCommand;
        }
        //make sure csv files are using utf8 encoding
        final File folder = new File(inputFolderName);
        final File[] files = folder.listFiles();
        final String[] cmdList = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            final File file = files[i];
            final String collectionName = collection_prefix + FilenameUtils.removeExtension(file.getName());
            final String fullFileName = file.getAbsolutePath();
            final String outCMD = cmd.replace("%userName%", userName).replace("%password%", password).replace("%dbName%", dbName).replace("%collectionName%", collectionName).replace("%fileName%", fullFileName);
            cmdList[i] = outCMD;
            System.out.println(outCMD);//copy the out and paste it to cmd, then it'll start importing.
        }
    }


}
