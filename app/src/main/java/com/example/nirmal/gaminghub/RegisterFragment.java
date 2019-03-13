package com.example.nirmal.gaminghub;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    EditText name=null;
    EditText password=null;
    EditText age=null;
    Button reg=null;

    String uname="";
    String pass="";
    String ag=""
;
    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState)
    {
            name=(EditText) getView().findViewById(R.id.uname);
            password=(EditText) getView().findViewById(R.id.pass);
            age=(EditText) getView().findViewById(R.id.age);
            reg=(Button) getView().findViewById(R.id.reg);

        reg.setOnClickListener(new View.OnClickListener()
        {
                                  public void onClick(View v)
                                  {
                                      uname=name.getText().toString();
                                      pass=password.getText().toString();
                                      ag=age.getText().toString();
                                      new RegisterTask().execute();
                                  }
                               }
        );
    }


    public class RegisterTask extends AsyncTask<Void, Void, Void>{
        int flag=0;
        public Void doInBackground(Void... params)
        {
            try {
                URL openUrl = new URL("http://192.168.1.4:8080/GamingHub/insert");
                // Toast.makeText(getApplicationContext(), "in async task ", Toast.LENGTH_LONG).show();

                HttpURLConnection connection = (HttpURLConnection) openUrl.openConnection();

                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                OutputStream os=connection.getOutputStream();
                // Log.d("After URL","here");
                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data= URLEncoder.encode("uname", "UTF-8")+"="+URLEncoder.encode(uname,"UTF-8")+"&"+URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8")+"&"+URLEncoder.encode("age","UTF-8")+"="+URLEncoder.encode(ag,"UTF-8");
                bw.write(data);
                bw.close();
                //   connection.setDoInput(true);
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"iso-8859-1"));

                String line = "";

                while ((line = br.readLine()) != null) {
                    if(line.equals("ok"))
                        flag=1;
                }
                br.close();

            } catch (Exception e) {
                e.printStackTrace();
                // Toast.makeText(getApplicationContext(), "in async task exception", Toast.LENGTH_LONG).show();
            }
            return null;
        }

        public void onPostExecute(Void result)
        {
            if(flag==1)
            {
                Toast.makeText(getContext(), "Logged In", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
