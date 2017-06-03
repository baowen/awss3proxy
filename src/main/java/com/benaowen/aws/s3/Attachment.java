package com.benaowen.aws.s3;


import java.util.Date;

/**
 * Created by ben on 04/11/16.
 */
public class Attachment {

    private long id;
    private long applicationId;
    private String filename;
    private String s3Filename;
    private String s3Path;
    private String s3Region;
    private String base64file;

    public Attachment() {
    }

    public Attachment(Long id, Long applicationId, Date createdAt, String filename, String s3Filename, String s3Path, String s3Region) {
        this.id = id;
        this.applicationId = applicationId;
        this.filename = filename;
        this.s3Filename = s3Filename;
        this.s3Path = s3Path;
        this.s3Region = s3Region;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


    public String getS3Filename() {
        return s3Filename;
    }

    public void setS3Filename(String s3Filename) {
        this.s3Filename = s3Filename;
    }


    public String getS3Path() {
        return s3Path;
    }

    public void setS3Path(String s3Path) {
        this.s3Path = s3Path;
    }


    public String getS3Region() {
        return s3Region;
    }

    public void setS3Region(String s3Region) {
        this.s3Region = s3Region;
    }

    public String getBase64file() {
        return base64file;
    }

    public void setBase64file(String base64file) {
        this.base64file = base64file;
    }
}
