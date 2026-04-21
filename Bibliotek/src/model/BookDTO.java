package model;

public class BookDTO {
    private String title;
    private int bookId;
    private String isbn;
    private int year_published;
    private int total_copies;
    private int available_copies;

    public BookDTO(String title, int bookId, String isbn, int year_published, int total_copies, int available_copies) {
        this.title = title;
        this.bookId = bookId;
        this.isbn = isbn;
        this.year_published = year_published;
        this.total_copies = total_copies;
        this.available_copies = available_copies;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }
    public int getBookId() {
        return bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getYear_published() {
        return year_published;
    }

    public void setYear_published(int year_published) {
        this.year_published = year_published;
    }

    public int getTotal_copies() {
        return total_copies;
    }

    public void setTotal_copies(int total_copies) {
        this.total_copies = total_copies;
    }

    public int getAvailable_copies() {
        return available_copies;
    }

    public void setAvailable_copies(int available_copies) {
        this.available_copies = available_copies;
    }


}
