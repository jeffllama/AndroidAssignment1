package ca.bcit.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class PeopleActivity extends AppCompatActivity {

    private String TAG = PeopleActivity.class.getSimpleName();
    private ListView lv;
    private int id;
    private static String SERVICE_URL = "https://statsapi.web.nhl.com/api/v1/people/";
    private ArrayList<People> people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        people = new ArrayList<People>();
        lv = findViewById(R.id.peopleList);
        new GetPeopleInfo().execute();

    }

    private class GetPeopleInfo extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = null;

            id = (Integer) getIntent().getExtras().get("Id");
            String url = SERVICE_URL + id;

            jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                Log.d(TAG, "Json: " + jsonStr);
                Gson gson = new Gson();
                People person = gson.fromJson(jsonStr, People.class);
                people = person.getPeople();
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

            PeopleAdapter adapter = new PeopleAdapter(PeopleActivity.this, people);

            // Attach the adapter to a ListView
            lv.setAdapter(adapter);
        }
    }
}