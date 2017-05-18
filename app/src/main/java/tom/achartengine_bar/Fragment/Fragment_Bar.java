package tom.achartengine_bar.Fragment;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Bar extends Fragment {

    private XYMultipleSeriesRenderer renderer;
    private XYMultipleSeriesDataset dataset;
    private String[] title = {"BUS1", "BUS2"};
    private double[] BUS1 = {20, 10, 30, 50, 40};
    private double[] BUS2 = {10, 30, 50, 60, 20};
    private List<double[]> values = new ArrayList<double[]>();
    private int[] color = {Color.BLUE, Color.RED};
    private Context context;

    public Fragment_Bar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        values.add(BUS1);
        values.add(BUS2);
        context = getActivity().getApplicationContext();
        renderer = buildBarRenderer(color);
        dataset = buildBarDataset(title,values);
        setChartSetting(renderer,"X","Y",0
                ,6,0,200);
        renderer.getSeriesRendererAt(0).setDisplayChartValues(true);//设置柱子上是否显示数量值  
        renderer.getSeriesRendererAt(1).setDisplayChartValues(true);//设置柱子上是否显示数量值  
        renderer.setXLabels(6);//X轴的近似坐标数  
        renderer.setYLabels(5);//Y轴的近似坐标数  
        renderer.setXLabelsAlign(Paint.Align.LEFT);//刻度线与X轴坐标文字左侧对齐  
        renderer.setYLabelsAlign(Paint.Align.LEFT);//Y轴与Y轴坐标文字左对齐  
        renderer.setPanEnabled(true, false);//允许左右拖动,但不允许上下拖动.  
        renderer.setZoomRate(1.1f);//放大的倍率  
        renderer.setBarSpacing(0.5f);//柱子间宽度 
        // Inflate the layout for this fragment
        return ChartFactory.getBarChartView(context,dataset,renderer, BarChart.Type.DEFAULT);
    }

    protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setAxisTitleTextSize(16);
        renderer.setChartTitleTextSize(20);
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        int length = colors.length;
        for (int i = 0; i < length; i++) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(colors[i]);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }
    protected void setChartSetting(XYMultipleSeriesRenderer renderer,String xTitle,String yTitle,
                                   double xMin,double xMax,double yMin,double yMax)
    {
        renderer.setChartTitle("柱状图");
        renderer.setXTitle(xTitle);
        renderer.setYTitle(yTitle);
        renderer.setXAxisMin(xMin);
        renderer.setXAxisMax(xMax);
        renderer.setYAxisMin(yMin);
        renderer.setYAxisMax(yMax);
        renderer.setXLabelsColor(Color.GREEN);
        renderer.setYLabelsColor(0,Color.GREEN);
//        renderer.setShowLabels(false);
        renderer.setMargins(new int[]{60,60,60,60});
        //renderer.setShowLegend(false);
    }
    protected XYMultipleSeriesDataset buildBarDataset(String[] titles, List<double[]> values) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        int length = titles.length;
        for (int i = 0; i < length; i++) {
            CategorySeries series = new CategorySeries(titles[i]);
            double[] v = values.get(i);
            int seriesLength = v.length;
            for (int k = 0; k < seriesLength; k++) {
                series.add(v[k]);

            }
            dataset.addSeries(series.toXYSeries());
        }
        return dataset;
    }



}
