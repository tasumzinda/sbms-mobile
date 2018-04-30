package org.totalit.sbms;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.totalit.sbms.Database.AppDatabase;
import org.totalit.sbms.domain.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {
    private AppDatabase mdb;
    private TextInputLayout inputLayoutUsername, inputLayoutPwd;
    private TextView userName;
    private TextView password;
    private Button loginBtn;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mdb = AppDatabase.getFileDatabase(getApplicationContext());
        inputLayoutUsername = findViewById(R.id.input_layout_username);
        inputLayoutPwd = findViewById(R.id.input_layout_pwd);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        requestQueue = Volley.newRequestQueue(this);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // getUsers("http://10.0.2.2:8090/rest/user/all");
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }

   /* public void getUsers() {
        Toast.makeText(LoginActivity.this, "Button Pressed", Toast.LENGTH_SHORT).show();
        String url = "http://10.0.2.2:8090/rest/user/all";
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        Toast.makeText(LoginActivity.this, jsonObject.getString("firstName"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            }, new Response.ErrorListener(){
                @Override
                        public void onErrorResponse(VolleyError error){
                  //  error.printStackTrace();
                    Log.i("Error", "Failed");
            }
        });
    }*/

    private void getUsers(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
               // Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    saveUsers(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();

        getJSON.execute();
    }
    private void saveUsers(String jsonUsers) throws JSONException {
        mdb = AppDatabase.getInMemoryDatabase(getApplicationContext());
        JSONArray jsonArray = new JSONArray(jsonUsers);
        // String[] users = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject obj = jsonArray.getJSONObject(i);
            int id = obj.getInt("id");
            String active = obj.getString("active");
            String firstName = obj.optString("firstName");
            String lastName = obj.getString("lastName");
            String userName = obj.getString("userName");
            String pwd = obj.getString("password");
            final User user = new User();
                    user.setId(id);

                    user.setActive(active);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setUserName(userName);
                    user.setPassword(pwd);

            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    mdb.userRepository().insertUser(user);

                }
            });
            Toast.makeText(getApplicationContext(), "Saved Success", Toast.LENGTH_SHORT).show();
        }

    }
    }


