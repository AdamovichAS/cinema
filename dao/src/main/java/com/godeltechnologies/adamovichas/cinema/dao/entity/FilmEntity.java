package com.godeltechnologies.adamovichas.cinema.dao.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "film")
public class FilmEntity {

    private Long id;
    private Long directorId;
    private String name;
    private LocalDate releaseDate;
    private Long genreId;
    private DirectorEntity director;
    private GenreEntiry genre;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "director_id",nullable = false,insertable = false, updatable = false)
    public Long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }
    @Column(name = "name",nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "release_date",nullable = false,columnDefinition = "DATE")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    @Column(name = "genre_id",nullable = false,insertable = false,updatable = false)
    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }
    @ManyToOne
    @JoinColumn(name = "director_id", referencedColumnName = "id")
    public DirectorEntity getDirector() {
        return director;
    }

    public void setDirector(DirectorEntity director) {
        this.director = director;
    }
    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    public GenreEntiry getGenre() {
        return genre;
    }

    public void setGenre(GenreEntiry genre) {
        this.genre = genre;
    }
}
