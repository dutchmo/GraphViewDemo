package com.gmatous.graph;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gmatous on 1/3/14.
 */
public class GraphListFragment extends ListFragment{
    private OnItemSelectedListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph_list,
                container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };
        values=getResources().getStringArray(R.array.graph_types);
        List<String> allTypes = new ArrayList();
        for (GraphTypes type: GraphTypes.values()) {
            allTypes.add(type.toString());
        }
        values = allTypes.toArray(new String [allTypes.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        final String item = (String) l.getItemAtPosition(position);
        // or l.getCheckedItemPosition();
        updateDetail(GraphTypes.valueOf(item));
        // TODO

    }

    public interface OnItemSelectedListener {
        public void onGraphSelected(GraphTypes type);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }


    // May also be triggered from the Activity
    public void updateDetail(GraphTypes type) {
        // Send data to Activity
        listener.onGraphSelected(type);
    }
}
