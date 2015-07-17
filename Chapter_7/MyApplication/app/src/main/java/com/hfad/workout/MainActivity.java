package com.hfad.workout;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity implements WorkoutListFragment.WorkoutListListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(long id) {
        /*
            LOGIC: if the view has a <FragmentContainer> it means that the xml in layout-large
            folder is being used, otherwise the one in layout folder is.
         */
        View fragmentContainer = findViewById(R.id.fragment_container);
        if(fragmentContainer != null) {
            // Tablet view in use, simply replace the fragment in <FragmentContainer>
            WorkoutDetailFragment details = new WorkoutDetailFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction(); // begin transaction
            details.setWorkoutId(id);
            ft.replace(R.id.fragment_container, details); // replace the fragment in the R.id.fragment_container
            // view with the newly created "details" fragment
            ft.addToBackStack(null); // add the fragment to the back stack so that we cam return to it
            // using the back button
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit(); // commit the transaction (apply changes, all at once)
        } else {
            // Phone view in use, start a new activity
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, (int)id);
            startActivity(intent);
        }
    }
}
