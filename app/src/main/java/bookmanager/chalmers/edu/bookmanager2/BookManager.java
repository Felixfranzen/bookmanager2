package bookmanager.chalmers.edu.bookmanager2;

import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by Felix on 2017-09-01.
 */

public interface BookManager {
    public int count();
    public Book getBook(int index);
    public Book createBook();
    public ArrayList<Book> getAllBooks();
    public void removeBook(Book book);
    public void moveBook (int from, int to);
    public int getMinPrice();
    public int getMaxPrice();
    public float getMeanPrice();
    public int getTotalCost();
    public void saveChanges(SharedPreferences prefs);
    public void loadBooks(SharedPreferences prefs);
}
