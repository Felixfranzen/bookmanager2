package bookmanager.chalmers.edu.bookmanager2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddBookActivity extends AppCompatActivity {

    private EditText title;
    private EditText author;
    private EditText course;
    private EditText isbn;
    private EditText price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Setup edit views
        title = (EditText) findViewById(R.id.add_title);
        author = (EditText) findViewById(R.id.add_author);
        course = (EditText) findViewById(R.id.add_course);
        isbn = (EditText) findViewById(R.id.add_isbn);
        price = (EditText) findViewById(R.id.add_price);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            onSaveClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onSaveClicked(){
        if (title.getText().length() == 0){
            Toast.makeText(this, "You need to enter a title", Toast.LENGTH_SHORT).show();
        }

        try {
            Integer.parseInt(price.getText().toString());
        } catch(NumberFormatException e) {
            Toast.makeText(this, "Price needs to be a number", Toast.LENGTH_SHORT).show();
            return;
        } catch(NullPointerException e) {
            Toast.makeText(this, "Price needs to be a number", Toast.LENGTH_SHORT).show();
            return;
        }

        Book newBook = SimpleBookManager.getInstance().createBook();
        newBook.setAuthor(author.getText().toString());
        newBook.setCourse(course.getText().toString());
        newBook.setIsbn(isbn.getText().toString());
        newBook.setPrice(Integer.parseInt(price.getText().toString()));
        newBook.setTitle(title.getText().toString());

        finish();
    }
}
