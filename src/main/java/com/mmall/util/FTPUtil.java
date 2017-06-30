package com.mmall.util;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * 项目：  mmall
 * 包名：  com.mmall.util
 * 作者：  chencong
 * 时间：  2017/6/29 21:16.
 * 描述：  FTP工具上传工具
 */
public class FTPUtil {

    private static Logger logger = LoggerFactory.getLogger(FTPUtil.class);

    private static String ftpIp = PropertiesUtil.getProperty("ftp.server.ip");
    private static String ftpUser = PropertiesUtil.getProperty("ftp.user");
    private static String ftpPass = PropertiesUtil.getProperty("ftp.pass");

    public FTPUtil(String ip, int port, String user, String pwd) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    /**
     * 批量文件上传
     *
     * @param filePath 上传文件的目标路径
     * @param fileList 上传文件list
     * @return 返回是否上传成功
     */
    public static boolean uploadFile(String filePath, List<File> fileList) throws IOException {
        FTPUtil ftpUtil = new FTPUtil(ftpIp, 21, ftpUser, ftpPass);
        logger.info("start connection ftp server.......");
        boolean result = ftpUtil.uploadFileDo(filePath, fileList);
        logger.info("started connection ftp server.....End upload file,The upload resulet:{}", result);
        return result;
    }

    /**
     * 批量文件上传
     * 远程路径
     *
     * @param remotePath 服务器下远程路径
     * @param fileList   上传文件list
     * @return 返回是否上传成功
     */
    private boolean uploadFileDo(String remotePath, List<File> fileList) throws IOException {
        boolean uploaded = true;
        FileInputStream fis = null;
        //连接ftp服务器
        if (connectServer(this.ip, this.port, this.user, this.pwd)) {
            //更改工作目录
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                //设置缓冲区
                ftpClient.setBufferSize(1024);
                //设置编码
                ftpClient.setControlEncoding("UTF-8");
                //设置文件为二进制
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                //打开本地被动模式
                ftpClient.enterLocalPassiveMode();
                //开始上传
                for (File fileItem : fileList) {
                    fis = new FileInputStream(fileItem);
                    ftpClient.storeFile(fileItem.getName(), fis);
                }
            } catch (IOException e) {
                logger.error("上传文件异常：", e);
                uploaded = false;
            } finally {
                fis.close();
                ftpClient.disconnect();
            }
        }
        return uploaded;
    }

    /**
     * 连接ftp服务器
     *
     * @param ip   ftp服务器ip
     * @param port 端口
     * @param user 登录用户名
     * @param pass 密码
     * @return 返回是否连接成功
     */
    private boolean connectServer(String ip, int port, String user, String pass) {
        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            isSuccess = ftpClient.login(user, pass);
        } catch (IOException e) {
            logger.error("连接ftp服务器异常：", e);
        }
        return isSuccess;
    }


    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }
}
