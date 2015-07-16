package com.hfad.workout;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity implements WorkoutListFragment.WorkoutListListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(long id) {
        WorkoutDetailFragment details = new WorkoutDetailFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction(); // begin transaction
        details.setWorkoutId(id);
        ft.replace(R.id.fragment_container, details); // replace the fragment in the R.id.fragement_container
                                                     // view with the newly created "details" fragment
        ft.addToBackStack(null); // add the fragment to the back stack so that we cam returm to it
                                // using the back button
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit(); // commit the transaction (apply changes, all at once)
    }
}
