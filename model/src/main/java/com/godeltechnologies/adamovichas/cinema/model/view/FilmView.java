package com.godeltechnologies.adamovichas.cinema.model.view;

import java.time.LocalDate;
import java.util.Date;

public class FilmView {

    private Long directorId;
    private String directorFirstName;
    private String directorLastName;
    private LocalDate directorBirthDate;
    private String filmName;
    private LocalDate filmReleaseDate;
    private String filmGenre;

    public Long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }

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

    public LocalDate getDirectorBirthDate() {
        return directorBirthDate;
    }

    public void setDirectorBirthDate(LocalDate directorBirthDate) {
        this.directorBirthDate = directorBirthDate;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public LocalDate getFilmReleaseDate() {
        return filmReleaseDate;
    }

    public void setFilmReleaseDate(LocalDate filmReleaseDate) {
        this.filmReleaseDate = filmReleaseDate;
    }

    public String getFilmGenre() {
        return filmGenre;
    }

    public void setFilmGenre(String filmGenre) {
        this.filmGenre = filmGenre;
    }

    public String getDirectorFullName(){
        return String.format("%s %s",directorFirstName,directorLastName);
    }
}
