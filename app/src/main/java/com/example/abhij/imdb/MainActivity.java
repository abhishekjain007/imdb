package com.example.abhij.imdb;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    FragmentManager manager = getSupportFragmentManager();
    EditText editText_search;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            switch (item.getItemId()) {
                case R.id.navigation_Movies:




                    Fragment_Movies fragment = new Fragment_Movies();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.container_main,fragment).commit();

                    Toast.makeText(MainActivity.this,"movies",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_TV_shows:
                    Fragment_Shows fragment1 = new Fragment_Shows();
                    FragmentTransaction transaction1 = manager.beginTransaction();
                    transaction1.replace(R.id.container_main,fragment1).commit();

                    Toast.makeText(MainActivity.this,"movies",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_Favourate:
                    Fragment_favourite fragment2=new Fragment_favourite();

                    FragmentTransaction transaction2 = manager.beginTransaction();
                    transaction2.replace(R.id.container_main,fragment2).commit();

                    Toast.makeText(MainActivity.this,"fav",Toast.LENGTH_SHORT).show();
                    return true;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        editText_search= (EditText) findViewById(R.id.menuItem_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, null);

        Fragment_Movies fragment = new Fragment_Movies();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container_main,fragment).commit();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        editText_search.setVisibility(View.VISIBLE);

        return super.onOptionsItemSelected(item);
    }
}
