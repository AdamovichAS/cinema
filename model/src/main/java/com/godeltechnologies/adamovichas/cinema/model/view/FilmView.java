package com.godeltechnologies.adamovichas.cinema.model.view;

import java.util.Date;

public class FilmView {

    private String directorFirstName;
    private String directorLastName;
    private Date directorBirthDate;
    private String filmName;
    private Date filmReleaseDate;
    private String filmGenre;

    public String getDirectorFirstName() {
        return directorFirstName;
    }

    public void setDirectorFirstName(String directorFirstName) {
        this.directorFirstName = directorFirstName;
    }

    public String getDirectorLastName() {
        return directorLastName;
    }

    public void setDirectorLastName(String directorLastName) {
        this.directorLastName = directorLastName;
    }

    public Date getDirectorBirthDate() {
        return directorBirthDate;
    }

    public void setDirectorBirthDate(Date directorBirthDate) {
        this.directorBirthDate = directorBirthDate;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public Date getFilmReleaseDate() {
        return filmReleaseDate;
    }

    public void setFilmReleaseDate(Date filmReleaseDate) {
        this.filmReleaseDate = filmReleaseDate;
    }

    public String getFilmGenre() {
        return filmGenre;
    }

    public void setFilmGenre(String filmGenre) {
        this.filmGenre = filmGenre;
    }

    public String getDirectorInfo(){
        return String.format("%s %s %s ",directorFirstName,directorLastName,directorBirthDate.toString());
    }

    public String getFilmInfo(){
        return String.format("%s %s %s", filmName, filmReleaseDate.toString(), filmGenre);
    }
}
