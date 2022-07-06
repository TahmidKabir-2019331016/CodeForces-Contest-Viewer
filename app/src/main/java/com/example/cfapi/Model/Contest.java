package com.example.cfapi.Model;

import android.text.format.Time;

public class Contest {
    private String name;
    private boolean isUpcoming;
    private long duration;
    private long startTime;


    public Contest() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUpcoming() {
        return isUpcoming;
    }

    public void setUpcoming(boolean upcoming) {
        isUpcoming = upcoming;
    }

    public String getDuration() {
        String time = "";
        long days = duration / 86400;
        if (days > 0) {
            time += days + " day(s) ";
        }
        duration -= days * 86400;
        long hours = duration / 3600;
        if (hours > 0) {
            time += hours + " hour(s) ";
        }
        duration -= 3600 * hours;
        long minutes = duration / 60;
        if (minutes > 0) {
            time += minutes + " minute(s).";
        }
        return time;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getStartTime() {
        String time = "";
        startTime *= -1;
        long days = startTime / 86400;
        if (days > 0) {
            time += days + " day(s) ";
        }
        startTime -= days * 86400;
        long hours = startTime / 3600;
        if (hours > 0) {
            time += hours + " hour(s) ";
        }
        startTime -= 3600 * hours;
        long minutes = startTime / 60;
        if (minutes > 0) {
            time += minutes + " minute(s).";
        }
        return time;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}