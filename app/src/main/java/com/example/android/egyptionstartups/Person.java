package com.example.android.egyptionstartups;

import android.widget.Button;

public class Person {
    String name;
    String ceo;
    String story;
    String site;
    String img_url;
    String personImage;
    public Person(String name, String ceo, String story, String site, String img_url, String personImage)
    {
        this.name = name;
        this.ceo = ceo;
        this.story = story;
        this.site = site;
        this.img_url = img_url;
        this.personImage = personImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getPersonImage() {
        return personImage;
    }

    public void setPersonImage(String personImage) {
        this.personImage = personImage;
    }
}
