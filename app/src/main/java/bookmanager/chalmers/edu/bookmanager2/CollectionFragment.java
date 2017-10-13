package bookmanager.chalmers.edu.bookmanager2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CollectionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CollectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CollectionFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private View view;

    public CollectionFragment() {
        // Required empty public constructor

    }

    public static CollectionFragment newInstance() {
        CollectionFragment fragment = new CollectionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_collection, container, false);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        final ArrayList<Book> books = SimpleBookManager.getBookManager().getAllBooks();
        BookAdapter itemsAdapter = new BookAdapter(getActivity(), books);
        RecyclerView list = (RecyclerView) view.findViewById(R.id.book_list);
        list.setAdapter(itemsAdapter);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));

        itemsAdapter.setOnItemClickedListener(new OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("INDEX", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }


    public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

        private Context mContext;
        private List<Book> mBooks;
        private OnItemClickListener listener;

        public void setOnItemClickedListener(OnItemClickListener list){
            listener = list;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView title;
            public TextView author;

            public ViewHolder(final View itemView) {
                super(itemView);

                title = (TextView) itemView.findViewById(R.id.book_item_title);
                author = (TextView) itemView.findViewById(R.id.book_item_author);

                // Setup the click listener
                itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // Triggers click upwards to the adapter on click
                        if (listener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                listener.onItemClick(itemView, position);
                            }
                        }
                    }
                });
            }
        }

        public BookAdapter(Context context, List<Book> books){
            mBooks = books;
            mContext = context;
        }

        public Context getContext() {
            return mContext;
        }

        @Override
        public BookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View bookView = inflater.inflate(R.layout.book_item, parent, false);

            // Return a new holder instance
            ViewHolder viewHolder = new ViewHolder(bookView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(BookAdapter.ViewHolder viewHolder, int position) {
            // Get the data model based on position
            Book book = mBooks.get(position);
            // Set item views based on your views and data model
            TextView titleView = viewHolder.title;
            TextView authorView = viewHolder.author;
            titleView.setText(book.getTitle());
            authorView.setText(book.getAuthor());
        }

        // Returns the total count of items in the list
        @Override
        public int getItemCount() {
            return mBooks.size();
        }


    }
}
