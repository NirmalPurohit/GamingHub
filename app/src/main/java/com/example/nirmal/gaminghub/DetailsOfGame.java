package com.example.nirmal.gaminghub;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class DetailsOfGame extends AppCompatActivity {

    public String gameID="";
    TextView tv=null;
    FrameLayout ll=null;
    Button download=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tv = (TextView) findViewById(R.id.detail);
        ll=(FrameLayout) findViewById(R.id.rl);
        Bundle bundle = getIntent().getExtras();
        final String value = bundle.getString("gameid");
        gameID = value;
        new ViewDetailsTask(DetailsOfGame.this).execute();
        download=(Button) findViewById(R.id.dwnld);
        download.setOnClickListener(new View.OnClickListener()
        {
                                       public void onClick(View v)
                                       {
                                           Intent loginActivity=new Intent(DetailsOfGame.this,LoginActivity.class);
                                          Bundle bundle=new Bundle();
                                           bundle.putString("game",gameID);
                                           loginActivity.putExtras(bundle);
                                           startActivity(loginActivity);
                                       }
                                    }
        );
    }

    public class ViewDetailsTask extends AsyncTask<Void, Void, Void> {

        StringBuilder getOutput = new StringBuilder();

        DetailsOfGame detailsOfGame=null;
        ViewDetailsTask(DetailsOfGame detailsOfGame)
        {
            this.detailsOfGame=detailsOfGame;
        }
        protected Void doInBackground(Void... param) {

            try {
                String gmID=gameID;
                URL openUrl = new URL("http://192.168.1.4:8080/GamingHub/ShowDataServlet");

                HttpURLConnection connection = (HttpURLConnection) openUrl.openConnection();

                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                OutputStream os=connection.getOutputStream();
                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data=URLEncoder.encode("gameid","UTF-8")+"="+URLEncoder.encode(gmID,"UTF-8");
                bw.write(data);
                bw.close();
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"iso-8859-1"));

                String line = "";

                while ((line = br.readLine()) != null) {
                    getOutput.append(line+"\n");
                }
                br.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void result)
        {

            tv.setText(getOutput);
            ll.setBackgroundResource(R.drawable.image);
        }
    }

}
