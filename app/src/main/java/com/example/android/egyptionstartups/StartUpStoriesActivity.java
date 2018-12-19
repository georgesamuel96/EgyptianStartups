package com.example.android.egyptionstartups;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StartUpStoriesActivity extends AppCompatActivity {

    private ArrayList<Person> persons = new ArrayList<>();
    private RecyclerView recyclerView;
    PersonAdapter personAdapter;
    String json_url = "http://192.168.87.2/EgyptianStartups/story.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up_stories);

        //getSupportActionBar().hide();

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        getData();
    }

    private void getData()
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, json_url, (String) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while(count < response.length())
                        {
                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                String name = jsonObject.getString("name");
                                String img = jsonObject.getString("img");
                                String ceo = jsonObject.getString("ceo");
                                String story = jsonObject.getString("story");
                                String site = jsonObject.getString("site");
                                String personImage = jsonObject.getString("personImage");
                                Person person = new Person(name, ceo, story, site, img, personImage);
                                persons.add(person);
                                count++;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        personAdapter = new PersonAdapter(persons, getApplicationContext());
                        recyclerView.setAdapter(personAdapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StartUpStoriesActivity.this, "Error", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        MySingleton.getInstance(StartUpStoriesActivity.this).addToRequestQueu(jsonArrayRequest);
    }
}
