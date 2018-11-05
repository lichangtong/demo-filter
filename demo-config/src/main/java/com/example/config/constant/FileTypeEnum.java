package com.example.config.constant;

import java.io.Serializable;

/**
 * @Auther: lichangtong
 * @Date: 2018/11/5 0005 19:15
 * @Description: 文件上传类型
 */
public enum FileTypeEnum implements Serializable {

    USER_ICON("USER_ICON", 0), PRODUCT_ICON("PRODUCT_ICON", 1);
    private Integer fileTypeId;
    private String fileTypeName;

    FileTypeEnum(String fileTypeName, Integer fileTypeId) {
        this.fileTypeId = fileTypeId;
        this.fileTypeName = fileTypeName;
    }

    public Integer getFileTypeId() {
        return fileTypeId;
    }

    public void setFileTypeId(Integer fileTypeId) {
        this.fileTypeId = fileTypeId;
    }

    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    public static String getNameByFileTypeId(Integer index) {
        return FileTypeEnum.values()[index].getFileTypeName();
    }
}
