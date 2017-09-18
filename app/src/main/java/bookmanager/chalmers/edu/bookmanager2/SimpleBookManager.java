package bookmanager.chalmers.edu.bookmanager2;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Felix on 2017-09-01.
 */

public class SimpleBookManager implements BookManager {

    private ArrayList<Book> books = new ArrayList<Book>();
    private static final BookManager instance = new SimpleBookManager();

    private SimpleBookManager(){
        for (int i = 0; i < 5; i++) {
            String author = "Author" + i;
            String title = "Title" + i;
            int price = i * 100;
            String isbn = String.valueOf(i * 1111);
            String course = "Course" + i;

            Book book = new Book(author, title, price, isbn, course);
            books.add(book);
        }
    }

    public static BookManager getInstance(){
        return instance;
    }

    public int count() {
        return books.size();
    }

    public Book getBook(int index) {
        if (index < 0 || index > count()){
            throw new IndexOutOfBoundsException("Specify a valid index");
        }

        return books.get(index);
    }

    public Book createBook() {
        Book book = new Book();
        books.add(book);
        return book;

    }

    public ArrayList<Book> getAllBooks() {
        return books;
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void moveBook (int from, int to) {
        if (from < 0 || from > count()){
            throw new IndexOutOfBoundsException("Specify a valid index");
        }

        if (to < 0 || to > count()){
            throw new IndexOutOfBoundsException("Specify a valid index");
        }

        books.add(to, books.remove(from));
    }
    public int getMinPrice() {
        int minPrice = getBook(0).getPrice();
        for (Book book : books) {
            int price = book.getPrice();
            if (minPrice > price) {
                minPrice = price;
            }
        }

        return minPrice;
    }

    public int getMaxPrice() {
        int maxPrice = getBook(0).getPrice();
        for (Book book : books) {
            int price = book.getPrice();
            if (maxPrice < price) {
                maxPrice = price;
            }
        }

        return maxPrice;
    }

    public float getMeanPrice() {
        if (count() == 0){
            return 0;
        }

        return getTotalCost() / count();
    }

    public int getTotalCost() {
        int sum = 0;
        for (Book book : books) {
            sum += book.getPrice();
        }
        return sum;
    }

    public void saveChanges() {

    }
}
