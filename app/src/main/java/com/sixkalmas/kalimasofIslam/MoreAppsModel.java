package com.sixkalmas.kalimasofIslam;

public class MoreAppsModel {


    String title;
    String description;
    String link;
    String iconLink;

    public MoreAppsModel() {
    }

    public MoreAppsModel(String title, String description, String link, String iconLink) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.iconLink = iconLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIconLink() {
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }
}
