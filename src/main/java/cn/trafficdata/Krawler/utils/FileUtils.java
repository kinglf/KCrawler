package cn.trafficdata.Krawler.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by Kinglf on 2016/8/12.
 */
public class FileUtils extends org.apache.commons.io.FileUtils{
    private static Logger logger= LoggerFactory.getLogger(FileUtils.class);
    public static boolean copyFile(String srcFileName,String descFileName){
        return true;
    }
    public static boolean copyFileCover(String srcFileName, String descFileName, boolean coverlay)
    {
        File srcFile = new File(srcFileName);
        if (!srcFile.exists())
        {
            logger.debug("复制文件失败，源文件 " + srcFileName + " 不存在!");
            return false;
        }
        if (!srcFile.isFile())
        {
            logger.debug("复制文件失败，" + srcFileName + " 不是一个文件!");
            return false;
        }
        File descFile = new File(descFileName);
        if (descFile.exists())
        {
            if (coverlay)
            {
                logger.debug("目标文件已存在，准备删除!");
                if (!delFile(descFileName))
                {
                    logger.debug("删除目标文件 " + descFileName + " 失败!");
                    return false;
                }
            }
            else
            {
                logger.debug("复制文件失败，目标文件 " + descFileName + " 已存在!");
                return false;
            }
        }
        else if (!descFile.getParentFile().exists())
        {
            logger.debug("目标文件所在的目录不存在，创建目录!");
            if (!descFile.getParentFile().mkdirs())
            {
                logger.debug("创建目标文件所在的目录失败!");
                return false;
            }
        }
        int readByte = 0;
        InputStream ins = null;
        OutputStream outs = null;
        try
        {
            ins = new FileInputStream(srcFile);

            outs = new FileOutputStream(descFile);
            byte[] buf = new byte[1024];
            while ((readByte = ins.read(buf)) != -1) {
                outs.write(buf, 0, readByte);
            }
            logger.debug("复制单个文件 " + srcFileName + " 到" + descFileName + "成功!");
            return true;
        }
        catch (Exception e)
        {
            logger.debug("复制文件失败：" + e.getMessage());
            return false;
        }
        finally
        {
            if (outs != null) {
                try
                {
                    outs.close();
                }
                catch (IOException oute)
                {
                    oute.printStackTrace();
                }
            }
            if (ins != null) {
                try
                {
                    ins.close();
                }
                catch (IOException ine)
                {
                    ine.printStackTrace();
                }
            }
        }
    }

    public static boolean copyDirectory(String srcDirName, String descDirName)
    {
        return copyDirectoryCover(srcDirName, descDirName, false);
    }

    public static boolean copyDirectoryCover(String srcDirName, String descDirName, boolean coverlay)
    {
        File srcDir = new File(srcDirName);
        if (!srcDir.exists())
        {
            logger.debug("复制目录失败，源目录 " + srcDirName + " 不存在!");
            return false;
        }
        if (!srcDir.isDirectory())
        {
            logger.debug("复制目录失败，" + srcDirName + " 不是一个目录!");
            return false;
        }
        String descDirNames = descDirName;
        if (!descDirNames.endsWith(File.separator)) {
            descDirNames = descDirNames + File.separator;
        }
        File descDir = new File(descDirNames);
        if (descDir.exists())
        {
            if (coverlay)
            {
                logger.debug("目标目录已存在，准备删除!");
                if (!delFile(descDirNames))
                {
                    logger.debug("删除目录 " + descDirNames + " 失败!");
                    return false;
                }
            }
            else
            {
                logger.debug("目标目录复制失败，目标目录 " + descDirNames + " 已存在!");
                return false;
            }
        }
        else
        {
            logger.debug("目标目录不存在，准备创建!");
            if (!descDir.mkdirs())
            {
                logger.debug("创建目标目录失败!");
                return false;
            }
        }
        boolean flag = true;

        File[] files = srcDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile())
            {
                flag = copyFile(files[i].getAbsolutePath(), descDirName + files[i].getName());
                if (!flag) {
                    break;
                }
            }
            else if (files[i].isDirectory())
            {
                flag = copyDirectory(files[i].getAbsolutePath(), descDirName + files[i].getName());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag)
        {
            logger.debug("复制目录 " + srcDirName + " 到 " + descDirName + " 失败!");
            return false;
        }
        logger.debug("复制目录 " + srcDirName + " 到 " + descDirName + " 成功!");
        return true;
    }

    public static boolean delFile(String fileName)
    {
        File file = new File(fileName);
        if (!file.exists())
        {
            logger.debug(fileName + " 文件不存在!");
            return true;
        }
        if (file.isFile()) {
            return deleteFile(fileName);
        }
        return deleteDirectory(fileName);
    }

    public static boolean deleteFile(String fileName)
    {
        File file = new File(fileName);
        if ((file.exists()) && (file.isFile()))
        {
            if (file.delete())
            {
                logger.debug("删除单个文件 " + fileName + " 成功!");
                return true;
            }
            logger.debug("删除单个文件 " + fileName + " 失败!");
            return false;
        }
        logger.debug(fileName + " 文件不存在!");
        return true;
    }

    public static boolean deleteDirectory(String dirName)
    {
        String dirNames = dirName;
        if (!dirNames.endsWith(File.separator)) {
            dirNames = dirNames + File.separator;
        }
        File dirFile = new File(dirNames);
        if ((!dirFile.exists()) || (!dirFile.isDirectory()))
        {
            logger.debug(dirNames + " 目录不存在!");
            return true;
        }
        boolean flag = true;

        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile())
            {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
            else if (files[i].isDirectory())
            {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag)
        {
            logger.debug("删除目录失败!");
            return false;
        }
        if (dirFile.delete())
        {
            logger.debug("删除目录 " + dirName + " 成功!");
            return true;
        }
        logger.debug("删除目录 " + dirName + " 失败!");
        return false;
    }

    public static boolean createFile(String descFileName)
    {
        File file = new File(descFileName);
        if (file.exists())
        {
            logger.debug("文件 " + descFileName + " 已存在!");
            return false;
        }
        if (descFileName.endsWith(File.separator))
        {
            logger.debug(descFileName + " 为目录，不能创建目录!");
            return false;
        }
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs())
            {
                logger.debug("创建文件所在的目录失败!");
                return false;
            }
        }
        try
        {
            if (file.createNewFile())
            {
                logger.debug(descFileName + " 文件创建成功!");
                return true;
            }
            logger.debug(descFileName + " 文件创建失败!");
            return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.debug(descFileName + " 文件创建失败!");
        }
        return false;
    }

    public static boolean createDirectory(String descDirName)
    {
        String descDirNames = descDirName;
        if (!descDirNames.endsWith(File.separator)) {
            descDirNames = descDirNames + File.separator;
        }
        File descDir = new File(descDirNames);
        if (descDir.exists())
        {
            logger.debug("目录 " + descDirNames + " 已存在!");
            return false;
        }
        if (descDir.mkdirs())
        {
            logger.debug("目录 " + descDirNames + " 创建成功!");
            return true;
        }
        logger.debug("目录 " + descDirNames + " 创建失败!");
        return false;
    }

    public static void writeFile(String content, String filePath)
    {
        try
        {
            if (createFile(filePath))
            {
                FileWriter fileWriter = new FileWriter(filePath, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(content);
                bufferedWriter.close();
                fileWriter.close();
            }
            else
            {
                logger.info("生成失败，文件已存在！");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        File dir = getFile(new String[] { "E:\\Project\\eclipse\\workspace\\crawler\\lib" });
        if (dir.isDirectory())
        {
            String[] files = dir.list();
            int i = 0;
            for (String file : files)
            {
                if (i == 0) {
                    System.out.println("Class-Path:  lib/" + file);
                } else {
                    System.out.println("  lib/" + file);
                }
                i++;
            }
        }
    }
    public static void appendFile(String fileName, String content)
    {
        try
        {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void createDir(String path)
    {
        File file = new File(path);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
    }
    public static String getFileExtName(String url){
        return url.substring(url.lastIndexOf(".")+1,url.length());
    }
}
