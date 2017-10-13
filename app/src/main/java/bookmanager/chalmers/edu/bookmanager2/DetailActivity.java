package bookmanager.chalmers.edu.bookmanager2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView titleView;
    private TextView authorView;
    private TextView courseView;
    private TextView priceView;
    private TextView isbnView;

    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        titleView = (TextView) findViewById(R.id.book_title);
        authorView= (TextView) findViewById(R.id.book_author);
        courseView = (TextView) findViewById(R.id.book_course);
        priceView = (TextView) findViewById(R.id.book_price);
        isbnView = (TextView) findViewById(R.id.book_isbn);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            book = SimpleBookManager.getBookManager().getBook(extras.getInt("INDEX"));
            setTitles(book);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {

            return true;
        } else if (id == R.id.action_remove){
            SimpleBookManager.getBookManager().removeBook(book);
            SimpleBookManager.getBookManager().saveChanges(getSharedPreferences("books", MODE_PRIVATE));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
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
