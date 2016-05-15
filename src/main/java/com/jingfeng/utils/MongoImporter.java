package com.jingfeng.utils;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

/**
 * Created by santiago on 2016-05-14.
 */
public class MongoImporter {

    public static String folderName = "E:/haobo/cms/csv";//config csv folder

    public static String importCommand = "mongoimport -u %userName% -p %password% --db %dbName% --collection %collectionName% --type csv --headerline --ignoreBlanks --file %fileName%";

    public static String dbName = "jingfeng_db";
    public static String userName = "jingfeng";
    public static String password = "password";//change accordingly
    public static String collection_prefix = "offLS_";//you can add a collection prefix. the first letter should be lower case, eg: not "OffLS".

    public static void main(final String[] args){
        //make sure csv files are using utf8 encoding
        final String[] extensions = {"csv"};
        final File folder = new File(folderName);
        final File[] files = folder.listFiles();
        final String[] cmdList = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            final File file = files[i];
            final String collectionName = collection_prefix + FilenameUtils.removeExtension(file.getName());
            final String fullFileName = file.getAbsolutePath();
            final String cmd = importCommand.replace("%userName%", userName).replace("%password%", password).replace("%dbName%", dbName).replace("%collectionName%", collectionName).replace("%fileName%", fullFileName);
            cmdList[i] = cmd;
            System.out.println(cmd);//copy the out and paste it to cmd, then it'll start importing.
        }
    }

}
