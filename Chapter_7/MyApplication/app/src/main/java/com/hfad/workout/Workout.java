package com.hfad.workout;

import android.content.SyncRequest;

public class Workout {
    private String name;
    private String description;

    public static final Workout[] workouts = {
            new Workout("Workout 1", "Workout 1 details.\nGo here."),
            new Workout("Workout 2", "Workout 2 details.\nGo here."),
            new Workout("Workout 3", "Workout 3 details.\nGo here."),
            new Workout("Workout 4", "Workout 4 details.\nGo here.")
    };

    private Workout(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return  this.description;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }
}
