package com.gmatous.graph;

import android.app.Fragment;
import android.app.FragmentTransaction;
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

    public void setGraph(String type) {
        GraphView graphView = createGraphView(type);
        LinearLayout layout = (LinearLayout) getView().findViewById(R.id.detailFragment);
        Assert.assertNotNull(layout); //runtime
        assert(graphView != null);

  //      FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
   //     transaction.replace(R.id.graph_view, graphView);
    //    transaction.addToBackStack(null);

// Commit the transaction
      //  transaction.commit();
        layout.removeAllViews();
        layout.addView(graphView);
    }

    private GraphView createGraphView(String type) {
        GraphView graphView;
        Context context = this.getView().getContext();
        assert context != null;
//        switch(type) {
//        case "Other":
//             graphView = new LineGraphView(
//                    context // context
//                    , type // heading
//            );
//            break;
//
//            default:
//                 graphView = new BarGraphView(
//                        context // context
//                        , type // heading
//                );
//                break;
//        }

        if (type.equals("GraphView - Line chart"))
            graphView = new LineGraphView(
                    context // context
                    , type // heading
            );

            else if (type.equals("GraphView - Bar chart"))
        graphView = new BarGraphView(
                context // context
                , type // heading
        );
        else
        graphView = new BarGraphView(
                context // context
                , type // heading
        );

        GraphViewSeries exampleSeries = new GraphViewSeries(new GraphView.GraphViewData[] {
                new GraphView.GraphViewData(1, 40)
                , new GraphView.GraphViewData(2, 12)
                , new GraphView.GraphViewData(3, 7)
                , new GraphView.GraphViewData(4, 8)
                , new GraphView.GraphViewData(5, 10)
                , new GraphView.GraphViewData(6, 26)
                , new GraphView.GraphViewData(7, 37)
                , new GraphView.GraphViewData(8, 53)
                , new GraphView.GraphViewData(9, 76)
        });


        graphView.addSeries(exampleSeries); // data
        graphView.setId(R.id.graph_view);
        return graphView;
    }
}
