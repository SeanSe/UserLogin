package com.project.sean.userlogin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

/**
 *
 * Created by Sean on 22/04/2016.
 */

public class MainActivity extends AppCompatActivity {


    // User Session Manager Class
    UserSessionManager session;

    // Button Logout
    Button btnLogout;
    //Button stock management
    Button btnStockManagement;
    //Button checkout
    Button btnCheckout;
    //Button employee management
    Button btnEmpManagement;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Session class instance
        session = new UserSessionManager(getApplicationContext());

        TextView lblName = (TextView) findViewById(R.id.lblName);
        TextView lblEmail = (TextView) findViewById(R.id.lblEmail);

        // Button logout
        btnLogout = (Button) findViewById(R.id.btnLogout);
        //Button stock management
        btnStockManagement = (Button) findViewById(R.id.btnStockManagement);
        //Button checkout
        btnCheckout = (Button) findViewById(R.id.btnCheckout);
        //Button employee management
        btnEmpManagement = (Button) findViewById(R.id.btnEmpManagement);

        Toast.makeText(getApplicationContext(),
                "User Login Status: " + session.isUserLoggedIn(),
                Toast.LENGTH_LONG).show();


        // Check user login (this is the important point)
        // If User is not logged in , This will redirect user to LoginActivity
        // and finish current activity from activity stack.
        if (session.checkLogin())
            finish();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // get name
        String name = user.get(UserSessionManager.KEY_NAME);

        // get role
        final String role = user.get(UserSessionManager.KEY_ROLE);

        // Show user data on activity
        lblName.setText(Html.fromHtml("Name: <b>" + name + "</b>"));
        lblEmail.setText(Html.fromHtml("Role: <b>" + role + "</b>"));


        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // Clear the User session data
                // and redirect user to LoginActivity
                session.logoutUser();
            }
        });

        btnStockManagement.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(role.equals("Manager")) {
                    Toast.makeText(getApplicationContext(), "This is stock management!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid role, manager access only",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(role.equals("Manager") || role.equals("Employee") ) {
                    Toast.makeText(getApplicationContext(), "This is Checkout!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEmpManagement.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(role.equals("Manager")) {
                    Toast.makeText(getApplicationContext(), "This is Checkout!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid role, manager access only",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}