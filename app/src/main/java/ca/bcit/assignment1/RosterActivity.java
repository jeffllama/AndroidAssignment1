package ca.bcit.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class RosterActivity extends AppCompatActivity {

    private String TAG = RosterActivity.class.getSimpleName();
    private ListView lv;
    private int id;
    private static String SERVICE_URL = "https://statsapi.web.nhl.com/api/v1/teams/id/roster";
    private ArrayList<Person> roster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster);

        roster = new ArrayList<Person>();
        lv = findViewById(R.id.roster);
        new GetFullNames().execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(RosterActivity.this, PeopleActivity.class);
                int id = roster.get(i).getPersonDetails().getId();
                intent.putExtra("Id", id);
                startActivity(intent);
            }
        });
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetFullNames extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = null;

            id = (Integer) getIntent().getExtras().get("Id");
            String url = SERVICE_URL.replace("id", "" + id);

            jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                Log.d(TAG, "Json: " + jsonStr);
                Gson gson = new Gson();
                BaseRoster baseRoster = gson.fromJson(jsonStr, BaseRoster.class);
                roster = baseRoster.getRoster();
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            RosterAdapter adapter = new RosterAdapter(RosterActivity.this, roster);

            // Attach the adapter to a ListView
            lv.setAdapter(adapter);
        }
    }
}