package com.example.nirmal.gaminghub;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.net.URL;
import java.net.URLEncoder;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyPurchaseFragment extends Fragment {
    EditText username=null;
    EditText password=null;
    Button login=null;
    String uname=null;
    String pass=null;
    TextView content=null;

    public MyPurchaseFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_purchase, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        username = (EditText)getView().findViewById(R.id.usernamelog);
        password = (EditText) getView().findViewById(R.id.passwordlog);
        content= (TextView) getView().findViewById(R.id.details);
        login = (Button) getView().findViewById(R.id.loginpur);
        login.setOnClickListener(new View.OnClickListener() {
                                     public void onClick(View v) {
                                         uname = username.getText().toString();
                                         pass = password.getText().toString();
                                         new LoginPurchaseTask().execute();
                                     }
                                 }
        );
    }

    public class LoginPurchaseTask extends AsyncTask<Void, Void, Void> {
        int flag = 0;
        StringBuilder output=new StringBuilder();
        protected Void doInBackground(Void... params) {
            URL openUrl = null;
            try {
                openUrl = new URL("http://<IP Adress>:8080/GamingHub/ShowPurchase");
                HttpURLConnection connection = (HttpURLConnection) openUrl.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                OutputStream os = connection.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "iso-8859-1"));
                String data = URLEncoder.encode("uname", "UTF-8") + "=" + URLEncoder.encode(uname, "UTF-8") + "&" + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
                bw.write(data);
                bw.close();
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "iso-8859-1"));
                String line = "";
                while ((line = br.readLine()) != null) {
                   output.append(line+"\n");
                }
                br.close();
            } 
            catch(Exception e) {
            }
            return null;
        }
        
        public void onPostExecute(Void result) {
                content.setText(output);
        }
    }
}
