package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.DateTimeUtil;
import com.mmall.util.FTPUtil;
import com.mmall.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * 项目：  mmall
 * 包名：  com.mmall.service.impl
 * 作者：  chencong
 * 时间：  2017/6/29 20:47.
 * 描述：  文件处理接口的实现类
 */
@Service("iFileService")
public class FileServiceImpl implements IFileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public String upload(MultipartFile file, String path) {
        //得到上传文件的原始文件名
        String fileName = file.getOriginalFilename();
        //获取文件扩展名 abc.jpg 切记从最后面开始获取第一个点
        String fileExtensionName = fileName.substring(fileName.lastIndexOf("."));
        String uploadFileName = DateTimeUtil.dateToStr(new Date(), "yyMMddHHmmss") + UUID.randomUUID().toString().replace("-", "") + fileExtensionName;
        logger.info("开始文件上传，时间：{},上传的文件名：{}，上传的路径：{}，新文件名：{}", DateTimeUtil.dateToStr(new Date()), fileName, path, fileExtensionName);

        File fileDir = new File(path);
        //判断file路径是否存在 不存在则直接创建
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);

        //调用SpringMVC
        try {
            file.transferTo(targetFile);

            // 将targetfil上传到ftp服务器
            FTPUtil.uploadFile("img", Lists.newArrayList(targetFile));
            // 上传完后 将upload文件夹下文件删除
            //targetFile.delete();
        } catch (IOException e) {
            logger.error("上传文件异常", e);
            return null;
        }
        //返回文件名
        logger.info("this file path in server:{}", targetFile.getName());
        return targetFile.getName();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(DateTimeUtil.dateToStr(new Date(), "yyMMddHHmmss") + UUID.randomUUID().toString().replace("-", "") + ".jpg");
        }
        String name = "132456.jpg";
        System.out.println(name.substring(name.lastIndexOf(".")));
    }
}
