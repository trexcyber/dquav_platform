package com.dquav.dquav_platform.entity;

/**
 * @author TrEx
 * @date 2021/7/5 - 16:11
 */
public class FileEntity {
    private String originalFilename;
    private String fileName;
    private String strongPath;

    @Override
    public String toString() {
        return "FileEntity{" +
                "originalFilename='" + originalFilename + '\'' +
                ", fileName='" + fileName + '\'' +
                ", strongPath='" + strongPath + '\'' +
                '}';
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }



    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStrongPath() {
        return strongPath;
    }

    public void setStrongPath(String strongPath) {
        this.strongPath = strongPath;
    }
}
