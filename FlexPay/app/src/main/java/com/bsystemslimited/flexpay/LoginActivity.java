package com.bsystemslimited.flexpay;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends Activity {

    //Initializing
    private static String URL = "http://197.159.128.38/FlexPayWebService/FlexPay.svc/SignIn?";

    private InternetChecker inChecker = new InternetChecker(this);
    private MobileDataConnectionDetector mobileData;
    private WiFiDataConnectionDetector wifiData;

    private Boolean isMobileDataAvailable = false;
    private Boolean isWifiAvailable = false;

    //UI Controls
    private ProgressDialog dialog;
    EditText txtUsername,txtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Setting Up UI Controls
        txtUsername = (EditText)findViewById(R.id.etUsername);
        txtPassword = (EditText)findViewById(R.id.etPassword);
        btnLogin =(Button) findViewById(R.id.bLogIn);

        //
        mobileData = new MobileDataConnectionDetector(LoginActivity.this);
        wifiData = new WiFiDataConnectionDetector(LoginActivity.this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isMobileDataAvailable = mobileData.checkMobileInternetConn();
                isWifiAvailable = wifiData.checkMobileInternetConn();
                //
                String username = "Username=" + txtUsername.getText().toString();
                String password = "&Password=" + txtPassword.getText().toString();

                if(txtUsername.getText().toString().contentEquals("")){
                    Toast.makeText(LoginActivity.this,"Please provide Username.",Toast.LENGTH_SHORT).show();
                }else if (txtPassword.getText().toString().contentEquals("")) {
                    Toast.makeText(getApplicationContext(), "Please provide Password", Toast.LENGTH_SHORT).show();
                }else {
                    if (isMobileDataAvailable != false || isWifiAvailable != false) {
                        new Primary_AuthTask(URL + username + password).execute();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "No Internet Connectivity", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }




    //Primary FlexPay Authentication Section
    private class Primary_AuthTask extends AsyncTask<Void,Void,JSONObject> {

        private JSONObject jsonObject;
        String url;

        public Primary_AuthTask(String _url){
            url = _url;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            dialog = ProgressDialog.show(LoginActivity.this,"FlexPay","Authenticating...Please wait");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected JSONObject doInBackground(Void... params){

            if (inChecker.isOnline()) {
                WebServiceExecutor webservice = new WebServiceExecutor();
                try {
                    jsonObject = webservice.HttpGetData(url).getJSONObject(
                            "SignInResult");
                    return jsonObject;

                } catch (JSONException e) {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), e.getMessage(),
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    dialog.dismiss();

                }

            }
            return null;

        }

        @Override
        protected void onPostExecute(JSONObject result){

            super.onPostExecute(result);
            dialog.dismiss();
            try {

                String response = result.getString("ResponsId");
                String responseMessage = "";
                if (response.contentEquals("0")) {
                    SaveSharedPreference.setUserName(getApplicationContext(),
                            txtUsername.getText().toString());
                    SaveSharedPreference.setUserPassword(
                            getApplicationContext(), txtPassword.getText()
                                    .toString());
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(LoginActivity.this, DashActivity.class);
                            LoginActivity.this.startActivity(i);
                            LoginActivity.this.finish();
                        }
                    }, 0);
                } else {
                    Toast.makeText(getApplicationContext(), "Login Unsuccessful.Please Check Credentials",
                            Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            } catch (Throwable e) {
                Toast.makeText(getApplicationContext(),
                        "Connection Lost", Toast.LENGTH_SHORT).show();
            }

        }

    }

}
