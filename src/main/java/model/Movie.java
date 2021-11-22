package model;

import java.io.Serializable;

public class Movie implements Serializable {

    private String title;
    private String director;
    private int lengthInMinutes;
    private String IMDB;
    private String thumbnail;

    public Movie(){
    }

    public Movie(String title, String director, int lengthInMinutes, String IMDB, String thumbnail){
        this.title = title;
        this.director = director;
        this.lengthInMinutes = lengthInMinutes;
        this.IMDB = IMDB;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }
    public String getIMDB(){
        return IMDB;
    }
    public void setIMDB(String IMDB){
        this.IMDB = IMDB;
    }
    public String getThumbnail(){
        return thumbnail;
    }
    public void setThumbnail(String thumbnail){
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString(){
        return "Movie{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", lengthInMinutes=" + lengthInMinutes + '\'' +
                ", IMDB='" + IMDB + '}';
    }
}
