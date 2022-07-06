package com.example.cfapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;


import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cfapi.Model.Contest;
import com.example.cfapi.Utilities.InternetUtils;
import com.example.cfapi.Utilities.JsonParser;

import java.net.URL;
import java.nio.channels.AsynchronousChannelGroup;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    TextView mError;
    ProgressBar mLoading;
    ContestAdapter mContestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_contest);
        mError = findViewById(R.id.tv_error);
        mLoading = findViewById(R.id.pb_loading);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);
        mContestAdapter = new ContestAdapter();
        mRecyclerView.setAdapter(mContestAdapter);
        loadContest();

    }

    private void loadContest() {
        URL url = InternetUtils.BuildURL();
        Log.v("URL", url.toString());
        showContestView();
        new GetJsonTask().execute(url);
    }

    private void showContestView() {
        mError.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showError() {
        mError.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            mContestAdapter = new ContestAdapter();
            mRecyclerView.setAdapter(mContestAdapter);
            loadContest();
        }
        return super.onOptionsItemSelected(item);
    }

    class GetJsonTask extends AsyncTask<URL, Void, List<Contest>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoading.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Contest> doInBackground(URL... urls) {
            if (urls.length == 0) return null;
            String json = InternetUtils.GetJson(urls[0]);
            return JsonParser.parseContest(json);
        }

        @Override
        protected void onPostExecute(List<Contest> contestList) {
            mLoading.setVisibility(View.INVISIBLE);
            if (contestList != null) {
                showContestView();
                mContestAdapter.setContestList(contestList);
            } else {
                showError();
            }
        }
    }
}