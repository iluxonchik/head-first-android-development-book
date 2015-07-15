package com.hfad.workout;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class WorkoutDetailFragment extends Fragment {
    private long workoutId;

    public WorkoutDetailFragment() {
        // All fragments must contain a public, no-argument constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }

    public void setWorkoutId(long id) {
        this.workoutId = id;
    }


}
