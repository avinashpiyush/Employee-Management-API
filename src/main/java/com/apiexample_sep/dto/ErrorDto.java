package com.apiexample_sep.dto;

import java.util.Date;

public class ErrorDto {

    private Date date;
    private String message;
    private String url;

    public ErrorDto(Date date, String message, String url) {
        this.date = date;
        this.message = message;
        this.url = url;
    }

    // Getters and Setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
