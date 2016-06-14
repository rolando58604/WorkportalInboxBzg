package com.rolandoamarillo.workportalinboxbizagi.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.rolandoamarillo.workportalinboxbizagi.R;
import com.rolandoamarillo.workportalinboxbizagi.activities.base.BaseActivity;
import com.rolandoamarillo.workportalinboxbizagi.adapters.TasksRecyclerViewAdapter;
import com.rolandoamarillo.workportalinboxbizagi.managers.TasksManager;
import com.rolandoamarillo.workportalinboxbizagi.models.Task;
import com.squareup.otto.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements TasksRecyclerViewAdapter.OnListFragmentInteractionListener {

    @BindView(R.id.progressLayout)
    View progressLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle(R.string.app_name);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, getString(R.string.about), Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        progressLayout.setVisibility(View.VISIBLE);
        bus.register(this);
        tasksManager.getTasks();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bus.unregister(this);
    }

    @Subscribe
    public void onAllTasks(TasksManager.TasksEvent event) {
        if (event.isSuccess()) {
            initView(event.getResponse());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void initView(List<Task> tasks) {
        TasksRecyclerViewAdapter adapter = new TasksRecyclerViewAdapter(tasks, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        progressLayout.setVisibility(View.GONE);
    }

    @Override
    public void onListFragmentInteraction(Task item) {
    }
}
