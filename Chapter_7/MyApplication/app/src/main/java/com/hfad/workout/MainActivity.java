package com.hfad.workout;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WorkoutDetailFragment frag = (WorkoutDetailFragment)getFragmentManager().findFragmentById(R.id.detail_frag);
        frag.setWorkoutId(1);

    }

}
