package com.example.falcon.cinema;

/**
 * Created by Optimus on 4/7/2017.
 */

public class MovieInfo {
    String name;
    String release;
    double imdb;
    int duration;
    String posterurl;
    int age;
    int format;

    public MovieInfo(String name, String release, double imdb, int duration, String posterurl, int age, int format) {
        this.name = name;
        this.release = release;
        this.imdb = imdb;
        this.duration = duration;
        this.posterurl = posterurl;
        this.age = age;
        this.format = format;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public double getImdb() {
        return imdb;
    }

    public void setImdb(double imdb) {
        this.imdb = imdb;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPosterurl() {
        return posterurl;
    }

    public void setPosterurl(String posterurl) {
        this.posterurl = posterurl;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }
}
