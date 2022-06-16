package com.example.truyentranhonline.util.dialog;


public class DisplayUIDialog{
    private String title;
    private String message;
    private String titleCancel;
    private String titleAccept;

    public DisplayUIDialog(String title, String message, String titleCancel, String titleAccept) {
        this.title = title;
        this.message = message;
        this.titleCancel = titleCancel;
        this.titleAccept = titleAccept;
    }

    public DisplayUIDialog(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitleCancel() {
        return titleCancel;
    }

    public void setTitleCancel(String titleCancel) {
        this.titleCancel = titleCancel;
    }

    public String getTitleAccept() {
        return titleAccept;
    }

    public void setTitleAccept(String titleAccept) {
        this.titleAccept = titleAccept;
    }
}
