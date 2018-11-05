package com.example.upload.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能描述: ALi OSS 配置类
 *
 * @auther: Administrator
 * @date: 2018/8/15/015 17:00
 */
@Component
@ConfigurationProperties(prefix = "ali.oss")
public class OssConfig {
    private String endpoint;
    //http://oss-cn-hangzhou.aliyuncs.com
// #阿里云主账号AccessKey拥有所有API的访问权限，风险很高。
// #强烈建议您创建并使用RAM账号进行API访问或日常运维，
// #请登录 https://ram.console.aliyun.com 创建RAM账号。
    private String accessKeyId;
    private String accessKeySecret;
    private String proxyHost;
    private String proxyUserName;
    private String proxyPassword;
    private String brucketName;
    private String defaulPackageName;

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public String getProxyUserName() {
        return proxyUserName;
    }

    public void setProxyUserName(String proxyUserName) {
        this.proxyUserName = proxyUserName;
    }

    public String getProxyPassword() {
        return proxyPassword;
    }

    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBrucketName() {
        return brucketName;
    }

    public void setBrucketName(String brucketName) {
        this.brucketName = brucketName;
    }

    public String getDefaulPackageName() {
        return defaulPackageName;
    }

    public void setDefaulPackageName(String defaulPackageName) {
        this.defaulPackageName = defaulPackageName;
    }
}
