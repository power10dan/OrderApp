package Cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.ArrayList;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.utils.ColorTemplate;

import com.t_danbubbletea.bubbleteaapp.R;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;



public class TeaTopRaterCard extends Card {

    private String[] mLabels = new String[] { "5 Stars", "4 Stars", "3 Stars", "2 Stars"
                                               , "1 Star"};

    public TeaTopRaterCard(Context context) {
        super(context);
        initCustomCard();
    }

    public TeaTopRaterCard(Context context, int layout_Id) {
        super(context, layout_Id);
        initCustomCard();
    }

    private void initCustomCard() {
        TeaRaterCustomHeader tCustomRaterText = new TeaRaterCustomHeader(getContext(),
                R.layout.tea_rater_text_inner_layout);
        addCardHeader(tCustomRaterText);
    }

    @Override
    public void setupInnerViewElements(ViewGroup viewGroup, View view) {

        BarChart mChart = (BarChart) view.findViewById(R.id.chart);
        mChart.setDescription("");

        mChart.setHighlightIndicatorEnabled(false);
        mChart.setDrawBorder(false);
        mChart.setDrawGridBackground(false);
        mChart.setDrawVerticalGrid(false);
        mChart.setDrawXLabels(false);
        mChart.setDrawYValues(false);
        mChart.setUnit(" People");
        mChart.setDrawBarShadow(false);

        mChart.setData(generateBarData(1, 20000, 5));

    }

    private BarData generateBarData(int dataSets, float range, int count) {

        ArrayList<BarDataSet> sets = new ArrayList<BarDataSet>();

        for(int i = 0; i < dataSets; i++) {

            ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

            for(int j = 0; j < count; j++) {
                entries.add(new BarEntry((float) (Math.random() * range) + range / 4, j));
            }

            BarDataSet ds = new BarDataSet(entries, mLabels[i]);
            ds.setColors(ColorTemplate.VORDIPLOM_COLORS);
            sets.add(ds);
        }

        BarData d = new BarData(ChartData.generateXVals(0, count), sets);
        return d;
    }
}
    class TeaRaterCustomHeader extends CardHeader {

        public TeaRaterCustomHeader(Context context, int innerLayout) {
            super(context, innerLayout);
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view) {
            TextView teaHeader = (TextView) view.findViewById(R.id.tea_rater_title);
            teaHeader.setText("Tea Rating");

        }
    }













