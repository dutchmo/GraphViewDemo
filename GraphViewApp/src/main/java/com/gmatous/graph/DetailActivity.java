package com.gmatous.graph;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

public class DetailActivity extends Activity implements GraphListFragment.OnItemSelectedListener {

    public static final String EXTRA_URL = "url";
    GraphTypes currentGraphType = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Need to check if Activity has been switched to landscape mode
        // If yes, finished and go back to the start Activity
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        setContentView(R.layout.activity_detail);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String currentGraphStr = extras.getString(EXTRA_URL);
            onGraphSelected(GraphTypes.valueOf(currentGraphStr));
        }
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
     outState.putString("CURRENT_GRAPH_TYPE", this.currentGraphType.toString());
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