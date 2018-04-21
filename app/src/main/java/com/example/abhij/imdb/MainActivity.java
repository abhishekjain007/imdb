package com.example.abhij.imdb;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//
//    MenuInflater inflater = getMenuInflater();
//    inflater.
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager manager = getSupportFragmentManager();

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
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
