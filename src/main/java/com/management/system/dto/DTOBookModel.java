package com.management.system.dto;
/* La clase DTOBookModel no servira como filtro, no es necesario para
pruebas personales pero es una buena practica para el mundo real o proyectos
publicos
 */
public class DTOBookModel {
    private String title;
    private String author;
    private int publisher;
    private boolean available;

    public DTOBookModel(String title, String author, int publisher, boolean available) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.available = available;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public void setPublisher(int publisher) {
        this.publisher = publisher;

    }

    public String getAuthor() {
        return author;
    }
    public String getTitle() {
        return title;
    }
    public int getPublisher() {
        return publisher;
    }
    public boolean isAvailable() {
        return available;
    }
}
