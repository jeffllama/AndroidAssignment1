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

public class TeamsActivity extends AppCompatActivity {

    private String TAG = TeamsActivity.class.getSimpleName();
    private ListView lv;
    private static String SERVICE_URL = "https://statsapi.web.nhl.com/api/v1/teams";
    private ArrayList<Team> teamList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        teamList = new ArrayList<Team>();
        lv = findViewById(R.id.teamList);
        new GetNames().execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TeamsActivity.this, RosterActivity.class);
                int id = teamList.get(i).getId();
                intent.putExtra("Id", id);
                startActivity(intent);
            }
        });
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetNames extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = null;

            jsonStr = sh.makeServiceCall(SERVICE_URL);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                Log.d(TAG, "Json: " + jsonStr);
                Gson gson = new Gson();
                BaseTeam baseTeam = gson.fromJson(jsonStr, BaseTeam.class);
                teamList = baseTeam.getTeams();
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

            TeamsAdapter adapter = new TeamsAdapter(TeamsActivity.this, teamList);

            lv.setAdapter(adapter);
        }
    }
}