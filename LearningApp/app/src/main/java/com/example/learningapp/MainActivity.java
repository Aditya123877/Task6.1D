package com.example.learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText usernameET, passwordET;
    TextView signUpTV;
    Button loginBTN;
    ManagerDB managerDB;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        managerDB = new ManagerDB(this);
        usernameET = (EditText) findViewById(R.id.UsernameText);
        passwordET = (EditText) findViewById(R.id.PasswordText);
        loginBTN = (Button) findViewById(R.id.LoginButton);
        signUpTV = (TextView) findViewById(R.id.NeedAnAccountTextView);
        addSampleInterests();
        setupButton();
    }

    private void setupButton() {
        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameET.getText().toString().trim();
                String password = passwordET.getText().toString().trim();
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    user = managerDB.verifyCredentials(username, password);
                    if (user != null) {
                        List<Interest> interests = managerDB.getUserInterests(user.getId()); // Fetch updated interests
                        user.setInterests(interests); // Update user object
                        Intent intent;
                        if (interests != null && !interests.isEmpty()) {
                            intent = new Intent(MainActivity.this, TaskActivity.class);
                        } else {
                            intent = new Intent(MainActivity.this, SelectInterestActivity.class);
                        }
                        intent.putExtra("User", user);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signUpTV.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, SignUpActivity.class)));
    }

    public void addSampleInterests() {
        managerDB.addInterest(new Interest("Algorithms"));
        managerDB.addInterest(new Interest("Data Structures"));
        managerDB.addInterest(new Interest("Web Development"));
        managerDB.addInterest(new Interest("UX/UI"));
        managerDB.addInterest(new Interest("Machine Learning"));
        managerDB.addInterest(new Interest("OS"));
        managerDB.addInterest(new Interest("NLP"));
        managerDB.addInterest(new Interest("Computer Vision"));
        managerDB.addInterest(new Interest("Cyber Security"));
        managerDB.addInterest(new Interest("SDLC"));
        managerDB.addInterest(new Interest("IoT"));
        managerDB.addInterest(new Interest("Cyber Security"));
        managerDB.addInterest(new Interest("Cloud Computing"));
        managerDB.addInterest(new Interest("Networking"));
        managerDB.addInterest(new Interest("Big Data"));
        managerDB.addInterest(new Interest("Artificial Intelligence"));
    }
}
