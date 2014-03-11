package com.gmatous.graph;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import junit.framework.Assert;

import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

/**
 * Created by gmatous on 1/3/14.
 */
public class GraphDetailFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph_detail,
                container, false);

        return view;
    }

    void setGraphView(GraphView graphView) {
        LinearLayout layout = (LinearLayout) getView().findViewById(R.id.detailFragment);
        Assert.assertNotNull(layout); //runtime
        assert (graphView != null);
        layout.removeAllViews();
        layout.addView(graphView);
    }

    void setAChart(GraphicalView aChart) {
        LinearLayout layout = (LinearLayout) getView().findViewById(R.id.detailFragment);
        Assert.assertNotNull(layout); //runtime
        assert (aChart != null);
        layout.removeAllViews();
        layout.addView(aChart);
    }

    public void setGraph(GraphTypes type) {
        Integer[] data = {40, 12, 7, 8, 10, 26, 37, 53, 65, 72, 76, 82};
        switch (type) {
            case A_CHART_ENGINE1:
                createAChart(type, data);
                break;
            case A_CHART_ENGINE2:
                createAChart(type, data);
                break;
            case A_CHART_ENGINE3:
                createAChart(type, data);
                break;
            case A_CHART_ENGINE4:
                createAChart(type, data);
                break;
            case GRAPH_VIEW1:
                createGraphView(type, data);
                break;
            case GRAPH_VIEW2:
                createGraphView(type, data);
                break;
            case DEFAULT:
                createAChart(GraphTypes.A_CHART_ENGINE3, data);
                break;
            default:
                createGraphView(type, data);
                break;
        }
        return;
    }


    void createGraphView(GraphTypes type, Integer[] data) {
        Context context = this.getView().getContext();
        GraphView graphView = null;
        switch (type) {
            case GRAPH_VIEW1:
                graphView = new BarGraphView(
                        context // context
                        , type.getGraphName() // heading
                );
                break;
            case GRAPH_VIEW2:
                graphView = new LineGraphView(
                        context // context
                        , type.getGraphName() // heading
                );
                break;
        }
        GraphView.GraphViewData[] graphViewData = new GraphView.GraphViewData[data.length];
        for (int i = 0; i < data.length; i++) {
            graphViewData[i] = new GraphView.GraphViewData(i + 1, data[i]);
        }
        GraphViewSeries exampleSeries = new GraphViewSeries(graphViewData);
        graphView.addSeries(exampleSeries); // data
        graphView.setId(R.id.graph_view);
        setGraphView(graphView);
        return;
    }

    protected void createAChart(GraphTypes type, Integer[] data) {
        GraphicalView mChart = null;
        Context context = this.getView().getContext();
        XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        XYSeriesRenderer mCurrentRenderer = new XYSeriesRenderer();
        mRenderer.addSeriesRenderer(mCurrentRenderer);
        XYSeries mCurrentSeries = new XYSeries("Sample Data");
        for (int i = 0; i < data.length; i++) {
            mCurrentSeries.add(i + 1, data[i]);
        }
        mDataset.addSeries(mCurrentSeries);
        switch (type) {
            case A_CHART_ENGINE1:
                //mChart = ChartFactory.getCubeLineChartView(context, mDataset, mRenderer, 0.3f);
                mChart = new AverageCubicTemperatureChart().execute(context);
                break;
            case A_CHART_ENGINE2:
                mChart = new StackedBarChart().execute(context);
                break;
            case A_CHART_ENGINE3:
                mChart = new CombinedTemperatureChart().execute(context);
                ;
                break;
            case A_CHART_ENGINE4:
                mChart = new MultipleTemperatureChart().execute(context);
                ;
                break;
        }
        setAChart(mChart);
    }

}