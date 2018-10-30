package com.example.config.para;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * 功能描述: JSON 入参转换类
 * @param:
 * @date: 2018/10/24 0024 19:33
 * @return:
 * @auther: lichangtong
 */
public class RequestParas implements Serializable {
    //接口参数
    private Map<String, String> map;
    //用户登录授权成功后，颁发给应用的授权信息
    private String token;
    //必须 API协议版本，可选值：1.0
    private String v;
    //必须 应用程序appId php h5 ios等应用Id
    private String appId;
    //必须 API输入参数签名结果
    private String sign;
    //必须 签名的摘要算法，可选值为：hmac，md5。
    private String signMethod;
    //必须 该用方法,无需客户端传入，系统自动填充。
    private String method;
    //非必须 app密钥,从后端缓存提取
    private String appIdSecret;

    public RequestParas() {
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignMethod() {
        return signMethod;
    }

    public void setSignMethod(String signMethod) {
        this.signMethod = signMethod;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAppIdSecret() {
        return appIdSecret;
    }

    public void setAppIdSecret(String appIdSecret) {
        this.appIdSecret = appIdSecret;
    }

    @Override
    public String toString() {
        return "RequestParas{" +
                "map=" + map +
                ", token='" + token + '\'' +
                ", v='" + v + '\'' +
                ", appId='" + appId + '\'' +
                ", sign='" + sign + '\'' +
                ", signMethod='" + signMethod + '\'' +
                ", method='" + method + '\'' +
                ", appIdSecret='" + appIdSecret + '\'' +
                '}';
    }
}
