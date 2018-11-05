package com.example.config.para;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.util.Streams;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * 功能描述: JSON 入参转换类
 *
 * @param:
 * @date: 2018/10/24 0024 19:33
 * @return:
 * @auther: lichangtong
 */
public class RequestParas implements Serializable {
    //接口参数
    private Map<String, Object> map = Maps.newHashMap();
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

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
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

    /**
     * 判断是否为系统参数
     *
     * @param name
     * @return true | false
     */
    public static boolean isExistSystem(String name) {
        if (StringUtils.isNotBlank(name) && ("token".equals(name)
                || "v".equals(name)
                || "appId".equals(name)
                || "sign".equals(name)
                || "signMethod".equals(name)
                || "method".equals(name)
                || "appIdSecret".equals(name))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 设置系统及参数
     *
     * @param requestParas
     * @param name
     * @param value
     */
    public static void setSystemParams(RequestParas requestParas, String name, String value) {
        if ("token".equals(name)) {
            requestParas.setToken(value);
        } else if ("v".equals(name)) {
            requestParas.setV(value);
        } else if ("appId".equals(name)) {
            requestParas.setAppId(value);
        } else if ("sign".equals(name)) {
            requestParas.setSign(value);
        } else if ("signMethod".equals(name)) {
            requestParas.setSignMethod(value);
        } else if ("method".equals(name)) {
            requestParas.setMethod(value);
        } else if ("appIdSecret".equals(name)) {
            requestParas.setAppIdSecret(value);
        }
    }

    /**
     * 设置client 入参
     *
     * @param item
     * @param requestParas
     * @throws IOException
     */
    public static void setParams(FileItemStream item, RequestParas requestParas) throws IOException {
        String name = item.getFieldName();
        InputStream is = item.openStream();
        if (item.isFormField()) {
            if (RequestParas.isExistSystem(name)) {
                RequestParas.setSystemParams(requestParas, name, Streams.asString(is));
            } else {
                requestParas.getMap().put(name, Streams.asString(is));
            }
        } else {
            if (is.available() > 0) {
                Map<String, Object> map = Maps.newHashMap();
                String fileName = item.getName();
                map.put("fileName", fileName);
                map.put("file", is);
                ArrayList arrayList = (ArrayList) requestParas.getMap().get(name);
                if (Objects.isNull(arrayList)) {
                    ArrayList arr = Lists.newArrayList();
                    arr.add(map);
                    requestParas.getMap().put(name, arr);
                } else {
                    arrayList.add(map);
                    requestParas.getMap().put(name, arrayList);
                }
            }
        }
    }
}

