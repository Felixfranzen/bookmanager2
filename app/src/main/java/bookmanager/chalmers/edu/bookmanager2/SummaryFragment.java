package bookmanager.chalmers.edu.bookmanager2;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SummaryFragment extends Fragment {

    private TextView countView;
    private TextView sumView;
    private TextView mostExpensiveView;
    private TextView leastExpensiveView;
    private TextView averagePriceView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_summary, container, false);

        countView = (TextView) view.findViewById(R.id.book_count);
        sumView = (TextView) view.findViewById(R.id.total_cost);
        mostExpensiveView = (TextView) view.findViewById(R.id.most_expensive);
        leastExpensiveView = (TextView) view.findViewById(R.id.least_expensive);
        averagePriceView = (TextView) view.findViewById(R.id.average_price);

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        BookManager bm = SimpleBookManager.getInstance();
        setTexts(bm.count(), bm.getTotalCost(), bm.getMaxPrice(), bm.getMinPrice(), bm.getMeanPrice());
    }

    private void setTexts (int count, int sum, int mostExpensive, int leastExpensive, float averagePrice){
        countView.setText(String.valueOf(count) + " books in your library");
        sumView.setText(String.valueOf(sum));
        mostExpensiveView.setText(String.valueOf(mostExpensive));
        leastExpensiveView.setText(String.valueOf(leastExpensive));
        averagePriceView.setText(String.valueOf(averagePrice));
    }
}
