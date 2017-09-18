package bookmanager.chalmers.edu.bookmanager2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView titleView;
    private TextView authorView;
    private TextView courseView;
    private TextView priceView;
    private TextView isbnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        titleView = (TextView) findViewById(R.id.book_title);
        authorView= (TextView) findViewById(R.id.book_author);
        courseView = (TextView) findViewById(R.id.book_course);
        priceView = (TextView) findViewById(R.id.book_price);
        isbnView = (TextView) findViewById(R.id.book_isbn);
    }

    private void setTitles(Book book) {
        // TODO USE CORRECT DATA
        titleView.setText(book.getTitle());
        authorView.setText(book.getAuthor());
        courseView.setText(book.getCourse());
        priceView.setText(String.valueOf(book.getPrice()));
        isbnView.setText(book.getIsbn());
    }
}
