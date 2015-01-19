package Cards;

import android.content.Context;

import android.view.ViewGroup;
import android.view.View;

import java.util.ArrayList;

import android.widget.TextView;

import android.graphics.Color;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.Legend.LegendForm;
import com.github.mikephil.charting.utils.XLabels;
import com.github.mikephil.charting.utils.YLabels;

import com.t_danbubbletea.bubbleteaapp.R;

import org.w3c.dom.Text;

public class UserStatisticsCard extends Card {

    private String[] mMonths = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
                                              "Aug", "Sep", "Oct", "Nov", "Dec"};
    private int[] mColors = new int[] {
            Color.rgb(137, 230, 81),
            Color.rgb(240, 240, 30),
            Color.rgb(89, 199, 250),
            Color.rgb(250, 104, 104)
    };


    public UserStatisticsCard(Context context){
        super(context);
        initCard();
    }

    public UserStatisticsCard(Context context, int innerLayout){
        super(context, innerLayout);
        initCard();
    }

    public void initCard(){

        UserStatsCardHeader userStatsCardHeader = new UserStatsCardHeader(getContext(),
                                                        R.layout.user_statistics_inner_text_layout);
        addCardHeader(userStatsCardHeader);
    }

    @Override
    public void setupInnerViewElements(ViewGroup viewGroup, View view){

        LineChart teaViewTimes = (LineChart) view.findViewById(R.id.chart1);
        //LineChart confirmedBuys = (LineChart) view.findViewById(R.id.chart2);

        LineData data = generateData(36, 100);

        chartSetUp(teaViewTimes, data, mColors[1 % mColors.length]);
        //chartSetUp(confirmedBuys, data, mColors[2 % mColors.length ]);

    }

    private void chartSetUp(LineChart lineChart, LineData lineData, int color){

        // char customization
        lineChart.setStartAtZero(true);
        lineChart.setDrawYValues(false);
        lineChart.setDrawBorder(false);
        lineChart.setDescription("");
        lineChart.setNoDataTextDescription("No Data Available.");
        lineChart.setDrawVerticalGrid(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setGridColor(Color.WHITE & 0x70FFFFFF);
        lineChart.setGridWidth(1.25f);

        // add data
        lineChart.setData(lineData);

        // get the legend (only possible after setting data)
        Legend l = lineChart.getLegend();

        // modify the legend
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(LegendForm.CIRCLE);
        l.setFormSize(6f);
        l.setTextColor(Color.BLACK);

        YLabels y = lineChart.getYLabels();
        y.setTextColor(Color.BLACK);
        y.setLabelCount(4);

        XLabels x = lineChart.getXLabels();
        x.setTextColor(Color.BLACK);
    }

    private LineData generateData(int count, float range) {
        ArrayList<String> xVals = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            xVals.add(mMonths[i%12]);
        }

        ArrayList<Entry> yVals = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 3;
            yVals.add(new Entry(val, i));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "DataSet 1");

        set1.setLineWidth(1.75f);
        set1.setCircleSize(3f);
        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLUE);
        set1.setHighLightColor(Color.BLACK);

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        return data;
    }

}
    class UserStatsCardHeader extends CardHeader{

        public UserStatsCardHeader(Context context, int innerLayout){
            super(context, innerLayout);
        }

        @Override
        public void setupInnerViewElements(ViewGroup viewGroup, View view){
            TextView userStatsTitle = (TextView) view.findViewById(R.id.user_stats_title);
            TextView userStatsSub = (TextView) view.findViewById(R.id.user_stats_subtitle);

            userStatsTitle.setText("Number of People Viewing This Tea");
            userStatsSub.setText("CLick on the months (Jan, Apr, etc) to view stats!");

        }

    }
