package com.codex.tourmate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codex.tourmate.fragments.EventFragment;
import com.codex.tourmate.fragments.WeatherFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView imageView;
    private TextView nameView, emailView;

    FirebaseUser user;
    public static final int INTERVAL = 2000;
    private long time;
    DatabaseReference rootreference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        imageView = headerView.findViewById(R.id.profile_view);
        nameView = headerView.findViewById(R.id.nameView);
        emailView = headerView.findViewById(R.id.email_view);
        user = FirebaseAuth.getInstance().getCurrentUser();
        emailView.setText(user.getEmail());

        rootreference = FirebaseDatabase.getInstance().getReference();
        rootreference.child("user").child(user.getUid()).child("info").child("userName")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String name = (String) dataSnapshot.getValue();
                            nameView.setText(name);
                            //imageView.setImageBitmap(decodeImage(users.get(0).getProfileImageData()));

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        rootreference.child("user").child(user.getUid()).child("info").child("profileImageData")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name = (String) dataSnapshot.getValue();
                        imageView.setImageBitmap(decodeImage(name));

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




        EventFragment eventFragment = new EventFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.event_container_layout, eventFragment);
        ft.commit();


    }

    public Bitmap decodeImage(String imageString) {
        byte[] bytes = Base64.decode(imageString, 0);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (!drawer.isDrawerOpen(GravityCompat.START)) {

            if (time + INTERVAL > System.currentTimeMillis()) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
            Toast.makeText(this, "Press again to exit app", Toast.LENGTH_SHORT).show();
            time = System.currentTimeMillis();
        }


    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_events) {
            // Handle the camera action
            loadEventFragment();


        } else if (id == R.id.nav_nearby) {

            Intent intent = new Intent(MainActivity.this,MapsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_weather) {

            loadWeatherFragment();

        } else if (id == R.id.nav_logout) {

            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.signOut();
            Intent intent = new Intent(MainActivity.this, LogInActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void loadEventFragment() {
        EventFragment eventFragment = new EventFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.event_container_layout, eventFragment);
        ft.commit();
    }

    public void loadWeatherFragment() {

        WeatherFragment weatherFragment = new WeatherFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.event_container_layout, weatherFragment);
        transaction.commit();


    }


}
