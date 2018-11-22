package com.example.upload.controller;

import com.example.config.constant.FileTypeEnum;
import com.example.config.para.RequestParas;
import com.example.config.result.ApiResult;
import com.example.config.util.ApiResultHelper;
import com.example.upload.utils.EpwkOssClientUtil;
import com.google.common.collect.Lists;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * @Auther: lichangtong
 * @Date: 2018/11/5 0005 09:48
 * @Description:
 */
@RestController
@RequestMapping("/demo")
public class UpLoadFileController {

    @Autowired
    EpwkOssClientUtil epwkOssClientUtil;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ApiResult upload(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
        Integer fileType = null;
        try {
            Map<String, String[]> map = request.getParameterMap();
            System.out.println(map.keySet().toArray().toString());
            for (Object objKey : map.keySet().toArray()) {
                System.out.println(objKey.toString());
                String key = objKey.toString();
                String[] strings = map.get(key);
                System.out.println(strings[0]);
            }
            if (ServletFileUpload.isMultipartContent(request)) {
                ServletFileUpload fileUpload = new ServletFileUpload();
                FileItemIterator iter = fileUpload.getItemIterator(request);
                fileType = Integer.valueOf(request.getParameter("fileType"));
                System.out.println("fileType = " + FileTypeEnum.getNameByFileTypeId(fileType));
                RequestParas requestParas = new RequestParas();
                while (iter.hasNext()) {
                    FileItemStream item = iter.next();
                    RequestParas.setParams(item, requestParas);
                }
                System.out.println(requestParas.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(request.toString());
        ApiResult apiResult = null;
        if (Objects.isNull(files) || files.length <= 0) {
            apiResult = ApiResultHelper.Initfailure("请选择要上传的文件");
        } else {
            //返回唯一标识码
            ArrayList arrayList = Lists.newArrayList();
            try {
                for (MultipartFile file : files) {
                    arrayList.add(epwkOssClientUtil.upLoadFile(file, file.getOriginalFilename(), FileTypeEnum.getNameByFileTypeId(fileType)));
                }
                apiResult = ApiResultHelper.InitSuccess();
                apiResult.setData(arrayList);
            } catch (IOException e) {
                apiResult = ApiResultHelper.Initfailure("文件上传失败");
            }
        }
        return apiResult;
    }
}
