package com.example.weatherapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    StringBuilder previousWeatherInfo;
    Button button;
    boolean showMore =false;
    StringBuilder weatherInfo;
    ToggleButton toggleButton;
    EditText editText;
    TextView textView;
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
        button = findViewById(R.id.button);
        textView=findViewById(R.id.textView);
        editText=findViewById(R.id.editTextText);
        progressBar=findViewById(R.id.progressBar);
        toggleButton = findViewById(R.id.toggleButton);
        toggleButton.setEnabled(false);
        button.setOnClickListener(v->{
            if(editText.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter The Location", Toast.LENGTH_SHORT).show();
            }
            else{
            new fetchAPI().execute("https://api.tomorrow.io/v4/weather/realtime?location="+ editText.getText().toString()+"&apikey=WimaiAgzsbYskXvVyMhNbb2Xlg20bwEI");
            }

        });
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMore=!showMore;
                if(weatherInfo.length()==0){
                    Toast.makeText(MainActivity.this, "Weather Data Not Foud", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(showMore) {
                    textView.setText(weatherInfo.toString());
                }
                else{
                    textView.setText(previousWeatherInfo.toString());
                }
            }
        });
    }
    private class fetchAPI extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            // Runs on the UI thread before doInBackground
            // Initialize and show a progress bar
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(0);
        }
//        0f1daa77253e9ce08602369c703981de
        @Override
        protected String doInBackground(String... params) {
            // Perform background computation
            String urlString = params[0]; // URL to call
            String result = "";
            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
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
                toggleButton.setEnabled(true);
                try {
                    JSONObject jsonResponse = new JSONObject(result);
                    JSONObject data = jsonResponse.getJSONObject("data");
                    JSONObject values = data.getJSONObject("values");
                    JSONObject location = jsonResponse.getJSONObject("location");
                    weatherInfo = new StringBuilder();
                    weatherInfo.append("Location: ").append(location.getString("name")).append("\n\n");
                    weatherInfo.append("Temperature: ").append(values.getDouble("temperature")).append(" °C\n");
                    weatherInfo.append("Feels Like: ").append(values.getDouble("temperatureApparent")).append(" °C\n");
                    weatherInfo.append("Humidity: ").append(values.getInt("humidity")).append(" %\n");
                    previousWeatherInfo=new StringBuilder(weatherInfo.toString());
                    weatherInfo.append("Pressure: ").append(values.getDouble("pressureSurfaceLevel")).append(" hPa\n");
                    weatherInfo.append("Wind Speed: ").append(values.getDouble("windSpeed")).append(" m/s\n");
                    weatherInfo.append("Wind Direction: ").append(values.getDouble("windDirection")).append(" °\n");
                    weatherInfo.append("Cloud Cover: ").append(values.getInt("cloudCover")).append(" %\n");
                    weatherInfo.append("Visibility: ").append(values.getDouble("visibility")).append(" km\n");
                    textView.setText(previousWeatherInfo.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    textView.setText("Error parsing data");
                }
            } else {
                textView.setText("Error fetching data Or Location Not Found");
            }
        }
    }
}