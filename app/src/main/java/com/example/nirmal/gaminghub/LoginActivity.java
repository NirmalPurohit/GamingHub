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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity {

    Button registerLink = null;
    EditText username = null;
    EditText password = null;
    String uname = null;
    String pass = null;
    Button login = null;
    String value = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        registerLink = (Button) findViewById(R.id.registerlink);
        Bundle bundle=getIntent().getExtras();
        value=bundle.getString("game");

        registerLink.setOnClickListener(new View.OnClickListener() {

                                            @Override
                                            public void onClick(View v) {
                                                Log.d("Login", "Register call");
                                                RegisterFragment registerFragment = new RegisterFragment();
                                                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                                fragmentTransaction.replace(R.id.Login_layout, registerFragment);
                                                fragmentTransaction.commit();
                                            }
                                        }
        );
        login = (Button) findViewById(R.id.blogin);
        login.setOnClickListener(new View.OnClickListener() {
                                     public void onClick(View v) {
                                         uname = username.getText().toString();
                                         pass = password.getText().toString();
                                        /* Bundle bundle = getIntent().getExtras();
                                         value = bundle.getString("game");*/
                                         new LoginTask().execute();


                                     }
                                 }
        );


    }


    public class LoginTask extends AsyncTask<Void, Void, Void> {
        int flag = 0;
      //  URL purchase = null;

        protected Void doInBackground(Void... params) {

            URL openUrl = null;

            try {
                openUrl = new URL("http://192.168.1.4:8080/GamingHub/ShowUser");

                // Toast.makeText(getApplicationContext(), "in async task ", Toast.LENGTH_LONG).show();

                HttpURLConnection connection = (HttpURLConnection) openUrl.openConnection();

                connection.setRequestMethod("POST");
                // connection.setDoInput(true);
                connection.setDoOutput(true);
                OutputStream os = connection.getOutputStream();
                Log.d("After URL", "here");
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "iso-8859-1"));
                Log.d("Button", "calling Async");
                String data = URLEncoder.encode("uname", "UTF-8") + "=" + URLEncoder.encode(uname, "UTF-8") + "&" + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");

                bw.write(data);
                bw.close();
                //   connection.setDoInput(true);
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "iso-8859-1"));

                String line = "";

                while ((line = br.readLine()) != null) {
                    if (line.equals("ok")) {

                        flag = 1;
                    }

                }
                // Log.d("AsyncTask", line);
                br.close();

            } catch (Exception e) {
            }

            return null;
        }

        public void onPostExecute(Void result) {
            if (flag == 1) {
                    Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                     new PurchaseTask().execute();
            }
        }
    }


    public class PurchaseTask extends AsyncTask<Void, Void, Void>
    {
        URL purchase=null;
        int flag=0;
        public Void doInBackground(Void... params)
        {
            try{
                purchase = new URL("http://192.168.1.4:8080/GamingHub/InsertPurchase");
                HttpURLConnection con = (HttpURLConnection) purchase.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                OutputStream osp = con.getOutputStream();
                BufferedWriter bwp = new BufferedWriter(new OutputStreamWriter(osp, "iso-8859-1"));
                Log.d("purchase","buffer");
                String datap = URLEncoder.encode("uname", "UTF-8") + "=" + URLEncoder.encode(uname, "UTF-8") + "&" + URLEncoder.encode("gameid", "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8");

                bwp.write(datap);
                Log.d("purchase", "Write");
                bwp.close();
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"iso-8859-1"));

                String line = "";

                while ((line = br.readLine()) != null) {
                    if(line.equals("ok"))
                        flag=1;
                }
                br.close();

            }
            catch(Exception e)
            {

            }
            return null;
        }
        public void onPostExecute(Void result) {
            if(flag==1)
                Toast.makeText(LoginActivity.this, "purchased", Toast.LENGTH_SHORT).show();
        }
    }
}
