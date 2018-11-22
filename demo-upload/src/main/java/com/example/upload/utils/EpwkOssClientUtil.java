package com.example.upload.utils;


import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;

/**
 * 功能描述: 阿里云OSS 实例
 *
 * @auther: Administrator
 * @date: 2018/8/15/015 16:55
 */
@Component
public class EpwkOssClientUtil {
    @Autowired
    private OssConfig ossConfig;
    private OSSClient ossClient;

    /**
     * ali OSS Client 配置类
     *
     * @return com.aliyun.oss.ClientConfiguration
     */
    @Bean
    public ClientConfiguration getClientconfig() {
        // 创建ClientConfiguration。ClientConfiguration是OSSClient的配置类，可配置代理、连接超时、最大连接数等参数。
        ClientConfiguration conf = new ClientConfiguration();
        // 设置OSSClient允许打开的最大HTTP连接数，默认为1024个。
        //conf.setMaxConnections(1024);
        // 设置Socket层传输数据的超时时间，默认为50000毫秒。
        //conf.setSocketTimeout(50000);
        // 设置建立连接的超时时间，默认为50000毫秒。
        //conf.setConnectionTimeout(50000);
        // 设置从连接池中获取连接的超时时间（单位：毫秒），默认不超时。
        //conf.setConnectionRequestTimeout(10000);
        // 设置连接空闲超时时间。超时则关闭连接，默认为60000毫秒。
        //conf.setIdleConnectionTime(60000);
        // 设置失败请求重试次数，默认为3次。
        //conf.setMaxErrorRetry(3);
        // 设置是否支持将自定义域名作为Endpoint，默认支持。
        //conf.setSupportCname(true);
        // 设置是否开启二级域名的访问方式，默认不开启。
        //conf.setSLDEnabled(true);
        // 设置连接OSS所使用的协议（HTTP/HTTPS），默认为HTTP。
        //conf.setProtocol(Protocol.HTTP);
        // 设置用户代理，指HTTP的User-Agent头，默认为aliyun-sdk-java。
        //conf.setUserAgent("aliyun-sdk-java");
        // 设置代理服务器端口。
        //conf.setProxyHost(ossConfig.getProxyHost());
        // 设置代理服务器验证的用户名。
        //conf.setProxyUsername(ossConfig.getProxyUserName());
        // 设置代理服务器验证的密码。
        //conf.setProxyPassword(ossConfig.getProxyPassword());
        return conf;
    }

    /**
     * 标注OSSClient 类
     *
     * @return com.aliyun.oss.OSSClient
     */
    @Bean
    public OSSClient getOssClient() {
        if (Objects.isNull(ossClient)) {
            ossClient = new OSSClient(ossConfig.getEndpoint(), new DefaultCredentialProvider(ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret()), getClientconfig());
        }
        return ossClient;
    }

    /**
     * 上传网络流方式上传
     *
     * @param fileUrl
     * @param objectName
     * @throws IOException
     */
    public void upLoadFile(String fileUrl, String objectName) throws IOException {
        // 上传网络流。
        URL uri = new URL(fileUrl);
        HttpURLConnection connection = (HttpURLConnection) uri.
                openConnection();
        //判断是否存在，不存在将就创建
        createBucketName(ossConfig.getBrucketName());
        ObjectMetadata objectMeta = new ObjectMetadata();
        //文件大小
        objectMeta.setContentLength(connection.getContentLengthLong());
        //文件类型
        //如果没有扩展名则填默认值application/octet-stream
        objectMeta.setContentType(getContentType(fileUrl));
        //指定该Object被下载时的内容编码格式
        objectMeta.setContentEncoding("utf-8");
        getOssClient().putObject(ossConfig.getBrucketName(), objectName, connection.getInputStream(), objectMeta);
        connection.disconnect();
    }

    /**
     * 上传本地方式上传
     *
     * @param file
     * @param objectName
     * @throws IOException
     */
    public void upLoadFile(File file, String objectName) {
        ObjectMetadata objectMeta = new ObjectMetadata();
        //文件大小
        objectMeta.setContentLength(file.length());
        //指定该Object被下载时的内容编码格式
        objectMeta.setContentEncoding("utf-8");
        //文件类型
        //如果没有扩展名则填默认值application/octet-stream
        objectMeta.setContentType(getContentType(file.getName()));
        getOssClient().putObject(ossConfig.getBrucketName(), objectName, file, objectMeta);
    }

    public String upLoadFile(MultipartFile file, String fileName, String fileSaveType) throws IOException {
        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentType(getContentType(fileName));
        //指定该Object被下载时的内容编码格式
        objectMeta.setContentEncoding("utf-8");
        try {
            PutObjectResult putObjectResult = getOssClient().putObject(ossConfig.getBrucketName() + "ab", fileSaveType + "/" + fileName, file.getInputStream(), objectMeta);
            return putObjectResult.getETag();
        } catch (OSSException e) {
            System.out.println(e.getErrorCode()+e.getErrorMessage());
            return null;
        }
    }

    /**
     * 删除上传的文件
     *
     * @param
     * @return
     */
    public void deleteOssFile(String objectName) {
        getOssClient().deleteObject(ossConfig.getBrucketName(), objectName);
    }

    /**
     * 批量删除，并返回删除的对象
     *
     * @param objectNames
     * @return List<String>
     */
    public List<String> deleteOssFiles(List<String> objectNames) {
        DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(ossConfig.getBrucketName()).withKeys(objectNames));
        List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
        return deletedObjects;
    }

    /**
     * 创建bucket ,判断如果已存在将不再创建
     *
     * @param bucketName
     */
    public void createBucketName(String bucketName) {
        if (!getOssClient().doesBucketExist(bucketName)) {
            getOssClient().createBucket(bucketName);
        }
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     *
     * @param fileName
     * @return
     */
    public static String getContentType(String fileName) {
        //文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension) || ".png".equalsIgnoreCase(fileExtension)) {
            return "image/jpeg";
        }
        if (".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if (".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if (".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if (".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if (".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        //默认返回类型
        return "image/jpeg";
    }
}
