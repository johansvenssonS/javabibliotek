package model;

import java.sql.Date;

public class CreateBookDTO {
    private String title;
    private String isbn;
    private int yearPublished;
    private int totalCopies;
    private int availableCopies;
    private String summary;
    private String language;
    private int pageCount;
    private String authorFirstName;
    private String authorLastName;
    private Date authorBirthDate;
    private String bookCategory;


    public CreateBookDTO(String title, String isbn, int yearPublished, int totalCopies, int availableCopies, String summary, String language, int pageCount, String authorFirstName, String authorLastName, Date authorBirthDate, String bookCategory) {
        this.title = title;
        this.isbn = isbn;
        this.yearPublished = yearPublished;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.summary = summary;
        this.language = language;
        this.pageCount = pageCount;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.authorBirthDate = authorBirthDate;
        this.bookCategory = bookCategory;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public Date getAuthorBirthDate() {
        return authorBirthDate;
    }

    public void setAuthorBirthDate(Date authorBirthDate) {
        this.authorBirthDate = authorBirthDate;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }




}
