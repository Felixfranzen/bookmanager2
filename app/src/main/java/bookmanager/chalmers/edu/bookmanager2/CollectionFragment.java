package bookmanager.chalmers.edu.bookmanager2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


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
        Log.i("COLLECTIONFRAG ONRESUME", "COLLECTIONFRAG ONRESUME");
        super.onResume();

        ArrayList<Book> books = SimpleBookManager.getBookManager().getAllBooks();
        ArrayAdapter<Book> itemsAdapter = new ArrayAdapter<Book>(getActivity(), android.R.layout.simple_list_item_1, books);
        ListView list = (ListView) view.findViewById(R.id.book_list);
        list.setAdapter(itemsAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("ITEM CLICKED: ", Integer.toString(position));
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
}
