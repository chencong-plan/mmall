package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 项目：  mmall
 * 包名：  com.mmall.service
 * 作者：  chencong
 * 时间：  2017/6/29 20:46.
 * 描述：  文件处理接口
 */
public interface IFileService {

    /**
     * 文件上传接口
     *
     * @param file
     * @param path
     * @return
     */
    String upload(MultipartFile file, String path);
}
