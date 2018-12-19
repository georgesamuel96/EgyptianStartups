package com.example.android.egyptionstartups;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferenceConfig sharedPreferenceConfig;
    private Button loginBtn;
    private EditText email, password;
    private TextView sigup;
    String server_url = "http://192.168.87.2/EgyptianStartups/login.php", userEmail, userPassword;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        if(sharedPreferenceConfig.readLoginStatus())
        {
            Intent i = new Intent(LoginActivity.this, homepage.class);
            startActivity(i);
            finish();
        }


        /*loginBtn = (Button)findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, homepage.class);
                startActivity(intent);
            }
        });*/

        sigup = (TextView)findViewById(R.id.signup_txt);
        sigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i = new Intent(LoginActivity.this, signup.class);
               startActivity(i);
            }
        });

        builder = new AlertDialog.Builder(LoginActivity.this);
        loginBtn = (Button)findViewById(R.id.login_btn);
        email = (EditText)findViewById(R.id.email_txt);
        password = (EditText)findViewById(R.id.pass_txt);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userEmail = email.getText().toString();
                userPassword = password.getText().toString();
                if(userEmail.equals("") || userPassword.equals(""))
                {
                    builder.setTitle("Something went wrong");
                    displayAlert("Enter a valid email and password");
                }
                else
                {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        String code = jsonObject.getString("code");
                                        if(code.equals("login_failed"))
                                        {
                                            builder.setTitle("Login Error");
                                            displayAlert(jsonObject.getString("message"));
                                        }
                                        else
                                        {
                                            Intent i = new Intent(LoginActivity.this, homepage.class);
                                            startActivity(i);
                                            sharedPreferenceConfig.writeLoginStatus(true);
                                            finish();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this, "Error from server", Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("email", userEmail);
                            params.put("password", userPassword);
                            return params;
                        }
                    };
                    MySingleton.getInstance(LoginActivity.this).addToRequestQueu(stringRequest);
                }
            }
        });

    }

    public void displayAlert(String message)
    {
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                email.setText("");
                password.setText("");
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
