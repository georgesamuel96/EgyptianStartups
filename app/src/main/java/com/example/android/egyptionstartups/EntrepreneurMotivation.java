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
import com.google.android.youtube.player.YouTubeBaseActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EntrepreneurMotivation extends YouTubeBaseActivity {

    private ArrayList<Video> videos = new ArrayList<>();
    private RecyclerView recyclerView;
    VideoAdapter videoAdapter;
    String json_url = "http://192.168.87.2/EgyptianStartups/video.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrepreneur_motivation);

        recyclerView = (RecyclerView)findViewById(R.id.youtube_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        getData();
    }

    private void getData() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, json_url, (String) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while(count < response.length())
                        {
                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                String id = jsonObject.getString("id");
                                String content = jsonObject.getString("content");
                                Video video = new Video(id, content);
                                videos.add(video);
                                count++;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        videoAdapter= new VideoAdapter(getApplicationContext(), videos);
                        recyclerView.setAdapter(videoAdapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EntrepreneurMotivation.this, "Error from server", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        MySingleton.getInstance(EntrepreneurMotivation.this).addToRequestQueu(jsonArrayRequest);
    }

}
