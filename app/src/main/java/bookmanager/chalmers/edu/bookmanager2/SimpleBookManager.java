package bookmanager.chalmers.edu.bookmanager2;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Felix on 2017-09-01.
 */

public class SimpleBookManager implements BookManager {

    private ArrayList<Book> books = new ArrayList<Book>();

    private static final BookManager instance = new SimpleBookManager();

    private SimpleBookManager(){
    }

    public static BookManager getBookManager(){
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

    public void saveChanges(SharedPreferences prefs) {
        Gson gson = new Gson();
        SharedPreferences.Editor prefsEditor = prefs.edit();

        String jsonText = gson.toJson(books);
        prefsEditor.putString("books", jsonText);
        prefsEditor.commit();

        Log.i("SAVED BOOKS: ", prefs.getString("books", null));
    }

    public void loadBooks(SharedPreferences prefs){
        Gson gson = new Gson();
        String jsonText = prefs.getString("books", null);
        ArrayList<Book> loadedBooks = gson.fromJson(jsonText, new TypeToken<ArrayList<Book>>(){}.getType());
        books = loadedBooks;
        Log.i("LOADED BOOKS: ", books.toString());
    }
}
