package com.hfad.workout;


import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class WorkoutListFragment extends ListFragment {

    static interface WorkoutListListener {
        void itemClicked(long id);
    };

    private WorkoutListListener listener;

    public WorkoutListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String[] names = new String[Workout.workouts.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = Workout.workouts[i].getName();
        }

        /*
            Fragment isn't a subclass of Context, so we need some other way to pass the context
            into the ArrayAdapter's constructor. We dos this with the help of layout's inflater
            .getContext() method.
         */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, names);
        setListAdapter(adapter);

        // Calling the superclass onCreateView() method gives you the default layout for ListFragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onAttach(Activity activity) {
        // Gets called when the fragment gets attached to an activity
        super.onAttach(activity);
        this.listener = (WorkoutListListener)activity;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Notify the listener that an item in the list has been clicked
        if(listener != null) {
            listener.itemClicked(id);
        }
    }

}
