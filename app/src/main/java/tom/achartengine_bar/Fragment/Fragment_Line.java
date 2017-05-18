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
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Line extends Fragment {

    private XYMultipleSeriesRenderer xyMultipleSeriesRenderer;
    private XYMultipleSeriesDataset xyMultipleSeriesDataset;
    private XYSeries xySeries;
    private GraphicalView achart;
    private Context context;
    private int[] y={10,12,13,14,15,10,12,13,15,13};
    private int[] x={0,1,2,3,4,5,6,7,8,9};
    private String date;
    private int flg =0;
    public Fragment_Line() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity().getApplicationContext();
        xySeries = new XYSeries("折线图");
        xyMultipleSeriesDataset = new XYMultipleSeriesDataset();
        xyMultipleSeriesDataset.addSeries(xySeries);
        xyMultipleSeriesRenderer =buildRenderer(Color.GREEN,PointStyle.CIRCLE,true);
        setChartSetting(xyMultipleSeriesRenderer,"X","Y",1,10,0,20);
        xyMultipleSeriesDataset = SetData();
        achart = ChartFactory.getLineChartView(context,xyMultipleSeriesDataset,xyMultipleSeriesRenderer);
        // Inflate the layout for this fragment
        return achart;
    }
    protected  XYMultipleSeriesDataset SetData()
    {

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        XYSeries series = new XYSeries("超标图");
        for(int i=0;i<y.length;i++)
        {
            xySeries.add(x[i],y[i]);

            if(y[i]>13)
            {
                flg = i;
            }
        }
        dataset.addSeries(xySeries);
        dataset.addSeries(series);
        return dataset;
    }
    protected XYMultipleSeriesRenderer buildRenderer(int color, PointStyle style, boolean fill)
    {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        XYSeriesRenderer xySeriesRenderer = new XYSeriesRenderer();
        xySeriesRenderer.setColor(color);
        xySeriesRenderer.setPointStyle(style);
        xySeriesRenderer.setFillPoints(fill);
        xySeriesRenderer.setDisplayChartValuesDistance(30);
        xySeriesRenderer.setDisplayChartValues(true);
        renderer.addSeriesRenderer(xySeriesRenderer);

        XYSeriesRenderer renderTwo = new XYSeriesRenderer();
        renderTwo.setColor(Color.RED);
        renderTwo.setFillPoints(true);
        renderTwo.setPointStyle(PointStyle.CIRCLE);
        renderTwo.setDisplayChartValues(true);
        renderer.addSeriesRenderer(renderTwo);
        return renderer;
    }

    protected void setChartSetting(XYMultipleSeriesRenderer renderer,String xTitle,String yTitle,
                                   double xMin,double xMax,double yMin,double yMax)
    {
        renderer.setChartTitle("折线图");
        renderer.setXTitle(xTitle);
        renderer.setYTitle(yTitle);
        renderer.setShowGridX(false);
        renderer.setXLabels(0);
        renderer.addXTextLabel(1,"My");
        renderer.addXTextLabel(2,"My");
        renderer.addXTextLabel(3,"My");
        renderer.setXAxisMin(xMin);
        renderer.setXAxisMax(xMax);
        renderer.setYAxisMin(yMin);
        renderer.setYAxisMax(yMax);
//        renderer.setShowGrid(false);
        renderer.setLabelsTextSize(50);
        renderer.setYLabelsAlign(Paint.Align.RIGHT);
       // renderer.setGridColor(Color.GREEN);
        renderer.setPanEnabled(true,true);
        renderer.setXLabels(10);
        renderer.setYLabels(20);
        renderer.setMargins(new int[]{60,60,60,60});
        renderer.setShowLegend(false);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
