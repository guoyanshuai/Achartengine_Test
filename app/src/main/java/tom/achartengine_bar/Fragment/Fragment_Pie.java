package tom.achartengine_bar.Fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Pie extends Fragment {

    private CategorySeries series;
    private DefaultRenderer drender;
    private int color[] = {Color.BLUE, Color.GRAY, Color.GREEN, Color.RED};
    private String tips[] = {"语文", "数学", "英语", "计算机"};
    private double sore[] = {98, 85, 85, 100};
    private Context context;

    public Fragment_Pie() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity().getApplicationContext();
        series = SetData("成绩表",sore);
        drender = builfCategroy(color);
        drender.setZoomEnabled(true);
        drender.setZoomButtonsVisible(true);
        drender.setChartTitleTextSize(50);
        drender.setLabelsColor(Color.RED);
        drender.setDisplayValues(true);
        drender.setZoomButtonsVisible(false);
        return ChartFactory.getPieChartView(context,series,drender);
    }

    protected DefaultRenderer builfCategroy(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLegendTextSize(15);
        renderer.setLabelsTextSize(15);
        renderer.setMargins(new int[]{60, 60, 60, 60});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;

    }

    protected CategorySeries SetData(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        int k=0;
        for (double value:values)
        {
            series.add("Project"+ ++k,value);
        }
        return series;
    }
}
