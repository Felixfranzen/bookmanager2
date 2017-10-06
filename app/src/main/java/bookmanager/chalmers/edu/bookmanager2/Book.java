package bookmanager.chalmers.edu.bookmanager2;

/**
 * Created by Felix on 2017-09-01.
 */

public class Book {

    private String author;
    private String title;
    private int price;
    private String isbn;
    private String course;


    // Create a new book using the specified parameters
    public Book(String author, String title, int price, String isbn, String course) {
        this.author = author;
        this.title = title;
        this.price = price;
        this.isbn = isbn;
        this.course = course;
    }

    // Create an empty book
    public Book() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String toString(){
        String print = author  + ", " + title;
        return print;
    }
}
