package com.example.android.timings;

import android.app.Application;

public class App extends Application {

    private Timing[] timings;

    public Timing[] getTimings() {
        return timings;
    }

    public void setTimings(Timing[] timings) {
        this.timings = timings;
    }
}
