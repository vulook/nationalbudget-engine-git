package com.softserve.entity;

import java.io.Serializable;

/**
 * Java application analyzes the State Budget of Ukraine
 *
 * @author vulook (https://github.com/vulook).
 */

public class DownloadDetail implements Serializable {

    private int yearDownload;
    private long fileSize;
    private String fileName;
    private String downloadStatus;
    private static final long serialVersionUID = 1L;

    public int getYearDownload() {
        return yearDownload;
    }

    public void setYearDownload(int yearDownload) {
        this.yearDownload = yearDownload;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(String downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    @Override
    public String toString() {
        return "Рік: " + yearDownload + ", Назва: " + fileName + ", Розмір: " + fileSize + ", Статус: "
                + downloadStatus;
    }

}
