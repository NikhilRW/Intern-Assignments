package com.example.asycnhronousprogramming;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView textView1;
    TextView textView2;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        progressBar = findViewById(R.id.progressBar);
        textView2 = findViewById(R.id.textView3);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v->{
        new fetchAPI().execute("https://api.api-ninjas.com/v1/facts");
        });
    }

//    private class fetchData extends AsyncTask<Void, Integer, String>{
//        @Override
//        protected void onPreExecute() {
//            progressBar.setVisibility(View.VISIBLE);
//        }
//
//        @Override
//        protected String doInBackground(Void... voids) {
//            try{
//                for (int i = 1; i <=100 ; i++) {
//                   Thread.sleep(60);
//                   publishProgress(i);
//                }
//                   return "Data Fetched";
//            }
//            catch (Exception e){
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            textView.setText(s);
//        }
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            Log.d("mytag", "onProgressUpdate: "+values[0]);
//            progressBar.setProgress(values[0]);
//        }
//    }
    private class fetchAPI extends AsyncTask<String, Integer, String>{
        @Override
        protected void onPreExecute() {
            // Runs on the UI thread before doInBackground
            // Initialize and show a progress bar
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(0);
        }

        @Override
        protected String doInBackground(String... params) {
            // Perform background computation
            String urlString = params[0]; // URL to call
            String result = "";
            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                        publishProgress(response.length());
                    }
                    in.close();
                    result = response.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // Runs on the UI thread when publishProgress is called
            // Update progress bar
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            // Runs on the UI thread after doInBackground completes
            // Hide progress bar and update UI with result
            progressBar.setVisibility(View.GONE);
            if (result != null) {
                textView2.setText(result);
            } else {
                textView2.setText("Error fetching data");
            }
        }
    }

}
