package com.gmatous.graph;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity implements GraphListFragment.OnItemSelectedListener {

    GraphTypes currentGraphType = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //notify("onCreate");
    }

    @Override
    public void onGraphSelected(GraphTypes currentGraph) {
        GraphDetailFragment fragment = (GraphDetailFragment) getFragmentManager()
                .findFragmentById(R.id.detailFragment);

        this.currentGraphType = currentGraph;
        if (fragment != null && fragment.isInLayout()) {
            fragment.setGraph(this.currentGraphType);
        }
        else {
            Intent intent = new Intent(getApplicationContext(),
                    DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_URL, currentGraph.toString());
            startActivity(intent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //notify("onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.currentGraphType != null)
            onGraphSelected(this.currentGraphType);
        //notify("onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //notify("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //notify("onDestroy");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
                String typeStr = savedInstanceState.getString("CURRENT_GRAPH_TYPE");
        this.currentGraphType = GraphTypes.valueOf(typeStr);
        Toast.makeText(getApplicationContext(),
                "Graph Type:" + this.currentGraphType, Toast.LENGTH_SHORT)
                .show();
        //notify("onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
GraphTypes graphType = this.currentGraphType == null ? GraphTypes.DEFAULT : this.currentGraphType;
     outState.putString("CURRENT_GRAPH_TYPE", graphType.toString());
        //notify("onSaveInstanceState");
    }

    private void notify(String methodName) {
        String name = this.getLocalClassName();
        String[] strings = name.split("\\.");
        Notification noti = new Notification.Builder(this)
                .setContentTitle(methodName + " " + strings[strings.length - 1]).setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentText(name).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify((int) System.currentTimeMillis(), noti);
    }
}