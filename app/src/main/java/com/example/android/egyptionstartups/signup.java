package com.example.android.egyptionstartups;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {


    private TextView signin;
    Button register;
    EditText Email, Password, ConfirmPassword;
    String email, password, confirmPassword;
    AlertDialog.Builder builder;
    String server_url = "http://192.168.87.2/EgyptianStartups/register.php";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        signin = (TextView)findViewById(R.id.signup_btn);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(signup.this, LoginActivity.class);
                startActivity(i);
            }
        });

        register = (Button)findViewById(R.id.signup_btn);
        Email = (EditText)findViewById(R.id.email);
        Password = (EditText)findViewById(R.id.password);
        ConfirmPassword = (EditText)findViewById(R.id.confirm_password);
        builder = new AlertDialog.Builder(signup.this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = Email.getText().toString();
                password = Password.getText().toString();
                confirmPassword = ConfirmPassword.getText().toString();
                if(email.equals("") || password.equals("") || confirmPassword.equals(""))
                {
                    builder.setTitle("Something went wrong");
                    builder.setMessage("Please fill all the fields");
                    displayAlert("input_error");
                }
                else
                {
                    if(!(password.equals(confirmPassword)))
                    {
                        builder.setTitle("Something went wrong");
                        builder.setMessage("Your passwords not matching");
                        displayAlert("input_error");
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
                                            String message = jsonObject.getString("message");
                                            builder.setTitle("Server Response");
                                            builder.setMessage(message);
                                            Toast.makeText(signup.this, "1234", Toast.LENGTH_SHORT).show();
                                            displayAlert(code);

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(signup.this, "Error from server", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("email", email);
                                params.put("password", password);
                                return params;
                            }
                        };
                        MySingleton.getInstance(signup.this).addToRequestQueu(stringRequest);
                    }
                }
            }
        });

    }

    public void displayAlert(final String code)
    {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(code.equals("input_error"))
                {
                    Email.setText("");
                    Password.setText("");
                    ConfirmPassword.setText("");
                }
                else if(code.equals("reg_success"))
                {
                    finish();
                }
                else if(code.equals("reg_failed"))
                {
                    Email.setText("");
                    Password.setText("");
                    ConfirmPassword.setText("");
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
