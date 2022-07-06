package com.example.cfapi.Utilities;

import android.text.format.Time;
import android.util.Log;

import com.example.cfapi.Model.Contest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class JsonParser {

    public static List<Contest> parseContest(String json) {
        List<Contest> contestList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray("result");
            for (int i = 0; i < result.length(); ++i) {
                Contest contest = new Contest();
                JSONObject object = result.getJSONObject(i);

                contest.setName(object.getString("name"));
                contest.setUpcoming(object.getString("phase").equals("BEFORE"));
                long duration = object.getInt("durationSeconds");
                contest.setDuration(duration);

                contest.setStartTime(object.getLong("relativeTimeSeconds"));
//                if (contest.isUpcoming())
                    contestList.add(contest);
            }

        } catch (JSONException e) {
            Log.v("JSONPARSER", "Exception");
            e.printStackTrace();
            return null;
        }
        return contestList;
    }
}
