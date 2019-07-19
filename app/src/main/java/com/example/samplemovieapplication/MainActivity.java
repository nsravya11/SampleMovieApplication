package com.example.samplemovieapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public List<Movie> Movies=new ArrayList<>();
    public MyAdapter myAdapter;
    public RecyclerView myRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myAdapter=new MyAdapter(Movies,MainActivity.this);
        myRecyclerView=(RecyclerView)findViewById(R.id.recycleView);
        GridLayoutManager rlayoutManager=new GridLayoutManager(getApplicationContext(),3);
        myRecyclerView.setLayoutManager(rlayoutManager);
        myRecyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        new myAsyncClass().execute("http://www.json-generator.com/api/json/get/bVkDkUdeaa?indent=2");
    }
    public class myAsyncClass extends AsyncTask<String,Void,String>
    {
        private static final String TAG = "myAsyncClass";
        ArrayList<Movie> myMovies=new ArrayList<>();
        @Override
        protected String doInBackground(String... strings) {
            String response="";
            try{
                URL uri=new URL(strings[0]);
                HttpURLConnection urlConnection=(HttpURLConnection)uri.openConnection();
                urlConnection.setRequestMethod("GET");
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                response = convertInputStreamToString(inputStream);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                Log.e("AAAA",e.toString());
                Log.e("background","error occured is do in background method");
            }
            return response;
        }
        private String convertInputStreamToString(InputStream inputStream) {
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                while((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                String image,name,language,genre;
                Double rating,views;
                Movie movie;
                JSONObject jsonRoot = new JSONObject(s);
                JSONArray dataArray = jsonRoot.optJSONArray("response").optJSONObject(0).optJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++)
                {
                    image=dataArray.optJSONObject(i).optString("iconUrl");
                    name=dataArray.optJSONObject(i).optString("name");
                    language=dataArray.optJSONObject(i).optString("langCode");
                    genre=dataArray.optJSONObject(i).optString("genre");
                    rating=dataArray.optJSONObject(i).optDouble("rating");
                    views=dataArray.optJSONObject(i).optDouble("views");
                    movie=new Movie(name,image,language,genre,views,rating);
                    myMovies.add(movie);
                }
                Log.d("AAinside",Integer.toString(myMovies.size()));
                myAdapter=new MyAdapter(myMovies,MainActivity.this);
                myRecyclerView=(RecyclerView)findViewById(R.id.recycleView);
                GridLayoutManager rlayoutManager=new GridLayoutManager(getApplicationContext(),3);
                myRecyclerView.setLayoutManager(rlayoutManager);
                myRecyclerView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }
            catch (Exception e)
            {
                Log.d(TAG,e.toString());
            }
        }
    }
}
